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
}
```

- save하여 쿠폰의 개수가 실제로 늘어나기 전에 다른 스레드에서 count를 읽는다면 더 많은 쿠폰이 발급될 수도 있다.
- count부분에 락을 걸어야 하는데, 그렇다고 저장까지 락을 걸면 불필요한 대기 시간이 발생한다.

### Redis를 활용하여 동시성 문제 해결하기

`incr`이란 명령어가 존재
- key에 대한 value를 1씩 증가하고, 해당 값을 반환한다.
