package Controller;
import Entity.Movie;
import Entity.Review;
import Entity.SystemSettings;
import Entity.Theatre;
import Entity.TheatreEnums;
import Entity.Admin;
import Entity.Holiday;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

import static Controller.RWController.*;

/**
 A class of CRUD operations on the admin.dat, settings.dat and holiday.dat file
 Calls RWController to read and write data into .dat file
 @version 1.0
 @since 2022-10-30
 */
public class AdminController implements Initialiser {
    /**
     * {@code String } denoting the location of the admin.dat file
     */
    private static final String ADMIN_FILE = "Data/admin.dat";

    /**
     * {@code ArrayList<Admin>} denoting the list of {@code Admin} classes 
     * while the admin.dat is being read
     */

    private static ArrayList<Admin> listofAdmins;
    /**
     * {@code String } denoting the location of the setting.dat file
     */

    private static final String SETTINGS_FILE = "Data/settings.dat";

     /**
     * denoting the {@code SystemSetting} class
     * while the settings.dat is being read
     */
    private static SystemSettings systemSetting;

    /**
     * {@code String } denoting the location of the holiday.dat file
     */
    private static final String HOLIDAY_FILE = "Data/holiday.dat";

    /**
     * denoting the list of {@code Holiday} classes
     * while the holiday.dat is being read
     */
    private static ArrayList<Holiday> holidays;
//    public static boolean CRUDTheatresInitialise() {
//        try {
//            readTheatreList();
//        } catch (IOException ex) {
//            return false;
//        } catch (ClassNotFoundException ex) {
//            return true;
//        }
//
//        return true;
//    }

      /** 
     * This method calls the RWController, to read the file, and put them into {@code holidays} ArrayList.
     * THIS READ FUNCTION HAS TO BE USED EVERYTIME THE PROGRAMME STARTS
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void readHolidays() throws IOException, ClassNotFoundException {
        if (serialisedRead(HOLIDAY_FILE) == null) holidays = new ArrayList<>();
        else holidays = (ArrayList<Holiday>) serialisedRead(HOLIDAY_FILE);
    }
    
    /** 
     * This method updates/overwrites the holiday.dat file.
     * @throws IOException
     */
    public static void updateHolidays() throws IOException {
        serialisedWrite(HOLIDAY_FILE, holidays);
    }
    
    /** 
     * This method gets the ArrayList of Holidays previous being created in
     * the readHolidays() function.
     * @return ArrayList<Holiday>
     */
    public static ArrayList<Holiday> retrieveHolidays() {
        return holidays;
    }
    
    /** 
     * This method adds a {@code Holiday} class into the list of {@code holidays} ArrayList.
     * @param holiday denotes the Holiday class to be added
     * @throws IOException
     */
    public static void addHoliday(Holiday holiday) throws IOException {
        holidays.add(holiday);
        updateHolidays();
    }
    
    
    /** 
     * This method removes a {@code Holiday} class from the {@code holidays} ArrayList.
     * @param holiday the holiday to be removed
     * @throws IOException
     */
    public static void removeHoliday(Holiday holiday) throws IOException{
        holidays.remove(holiday);
        updateHolidays();
    }
    
    /** 
     * This method calls the RWController, to read the file, and put them into {@code systemSetting} Class.
     * THIS READ FUNCTION HAS TO BE USED EVERYTIME THE PROGRAMME STARTS
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void readSystemSettings() throws IOException, ClassNotFoundException {
        if (serialisedRead(SETTINGS_FILE) == null) systemSetting = new SystemSettings(2.50, 1.50, 4.50, 3, 8.50, 2.50, 1, 1);
        else systemSetting = (SystemSettings) serialisedRead(SETTINGS_FILE);
    }
    
    /** 
     * This method updates/overwrites the settings.dat file.
     * @throws IOException
     */
    public static void updateSystemSettings() throws IOException {
        serialisedWrite(SETTINGS_FILE, systemSetting);
    }
    
    /** 
     * This method gets the {@code SystemSettings} class previous being created in
     * the readSystemSettings() function
     * @return SystemSetting
     */
    public static SystemSettings retrieveSystemSettings() {
        return systemSetting;
    }
    
    /** 
     * This method calls the RWController, to read the file, and put them into {@code listOfAdmins} Hashmap.
     * THIS READ FUNCTION HAS TO BE USED EVERYTIME THE PROGRAMME STARTS
     * @throws IOException
     * @throws ClassNotFoundException
     */
    //THIS READ FUNCTION HAS TO BE USED EVERYTIME THE PROGRAMME STARTS
    public static void readAdminList() throws IOException, ClassNotFoundException {
        if (serialisedRead(ADMIN_FILE) == null) listofAdmins = new ArrayList<>();
        else listofAdmins = (ArrayList<Admin>) serialisedRead(ADMIN_FILE);
    }

    
    /** 
     * This method updates/overwrites the admin.dat file.
     * @throws IOException
     */
    public static void updateAdminList() throws IOException {
        serialisedWrite(ADMIN_FILE, listofAdmins);
    }

    
    /** 
     * This method gets the ArrayList of {@code Admin} Classes previous being created in
     * the readTheatreList() function
     * @return ArrayList<Admin>
     */
    //AFTER READING FROM FILE, THE PROGRAMME CAN THEN RETRIEVE THE HASHMAP DATA
    public static ArrayList<Admin> retrieveAdminList() {
        return listofAdmins;
    }
    
    /** 
     * This method adds an {@code Admin} class into the {@code listOfAdmins} ArrayList.
     * @param admin
     * @throws IOException
     */
    public static void addAdminIntoList(Admin admin) throws IOException {
        listofAdmins.add(admin);
        updateAdminList();

    }

    
    /** 
     * This method removes an {@code Admin} class from the {@code listOfAdmins} ArrayList.
     * @param admin
     * @throws IOException
     */
    public static void removeAdminFromList(Admin admin) throws IOException{
        listofAdmins.remove(admin);
        updateAdminList();
    }
    /** 
     * This method intialises the .dat files and place all of them 
     * in runtime during execution of the java file
     * @return boolean true to denote all the .dat files are properly read
     * @return false to denote file integrity issues
     */
    @Override
    public boolean initialise(){
        try{readHolidays();
        readAdminList();
        readSystemSettings();
        return true;
    } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        } catch (ClassNotFoundException ex) {
            return true;
        }
    }
}
