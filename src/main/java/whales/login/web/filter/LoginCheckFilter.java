package whales.login.web.filter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;
import whales.login.web.SessionConst;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {

    private static final String[] whitelist = {"/", "/members/add", "/login", "/logout", "/css/*"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        //위에것들은 쓰기 위해서 폼 갖춰논 것 이제 로직 작성
        try {
            log.info("인증 필터 체크 시작{}", requestURI);
            //인증 체크 메서드
            if (isLoginCheckPath(requestURI)){
                log.info("인증 체크 로직 실행 {}", requestURI);
                HttpSession session = httpRequest.getSession(false);
                if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
                    log.info("미인증 사용자 요청", requestURI);
                    //로그인으로 리다이랙트
                    httpResponse.sendRedirect("/login");
                    return ;
                }
            }
            chain.doFilter(request, response);
        }catch (Exception e){
            throw e;
        }finally {
            log.info("인증 체크 필터 종료{}", requestURI);
        }
    }

    /**
     * 화이트 리스트일 경우 인증 체크 안하는 메서드
     */
    private boolean isLoginCheckPath(String requestURI){
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }

}
