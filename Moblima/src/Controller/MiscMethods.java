package Controller;
import Entity.Movie;
import Entity.MovieEnums;
import Entity.TheatreEnums;

import java.util.*;

public class MiscMethods {
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

    public static int readChoice(int i, int j) {
        Scanner sc = new Scanner(System.in);
        int choice;

        try {
            choice = sc.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("Invalid input, try again.");
            sc.nextLine();  // flush scanner
            return readChoice(i, j);
        }

        if (choice < i || choice > j) {
            System.out.println("Invalid input, try again.");
            return readChoice(i, j);
        }
        return choice;
    }

    public static String generateSpaces(int size) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) stringBuilder.append(" ");
        return stringBuilder.toString();
    }

    public static String readString(String... message) {
        for (String m : message) System.out.println(m);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

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

}
