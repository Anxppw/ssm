package cn.anx.test;

import cn.anx.config.Dao;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class IOCTest {


    @Test
    public void fun3() throws NoSuchMethodException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Dao.class);
        DriverManagerDataSource dataSource = (DriverManagerDataSource) applicationContext.getBean("dataSource");
        System.out.println(dataSource);
    }

}
