import com.memforward.Person;
import com.memforward.ReflectDemo;
import com.memforward.Student;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectTest {

    /**
     * 获取一个类的Class对象
     */
    @Test
    public void getClassTest() throws ClassNotFoundException {
        //方法一
        Class clazz1 = Class.forName("com.memforward.Student");
        System.out.println("clazz1:" + clazz1);
        //方法二
        Class clazz2 = Student.class;
        System.out.println("clazz2:" + clazz2);
        //方法三
        Class clazz3 = new Student().getClass();
        System.out.println("clazz3:" + clazz3);
        //判断是否指向同一个对象
        System.out.println(clazz1 == clazz2);
        System.out.println(clazz2 == clazz3);
        System.out.println(clazz1 == clazz3);
    }
    
    @Test
    public void testField() throws Exception {
        Class clazz = Student.class;
        System.out.println("-----------getFields------------");
        Field[] fields01 = clazz.getFields();
        for (Field field: fields01) System.out.println(field);
        System.out.println("-----------getDeclaredFileds----");
        Field[] fields02 = clazz.getDeclaredFields();
        for (Field field:fields02) System.out.println(field);
        System.out.println("--------------------------------");
        Field nameField = clazz.getField("name");
        Student stu = new Student();
        System.out.println("原来的stu：" + stu);
        //通过Filed获得实例的值
        String sname = (String)nameField.get(stu);
        //通过Field设置实例的值
        nameField.set(stu, "胡近民");
        //如果要获取私有的值
        Field ageField = clazz.getDeclaredField("age");
        //操作私有的值必须要开启权限,暴力反射
        ageField.setAccessible(true);
        ageField.set(stu,1000);
        System.out.println("修改后的stu：" + stu);
    }

    @Test
    public void testConstructor() throws Exception{
        Class clazz = Student.class;
        //获取Constructor对象
        //1.1返回了无参构造
        Constructor constructor01 = clazz.getConstructor();
        Student stu01 = (Student) constructor01.newInstance();
        System.out.println("获得了无参构造器：" + stu01);
        //1.2 返回有参构造
        Constructor constructor02 = clazz.getConstructor(String.class,String.class,Integer.class);
        Student stu02 = (Student) constructor02.newInstance("胡近民", "男", 1000);
        System.out.println("获得了有参构造器:" + stu02);
        //无参构造器生成实例可以直接由Class对象获得
        Student stu03 = (Student) clazz.newInstance();
        System.out.println("Class对象直接生成的实例：" + stu03);
    }

    @Test
    public void testMethod() throws Exception{
        Class clazz = Student.class;
        //获得方法
        //1.1获得无参方法
        Method md01 = clazz.getMethod("live");
        //调用方法（需要有实例才可以执行该方法）
        md01.invoke(new Student());
        //1.2获得有参的方法
        Method md02 = clazz.getMethod("live", String.class);
        md02.invoke(new Student(),"1");
    }

    @Test
    public void testReflectDemo(){
        ReflectDemo.loadProperties("beansConfig.xml");
        ReflectDemo rd = new ReflectDemo();
        Student stu = (Student) rd.getBean("student");
        stu.live();
        Person person = (Person) rd.getBean("person");
        person.doSomething();
        person.saySomething();
    }
}
