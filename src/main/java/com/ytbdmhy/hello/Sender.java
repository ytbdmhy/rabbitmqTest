package com.ytbdmhy.hello;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.ytbdmhy.util.RabbitMqConnectionUtil;

/**
 * @Copyright: weface
 * @Description:
 * @author: miaohaoyun
 * @since:
 * @history: created in 10:39 2019-06-13 created by miaohaoyun
 */
public class Sender {

    private final static String QUEUE = "testhello";

    public static void main(String[] args) throws Exception {
        // 获取连接
        Connection connection = RabbitMqConnectionUtil.getConnection();
        // 创建连接
        Channel channel = connection.createChannel();
        // 声明队列
        // 参数1：队列的名字
        // 参数2：是否持久化队列，我们的队列存在内存中，如果mq重启则丢失。如果为true，则保存在erlang的数据库中，重启，依旧保存
        // 参数3：是否排外，我们连接关闭后是否自动删除队列，是否私有当前队列，如果私有，其他队列不能访问
        // 参数4：是否自动删除
        // 参数5：我们传入的其他参数
        channel.queueDeclare(QUEUE, false, false, false, null);
        // 发生内容
        channel.basicPublish("", QUEUE, null, "要发送的消息".getBytes());
        // 关闭连接
        channel.close();
        connection.close();
    }
}
