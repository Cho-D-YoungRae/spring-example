package my.spring.example.testcontainerssetting.testcontainers;

import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
class TestContainers {

    @Container
    static GenericContainer postgreSQLContainer = new GenericContainer("postgres")
            .withExposedPorts(5432)
            .withEnv("POSTGRES_USER", "studytest")
            .withEnv("POSTGRES_PASSWORD", "studytest")
            .withEnv("POSTGRES_DB", "studytest");

    @Test
    void test() {
        System.out.println("작동확인");
    }
}
