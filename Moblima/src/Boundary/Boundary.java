package Boundary;


/**
    abstract class representing a Boundary
	For ALL boundary classes to override and extend
    @version 1.0
    @since 2022-10-23
 */

public abstract class Boundary {
    /** previous boundary */
    public Boundary prev;

    /**Abstract method to be implemented at each Boundary */
    protected abstract void start();

    /**
     * to kill the current boundary, return to the previous page
     */
    protected void end(){
        if(prev==null) System.exit(1);
        else prev.start();
    }

    
    /** 
     * method for transition from x to y
     * save y.previous as x
     * so that in getPrev function and return to the prev menu
     * @param x
     * @param y
     */
    protected void direct(Boundary x, Boundary y){
        y.prev=x;
        y.start();
    }

    
    /** 
     * this method returns to the prev menu
     * @return Boundary
     */
    protected Boundary getPrev(){
        return prev;
    }
}
