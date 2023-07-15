import Movie from "./movie-and-reservation/Movie";
import PercentDiscountPolicy from "./discount/PercentDiscountPolicy";
import AmountDiscountPolicy from "./discount/AmountDiscountPolicy";
import Screening from "./movie-and-reservation/Screening";
import Reservation from "./movie-and-reservation/Reservation";

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

    movie.setNoneDiscount();

    const screening: Screening = new Screening(movie);
    const reservation1: Reservation = new Reservation(screening, 3);
    const reservation2: Reservation = new Reservation(screening, 2);

    // 예매할 때 가격을 구한다.
    const reservation1Fee = reservation1.calculateFee();
    console.log(`reservation1Fee: ${reservation1Fee}`);
    console.assert(reservation1Fee === 15000 * 3);

    const reservation2Fee = reservation2.calculateFee();
    console.log(`reservation2Fee: ${reservation2Fee}`);
    console.assert(reservation2Fee === 15000 * 2);
}

main();
