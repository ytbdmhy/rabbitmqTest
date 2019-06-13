package com.ytbdmhy.hello;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.ytbdmhy.util.RabbitMqConnectionUtil;

/**
 * @Copyright: weface
 * @Description:
 * @author: miaohaoyun
 * @since:
 * @history: created in 10:50 2019-06-13 created by miaohaoyun
 */
public class Recver {

    private final static String QUEUE = "testhello";

    public static void main(String[] args) throws Exception {
        Connection connection = RabbitMqConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE, false, false, false, null);
        QueueingConsumer queueingConsumer = new QueueingConsumer(channel);
        // 接受消息，参数2表示自动确认
        channel.basicConsume(QUEUE, true, queueingConsumer);
        while (true) {
            // 获取消息
            QueueingConsumer.Delivery delivery = queueingConsumer.nextDelivery(); // 如果没有消息就等待，有消息就获取消息，并销毁，是一次性的
            String message = new String(delivery.getBody());
            System.out.println(message);
        }
    }
}
