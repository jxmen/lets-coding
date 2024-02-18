import TimeInterval.*

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int)

// Supported intervals that might be added to dates:
enum class TimeInterval { DAY, WEEK, YEAR }

operator fun MyDate.plus(timeInterval: TimeInterval): MyDate = addTimeIntervals(timeInterval, 1)

fun task1(today: MyDate): MyDate {
    return today + YEAR + WEEK
}

data class RepeatedTimeInterval(val timeInterval: TimeInterval, val amount: Int)

operator fun TimeInterval.times(number: Int) =
    RepeatedTimeInterval(this, number)

operator fun MyDate.plus(repeatedTimeInterval: RepeatedTimeInterval): MyDate {
    return addTimeIntervals(repeatedTimeInterval.timeInterval, repeatedTimeInterval.amount)
}

fun task2(today: MyDate): MyDate {
    return today + YEAR * 2 + WEEK * 3 + DAY * 5
}

fun main() {
    val task1 = task1(MyDate(2024, 1, 29))
    println(task1)
}


/**
 * First, add the extension function plus() to MyDate, taking the TimeInterval as an argument.
 *
 * Use the utility function MyDate.addTimeIntervals() declared in DateUtil.kt
 *
 * Then, try to support adding several time intervals to a date. You may need an extra class.
 */
