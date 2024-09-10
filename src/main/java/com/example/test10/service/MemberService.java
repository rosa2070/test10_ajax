package com.example.test10.service;

import com.example.test10.dto.MemberDto;
import com.example.test10.entity.Member;
import com.example.test10.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    public MemberDto join(MemberDto dto) {
        Member member = dto.toEntity();
        Member m = memberRepository.save(member);
        return new MemberDto(m);
    }


    public List<MemberDto> list() {
        List<Member> mList = memberRepository.findAll();
        List<MemberDto> list1 = mList.stream().map(m -> new MemberDto(m)).toList();
        return list1;
    }
    //delete
    public void delete(String id) {
        memberRepository.deleteById(id);
    }

    //select (id로 조회)
    public MemberDto select(String id) {
        Optional<Member> m = memberRepository.findById(id);
        if(m.isEmpty()) {
            return null;
        } else {
            return new MemberDto(m.get());
        }
    }

    //update (비밀번호, 이메일, 나이 수정)
    public MemberDto update(MemberDto dto) {
        Member m = dto.toEntity();
        Optional<Member> m1 = memberRepository.findById(m.getId());
        if (!m1.isEmpty()) {
            Member m2 = m1.get();
            m2.setAge(m.getAge());
            m2.setEmail(m.getEmail());
            m2.setPwd(m.getPwd());
            return new MemberDto(m2);
        }
        return null;

    }

    //Optional로 받아와서 써도 ok
    public MemberDto findMember(String pwd, String email) {
        Member member = memberRepository.findMember(pwd, email);
        if (member != null) {
            return new MemberDto(member);
        } else {
            return null;
        }
    }


}
