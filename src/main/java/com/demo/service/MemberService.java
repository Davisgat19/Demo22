package com.demo.service;

import com.demo.entity.Member;
import com.demo.repository.MemberRepository;
import com.demo.entity.Address;
import com.demo.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AddressRepository addressRepository;

    // Save new member
    public Member saveMember(Member member) {
        Address address = member.getAddress();
        if (address != null && address.getId() == null) {
            addressRepository.save(address);
        }
        return memberRepository.save(member);
    }

    // Update existing member
    public Member updateMember(Long id, Member member) {
        Optional<Member> existingMemberOptional = memberRepository.findById(id);
        if (existingMemberOptional.isPresent()) {
            Member existingMember = existingMemberOptional.get();
            Address address = member.getAddress();
            if (address != null && address.getId() == null) {
                addressRepository.save(address);
            }
            existingMember.setFirstName(member.getFirstName());
            existingMember.setLastName(member.getLastName());
            existingMember.setAddress(address);
            existingMember.setEmail(member.getEmail());
            existingMember.setPhone(member.getPhone());
            existingMember.setDateOfBirth(member.getDateOfBirth());
            return memberRepository.save(existingMember);
        } else {
            return null;
        }
    }


    public Optional<Member> findMemberById(Long id) {
        return memberRepository.findById(id);
    }

    public Iterable<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
