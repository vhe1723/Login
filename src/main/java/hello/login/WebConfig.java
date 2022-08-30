package hello.login;

import hello.login.web.filter.LogFilter;
import hello.login.web.filter.LoginCheckFilter;
import hello.login.web.interceptor.LogInterceptor;
import hello.login.web.interceptor.LoginCheckInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;


@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * InterCeptor
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")                                 //add path
                .excludePathPatterns("/css/**", "/*.ico", "/error", "/login");    //except path

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/members/add", "/login", "/logout", "/css/**", "/*ico", "/error");
    }

    /**
     * Ex Filter
     * @return
     */
//    @Bean
    public FilterRegistrationBean logFilter() {
        FilterRegistrationBean<Filter> filterRegistartionBean = new FilterRegistrationBean<>();
        filterRegistartionBean.setFilter(new LogFilter());    //적용할 필터설정
        filterRegistartionBean.setOrder(1);                   //Filter 호출 순서
        filterRegistartionBean.addUrlPatterns("/*");          //접근할 url

        return filterRegistartionBean;
    }
//    @Bean
    public FilterRegistrationBean loginCheckFilter() {
        FilterRegistrationBean<Filter> filterRegistartionBean = new FilterRegistrationBean<>();
        filterRegistartionBean.setFilter(new LoginCheckFilter());    //적용할 필터설정
        filterRegistartionBean.setOrder(2);                   //Filter 호출 순서
        filterRegistartionBean.addUrlPatterns("/*");          //접근할 url

        return filterRegistartionBean;
    }

}
