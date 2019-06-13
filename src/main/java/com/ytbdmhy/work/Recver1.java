package com.ytbdmhy.work;

import com.rabbitmq.client.*;
import com.ytbdmhy.util.RabbitMqConnectionUtil;

import java.io.IOException;

/**
 * @Copyright: weface
 * @Description:
 * @author: miaohaoyun
 * @since:
 * @history: created in 11:47 2019-06-13 created by miaohaoyun
 */
public class Recver1 {

    private final static String QUEUE = "testhellowork";

    public static void main(String[] args) throws Exception {
        Connection connection = RabbitMqConnectionUtil.getConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE, false, false, false, null);
        channel.basicQos(1); // 告诉服务器，当前消息没有确认之前，不要发送新消息，合理自动分配资源
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                // 收到消息时调用
                System.out.println("消费者1收到的消息：" + new String(body));
//                super.handleDelivery(consumerTag, envelope, properties, body);
                // 确认消息
                // 参数2：false为确认收到消息，true为拒绝收到消息
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        // 注册消费者
        // 参数2：手动确认，我们收到消息后，需要手动确认，告诉服务器，我们收到消息了
        channel.basicConsume(QUEUE, false, defaultConsumer);
    }
}
