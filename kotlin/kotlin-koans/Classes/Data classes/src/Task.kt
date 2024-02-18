data class Person(val name: String, val age: Int)

fun getPeople(): List<Person> {
    return listOf(Person("Alice", 29), Person("Bob", 31))
}

fun comparePeople(): Boolean {
    val p1 = Person("Alice", 29)
    val p2 = Person("Alice", 29)
    return p1 == p2  // should be true
}

class InitOrderMemo(name: String) {

    val firstProperty = "1번째 프로퍼티: $name".also(::println)

    init {
        println("1번째 생성자 블럭. 이름: $name")
    }

    val secondProperty = "2번째 프로퍼티. 길이: ${name.length}".also(::println)

    init {
        println("2번째 생성자 블럭. 이름: $name")
    }
}

fun main() {
    InitOrderMemo("박주영")

    println(comparePeople())
}
