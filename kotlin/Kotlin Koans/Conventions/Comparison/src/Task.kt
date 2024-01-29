data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    /* TODO */

    override fun compareTo(other: MyDate): Int = when {
        year != other.year -> year - other.year
        month != other.month -> month - other.month
        else -> dayOfMonth - other.dayOfMonth
    }
}

fun test(date1: MyDate, date2: MyDate) {
    // this code should compile:
    println(date1 < date2)
}

fun main() {
    test(MyDate(2024, 1, 29), MyDate(2024, 1, 28))
    test(MyDate(2014, 10, 20), MyDate(2014, 6, 11))
}
