package Boundary.Staff;
import Boundary.Boundary;
import Boundary.SupportFunctions;
import Entity.*;
import static Controller.CRUDMovies.*;
import static Controller.CRUDTheatre.*;
import static Controller.MiscMethods.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ModifyTheatreBoundary extends Boundary {

    @Override
    protected void start() {
        display();
    }

    public void display(){
        String selection = "";
        
        SupportFunctions.clearScreen();
        printHeader("Modify Theatres");
        printMenu("Select Cineplex to modify Movie Theatres",
                "1.GV",
                "2.Cathay",
                "3.FilmGarde",
                "4.Return");
        int choice = readChoice(1, 4);
        if (choice == 1) selection="GV";
        else if (choice ==2) selection="Cathay";
        else if (choice ==3) selection="FilmGarde";
        else if (choice ==4) end();
        displayCinemaList(readCineplex(selection));

    }

    public void displayCinemaList(TheatreEnums.Cineplex cineplex){
        Scanner sc= new Scanner(System.in);
        ArrayList<Theatre> theatres = retrieveTheatreList(cineplex);
        if (theatres == null) {
            System.out.println("No theatres Found");
            System.out.println("1. Add theatres in "+cineplex.toString());
            System.out.println("2. Return ");
            int choice = readChoice(1, 2);
            if (choice ==1) addTheatres(cineplex);
            else if (choice == 2) display();
        }

        else {
            for (Theatre theatre: retrieveTheatreList(cineplex)){
                System.out.println(theatre.getCineplex()+"      "+ theatre.getCinemaLocation()
                        +"      "+theatre.getCode()+"      "+"(" + (theatre.isIs3D() ? "3D" : "Digital") + ")" +"      "+theatre.getTheatreClass());
                System.out.println();

            }
            System.out.println("1. Add theatres in "+cineplex.toString());
            System.out.println("2. Return ");
            int choice = readChoice(1, 2);
            if (choice ==1) addTheatres(cineplex);
            else if (choice == 2) display();
        }
    }

    public void addTheatres(TheatreEnums.Cineplex cineplex){

        boolean is3D;
        TheatreEnums.CinemaLocation location= null;
        TheatreEnums.TheatreClass theatreClass= null;

        printHeader("addTheatres");

        Scanner sc= new Scanner (System.in);

        while(location==null) {
            String input = readString("Where is the location of the theatre? ",
                    "Type Jurong East, Woodlands, Bugis").toUpperCase();
            location = readLocation(input);
        }
        while(theatreClass==null) {
            String input2 = readString("What is the class of the theatre?",
                    "Type Platinum Suites, Elite Club seats, Ultima seats",
                    "Dolby Atmos, Gold Class, normal ").toUpperCase();
            theatreClass = readTheatreClass(input2);
        }

        System.out.println("is the theatre 3D? 1- yes, 0- no");
        int choice=  readChoice(0,1);

        if (choice ==0 ) is3D = false;
        else  is3D = true;

        System.out.println("Enter a 3 Character code name of theatre: ");
        String codeName= sc.nextLine().toUpperCase();

        Theatre toAppend = new Theatre(is3D, theatreClass, cineplex, location, codeName);

        try{
            addTheatreIntoList(toAppend);
            System.out.println("Added sucessfully!");
        } catch(IOException i){
            i.printStackTrace();
            System.out.println("Failed to add into system..");
        } finally{
            end();
        }
    }
}
