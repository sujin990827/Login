package whales.login.web.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import whales.login.domain.member.Member;
import whales.login.domain.member.MemberRepository;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberRepository memberRepository;

    @GetMapping("/add")
    private String addForm(@ModelAttribute("member") Member member) {
        return "/members/addMemberForm";
    }
    
    @PostMapping("/add")
    private String save(@Valid @ModelAttribute Member member, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "/members/addForm";
        }
        memberRepository.save(member);
        return  "redirect:/";
    }

}
