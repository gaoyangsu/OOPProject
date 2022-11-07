package Controller;
import java.io.*;

/**
   Represents the main Controller Class for reading/writing the respective .dat files
   @version 1.0
   @since   2022-10-25
 */
public class RWController {

    
    /** 
     * Reads a particular .dat file from the filepath
     * @param filePath the source file location to read the .dat files
     * @return the object type (different types of ArrayLists, Hashmaps and Classes)
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object serialisedRead(String filePath) throws IOException, ClassNotFoundException {
        Object data;
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;
        try {
            fileInputStream = new FileInputStream(filePath);
            objectInputStream = new ObjectInputStream(fileInputStream);
            data = objectInputStream.readObject();
            objectInputStream.close();
        }
        catch (EOFException i){
            i.printStackTrace();
            System.out.println(i);
            return null;
        }
        return data;
    }


    
    /** 
     * Writes a particular .dat file into the filepath.
     * @param filePath the source file location to write the .dat files
     * @param data
     * @throws IOException
     */
    protected static void serialisedWrite (String filePath, Object data) throws IOException {
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;

        fileOutputStream = new FileOutputStream(filePath);
        objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(data);
        objectOutputStream.close();
    }
}
