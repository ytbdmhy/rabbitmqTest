package com.ytbdmhy.publish;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.ytbdmhy.util.RabbitMqConnectionUtil;

/**
 * @Copyright: weface
 * @Description:
 * @author: miaohaoyun
 * @since:
 * @history: created in 11:56 2019-06-13 created by miaohaoyun
 */
public class Sender {

    private final static String EXCHANGE_NAME = "testexchange";

    public static void main(String[] args) throws Exception {
        Connection connection = RabbitMqConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明交换机
        // 定义一个交换机，类型为fanout，也就是发布订阅者模式
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        // 发布订阅模式，因为消息是先发布到交换机中，而交换机是没有保存功能的，所以如果没有消费者，消息会丢失
        channel.basicPublish(EXCHANGE_NAME, "", null, "发布订阅模式的消息".getBytes());
        channel.close();
        connection.close();
    }
}
