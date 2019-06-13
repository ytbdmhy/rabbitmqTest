package com.ytbdmhy.route;

import com.rabbitmq.client.*;
import com.ytbdmhy.util.RabbitMqConnectionUtil;

import java.io.IOException;

/**
 * @Copyright: weface
 * @Description:
 * @author: miaohaoyun
 * @since:
 * @history: created in 13:46 2019-06-13 created by miaohaoyun
 */
public class Recver1 {

    private final static String EXCHANGE_NAME = "testroute";
    private final static String QUEUE = "testroute1queue";

    public static void main(String[] args) throws Exception {
        Connection connection = RabbitMqConnectionUtil.getConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE, false, false, false, null);
        // 绑定队列到交换机
        // 参数3：绑定到交换机指定的路由的名字
        channel.queueBind(QUEUE, EXCHANGE_NAME, "key1");
        // 如果需要绑定多个路由，再绑定一次即可
        channel.queueBind(QUEUE, EXCHANGE_NAME, "key2");
        channel.basicQos(1);
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                super.handleDelivery(consumerTag, envelope, properties, body);
                System.out.println("消费者1：" + new String(body));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        channel.basicConsume(QUEUE, false, defaultConsumer);
    }
}
