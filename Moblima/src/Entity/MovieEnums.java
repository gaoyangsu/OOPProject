package Entity;
public class MovieEnums {
    public enum MovieStatus{
        COMING_SOON("Coming Soon"),
        PREVIEW ("Preview"),
        NOW_SHOWING ("Now Showing"),
        END_OF_SHOWING("End of Showing");

        private String movieStatus;
        MovieStatus(String movieStatus ) {this.movieStatus=movieStatus;};

        public String toString(){
            return movieStatus;
        }
    }

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
