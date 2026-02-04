package study.studySpring.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.studySpring.domain.Member;
import study.studySpring.repository.MemberRepository;
import study.studySpring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    // 회원가입 메소드
    public long join(Member member){
        // AOP의 필요성을 인식하기 위한 메서드 내부 작동시간 구하기
        long start = System.currentTimeMillis();

        try {
            // 동명의 회원은 X(중복)
            validateDuplicateMember(member);

            memberRepository.save(member);
            // 간단한 로직으로 회원 가입 시 아이디 반환
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }


    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m ->{
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public List<Member> findMembers(){
        long start = System.currentTimeMillis();
        try {
            return memberRepository.findAll();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers = " + timeMs + "ms");
        }

    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }


}
