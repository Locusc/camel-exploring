package cn.locusc.camelinaction.c2.spring;

public class EnglishGreeter implements Greeter {

    public String sayHello() {
        return "Hello " + System.getProperty("user.name");
    }

}
