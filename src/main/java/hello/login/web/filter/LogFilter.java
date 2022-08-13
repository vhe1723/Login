package hello.login.web.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log filter init");
    }

    //요청이 올때마다 doFilter가 먼저 호출된다.
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("log filter dofilter");

        HttpServletRequest httpRequest = (HttpServletRequest) request;  //다운캐스팅
        //ServletRequest는 HttpServletRequest의 부모로서, 기능이 별로없음. HTTP를 사용하지 않는 환경에서 쓰기위해 ServletRequest로 요청을 받게 설계되어있다.

        String requestURI = httpRequest.getRequestURI();

        String uuid = UUID.randomUUID().toString();

        try {
            log.info("REQUEST [{}][{}]", uuid, requestURI);
            chain.doFilter(request, response);   //다음필터가 있으면 호출, 없다면 서블릿을 호출 (LoginFilter 호출)
        } catch (Exception e) {
            throw e;
        }finally {
            log.info("RESPONSE [{}][{}]", uuid, requestURI);
        }
    }

    @Override
    public void destroy() {
        log.info("log filter destroy");
    }
}

