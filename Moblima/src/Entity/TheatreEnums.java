package Entity;
/**
    An Class Containing the various enum Classes catering to the settings of the theatre
    @version 1.0
    @since 2022-10-25
 */
public class TheatreEnums {
    /**
        This enum class shows the cineplex brand to be chosen
        @param GV golden village
        @param CATHAY cathay cineplex
        @param FILMGARDE Filmgarde
     */
    public enum Cineplex{
        GV("GV"),
        CATHAY("CATHAY"),
        FILMGARDE("FILMGARDE"); 

        private String cineplex;
        Cineplex(String cineplex ) {this.cineplex=cineplex;}

        public String toString() {
            return cineplex;
        }
    }

    /**
        This enum class shows the locations of the theatres to be chosen
        @param JURONG_EAST Jurong East
        @param BUGIS Bugis
        @param WOODLANDS Woodlands
     */
    public enum CinemaLocation{
        JURONG_EAST("Jurong East"),
        BUGIS("Bugis"),
        WOODLANDS("Woodlands");

        private String cinemaLocation;

        CinemaLocation(String cinemaLocation){this.cinemaLocation=cinemaLocation;}
        public String toString(){return cinemaLocation;}
    }

    /**
        This enum class shows the classes of the theatres to be chosen
     */
    public enum TheatreClass{
        PLATINUM_SUITES("Platinum Suites"),
        ELITE_CLUB_SEATS("Elite Club seats"),
        ULTIMA_SEATS("Ultima seats"),
        DOLBY_ATMOS("Dolby Atmos"),
        GOLD_CLASS("Gold Class"),
        NORMAL("normal");
        private String theatreClass;

        TheatreClass(String theatreClass){this.theatreClass=theatreClass;}
        public String toString(){return theatreClass;}
    }
}
