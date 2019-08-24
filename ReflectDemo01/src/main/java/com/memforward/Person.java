package com.memforward;

public class Person {
    private String name;
    private Integer age;
    public Person(){
        this.name = "习";
        this.age = 999;
    }
    public void doSomething(){
        System.out.println(name + "：修身治国齐家平天下....");
    }

    public void saySomething(){
        System.out.println(name + "：TW必将光复，HK属于CN！");
    }
}
