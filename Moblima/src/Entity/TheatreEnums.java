package Entity;

public class TheatreEnums {
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

    public enum CinemaLocation{
        JURONG_EAST("Jurong East"),
        BUGIS("Bugis"),
        WOODLANDS("Woodlands");

        private String cinemaLocation;

        CinemaLocation(String cinemaLocation){this.cinemaLocation=cinemaLocation;}
        public String toString(){return cinemaLocation;}
    }

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
