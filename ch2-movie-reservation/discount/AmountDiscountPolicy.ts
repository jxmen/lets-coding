import DiscountPolicy from "./DiscountPolicy";

export default class AmountDiscountPolicy implements DiscountPolicy {
    private amount: number;

    constructor(amount: number) {
        this.amount = amount;
    }

    apply(fee: number): number {
        return fee - this.amount;
    }
}
