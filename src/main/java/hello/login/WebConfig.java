package hello.login;

import hello.login.web.filter.LogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;


@Configuration
public class WebConfig {

    //스프링 부트에서 필터걸때 사용하는 FilterRegistrationBean
    @Bean
    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean<Filter> filterFilterRegistartionBean = new FilterRegistrationBean<>();
        filterFilterRegistartionBean.setFilter(new LogFilter());    //적용할 필터설정
        filterFilterRegistartionBean.setOrder(1);
        filterFilterRegistartionBean.addUrlPatterns("/*");          //접근할 url

        return filterFilterRegistartionBean;
    }
}
