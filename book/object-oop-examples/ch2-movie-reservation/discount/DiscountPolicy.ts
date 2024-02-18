export default interface DiscountPolicy {
    apply(fee: number): number
}
