package com.ahut.activemqdemo.mq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author LeiGe.King
 * @create 2020-12-26 19:17
 */
public class ConsumerTest {
    public static void main(String[] args) throws JMSException {
        //创建连接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_USER, ActiveMQConnectionFactory.DEFAULT_PASSWORD, "tcp://192.168.80.128:61616");
        //创建连接
        Connection connection = activeMQConnectionFactory.createConnection();
        //启动连接
        connection.start();
        //创建会话
        // 创建session 第一个参数表示是否支持事务，false时，第二个参数Session.AUTO_ACKNOWLEDGE，Session.CLIENT_ACKNOWLEDGE，DUPS_OK_ACKNOWLEDGE其中一个
        // 第一个参数设置为true时，第二个参数可以忽略 服务器设置为SESSION_TRANSACTED

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //创建队列
//        Queue queue = session.createQueue("ahut.queue");
        Queue queue = session.createQueue("map");
        //创建消费者
        MessageConsumer consumer = session.createConsumer(queue);
        //消费消息
       consumer.setMessageListener(new MessageListener() {
           @Override
           public void onMessage(Message message) {
               /*if (message instanceof  TextMessage) {
                   try {
                       String text = ((TextMessage) message).getText();
                       System.out.println(text);
                   } catch (JMSException e) {
                       e.printStackTrace();
                   }
               }*/
               if (message instanceof  MapMessage){
                   try {
                       String order = ((MapMessage) message).getString("order");
                       String result = ((MapMessage) message).getString("result");
                       System.out.println("order="+order+"*****"+"result="+result);
                   } catch (JMSException e) {
                       e.printStackTrace();
                   }

               }
           }
       });
        //关闭

    }
}

