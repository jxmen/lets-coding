# 선착순 쿠폰 시스템 인프런 강의 예제 코드

[실습으로 배우는 선착순 이벤트 시스템
](https://www.inflearn.com/course/%EC%84%A0%EC%B0%A9%EC%88%9C-%EC%9D%B4%EB%B2%A4%ED%8A%B8-%EC%8B%9C%EC%8A%A4%ED%85%9C-%EC%8B%A4%EC%8A%B5) 인프런 강의 예제 코드입니다.

### 요구사항 정의

```
선착순 100명에게 할인쿠폰을 제공하는 이벤트를 진행하고자 한다.

이 이벤트는 아래와 같은 조건을 만족하여야 한다.
- 선착순 100명에게만 지급되어야한다.
- 101개 이상이 지급되면 안된다.
- 순간적으로 몰리는 트래픽을 버틸 수 있어야합니다.
```

### 처음 코드의 문제점

```Java
public void apply(Long userId) {
    long count = couponRepository.count();
    if (count > MAX_COUPON_COUNT) {
        return;
    }

    couponRepository.save(new Coupon(userId));
}G
```

- save하여 쿠폰의 개수가 실제로 늘어나기 전에 다른 스레드에서 count를 읽는다면 더 많은 쿠폰이 발급될 수도 있다.
- count부분에 락을 걸어야 하는데, 그렇다고 저장까지 락을 걸면 불필요한 대기 시간이 발생한다.

### Redis를 활용하여 동시성 문제 해결하기

`incr`이란 명령어가 존재
- key에 대한 value를 1씩 증가하고, 해당 값을 반환한다.

### 쿠폰 개수 조회를 Redis로 변경한 이후에도 발생할 수 있는 문제점

```Java
public void apply(Long userId) {
    long count = couponCountRepository.count();
    if (count > MAX_COUPON_COUNT) {
        return;
    }

    // write가 다량으로 들어온다면 문제 발생!!!
    couponRepository.save(new Coupon(userId));
}
```

쿠폰을 발급하는 write 요청 자체도 단기간에 많이 몰린다면, RDB에 많은 부하가 가게 된다. kafka를 통해 해결하는 방법을 알아본다. kafka를 사용하면 처리량을 조절할 수 있다.

### 요구사항 추가 - 1인당 발급 가능한 쿠폰 1개로 제한

Set 자료구조를 사용하여 중복을 방지한다. Redis에서도 Set을 지원한다.

- SADD: Set에 값 추가

### Kafka에서 처리하다가 실패한 경우 처리

- Kafka에서 처리하다가 실패한 경우, 다시 처리해야 한다.
- 실패한 정보를 FailedEvent에 저장해두고, 배치를 통해 주기적으로 읽어서 쿠폰이 100개까지 다시 발급되도록 한다.
