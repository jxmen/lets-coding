class LazyProperty(val initializer: () -> Int) {
    /* TODO */
    val lazy: Int
        get() {
            return initializer()
        }
}

fun main() {
    val intializer = () -> 5
    val lazyProperty = LazyProperty(initializer = intializer)
    lazyProperty.lazy
}
