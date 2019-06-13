package com.ytbdmhy.route;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.ytbdmhy.util.RabbitMqConnectionUtil;

/**
 * @Copyright: weface
 * @Description:
 * @author: miaohaoyun
 * @since:
 * @history: created in 13:44 2019-06-13 created by miaohaoyun
 */
public class Sender {

    private final static String EXCHANGE_NAME = "testroute";

    public static void main(String[] args) throws Exception {
        Connection connection = RabbitMqConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        channel.basicPublish(EXCHANGE_NAME, "key3", null, "路由模式的消息".getBytes());
        channel.close();
        connection.close();
    }
}
