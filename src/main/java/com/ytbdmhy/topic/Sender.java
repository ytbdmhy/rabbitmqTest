package com.ytbdmhy.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.ytbdmhy.util.RabbitMqConnectionUtil;

/**
 * @Copyright: weface
 * @Description:
 * @author: miaohaoyun
 * @since:
 * @history: created in 13:54 2019-06-13 created by miaohaoyun
 */
public class Sender {

    private final static String EXCHANGE_NAME = "testtopexchange";

    public static void main(String[] args) throws Exception {
        Connection connection = RabbitMqConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        channel.basicPublish(EXCHANGE_NAME, "abc.adb.2", null, "topic模式消息发送者：".getBytes());
        channel.close();
        connection.close();
    }
}
