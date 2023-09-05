package cn.locusc.camelinaction.c2;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FtpToJMSRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // RAW标识使用原始值 含有保留字符的情况
        // from("ftp://rider.com/orders?username=rider&password=RAW(++%%w?rd)");
        from("ftp://rider.com/orders?username=rider&password=secret")
                .to("jms:incomingOrders");
    }

}
