class Invokable {
    var numberOfInvocations: Int = 0
        private set

    operator fun invoke(): Invokable {
        numberOfInvocations++
        return this
    }
}

fun invokeTwice(invokable: Invokable) = invokable()()


fun main() {
    operator fun Int.invoke() { println(this)}

    1()
    2()
    3()
}
