
public class Movie implements java.io.Serializable{

    private int Movieid;
    private String MovieName;
    private String MovieDesc;
    private MovieEnums.Status MovieStatus;
    private MovieEnums.Class MovieClass;

    public Movie (int i, String w, String h){
        Movieid = i;
        MovieName = w;
        MovieDesc = h;
        MovieStatus = getMovieStatus();
        MovieClass = getMovieClass();
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
