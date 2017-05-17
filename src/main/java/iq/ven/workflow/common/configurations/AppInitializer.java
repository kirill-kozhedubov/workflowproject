package iq.ven.workflow.common.configurations;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ServletContext.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{AppContext.class};
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
