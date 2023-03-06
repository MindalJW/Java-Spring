package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);//DB저장
    Optional<Member> findById(Long id);//Id로 찾기
    Optional<Member> findByName(String name);//이름으로 찾기
    List<Member> findAll();//전체DB조히
}
