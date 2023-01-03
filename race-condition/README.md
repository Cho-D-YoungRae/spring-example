# Race Condition

## 참고

- [재고시스템으로 알아보는 동시성이슈 해결방법](https://www.inflearn.com/course/%EB%8F%99%EC%8B%9C%EC%84%B1%EC%9D%B4%EC%8A%88-%EC%9E%AC%EA%B3%A0%EC%8B%9C%EC%8A%A4%ED%85%9C/dashboard)

## Synchronized

- 한 개의 쓰레드만 접근하도록 함
- @Transactional 의 동작 방식 때문에 synchronized 과 함께 사용 불가능
- 서버가 1대일때는 가능하지만, 여러대의 서버를 사용하게 되면 결국 같은 문제 발생

## Pessimistic Lock

- 실제 데이터에 Lock 을 걸어서 정합성을 맞추는 방법
- exclusive lock 을 사용하면 다른 트랜잭션에서는 lock 이 해제되기 전에 데이터를 가져갈 수 없게 됨
- 데드락이 걸릴 수 있기 때문에 주의
- 충돌이 빈번하면 Optimistic Lock 보다 좋은 성능 기대
- Lock 을 사용하므로 성능 감소

## Optimistic Lock

- 실제 Lock 을 사용하지 않고 버전을 이용하여 정합성을 맞추는 방법
- 먼저 데이터를 읽은 후 update 를 수행할 때 현재 내가 읽은 버전이 맞는지 확인하여 업데이트. 내가 읽은 버전에서 수정사항이 생겼을 경우에는 application 에서 다시 읽은 후 작업을 수행해야 함.
- Lock 을 사용하지 않으므로 Pessimistic Lock 보다 성능상 이점
- update 실패시 재시도 로직을 추가해야 함
- 충돌이 자주 발생하면 Pessimistic Lock 이 성능 상 더 좋음

## Named Lock

- 이름을 가진 metadata locking
- 이름을 가진 lock 을 획득한 후 해제할 때까지 다른 세션은 이 lock 을 획득할 수 없도록 함
- 실제로 사용할 때는 Data Source 분리해서 사용하는 것이 좋음
  - 같은 DataSource 사용하면 커넥션 풀이 부족해지는 현상 발생 가능
  - Entity 도 그대로 사용하지 않고 별도의 JDBC 를 사용하는 등의 방법
- 분산 Lock 을 구현할 때 주로 사용
  - Pessimistic Lock 은 time-out 을 구현하기 어려우나 Named Lock 은 쉽게 가능
- 데이터 삽입 시 정합성을 맞춰야 하는 경우에도 사용 가능
- 트랜잭션 종료 시 Lock 해제, 세션 관리를 잘 해주어야 함 -> 트랜잭션 종료 시 lock 이 자동으로 해제되지 않음. 별도의 명령어로 해제하거나 선점시간이 끝나야 함.
- 실제로 사용할 때는 구현 방법 복잡

## Redis - Lettuce

- spring data redis 의 기본 라이브러리
- `setnx` 를 활용한 분산락 구현
- spin lock 방식
  - Lock 을 획득하려는 쓰레드가 Lock 을 획득할 수 있는지 반복적으로 Lock 획득 시도
  - Lock 획득 재시도 로직 직접 작성
- time-out 직접 구현
- spin lock 방식이므로 동시에 많은 쓰레드가 Lock 획득 대기 상태라면 Redis 에 부하를 줄 수 있음

## Redis - Redisson

- Lock 획득 재시도를 기본으로 제공
- pub-sub 방식으로 구현되어 있으므로 Lettuce 와 비교했을 때 Redis 에 부하가 덜 함
- 별도 라이브러리를 사용해야 함
