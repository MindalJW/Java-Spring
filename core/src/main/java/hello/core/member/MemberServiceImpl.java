package hello.core.member;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component//컴포넌트 스캔에 대상이 됨 ->따로 수동으로 설정을하지않아도 자동 빈등록
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

//    @Autowired//자동 의존관계 주입 - 이 어노테이션이 붙어있으면 스프링빈에서 찾아서 자동으로 주입해줌(getBean(MemberRepository.class)랑 비슷)
//    public MemberServiceImpl(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
