import DiscountPolicy from "./DiscountPolicy";

export default class NoneDiscountPolicy implements DiscountPolicy {
    apply(fee: number): number {
        return fee;
    }
}
