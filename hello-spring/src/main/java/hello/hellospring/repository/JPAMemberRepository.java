package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JPAMemberRepository implements MemberRepository{

    private final EntityManager em;//스프링부트가 자동으로 EntityManager을 자동으로 만듬

    public JPAMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                /*name 필드(m.name)가 특정 값(where m.name = :name)과 일치하는 모든 Member 엔티티(select m from Member m)를 선택합니다.
                 :name 구문은 쿼리가 실행될 때 실제 값으로 대체될 자리 표시자입니다.
                 Member.class는 쿼리가 반환할 항목의 유형을 지정합니다. 이 경우 쿼리는 Member 엔터티 목록을 반환*/
                .setParameter("name", name)//쿼리의 :name 매개변수 값을 findByName 메소드에 전달된 name 매개변수 값으로 설정
                .getResultList();//지정된 이름과 일치하는 Member 항목 목록을 반환
        return result.stream().findAny();//result에서 아무거나 하나 반환. 어차피 result에는 이름이 같은 개체만 들어가게 설정되어있고
        //애초에 중복되는 이름은 회원가입할수없기때문에 찾고자하는 이름을 정확히 찾을수있다.
    }

    @Override
    public List<Member> findAll() {
        List<Member> result = em.createQuery("select m from Member m", Member.class)
                .getResultList();
        return result;
    }
}
