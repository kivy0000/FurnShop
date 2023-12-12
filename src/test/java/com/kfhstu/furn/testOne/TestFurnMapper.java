package com.kfhstu.furn.testOne;

import com.kfhstu.furn.bean.Furn;
import com.kfhstu.furn.dao.FurnMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Optional;

/**
 * @author KFH
 * @version 1.0
 * 测试逆向工程的方法
 * Your password does not satisfy the current policy requirements
 */
public class TestFurnMapper {

    @Test
    public void testMapper() {
        //获取ioc容器
        ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
        //拿到mapper,这里直接通过容器拿到对象，因为配置了扫描器
        FurnMapper mapperBean = ioc.getBean(FurnMapper.class);
        //提交1条数据
        BigDecimal bigDecimal = new BigDecimal("666.66");
        Furn furn = new Furn(null, "蔡徐坤的专用篮球", "全友家居", bigDecimal, 66, 100, "'assets/image/product-image/9.jpg'");
        int i = mapperBean.insertSelective(furn);
        /*lambda表达式
        函数式接口：只有一个抽象方法的接口
        public void ifPresent(Consumer<? super T> consumer)
        总的来说：这段表达式会被编译成一段函数，作为consumer函数接口的匿名内部类，箭头左边是传入的参数，可以忽略，箭头右边是匿名内部类所实现的方法
        如果Optional.ofNullable(sqlSession)不为空，则会执行ifPresent(p -> sqlSession.close());，否则不会
         */
        //将传入的对象作为value值封装为Optional对象
        //如果value值不为空，则调用语句块中的方法,
        //Optional.ofNullable(sqlSession).ifPresent(p -> sqlSession.close());
        System.out.println(furn.getId());


    }

}
