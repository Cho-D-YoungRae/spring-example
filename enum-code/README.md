# Enum Code API

> 뷰에 보여지는 값을 위해서 상수(enum)을 프론트에서도 관리해야하는 번거로움을 해결하기 위한 API  


## 사용법

1. enum 에 [`EnumMapperType`](./src/main/java/com/example/enumcode/EnumMapperType.java) 를 구현
2. `EnumMapperType` 을 구현한 enum 을 [`EnumMapperConfig`](./src/main/java/com/example/enumcode/EnumMapperConfig.java) 에서 `EnumMapperFactory` 에 등록
3. [`CodeControllerTest`](./src/test/java/com/example/enumcode/CodeControllerTest.java) 의 '코드 리스트 전체 조회 API' 에 테스트 및 문서 추가


## 참고

- https://techblog.woowahan.com/2527/
- https://github.com/jojoldu/enum-mapper
- https://jojoldu.tistory.com/137
- https://github.com/jojoldu/blog-code/tree/master/enum-uses
- https://jojoldu.tistory.com/122
