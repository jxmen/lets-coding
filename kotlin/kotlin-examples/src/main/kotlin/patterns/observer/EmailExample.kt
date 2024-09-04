package org.example.patterns.observer

// 예제: 이메일 발행 업체가 있고, 이메일을 발행하면 구독자들에게 이메일을 발송하는 예제

interface MailPublisher {
    fun add(subscriber: MailSubscriber)
    fun remove(subscriber: MailSubscriber)
    fun publish(title: String)
}

interface MailSubscriber {
    fun notifyEmail(
        publisherName: String,
        title: String
    ) // 이메일을 발행 업체를 통해 통지받다

    fun subscribe(publisher: MailPublisher)
    fun unsubscribe(publisher: MailPublisher)
}

class NewNick : MailPublisher {
    private val subscribers: MutableSet<MailSubscriber> = mutableSetOf()

    override fun add(subscriber: MailSubscriber) {
        this.subscribers.add(subscriber)
    }

    override fun remove(subscriber: MailSubscriber) {
        this.subscribers.remove(subscriber)
    }

    override fun publish(title: String) {
        subscribers.forEach { it.notifyEmail(
            publisherName = "뉴닉",
            title = title
        ) }
    }
}

class TheSlang : MailPublisher {
    private val subscribers: MutableSet<MailSubscriber> = mutableSetOf()

    override fun add(subscriber: MailSubscriber) {
        this.subscribers.add(subscriber)
    }

    override fun remove(subscriber: MailSubscriber) {
        this.subscribers.remove(subscriber)
    }

    override fun publish(title: String) {
        subscribers.forEach { it.notifyEmail(
            publisherName = "더슬랭",
            title = title
        ) }
    }
}

class SeoulSi : MailPublisher {
    private val subscribers: MutableSet<MailSubscriber> = mutableSetOf()

    override fun add(subscriber: MailSubscriber) {
        this.subscribers.add(subscriber)
    }

    override fun remove(subscriber: MailSubscriber) {
        this.subscribers.remove(subscriber)
    }

    override fun publish(title: String) {
        subscribers.forEach { it.notifyEmail(
            publisherName = "서울시청",
            title = title
        ) }
    }
}


class GitHub : MailPublisher {
    private val subscribers: MutableSet<MailSubscriber> = mutableSetOf()

    override fun add(subscriber: MailSubscriber) {
        this.subscribers.add(subscriber)
    }

    override fun remove(subscriber: MailSubscriber) {
        this.subscribers.remove(subscriber)
    }

    override fun publish(title: String) {
        subscribers.forEach { it.notifyEmail(
            publisherName = "깃허브",
            title = title
        ) }
    }
}

// 네이밍을 뭐로하지... Person? -> 음... 한 사람이 여러 계정을 가지고 있을 수도 있다.
class EmailAccount(val email: String) : MailSubscriber {
    override fun notifyEmail(publisherName: String, title: String) {
        println("[$email] 이메일을 받았습니다. 발행 업체: $publisherName, 제목: $title")
    }

    override fun subscribe(publisher: MailPublisher) {
        publisher.add(this)
    }

    override fun unsubscribe(publisher: MailPublisher) {
        publisher.remove(this)
    }
}


/**
 * NOTE: 처음에는 이렇게 했는데, EmailAccount 관점에서는 발행 업체들을 구독(subscribe)하는 형태라 subscribe/unsubscribe 등의 메서드를 추가하였다.
 */
fun main2() {
    val newNick: MailPublisher = NewNick() // 뉴닉
    val theSlang: MailPublisher = TheSlang() // 더슬랭
    val seoulSi: MailPublisher = SeoulSi() // 서울시청

    val myPersonalEmailAccount = EmailAccount(email = "sprnd645@gmail.com")
    listOf(newNick, theSlang, seoulSi).forEach { it.add(subscriber = myPersonalEmailAccount) }

    newNick.publish(title = "🪙주담대 받으려면 집 없어야 한다고?")
    theSlang.publish(title = "🕶 추석민생안정대책으로 이득 보기")
    seoulSi.publish(title = "추석 의료공백 없도록... 서울시")
}

fun main() {
    val github: MailPublisher = GitHub() // 깃허브
    val newNick: MailPublisher = NewNick() // 뉴닉
    val theSlang: MailPublisher = TheSlang() // 더슬랭
    val seoulSi: MailPublisher = SeoulSi() // 서울시청

    // 비즈니스 메일은 개발 관련 발행업체를 구독한다.
    val myBusinessEmailAccount = EmailAccount(email = "me@jxmen.dev")
    myBusinessEmailAccount.subscribe(publisher = github)

    // 개인 메일은 경제, 생활 관련 발행업체를 구독한다.
    val myPersonalEmailAccount = EmailAccount(email = "sprnd645@gmail.com")
    myPersonalEmailAccount.subscribe(publisher = newNick)
    myPersonalEmailAccount.subscribe(publisher = theSlang)
    myPersonalEmailAccount.subscribe(publisher = seoulSi)

    github.publish(title = "🚀 [naver/fixture-monkey] Release 1.1.0")
    newNick.publish(title = "🪙주담대 받으려면 집 없어야 한다고?")
    theSlang.publish(title = "🕶 추석민생안정대책으로 이득 보기")
    seoulSi.publish(title = "추석 의료공백 없도록... 서울시 대응방안 마련")

    myPersonalEmailAccount.unsubscribe(publisher = newNick)

    println("=== 구독 취소 후 ===")
    newNick.publish(title = "🪙중국은 경기침체 클래스도 차이나네")
}
