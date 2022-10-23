package Boundary;

public abstract class Boundary {
    public Boundary prev;
    protected abstract void start();
    protected void end(){
        if(prev==null) System.exit(1);
        else prev.start();
    }

    protected void direct(Boundary x, Boundary y){
        y.prev=x;
        y.start();
    }

    protected Boundary getPrev(){
        return prev;
    }
}
