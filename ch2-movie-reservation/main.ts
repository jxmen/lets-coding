import Movie from "./movie/Movie";
import PercentDiscountPolicy from "./discount/PercentDiscountPolicy";
import AmountDiscountPolicy from "./discount/AmountDiscountPolicy";

function main() {
    const movie: Movie = new Movie(
        "범죄도시3",
        15000
    );

    let fee: number = movie.calculateFee();
    console.log(`fee: ${fee}`);
    console.assert(fee === 15000);

    movie.changeDiscountPolicy(new PercentDiscountPolicy(10));
    fee = movie.calculateFee();
    console.log(`fee: ${fee}`);
    console.assert(fee === (15000 * 0.9));

    movie.changeDiscountPolicy(new AmountDiscountPolicy(2000));
    fee = movie.calculateFee();
    console.log(`fee: ${fee}`);
    console.assert(fee === (15000 - 2000));
}

main();
