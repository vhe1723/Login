package hello.login.web.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Slf4j
@RestController
public class SessionInfoController {

    @GetMapping("/session-info")
    public String sessionInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return "세션이 없습니다.";
        }

        session.getAttributeNames().asIterator()
                .forEachRemaining(name -> log.info("session name = {}, value = {}", name, session.getAttribute(name)));

        //session Info
        log.info("sessionId={}", session.getId());                                  //Session Id
        log.info("getMaxInactiveInterval()={}", session.getMaxInactiveInterval());  //Session 유효 시간 (초)
        log.info("getCreationTime={}", new Date(session.getCreationTime()));        //Session 생성 시간
        log.info("lastAccessedTime={}", new Date(session.getLastAccessedTime()));   //마지막으로 Session에 접근한 시간
        log.info("isNew={}",session.isNew());                                       //신규 Session인지 판단   true/false

        return "세션 출력";
    }
}
