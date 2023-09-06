package cn.locusc.camelinaction.c3;

import org.apache.camel.builder.RouteBuilder;

/**
 * @author Jay
 * Enrich为生产者模式-适合单次获取
 * but using enrich will write the message content as a file; using pollEnrich will read the file as the source
 * from page 147
 * 2023/9/5
 */
public class EnrichTest extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("jms:queue:quotes")
                .enrich("netty4:tcp://riders.com:9876?textline=true&amp;sync=true",
                        (oldExchange, newExchange) -> oldExchange)
                .to("log:quotes");
    }

}
