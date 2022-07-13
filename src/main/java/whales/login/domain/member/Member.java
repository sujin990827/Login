package whales.login.domain.member;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;


@Data
@Entity
public class Member {

    @Id @GeneratedValue
    private Long id;

    @NotEmpty
    private String LoginId;//로그인 아이디

    @NotEmpty
    private  String name; //이름

    @NotEmpty
    private String password; //로그인 비밀번호  --> 저장소 만들어야함
}
