import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;


public class CRUDMovies {

    public static void addmovie(Movie movie){

        ArrayList<Movie> Movies = null;
        Movies = getAllMovieData();
        Movies.add(movie);
        SaveToAllMovieData(Movies);
        PrintAllMovieData();
        
        return;
    }



    public static void removeMovie(Movie movie){
        ArrayList<Movie> Movies = null;
        Movies = getAllMovieData();

        Iterator<Movie> itr = Movies.iterator(); 
        while(itr.hasNext()){
            Movie name = itr.next();
            if(name.getMovieID() == movie.getMovieID()){
                itr.remove();
            }
        }
        SaveToAllMovieData(Movies);
        PrintAllMovieData();

        return;
    }








    

    public static ArrayList<Movie> getAllMovieData(){
        ArrayList<Movie> Movies = null;
        try{
            //deserialize the Movies.ser file
            FileInputStream file = new FileInputStream("Data\\Movies.ser");
            ObjectInputStream in = new ObjectInputStream(file);
            Movies = (ArrayList<Movie>)in.readObject();
            in.close();
            file.close();
        }
        catch (IOException i) {
            i.printStackTrace();
            return Movies;
        } 
        catch (ClassNotFoundException c) {
            System.out.println("Movies class not found");
            c.printStackTrace();
            return Movies;
        }
        
        return Movies;
    }

    public static void SaveToAllMovieData(ArrayList<Movie> Movies){
        try{
            //serialize the Movies.ser file
            FileOutputStream file2 = new FileOutputStream("Data\\Movies.ser");
            ObjectOutputStream out = new ObjectOutputStream(file2);
            out.writeObject(Movies);
            out.close();
            file2.close();
        }
        catch (IOException i) {
            i.printStackTrace();
            return;
        }
        return;
    }


    public static void PrintAllMovieData(){
        ArrayList<Movie> Movies = null;
        Movies = getAllMovieData();
        for(Movie z: Movies){
            System.out.println(z.getMovieID());
            System.out.println(z.getMovieName());
            System.out.println(z.getMovieDesc());
        }
        return;
    }

}
