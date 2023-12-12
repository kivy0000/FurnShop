package com.kfhstu.furn.testOne;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 测试ssm是否整合成功
 */
public class TestFirst {

    @Test
    public void say() {
        //获取ioc容器
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        //测试能否拿到数据源
        Object druidDataSource = ioc.getBean("druidDataSource");
        Object sqlSessionFactory = ioc.getBean("sqlSessionFactory");
        System.out.println(" druidDataSource= " + druidDataSource);
        System.out.println(" sqlSessionFactory= " + sqlSessionFactory);
    }


    @Test
    //测试Optional类
    public void say2() {
        Object o = null;
        System.out.println(Optional.ofNullable(o).orElse("ikun Test ~~"));
    }

    @Test
    public void lambda1(){
        //这里会自动推断,函数式接口：只有一个抽象方法的接口
        new Thread(() -> System.out.println("i am running")).start();
    }


    @Test//测试stream流
    public void stream(){
        Map<String, Object> map = new HashMap<>();
        map.put("name1","name1");
        map.put("name2","name2");
        Collection<Object> values = map.values();
        values.stream().forEach(System.out::println);

    }
    @Test//使用匿名内部类类似于lambda
    public void say3(){
        //使用匿名内部类
        new AAA(){
            @Override
            public void say(String name, Integer age) {
                name = name + "%";
                age = age + 1;
                System.out.println("name=" + name);
                System.out.println("age=" + age);
            }
        }.say("ikun",12);
        //使用lambda表达式,需要为函数式接口
       AAA aaa =  (name,age) -> {
            System.out.println(name + age + "%");
        };
       aaa.say("wo",1);
       //线程
        new Thread(() -> System.out.println("开启了线程")).start();
    }
}
interface AAA{
    void say(String name ,Integer age);
//    void say2();
}
