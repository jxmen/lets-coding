
class LazyProperty(val initializer: () -> Int) {
    val lazyValue: Int by lazy {
        println("compueted!")
        initializer()
    }
}

