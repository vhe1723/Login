package hello.login;

import hello.login.web.filter.LogFilter;
import hello.login.web.filter.LoginCheckFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;


@Configuration
public class WebConfig {

    //스프링 부트에서 필터걸때 사용하는 FilterRegistrationBean
    @Bean
    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean<Filter> filterRegistartionBean = new FilterRegistrationBean<>();
        filterRegistartionBean.setFilter(new LogFilter());    //적용할 필터설정
        filterRegistartionBean.setOrder(1);                   //Filter 호출 순서
        filterRegistartionBean.addUrlPatterns("/*");          //접근할 url

        return filterRegistartionBean;
    }
    @Bean
    public FilterRegistrationBean loginCheckFilter() {
        FilterRegistrationBean<Filter> filterRegistartionBean = new FilterRegistrationBean<>();
        filterRegistartionBean.setFilter(new LoginCheckFilter());    //적용할 필터설정
        filterRegistartionBean.setOrder(2);                   //Filter 호출 순서
        filterRegistartionBean.addUrlPatterns("/*");          //접근할 url

        return filterRegistartionBean;
    }

}
