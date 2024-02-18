import java.util.HashMap

/* TODO */


fun usage(): Map<Int, String> {
    return buildMutableMap {
        put(0, "0")
        for (i in 1..10) {
            put(i, "$i")
        }
    }
}

fun buildMutableMap(function: () -> Unit): Map<Int, String> {
    TODO("Not yet implemented")
}
