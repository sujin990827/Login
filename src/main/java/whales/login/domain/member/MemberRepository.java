package whales.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

//저장
    @Transactional
    public void save(Member member) {

        em.persist(member);  //jpa 가 저장하는 로직
    }
//    private static Map<Long, Member> store = new HashMap<>();
//    private static long sequence = 0;
//
////저장
//    public Member save(Member member){
//        member.setId(sequence++);
//        log.info("save member: {}", member);
//        store.put(member.getId(), member);
//
//        return member;
//    }

    //아이디 찾기
    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    //로그인 아이디로 찾기
    @Transactional
    public Member findByName(String name){
        List<Member> member =  em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return member.get(0); //동명이인 고려 안함
    }

    public List<Member> findAll(){
        List<Member> result = em.createQuery("select m from Member m", Member.class)
                .getResultList();
        return result;
    }
}
