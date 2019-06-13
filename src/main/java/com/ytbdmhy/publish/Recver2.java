package com.ytbdmhy.publish;

import com.rabbitmq.client.*;
import com.ytbdmhy.util.RabbitMqConnectionUtil;

import java.io.IOException;

/**
 * @Copyright: weface
 * @Description:
 * @author: miaohaoyun
 * @since:
 * @history: created in 13:40 2019-06-13 created by miaohaoyun
 */
public class Recver2 {

    // 定义交换机
    private final static String EXCHANGE_NAME = "testexchange";
    private final static String QUEUE = "testpubqueue2";

    public static void main(String[] args) throws Exception {
        Connection connection = RabbitMqConnectionUtil.getConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE, false, false, false, null);
        // 绑定队列到交换机
        channel.queueBind(QUEUE, EXCHANGE_NAME, "");
        channel.basicQos(1);
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                super.handleDelivery(consumerTag, envelope, properties, body);
                System.out.println("消费者2：" + new String(body));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        channel.basicConsume(QUEUE, false, defaultConsumer);
    }
}
