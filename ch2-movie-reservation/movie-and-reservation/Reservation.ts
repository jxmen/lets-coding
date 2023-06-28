import Screening from "./Screening";

export default class Reservation {
    private screening: Screening;
    private reservationPeopleNumber: number;

    constructor(screening: Screening, reservationPeopleNumber: number) {
        this.screening = screening;
        this.reservationPeopleNumber = reservationPeopleNumber;
    }

    public calculateFee() {
        return this.screening.getMovieFee() * this.reservationPeopleNumber;
    }
}
