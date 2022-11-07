package Entity;

/**
    An Class Containing the various enum Classes catering to the configuration of a particular movie
    @version 1.0
    @since 2022-10-25
 */
public class MovieEnums {

    /**
        This enum class shows the Status of the movie to be Chosen
     */
    public enum MovieStatus{
        COMING_SOON("COMING SOON"),
        PREVIEW ("PREVIEW"),
        NOW_SHOWING ("NOW SHOWING"),
        END_OF_SHOWING("END OF SHOWING");

        private String movieStatus;
        MovieStatus(String movieStatus ) {this.movieStatus=movieStatus;};

        public String toString(){
            return movieStatus;
        }
    }

     /**
        This enum class shows the AgeAdvisory category of a movie to be chosen.
     */
    public enum AgeAdvisory{
        G("G"), PG("PG"), PG13("PG13"), NC16("NC16"), M18("M18"), R21("R21");

        private String ageAdvisory;
        AgeAdvisory(String ageAdvisory) {
            this.ageAdvisory = ageAdvisory;
        }

        @Override
        public String toString() {
            return ageAdvisory;
        }
    }

}
