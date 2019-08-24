package com.memforward;

public class Student {

    public String name;
    public String gender;
    private Integer age;

    public Student(){
        this.name = "江锦平";
        this.gender = "男";
        this.age = 999;
    }
    public Student(String name, String gender, Integer age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public void live(){
        System.out.println("长生不老+1+1+1....");
    }

    public void live(String num){
        System.out.println("寿命延长："+num+"s");
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                '}';
    }
}
