# Race Condition

## 참고

- [재고시스템으로 알아보는 동시성이슈 해결방법](https://www.inflearn.com/course/%EB%8F%99%EC%8B%9C%EC%84%B1%EC%9D%B4%EC%8A%88-%EC%9E%AC%EA%B3%A0%EC%8B%9C%EC%8A%A4%ED%85%9C/dashboard)

## Synchronized

- 한 개의 쓰레드만 접근하도록 함
- @Transactional 의 동작 방식 때문에 synchronized 과 함께 사용 불가능
- 서버가 1대일때는 가능하지만, 여러대의 서버를 사용하게 되면 결국 같은 문제 발생
