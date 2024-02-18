fun containsEven(collection: Collection<Int>): Boolean =
    collection.any { it % 2 == 0 }


// Pass a lambda to the any function to check if the collection contains an even number.

val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }

fun main() {
    val listOf = listOf(1, 2, 3, 4, 5)

    val sum = listOf.fold(0) { acc, e -> acc + e}
    println(sum)
}
