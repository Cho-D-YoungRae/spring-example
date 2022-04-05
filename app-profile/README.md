# Spring application profile example

> 실행환경: `java11`, `IntelliJ IDEA - Ultimate`

- [github]() 실제 코드는 이곳에서 확인할 수 있습니다.

`SpringBoot` 2.4 부터 profile 설정이 업데이트 되었다고 합니다. 
이와 관련해서 profile 을 적용하는 몇 가지 방법을 시도해보았습니다.

기존의 `spring.profiles<.*>` 으로 설정하면 안된다고 합니다. => deprecated 된 것으로 보입니다.

기본적으로 가장 마지막에 적용된 값으로 overriding 됩니다.

## 프로젝트 구조

> 테스트를 해보기 위해서 임의로 구성한 것입니다.

- src/main/resources/
  - application.yml
  - application-ec2.yml
  - application-s3.yml
  - application-prod.yml
- src/test/resources/
  - application-test.yml

## profile 을 적용하는 방법

- `application-<profile>.properties`
- `application` 설정 파일에서 `spring.config.activate.on-profile: "profile이름"` 

## application 설정파일 세팅 및 여러 profile 적용

> `**-profile-property`는 해당 profile 이 적용되었을 때 사용할 수 있는 property로 예제를 위해 임의로 두었습니다. 

**`application.yml`**

```yaml
spring:
  application:
    name: "app-profile-practice"
  profiles:
    group:
      "production": "mysql,rabbitmq"
      "local": "h2,rabbitmq"
      "aws": "ec2,s3,production"
default-profile-property: "default-property" # default profile 에만 적용되는 임의의 property
---
spring.config.activate.on-profile: "mysql"
mysql-profile-property: "mysql-property"  # mysql profile 에만 적용되는 임의의 property
---
spring.config.activate.on-profile: "rabbitmq"
rabbitmq-profile-property: "rabbitmq-property"  # rabbitmq profile 에 적용되는 임의의 property
---
spring.config.activate.on-profile: "h2"
h2-profile-property: "h2-property"  # h2 profile 에 적용되는 임의의 property
```

- 가장 최상단에는 default 설정들이 담겨있으며 해당 설정은 어떤 `profile`도 선택되지 않아도 항상 사용됩니다.
- `---` 을 기준으로 `profile`들이 나뉘어져 있는 모습을 볼 수 있습니다.
- `spring.profiles.group` 을 통해서 여러 `profile`들을 동시에 가져와 사용할 수 있으며 해당 그룹을 적용하려면 해당 그룹 이름을 `profile`이름처럼 적용하면 됩니다.
- `spring.profiles.group."aws"` 보시면 알 수 있듯, 외부 파일로 되어있는 `profile`도 grouping 할 수 있는 것을 볼 수 있고, 다른 group 또한 다시 grouping 할 수 있습니다.

## 외부 파일은 하나의 application 설정파일에만 overriding??

**`application-prod.yml`**

```yaml
spring:
  profiles:
    group:
      "aws": "ec2,s3"
      "production": "mysql,rabbitmq"
```

```java
@SpringBootTest
@ActiveProfiles("prod")
class ProdProfileTest {

    @Autowired
    Environment environment;

    @Test
    void prod_프로필_적용() {
        String[] activeProfiles = environment.getActiveProfiles();
        assertThat(activeProfiles).containsExactly("prod");
    }
}
```

위와 같이 외부 파일로 `prod` profile 을 분리하였지만 그 안에 작성해둔 profile grouping 이 하나도 적용되지 않았습니다.
spring-boot github wiki 를 보니 외부파일로 되어있는 설정들이 모두 하나의 파일에만 override 된다는 것 같고 그것이 `applicationl.yml`인 것으로 보입니다.

## Reference

- [spring-boot github wiki](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-Config-Data-Migration-Guide#profile-groups)