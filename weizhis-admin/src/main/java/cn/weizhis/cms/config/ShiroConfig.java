package cn.weizhis.cms.config;

import cn.weizhis.cms.common.ShiroFormAuthenticationFilter;
import cn.weizhis.cms.common.WeizhisSessionManager;
import cn.weizhis.cms.redis.RedisShiroSessionDAO;
import cn.weizhis.cms.shiro.UserRealm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Auther: minliang
 * @Date: 2018/10/9 10:05
 * @Description:
 */
@Configuration
public class ShiroConfig {

    @Bean("sessionManager")
    public SessionManager sessionManager(@Value("${spring.redis.open}") boolean redisOpen,
                                         @Value("${shiro.redis}") boolean shiroRedis,
                                         RedisShiroSessionDAO redisShiroSessionDAO){
        DefaultWebSessionManager sessionManager = new WeizhisSessionManager();
//        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        //设置session过期时间为1小时(单位：毫秒)，默认为30分钟
        sessionManager.setGlobalSessionTimeout(60 * 60 * 1000);

        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setSessionIdCookieEnabled(true);

        //如果开启redis缓存则shiro session存到redis里
        if(redisOpen && shiroRedis){
            sessionManager.setSessionDAO(redisShiroSessionDAO);
        }
        return sessionManager;
    }

//    @Bean
//    public UserRealm myShiroRealm() {
//        UserRealm myShiroRealm = new UserRealm();
////        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
//        return myShiroRealm;
//    }

    @Bean("securityManager")
    public SecurityManager securityManager(SessionManager sessionManager, UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        securityManager.setSessionManager(sessionManager);

        return securityManager;
    }
//
//    @Bean("securityManager")
//    public SecurityManager securityManager(OAuth2Realm oAuth2Realm, SessionManager sessionManager) {
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setRealm(oAuth2Realm);
//        securityManager.setSessionManager(sessionManager);
//
//        return securityManager;
//    }


    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        shiroFilter.setLoginUrl("/unauthorized");
        shiroFilter.setUnauthorizedUrl("/unauthorized");
        //oauth过滤
//        Map<String, Filter> filters = new HashMap<>();
//        filters.put("oauth2", new OAuth2Filter());
//        shiroFilter.setFilters(filters);

        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/swagger/**", "anon");
        filterMap.put("/v2/api-docs", "anon");
        filterMap.put("/swagger-ui.html", "anon");
        filterMap.put("/webjars/**", "anon");
        filterMap.put("/swagger-resources/**", "anon");

        filterMap.put("/statics/**", "anon");
        filterMap.put("/login.html", "anon");
        filterMap.put("/login", "anon");
        filterMap.put("/favicon.ico", "anon");
        filterMap.put("/captcha.jpg", "anon");
//        filterMap.put("/**", "oauth2");
        filterMap.put("/**", "authc");
//        shiroFilter.setFilterChainDefinitionMap(filterMap);
        LinkedHashMap<String, Filter> filtsMap=new LinkedHashMap<>();
        filtsMap.put("authc",new ShiroFormAuthenticationFilter());
        shiroFilter.setFilters(filtsMap);
        shiroFilter.setFilterChainDefinitionMap(filterMap);

        return shiroFilter;
    }

    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

}
