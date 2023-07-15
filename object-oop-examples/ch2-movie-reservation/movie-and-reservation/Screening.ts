import Movie from "./Movie";

export default class Screening {
    private movie: Movie;

    constructor(movie: Movie) {
        this.movie = movie;
    }

    public getMovieFee() {
       return this.movie.calculateFee();
    }
}
