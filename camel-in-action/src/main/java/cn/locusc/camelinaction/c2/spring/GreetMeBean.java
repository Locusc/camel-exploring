package cn.locusc.camelinaction.c2.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GreetMeBean {

    private Greeter greeter;

    public void setGreeter(Greeter greeter) {
        this.greeter = greeter;
    }

    public void execute() {
        System.out.println(greeter.sayHello());
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        GreetMeBean greetMeBean = (GreetMeBean) applicationContext.getBean("greetMeBean");
        greetMeBean.execute();
    }

}
