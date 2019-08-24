package com.memforward;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class ReflectDemo {
    public static Document document = null;
    public static void loadProperties(String config) {
        SAXReader reader = new SAXReader();
        try {
            Document document = reader.read(new File("src/main/resources/beansConfig.xml"));
            ReflectDemo.document = document;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public Object getBean(String id){
        Element bean = null;
        String className = null;
        Element root = document.getRootElement();
        List<Element> beans = root.elements("bean");
        //找到配置文件里的节点
        for(Element e : beans){
            if(e.attribute("id").getValue().equals(id)){
                bean = e;
                break;
            }
        }
        if(bean == null) throw new RuntimeException("没有你指定的类：" + id);
        className = bean.attribute("class").getValue();
        try {
            Class clazz = Class.forName(className);
            Object obj = clazz.newInstance();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
