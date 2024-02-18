// Return customers who have more undelivered orders than delivered
fun Shop.getCustomersWithMoreUndeliveredOrders(): Set<Customer> =
    customers.filter { it ->
        val (delivered, unDelivered) = it.orders.partition { it.isDelivered }
        unDelivered.size > delivered.size
    }.toSet()

fun main() {
    val numbers = listOf(1, 3, -4, 2, -11)
    val (positive, negative) =
        numbers.partition { it > 0 }

    println(positive)
    println(negative)
}
