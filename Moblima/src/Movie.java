
public class Movie implements java.io.Serializable{

    private int Movieid;
    private String MovieName;
    private String MovieDesc;

    public Movie (int i, String w, String h){
        Movieid = i;
        MovieName = w;
        MovieDesc = h;
    }

    public int getMovieID(){

        return this.Movieid;
    }
    public String getMovieName(){

        return this.MovieName;
    }
    public String getMovieDesc(){

        return this.MovieDesc;
    }



    //not implemented yet
    public MovieEnums.Status getMovieStatus(){
        //get current time
        //get movie screening time
        //do some math

        return null;
    }
    public MovieEnums.Class getMovieClass(){
        //get movie genre

        return null;
    }
}
