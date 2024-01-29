// Build a map from the customer name to the customer
fun Shop.nameToCustomerMap(): Map<String, Customer> =
    customers.associateBy { it.name }

// Build a map from the customer to their city
fun Shop.customerToCityMap(): Map<Customer, City> =
    customers.associateWith { it.city }

// Build a map from the customer name to their city
fun Shop.customerNameToCityMap(): Map<String, City> =
    customers.associate {it.name to it.city}


fun main() {
    val city1 = City("고객1")
    val product1 = Product("상품1", 10000.000)
    val order1 = Order(products = listOf(product1), isDelivered = false)
    val customer1Orders = listOf<Order>(order1)
    val customer1 = Customer("고객1", city = city1, orders = customer1Orders)
    val shop = Shop(name = "박주영샵", customers = listOf(customer1))

    val customerToCityMap = shop.nameToCustomerMap()
    println(customerToCityMap)
}
