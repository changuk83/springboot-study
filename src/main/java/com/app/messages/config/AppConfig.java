package com.app.messages.config;

import com.app.messages.filter.AuditingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@ComponentScan("com.app.messages")
@EnableTransactionManagement
public class AppConfig {

    private DataSource dataSource;

    public AppConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Hibernate 에서 Session 은 Entity를 저장하고 불러오기 위한 주요 interface
     * SessionFactory instance 에서 Session 을 생성할 수 있는데
     * SessionFactory 의 instance 를 생성하려면 Spring 의 LocalSessionFactoryBean 을 사용하면 된다.
     * @return
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setPackagesToScan("com.app.messages");
        return sessionFactoryBean;
    }

    /**
     * Servlet Filter 같은걸 구현한 예제
     * @return
     */
    @Bean
    public FilterRegistrationBean<AuditingFilter> auditingFilterFilterRegistrationBean() {
        FilterRegistrationBean<AuditingFilter> registrationBean = new FilterRegistrationBean<>();
        AuditingFilter filter = new AuditingFilter();
        registrationBean.setFilter(filter);
        registrationBean.setOrder(Integer.MAX_VALUE);
        registrationBean.setUrlPatterns(Arrays.asList("/test/*"));
        return registrationBean;
    }


}
