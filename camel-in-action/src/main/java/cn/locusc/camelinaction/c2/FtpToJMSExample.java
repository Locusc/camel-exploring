package cn.locusc.camelinaction.c2;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spi.Registry;

import javax.jms.ConnectionFactory;

public class FtpToJMSExample {

    public static void main(String[] args) throws Exception {
        DefaultCamelContext context = new DefaultCamelContext();

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm/localhost");

        context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

        Registry registry = context.getRegistry();
        registry.bind("myFilter", new OrderFileFilter<>());

        // 使用#引用注册表对象实例
        context.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("ftp://rider.com/orders?username=rider&password=secret&filter=#myFilter")
                        .process(exchange -> {
                            System.out.println("We just downloaded: " + exchange.getIn().getHeader("CamelFileName"));
                        })
                        .to("jms:incomingOrders");
            }
        });

        // start the route and let it do its work
        context.start();
        Thread.sleep(10000);

        // stop the CamelContext
        context.stop();
    }

}
