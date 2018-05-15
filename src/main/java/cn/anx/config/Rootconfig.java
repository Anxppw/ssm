package cn.anx.config;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.*;

/*
* ApplicationContext
* register proxyCreator
* create proxy class
* */
@Configuration
@ComponentScan(basePackages = {"cn.anx.service","cn.anx.mapper","cn.anx.config"},
        excludeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Configuration.class) })
@Import(Dao.class)
@EnableAspectJAutoProxy
public class Rootconfig {

    @Bean
    public BeanNameAutoProxyCreator proxyCreator(){
        BeanNameAutoProxyCreator proxyCreator = new BeanNameAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        proxyCreator.setBeanNames("*ServiceImpl");
        return proxyCreator;
    }

    @Bean
    public AOPConfig aopConfig(){
        return new AOPConfig();
    }

}
