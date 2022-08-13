package hello.login.web.filter;

import hello.login.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {

    //세션검증을 하지않을(로직을 실행시키지 않을) url을 whiteList로 풀어준다 ex)첫화면, 로그인화면, 회원가입화면 ...
    private static final String[] whitelist = {"/", "/members/add", "/login", "/logout", "/css/*"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;  //다운캐스팅
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            log.info("인증 체크 필터 시작{}", requestURI);
            if (isLoginCheckPath(requestURI)) {
                log.info("인증 체크 로직 실행 {}", requestURI);
                HttpSession session = httpRequest.getSession(false);
                if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
                    log.info("미인증 사용자 요청 {}", requestURI);
                    //로그인으로 redirect   requestURL은 LoginController에서 가지고있다가 로그인성공시 redirect
                    httpResponse.sendRedirect("login?redirectURL=" + requestURI);
                    return;
                }
            }
            chain.doFilter(request, response);   //다음필터가 있으면 호출, 없다면 서블릿을 호출
        } catch (Exception e) {
            throw e;    //예외 로깅 가능 하지만, 톰캣가지 예외를 보내주어야 함
        }finally{
            log.info("인증 체크 필터 종료 {}", requestURI);
        }
    }

    /**
     * 화이트 리스트는 인증 체크 X
     */
    //LoginCheck 여부를 판단하는 로직
    private boolean isLoginCheckPath(String requestURI) {
        //PatternMatchUtils => 스프링이 제공하는 라이브러리 두 객체를 Match  true/false로 반환
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }

}
