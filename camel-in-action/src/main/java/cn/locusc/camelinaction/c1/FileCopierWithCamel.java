package cn.locusc.camelinaction.c1;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class FileCopierWithCamel {

    public static void main(String[] args) throws Exception {
        DefaultCamelContext defaultCamelContext = new DefaultCamelContext();
        defaultCamelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() {
                from("file:data/inbox?noop=true")
                        .to("file:data/outbox"); // 将文件从inbox路由到outbox
            }
        });

        defaultCamelContext.start();
        Thread.sleep(10000);
        defaultCamelContext.stop();
    }

}
