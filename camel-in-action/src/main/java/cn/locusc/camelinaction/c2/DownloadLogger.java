package cn.locusc.camelinaction.c2;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class DownloadLogger implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        System.out.println("We just downloaded: "
                + exchange.getIn().getHeader("CamelFileName"));
    }

}
