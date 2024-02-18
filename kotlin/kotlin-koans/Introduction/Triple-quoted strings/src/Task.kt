const val question = "life, the universe, and everything"
const val answer = 42

val tripleQuotedString = """
    #question = "$question"
    #answer = $answer""".trimMargin("#")

val text = """
    for (c in "foo")
        print(c)
"""

fun main() {
    println(tripleQuotedString)

    println(text)
    println(text.trimIndent())
}
