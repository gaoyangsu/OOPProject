package Boundary;

import java.io.Console;

import Entity.Movie;

/**
    Some support functions for the Boundary Classes
    @version 1.0
    @since 2022-10-23
 */
public class SupportFunctions {
    /** method to clearScreen to prevent an overload of content on the console */
    public static void clearScreen() {   
        System.out.print("\033[H\033[2J");   
        System.out.flush();   
    }

    /** method to print app icon */
    public static void printMoblima(){

        System.out.println(",--.   ,--.  ,-----.  ,-----.   ,--.    ,--. ,--.   ,--.    ,--- . ");
        System.out.println("|   `.'   | '  .-.  ' |  |) /_  |  |    |  | |   `.'    |  /  O   ] ");
        System.out.println("|  |'.'|  | |  | |  | |  .-.    |  |    |  | |  |'.'|   | |  .-.   | ");
        System.out.println("|  |   |  | '  '-'  ' |  '--' / |  '--. |  | |  |   |   | |  | |   | ");
        System.out.println("`--'   `--'  `-----'  `------'  `-----' `--' `--'   `---' `--' `---' ");
    }


}



