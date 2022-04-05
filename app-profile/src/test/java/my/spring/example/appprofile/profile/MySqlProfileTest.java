package my.spring.example.appprofile.profile;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("mysql")
class MySqlProfileTest {

    @Autowired
    Environment environment;

    @Test
    void mysql_프로필_적용() {
        String[] activeProfiles = environment.getActiveProfiles();
        assertThat(activeProfiles).containsExactly("mysql");

        // default property => 적용
        String appName = environment.getProperty("spring.application.name");
        assertThat(appName).isEqualTo("app-profile-practice");
        String defaultProfileProperty = environment.getProperty("default-profile-property");
        assertThat(defaultProfileProperty).isEqualTo("default-property");

        // mysql, h2 profile 공통 property => mysql profile 값으로 설정
        String jdbcUrl = environment.getProperty("spring.datasource.url");
        assertThat(jdbcUrl).isEqualTo("jdbc:mysql://localhost/test");

        // mysql profile property => 젹용 X
        String mysqlProfileProperty = environment.getProperty("mysql-profile-property");
        assertThat(mysqlProfileProperty).isEqualTo("mysql-property");

        // h2 profile property => 젹용 X
        String h2ProfileProperty = environment.getProperty("h2-profile-property");
        assertThat(h2ProfileProperty).isNull();

        // rabbitmq profile property => 젹용 X
        String rabbitmqUsername = environment.getProperty("spring.config.rabbitmq.username");
        assertThat(rabbitmqUsername).isNull();
        String rabbitmqProfileProperty = environment.getProperty("rabbitmq-profile-property");
        assertThat(rabbitmqProfileProperty).isNull();
    }
}
