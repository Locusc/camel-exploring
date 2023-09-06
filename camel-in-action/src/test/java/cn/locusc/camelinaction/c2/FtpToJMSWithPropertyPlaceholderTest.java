package cn.locusc.camelinaction.c2;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.spi.PropertiesComponent;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;

import javax.jms.ConnectionFactory;

/**
 * @author Jay
 * 从配置文件获取动态路由参数示例
 * 2023/9/4
 */
public class FtpToJMSWithPropertyPlaceholderTest extends CamelTestSupport {

    @Override
    protected CamelContext createCamelContext() throws Exception {
        // create CamelContext
        CamelContext camelContext = super.createCamelContext();

        // connect to embedded ActiveMQ JMS broker
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
        camelContext.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

        // setup the properties component to use the test file

        // org.apache.camel.component.properties.PropertiesComponent
        // class PropertiesComponent extends UriEndpointComponent 该组件已不存在
        // PropertiesComponent prop = camelContext.getComponent("properties", PropertiesComponent.class);

        PropertiesComponent prop = camelContext.getPropertiesComponent();

        prop.setLocation("classpath:rider-test.properties");
        return camelContext;
    }

    @Test
    public void testPlacingOrders() throws Exception {
        getMockEndpoint("mock:incomingOrders").expectedMessageCount(1);
        assertMockEndpointsSatisfied();
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                // load file orders from src/data into the JMS queue
                from("file:src/data?noop=true")
                        .to("jms:{{myDest}}");

                // test that our route is working
                from("jms:incomingOrders")
                        .to("mock:incomingOrders");
            }
        };
    }

}
