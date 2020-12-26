package com.ahut.activemqdemo.mq;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQMapMessage;

import javax.jms.*;

/**
 * @author LeiGe.King
 * @create 2020-12-26 14:51
 */
public class ProducerTest {
    public static void main(String[] args) throws JMSException {
        //创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://192.168.80.128:61616");
        //创建连接
        Connection connection = activeMQConnectionFactory.createConnection();
        //启动连接
        connection .start();
        //创建会话
        // 创建session 第一个参数表示是否支持事务，false时，第二个参数Session.AUTO_ACKNOWLEDGE，Session.CLIENT_ACKNOWLEDGE，DUPS_OK_ACKNOWLEDGE其中一个
        // 第一个参数设置为true时，第二个参数可以忽略 服务器设置为SESSION_TRANSACTED

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建队列
        //Queue queue = session.createQueue("ahut.queue");
        Queue queue = session.createQueue("map");
        //创建生产者
        MessageProducer producer = session.createProducer(queue);
        //生产消息
//        ActiveMQTextMessage activeMQTextMessage = new ActiveMQTextMessage();
//        activeMQTextMessage.setText("今天的婷婷更加可爱了嘛(*╹▽╹*)");
        ActiveMQMapMessage activeMQMapMessage = new ActiveMQMapMessage();
        activeMQMapMessage.setString("order","10086");
        activeMQMapMessage.setString("result","ok");
        //发送消息
//        producer.send(activeMQTextMessage);
        producer.send(activeMQMapMessage);
        //关闭
        producer.close();
        session.close();
        connection.close();
    }
}
