package com.kfhstu.furn.service;

import com.kfhstu.furn.bean.Furn;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author KFH
 * @version 1.0
 * 测试FurnService
 */
public class TestFurnService {
    private ApplicationContext ioc;
    private FurnService furnService;


    @Before
    public void init(){
        //加载容器后，获取serviceimpl
        ioc=  new ClassPathXmlApplicationContext("applicationContext.xml");
        furnService = ioc.getBean(FurnService.class);


    }
    @Test
    public void save(){
        BigDecimal bigDecimal = new BigDecimal("666.66");
        Furn furn = new Furn(null, "三千瓦的小台灯", "全友家居", bigDecimal, 166, 100, "assets/image/product-image/10.jpg");
        furnService.save(furn);
        System.out.println("ok````~~~~~~");
    }
    @Test
    public void selectAll(){
        furnService.findAll();
    }
    @Test
    public void update(){
        Furn furn = new Furn();
        furn.setId(33);
        furn.setName("M78星云咖啡桌");
        furn.setMaker("玉伟家居");
        furnService.updateFurn(furn);

    }
    @Test
    public void delete(){
        Furn furn = new Furn();
        furn.setId(35);
        furnService.deleteFurn(furn.getId());
    }
    @Test//测试关键词查找
    public void findByCondition(){
        List<Furn> hua = furnService.findByCondition("华为");
        System.out.println(hua);

    }
}
