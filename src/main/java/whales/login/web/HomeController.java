package whales.login.web;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import whales.login.domain.member.Member;
import whales.login.domain.member.MemberRepository;
import whales.login.web.session.SessionManager;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

    @GetMapping("/")
    public String homeLogin (@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)Member loginMember,
                             Model model){
        //로그인 성공 사용자
        if (loginMember == null){
            return "home";
        }
        model.addAttribute("member", loginMember);
        return "loginHome";
    }
}
