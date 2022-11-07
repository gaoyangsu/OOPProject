package Controller;

/**
 This interface is for the respective classes to override the initialise() method
 @version 1.0
 @since 2022-11-03
 */
public interface Initialiser {
    /** abstract method to be overriden amongst the different CRUD classes which implement this interface */
    public boolean initialise();
}
