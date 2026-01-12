package study.studySpring.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import study.studySpring.domain.Member;

import java.util.List;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");
        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // 확인 1. 콘솔에 찍어보기
        //System.out.println("result = " + (result == member));

        // 확인 2. Assertions 의 assertEquals 메소드 활용하기
        Assertions.assertEquals(member, result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        Assertions.assertEquals(result, member1);
        //--==>> 정상동작
        // Assertions.assertEquals(result, member2);
        //--==>> 실패
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        Assertions.assertEquals(result.size(), 2);
    }
}
