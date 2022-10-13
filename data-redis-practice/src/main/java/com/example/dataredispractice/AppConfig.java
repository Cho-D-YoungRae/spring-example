package com.example.dataredispractice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ApplicationRunner applicationRunner() {
        return new ApplicationRunner() {

            @Autowired
            private MemberRepository memberRepository;

            @Override
            public void run(ApplicationArguments args) throws Exception {
                Member member = memberRepository.save(Member.builder()
                        .name("name")
                        .introduction("introduction")
                        .build());

                Member foundMember = memberRepository.findById(member.getId()).get();
                System.out.println("foundMember = " + foundMember);
            }

        };
    }
}
