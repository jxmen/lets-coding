fun eval(expr: Expr): Int =
    when (expr) {
        is Num -> expr.value
        is Sum -> eval(expr.left) + eval(expr.right)
    }

// sealed로 정의될 경우, 다른 패키지나 모듈에서 접근할 수 없다. 패턴 매칭/when 등에서 유용하게 사용할 수 있다.
sealed interface Expr

class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr


fun main() {
    println(eval(Num(15)))
    println(eval(Sum(Num(20), Num(30))))
}
