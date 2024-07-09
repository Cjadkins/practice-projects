/* 
* University of Central Florida
* COP3330 - Fall 2015
* Author - Cort Adkins
*/

package synchro;

public class SynchronizedBuffer implements bufferInterface {
    
    private String buffer = null;
    private boolean occupied = false;
    int i = 0;
    int j = 0;
    
    @Override
    public synchronized void put(String value) throws InterruptedException{
        
        while(occupied){
            displayState("Producer wait #"+ ++i + "\t\t\t"+buffer+"\t\t"+occupied);
            System.out.println();
            wait();
        }
        buffer = value;
        occupied = true;
        displayState("Producer writes: " +buffer);
        System.out.println();
        notifyAll();
    }
    
    @Override
    public synchronized String get() throws InterruptedException{
        
        while(!occupied){
            if(buffer == null){
                buffer = "NIL";
            }
            displayState("Consumer wait #" + ++j + "\t\t\t"+buffer+"\t\t"+occupied);
            System.out.println();
            wait();
        }
        
        occupied = false;
        displayState("Consumer reads: "+buffer);
        System.out.println();
        notifyAll();
        return buffer;
    }
    
    private synchronized void displayState(String operation){
        System.out.printf("%-40s%n", operation);
    }
}