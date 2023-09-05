package cn.locusc.camelinaction.c2.spring;

public class DanishGreeter implements Greeter {

    public String sayHello() {
        return "Davs " + System.getProperty("user.name");
    }

}
