import DiscountPolicy from "./DiscountPolicy";

export default class PercentDiscountPolicy implements DiscountPolicy {
    private percent: number;

    constructor(percent: number) {
        this.percent = percent;
    }

    apply(fee: number): number {
        return fee * ((100 - this.percent) / 100);
    }
}
