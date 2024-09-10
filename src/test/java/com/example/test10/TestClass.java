package com.example.test10;

import com.example.test10.entity.Member;
import com.example.test10.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

@SpringBootTest
@Transactional
@Commit
public class TestClass {
    @Autowired private MemberRepository memberRepository;

    @Test
    void test1() {
        Member m = memberRepository.findMember2("2", "bb@");
        System.out.println(m);

        Member m1 = memberRepository.findByEmail("cc@");
        System.out.println("m1=====>" + m1);

        Member m2 = memberRepository.findByPwdAndEmail("2", "ee@");
        System.out.println("m2=====>" + m2);
    }
}
