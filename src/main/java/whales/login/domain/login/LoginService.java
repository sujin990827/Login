package whales.login.domain.login;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import whales.login.domain.member.Member;
import whales.login.domain.member.MemberRepository;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository memberRepository;

    /**
     * return이 널이면 로그인 실패
     */
    public Member login(String loginId, String password){
        Member member = memberRepository.findByName(loginId);
        if  (member.getPassword().equals(password)){
            return member;
        }else{
            return null;
        }
    }
}
