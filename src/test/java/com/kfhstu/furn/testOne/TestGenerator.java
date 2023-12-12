package com.kfhstu.furn.testOne;

import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author KFH
 * @version 1.0
 * 运行一次逆向工程
 */
public class TestGenerator {

    @Test
    public void testGenerator() throws Exception{

        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        //逆向工程配置传入,直接放在项目下
        File configFile = new File("D:\\IDEA.JAVA.Projects\\chapter35_SSM\\mbg.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);

    }
}
