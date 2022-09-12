package com.example.flywaypractice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FlywayPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlywayPracticeApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return new ApplicationRunner() {

            @Autowired
            private MemberRepository memberRepository;

            @Override
            public void run(ApplicationArguments args) throws Exception {
                memberRepository.save(Member.builder()
                        .name("sample")
                        .build());
            }
        };
    }

}
