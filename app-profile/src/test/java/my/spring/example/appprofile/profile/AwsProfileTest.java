package my.spring.example.appprofile.profile;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("aws")
class AwsProfileTest {

    @Autowired
    Environment environment;

    @Test
    void aws_프로필_적용() {
        String[] activeProfiles = environment.getActiveProfiles();
        assertThat(activeProfiles).containsExactly("aws", "ec2", "s3", "production", "mysql", "rabbitmq");

        String defaultProfileProperty = environment.getProperty("default-profile-property");
        assertThat(defaultProfileProperty).isEqualTo("default-property");

        String ec2ProfileProperty = environment.getProperty("ec2-profile-property");
        assertThat(ec2ProfileProperty).isEqualTo("ec2-property");

        String s3ProfileProperty = environment.getProperty("s3-profile-property");
        assertThat(s3ProfileProperty).isEqualTo("s3-property");
    }
}
