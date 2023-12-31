package cn.locusc.camelinaction.c2;

import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringOrderRouterWithFilterTest extends CamelSpringTestSupport {

    @Override
    protected AbstractXmlApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext("SpringOrderRouterWithFilterTest.xml");
    }

    @Test
    public void testPlacingOrders() throws Exception {
        getMockEndpoint("mock:xml").expectedMessageCount(1);
        assertMockEndpointsSatisfied();
    }

}
