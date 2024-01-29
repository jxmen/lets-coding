val month = "(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC)"

fun getPattern(): String = """\d{2}\s${month}\s\d{4}"""

fun main() {
    val listOf = listOf("JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC")

    val regex = getPattern().toRegex()
    listOf.forEach { println(regex.matches("13 ${it} 1992"))}
}
