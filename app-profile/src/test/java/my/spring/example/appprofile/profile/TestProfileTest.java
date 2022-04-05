package my.spring.example.appprofile.profile;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class TestProfileTest {

    @Autowired
    Environment environment;

    @Test
    void test_프로필_적용() {
        String[] activeProfiles = environment.getActiveProfiles();
        assertThat(activeProfiles).containsExactly("test");

        String jdbcUrl = environment.getProperty("spring.datasource.url");
        assertThat(jdbcUrl).isEqualTo("jdbc:h2:mem:test_db");

        // default property => 적용
        String appName = environment.getProperty("spring.application.name");
        assertThat(appName).isEqualTo("app-profile-practice");
        String defaultProfileProperty = environment.getProperty("default-profile-property");
        assertThat(defaultProfileProperty).isEqualTo("default-property");
    }
}
