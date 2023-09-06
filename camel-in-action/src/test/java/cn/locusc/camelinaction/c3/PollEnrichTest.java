package cn.locusc.camelinaction.c3;

import org.apache.camel.builder.RouteBuilder;

/***
 * @author Jay
 * pollEnrich和Enrich可以进行消息聚合
 * pollEnrich 采用轮询的方式获取消息, 包含三种轮询模式
 * 推荐使用 pollEnrich(timeout = 0) 非阻塞模式
 * 2023/9/5
 */
public class PollEnrichTest extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("quartz2://report?cron=0+0+6+*+*+?")
                .to("http://riders.com/orders/cmd=received&date=yesterday")
                // .process(new OrderToCsvProcessor())
                .pollEnrich("ftp://riders.com/orders/?username=rider&password=secret", 0,
                        (oldExchange, newExchange) -> {
                            if (newExchange == null) {
                                return oldExchange;
                            }
                            String http = oldExchange.getIn().getBody(String.class);
                            String ftp = newExchange.getIn().getBody(String.class);
                            String body = http + "\n" + ftp;
                            oldExchange.getIn().setBody(body);
                            return oldExchange;
                        })
                .to("file://riders/orders");
    }

}
