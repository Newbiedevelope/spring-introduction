package study.studySpring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.studySpring.repository.MemberRepository;
import study.studySpring.repository.MemoryMemberRepository;
import study.studySpring.service.MemberService;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
