package com.demo.initializer;

import com.demo.entity.Address;
import com.demo.entity.Member;
import com.demo.repository.AddressRepository;
import com.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public void run(String... args) throws Exception {
        Address address1 = new Address("123 Gata", "12345", "Göteborg");
        Address address2 = new Address("456 Gata", "67890", "Stockholm");

        address1 = addressRepository.save(address1);
        address2 = addressRepository.save(address2);

        Member member1 = new Member("John", "Doe", address1, "john.doe@example.com", "1234567890", LocalDate.of(1990, 1, 1));
        Member member2 = new Member("Jane", "Doe", address1, "jane.doe@example.com", "0987654321", LocalDate.of(1992, 2, 2));
        Member member3 = new Member("Jim", "Doe", address2, "jim.doe@example.com", null, LocalDate.of(1985, 3, 3));
        Member member4 = new Member("Johan", "Doe", address2, "johan.doe@example.com", "1122334455", LocalDate.of(1987, 4, 4));
        Member member5 = new Member("Erik", "Doe", address2, "erik.doe@example.com", "5566778899", LocalDate.of(1995, 5, 5));

        // Save members
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        memberRepository.save(member4);
        memberRepository.save(member5);
    }
}
