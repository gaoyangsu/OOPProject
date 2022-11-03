package Boundary;

import java.io.Console;

import Entity.Movie;

public class SupportFunctions {
    public static void clearScreen() {   
        System.out.print("\033[H\033[2J");   
        System.out.flush();   
    }

    public static void printMoblima(){

        System.out.println(",--.   ,--.  ,-----.  ,-----.   ,--.    ,--. ,--.   ,--.   ,--- . ");
        System.out.println("|   `.'   | '  .-.  ' |  |) /_  |  |    |  | |   `.'    |  /  O   ] ");
        System.out.println("|  |'.'|  | |  | |  | |  .-.    |  |    |  | |  |'.'|   | |  .-.   | ");
        System.out.println("|  |   |  | '  '-'  ' |  '--' / |  '--. |  | |  |   |   | |  | |   | ");
        System.out.println("`--'   `--'  `-----'  `------'  `-----' `--' `--'   `---' `--' `---' ");
    }


}



