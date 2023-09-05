package cn.locusc.camelinaction.c2;

import org.apache.camel.builder.RouteBuilder;

/**
 * @author Jay
 * use simple (simple表达式)
 * 2023/9/4
 */
public class SimpleOrderRouterTest extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("jms:incomingOrders")
                .choice()
                .when(simple("${header.CamelFileName} ends with 'xml'"))
                .to("jms:xmlOrders")
                .when(simple("${header.CamelFileName} ends with 'csv'"))
                .to("jms:csvOrders");
    }

}
