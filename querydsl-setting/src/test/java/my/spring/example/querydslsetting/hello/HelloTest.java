package my.spring.example.querydslsetting.hello;

import com.querydsl.jpa.impl.JPAQueryFactory;
import my.spring.example.querydslsetting.entity.Hello;
import my.spring.example.querydslsetting.entity.QHello;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class HelloTest {

    @Autowired
    EntityManager em;

    @Test
    void test() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        Hello hello = Hello.builder()
                .description("test-hello")
                .build();
        em.persist(hello);

        em.flush();
        em.clear();

        Hello result = queryFactory
                .selectFrom(QHello.hello)
                .where(QHello.hello.eq(hello))
                .fetchOne();
        assertThat(result.getId()).isEqualTo(hello.getId());
        assertThat(result.getDescription()).isEqualTo(hello.getDescription());
    }
}
