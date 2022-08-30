package com.example.enumcode;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class EnumMapperFactoryTest {

    private EnumMapperFactory enumMapperFactory;

    @BeforeEach
    void setUp() {
        enumMapperFactory = new EnumMapperFactory();
    }

    @Test
    void get() {
        // given
        enumMapperFactory.put(TestEnum1.class.getSimpleName(), TestEnum1.class);

        // when
        List<EnumMapperValue> testEnumValues = enumMapperFactory.get("TestEnum1");

        // then
        assertThat(testEnumValues)
                .hasSize(2)
                .containsExactly(new EnumMapperValue(TestEnum1.TEST1_1), new EnumMapperValue(TestEnum1.TEST1_2));
    }

    @Test
    void getList() {
        // given
        enumMapperFactory.put(TestEnum1.class.getSimpleName(), TestEnum1.class);
        enumMapperFactory.put(TestEnum2.class.getSimpleName(), TestEnum2.class);

        // when
        Map<String, List<EnumMapperValue>> enumValueMap = enumMapperFactory.get(List.of("TestEnum1", "TestEnum2"));

        // then
        assertAll(
                () -> assertThat(enumValueMap.keySet())
                        .hasSize(2)
                        .containsExactly("TestEnum1", "TestEnum2"),
                () -> assertThat(enumValueMap.get("TestEnum1"))
                        .hasSize(2)
                        .containsExactly(
                                new EnumMapperValue(TestEnum1.TEST1_1), new EnumMapperValue(TestEnum1.TEST1_2)),
                () -> assertThat(enumValueMap.get("TestEnum2"))
                        .hasSize(2)
                        .containsExactly(
                                new EnumMapperValue(TestEnum2.TEST2_1), new EnumMapperValue(TestEnum2.TEST2_2))
        );
    }

    @Test
    void getAll() {
        // given
        enumMapperFactory.put(TestEnum1.class.getSimpleName(), TestEnum1.class);
        enumMapperFactory.put(TestEnum2.class.getSimpleName(), TestEnum2.class);

        // when
        Map<String, List<EnumMapperValue>> enumValueMap = enumMapperFactory.getAll();

        // then
        assertAll(
                () -> assertThat(enumValueMap.keySet())
                        .hasSize(2)
                        .containsExactly("TestEnum1", "TestEnum2"),
                () -> assertThat(enumValueMap.get("TestEnum1"))
                        .hasSize(2)
                        .containsExactly(
                                new EnumMapperValue(TestEnum1.TEST1_1), new EnumMapperValue(TestEnum1.TEST1_2)),
                () -> assertThat(enumValueMap.get("TestEnum2"))
                        .hasSize(2)
                        .containsExactly(
                                new EnumMapperValue(TestEnum2.TEST2_1), new EnumMapperValue(TestEnum2.TEST2_2))
        );
    }

    @RequiredArgsConstructor
    enum TestEnum1 implements EnumMapperType {

        TEST1_1("테스트1-1"),
        TEST1_2("테스트1-2"),
        ;

        private final String title;

        @Override
        public String getCode() {
            return name();
        }

        @Override
        public String getTitle() {
            return title;
        }
    }

    @RequiredArgsConstructor
    enum TestEnum2 implements EnumMapperType {

        TEST2_1("테스트2-1"),
        TEST2_2("테스트2-2"),
        ;

        private final String title;

        @Override
        public String getCode() {
            return name();
        }

        @Override
        public String getTitle() {
            return title;
        }
    }
}