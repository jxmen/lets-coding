// Find the most expensive product among all the delivered products
// ordered by the customer. Use `Order.isDelivered` flag.
fun findMostExpensiveProductBy(customer: Customer): Product? {
    return customer.orders.filter { it.isDelivered }
        .flatMap { it.products }
        .maxByOrNull { it.price }
}

// Count the amount of times a product was ordered.
// Note that a customer may order the same product several times.
fun Shop.getNumberOfTimesProductWasOrdered(product: Product): Int {
    return this.customers.flatMap { it.orders }
        .flatMap { it.products }
        .count {it == product}
}

fun main() {
    val words = "The quick brown fox jumps over the lazy dog".split(" ")
//convert the List to a Sequence
    val wordsSequence = words.asSequence()

    val lengthsSequence = wordsSequence.filter { println("filter: $it"); it.length > 3 }
//        .map { println("length: ${it.length}"); it.length }
        .take(3)

    println("Lengths of first 4 words longer than 3 chars")
// terminal operation: obtaining the result as a List
    println(lengthsSequence.toList())
}
