package Boundary.Staff;
import Boundary.Boundary;
import Boundary.SupportFunctions;
import Entity.*;
import static Controller.CRUDMovies.*;
import static Controller.CRUDTheatre.*;
import static Controller.MiscMethods.*;
import static Boundary.SupportFunctions.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
    Boundary/View Class representing a the Moviegoer main after logging in as member
    or choose to book ticket as a guest
    @version 1.0
    @since 2022-10-23
 */
public class ModifyTheatreBoundary extends Boundary {

    /**
     * overriden start method from Boundary abstract class
     */
    @Override
    protected void start() {
        display();
    }

    /** this method displays the cineplexs to choose from,
     * then after the user chooses a specific cineplex,
     * displayCinemaList would be called based on the chosen cineplex
     */
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

    
    /** 
     * This method displays a list of theatres pertaining to a specific Cineplex
     * and shows a menu to either add or remove theatres 
     * @param cineplex
     */
    public void displayCinemaList(TheatreEnums.Cineplex cineplex){
        Scanner sc= new Scanner(System.in);
        ArrayList<Theatre> theatres = retrieveTheatreList(cineplex);
        if (theatres == null) {
            System.out.println("No Theatres Found");
            System.out.println("1. Add Theatres in "+cineplex.toString());
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
            System.out.println("1. Add Theatres in "+cineplex.toString());
            System.out.println("2. Remove Theatres in "+cineplex.toString());
            System.out.println("3. Return ");
            int choice = readChoice(1, 3);
            if (choice ==1) addTheatres(cineplex);
            if (choice ==2) removeTheatres(cineplex);
            else if (choice == 3) display();
        }
    }

    
    /** 
     * This method is called by displayCinemaList() to add a theatre into the list
     * @param cineplex
     */
    public void addTheatres(TheatreEnums.Cineplex cineplex){

        boolean is3D;
        TheatreEnums.CinemaLocation location= null;
        TheatreEnums.TheatreClass theatreClass= null;

        printHeader("addTheatres");

        Scanner sc= new Scanner (System.in);

        while(location==null) {
            String input = readString("Where is the location of the Theatre? ",
                    "Type Jurong East, Woodlands, Bugis").toUpperCase();
            location = readLocation(input);
        }
        while(theatreClass==null) {
            String input2 = readString("What is the class of the Theatre?",
                    "Type Platinum Suites, Elite Club seats, Ultima seats",
                    "Dolby Atmos, Gold Class, normal ").toUpperCase();
            theatreClass = readTheatreClass(input2);
        }

        System.out.println("is the Theatre 3D? 1- yes, 0- no");
        int choice=  readChoice(0,1);

        if (choice ==0 ) is3D = false;
        else  is3D = true;

        System.out.println("Enter a 3 Character code name of Theatre: ");
        String codeName= sc.nextLine().toUpperCase();

        Theatre toAppend = new Theatre(is3D, theatreClass, cineplex, location, codeName);

        try{
            addTheatreIntoList(toAppend);
            System.out.println("Added successfully!");
        } catch(IOException i){
            i.printStackTrace();
            System.out.println("Failed to add into system.");
        } finally{
            end();
        }
    }

    
    /** 
     * This method is called by removeTheatres() to remove a theatre into the list
     * @param cineplex
     */
    public void removeTheatres(TheatreEnums.Cineplex cineplex) {

        System.out.println("Enter the 3 Character code name of the Theatre to be removed: ");
        Scanner sc= new Scanner (System.in);
        String codeName= sc.nextLine();
        
        for (Theatre theatre: retrieveTheatreList(cineplex)){
            if(theatre.getCode().equals(codeName)){
                try{
                    removeTheatreFromList(theatre);
                    System.out.println("Removed successfully!");
                } 
                catch(IOException i){
                    System.out.println("Failed to remove the Theatre.");
                } 
                finally{
                    clearScreen();
                    end();
                }
            }
        }

    }

}
