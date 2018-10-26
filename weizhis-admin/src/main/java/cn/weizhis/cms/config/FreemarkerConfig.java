//package cn.weizhis.cms.config;
//
//import cn.weizhis.cms.shiro.ShiroTag;
//import freemarker.template.TemplateException;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;
//import org.springframework.web.servlet.ViewResolver;
//import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
//import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Properties;
//
///**
// * @Auther: minliang
// * @Date: 2018/10/10 11:38
// * @Description:
// */
//@Configuration
//public class FreemarkerConfig  {
////    @Bean
////    public ViewResolver viewResolver() {
////        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
////        resolver.setCache(true);
////        resolver.setPrefix("");
////        resolver.setSuffix(".ftl");
////        resolver.setContentType("text/html; charset=UTF-8");
////        return resolver;
////    }
//
//    @Bean
//    public FreeMarkerConfigurer freeMarkerConfigurer(ShiroTag shiroTag) throws IOException, TemplateException {
////        FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
////        configurer.setTemplateLoaderPath("classpath:/templates");
//        Map<String, Object> variables = new HashMap<>(1);
//        variables.put("shiro", shiroTag);
////        configurer.setFreemarkerVariables(variables);
//
////        Properties settings = new Properties();
////        settings.setProperty("default_encoding", "utf-8");
////        settings.setProperty("number_format", "0.##");
////        configurer.setFreemarkerSettings(settings);
//
//        FreeMarkerConfigurationFactory factory = new FreeMarkerConfigurationFactory();
//        factory.setTemplateLoaderPath("classpath:/templates/");
//        factory.setDefaultEncoding("UTF-8");
//        factory.setPreferFileSystemAccess(false);
//        factory.setFreemarkerVariables(variables);
//        FreeMarkerConfigurer result = new FreeMarkerConfigurer();
//        freemarker.template.Configuration configuration = factory.createConfiguration();
//        configuration.setClassicCompatible(true);
//        result.setConfiguration(configuration);
//        Properties settings = new Properties();
//        settings.put("template_update_delay", "0");
//        settings.put("default_encoding", "UTF-8");
//        settings.put("number_format", "0.######");
//        settings.put("classic_compatible", true);
//        settings.put("template_exception_handler", "ignore");
//        result.setFreemarkerSettings(settings);
//        return result;
//    }
//
//}
