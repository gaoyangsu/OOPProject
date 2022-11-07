package Controller;
import Entity.Movie;
import Entity.MovieEnums;
import Entity.TheatreEnums;

import java.text.SimpleDateFormat;
import java.util.*;

import static Boundary.Staff.AddScreeningSchedule.dateFormat;

/**
    Represents the miscellaneous controller methods to be used across the entire application.
    @version 1.0
    @since 2022-10-23
 */
public class MiscMethods {
    
    /** 
     * This method is to print multiple Strings, separated by commas
     * @param menu multiple Strings, separated by commas
     */
    public static void printMenu(String... menu) {
        for (String s : menu) {
            System.out.println(s);
        }
    }

    /**
     * This method is to print specified {@code String} to standard output.
     * @param header the header message to be written to standard output
     */
    public static void printHeader(String header) {
        int length = 80;
        for (int i = 0; i < length; i++) System.out.print("#");
        System.out.println();

        int indent = (length - header.length()) / 2;
        for (int i = 0; i < indent; i++) System.out.print(" ");
        System.out.print(header);
        for (int i = 0; i < indent; i++) System.out.print(" ");
        System.out.println();

        for (int i = 0; i < length; i++) System.out.print("#");
        System.out.println();
    }
    
    /** 
     * This method simplifies the reading of Doubles, 
     * By combining display of text messages together an input of double double
     * @param message the message to hint user on what double to input
     * @return double
     */
    public static double readDouble(String... message){
        for (String m : message) System.out.println(m);
        Scanner sc = new Scanner(System.in);
        return sc.nextDouble();
    }
    
    
    /** 
     * A method to enter a particular int from a range between i to j
     * @param i the lower threshold of allowable int to be entered
     * @param j the upper threshold of allowable int to be entered
     * @return int as a choice 
     */
    public static int readChoice(int i, int j) {
        Scanner sc = new Scanner(System.in);
        int choice;

        try {
            choice = sc.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("You have entered an invalid choice, please try again.");
            sc.nextLine();  // flush scanner
            return readChoice(i, j);
        }

        if (choice < i || choice > j) {
            System.out.println("You have entered an invalid choice, please try again.");
            return readChoice(i, j);
        }
        return choice;
    }

    
    /** 
     * A method to generate spaces between Strings
     * @param size of the String
     * @return String of the space generated
     */
    public static String generateSpaces(int size) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) stringBuilder.append(" ");
        return stringBuilder.toString();
    }

    
    /** 
     * This method simplifies the reading of Strings, 
     * By combining display of text messages together an input of a String
     * @param message the String message to hint the user on what to type in
     * @return String the message to type in
     */
    public static String readString(String... message) {
        for (String m : message) System.out.println(m);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
    
    
    /** 
     * This method simplifies the reading of characters.
     * @return char
     */
    public static char readCharacter() {
        Scanner sc = new Scanner(System.in);
        return Character.toUpperCase(sc.next().charAt(0));
    }

    
    /** 
     * This method is to map the {@code String} to respective {@code AgeAdvisory}.
     * @param input the {@code String} to be mapped
     * @return the {@code AgeAdvisory} the input mapped to
     */
    public static MovieEnums.AgeAdvisory readAgeAdvisory(String input) {
        switch (input.toUpperCase()) {
            case "G":
                return MovieEnums.AgeAdvisory.G;
            case "PG":
                return MovieEnums.AgeAdvisory.PG;
            case "PG13":
                return MovieEnums.AgeAdvisory.PG13;
            case "NC16":
                return MovieEnums.AgeAdvisory.NC16;
            case "M18":
                return MovieEnums.AgeAdvisory.M18;
            case "R21":
                return MovieEnums.AgeAdvisory.R21;
            default:
                return null;
        }
    }

    /**
     * This method is to map the {@code String} to respective {@code MovieStatus}.
     * @param input the {@code String} to be mapped
     * @return the {@code MovieStatus} the input mapped to
     */
    public static MovieEnums.MovieStatus readMovieStatus(String input) {
        switch (input.toUpperCase()) {
            case "COMING SOON":
                return MovieEnums.MovieStatus.COMING_SOON;
            case "NOW SHOWING":
                return MovieEnums.MovieStatus.NOW_SHOWING;
            case "END OF SHOWING":
                return MovieEnums.MovieStatus.END_OF_SHOWING;
            case "PREVIEW":
                return MovieEnums.MovieStatus.PREVIEW;
            default:
                return null;
        }
    }

    
    /** 
     * This method is to map the {@code String} to respective {@code Cineplex}.
     * @param input the {@code String} to be mapped
     * @return the {@code Cineplex} the input mapped to
     */
    public static TheatreEnums.Cineplex readCineplex(String input) {
        switch (input.toUpperCase()) {
            case "GV":
                return TheatreEnums.Cineplex.GV;
            case "CATHAY":
                return TheatreEnums.Cineplex.CATHAY;
            case "FILMGARDE":
                return TheatreEnums.Cineplex.FILMGARDE;
            default:
                return null;
        }
    }


    
    /** 
     * This method is to map the {@code String} to respective {@code CinemaLocation}.
     * @param input the {@code String} to be mapped
     * @return the {@code CinemaLocation} the input mapped to
     */
    public static TheatreEnums.CinemaLocation readLocation(String input) {
        switch (input.toUpperCase()) {
            case "JURONG EAST":
                return TheatreEnums.CinemaLocation.JURONG_EAST;
            case "BUGIS":
                return TheatreEnums.CinemaLocation.BUGIS;
            case "WOODLANDS":
                return TheatreEnums.CinemaLocation.WOODLANDS;
            default:
                return null;
        }
    }


    
    /** 
     * This method is to map the {@code String} to respective {@code TheatreClass}.
     * @param input the {@code String} to be mapped
     * @return the {@code TheatreClass} the input mapped to
     */
    public static TheatreEnums.TheatreClass readTheatreClass(String input) {
        switch (input.toUpperCase()) {
            case "PLATINUM SUITES":
                return TheatreEnums.TheatreClass.PLATINUM_SUITES;
            case "ELITE CLUB SEATS":
                return TheatreEnums.TheatreClass.ELITE_CLUB_SEATS;
            case "ULTIMA SEATS":
                return TheatreEnums.TheatreClass.ULTIMA_SEATS;
            case "DOLBY ATMOS":
                return TheatreEnums.TheatreClass.DOLBY_ATMOS;
            case "GOLD CLASS":
                return TheatreEnums.TheatreClass.GOLD_CLASS;
            case "NORMAL":
                return TheatreEnums.TheatreClass.NORMAL;
            default:
                return null;
        }
    }

    
    /** 
     * This method is to add line breaks based on {@code lengthOfSpace} and to 
     * add new line based on {@code maxLineLength}
     * @param input the original {@code String} to display
     * @param maxLineLength the max num of chars per line, if exceed, \n into new line
     * @param lengthOfSpace the empty spaces added from the {@code String}
     * @return the updated {@code String} with line breaks
     */
    public static String addLinebreaks(String input, int maxLineLength, int lengthOfSpace) {
        StringTokenizer tok = new StringTokenizer(input, " ");
        StringBuilder output = new StringBuilder(input.length());
        int lineLen = 0;
        while (tok.hasMoreTokens()) {
            String word = tok.nextToken();

            if (lineLen + word.length() > maxLineLength) {
                output.append("\n");
                for (int i = 0; i < lengthOfSpace; i++) output.append(" ");
                lineLen = 0;
            }
            output.append(word).append(" ");
            lineLen += word.length();
        }
        return output.toString();
    }

    
    /** 
     * This method parses a date {@code String} into the {@code Date} class format
     * @return a {@code Date} class
     */
    public static Date dateInput(){
        System.out.println();
        System.out.println("    _/_/_/          _/_/    _/_/_/_/_/     _/_/_/_/");
        System.out.println("   _/    _/      _/    _/      _/         _/");
        System.out.println("  _/    _/      _/_/_/_/      _/         _/_/_/");
        System.out.println(" _/    _/      _/    _/      _/         _/");
        System.out.println("_/_/_/        _/    _/      _/         _/_/_/_/");

                Date toReturnDate=null;
        boolean dateInputChecked=false;
        while (!dateInputChecked) {
            String date = enterDateString();
            try {
                toReturnDate = dateFormat.parse(date);
                dateInputChecked=true;

            } catch (Exception e) {
                System.out.println("UNABLE TO CONVERT DATE! Please enter again!");
            }
        }
        return toReturnDate;
    }
    
    /** 
     * {@code dateInput()} calls this method for the user to enter {@code String} of date
     * @return String
     */
    //convert String object into Date() object;

    public static String enterDateString() {
        String year, month, day, time;
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter Year(e.g. 2022): ");
        year = sc.nextLine();
        System.out.println("Enter Month(e.g. 10 for October): ");
        month = sc.nextLine();
        System.out.println("Enter Day(e.g. 01 for first day of month): ");
        day= sc.nextLine();
        System.out.println("Enter Time in HH:MM(e.g. 12:00)");
        time= sc.nextLine();

        String concat= year+"-"+month+"-"+day+"T"+time+":00";
        return concat;
    }

    
    /** 
     * This function converts a {@code Date} class into a SimpleDateFormat String
     * @param date the input {@code Date} class
     * @return the converted {@code Date} into a {@code String}
     */
    public static String dateOutput(Date date){
        SimpleDateFormat dateForm= new SimpleDateFormat("yyyy-MM-dd' T 'HH:mm:ss");
        return dateForm.format(date);
    }

    //Convert Date() object into String for Viewing on the console

    
}
