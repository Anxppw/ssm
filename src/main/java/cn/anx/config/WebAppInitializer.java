package cn.anx.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import org.apache.log4j.Logger;

/*
* replace web.xml
* */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    private final static Logger LOG = Logger.getLogger(WebAppInitializer.class);
    @Override
    protected Class<?>[] getRootConfigClasses() {
        LOG.info("----root config initialize----");
        return new Class[]{Rootconfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        LOG.info("----web config initialize----");
        return new Class[]{Webconfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        LOG.info("----rootpath of mapper initialize----");
        return new String[]{"/"};
    }
}
