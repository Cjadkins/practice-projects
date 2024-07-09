/* 
* University of Central Florida
* COP3330 - Fall 2015
* Author - Cort Adkins
*/

package synchro;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;

public class Consumer implements Runnable{
    
    private static final SecureRandom generator = new SecureRandom();
    private final bufferInterface sharedLocation;
    private final String Sentinel = "@@@";
    private ArrayList<String> wordList = new ArrayList<String>();
    private int count = 0;
    
    //consumer only needs shared location as parameter
    public Consumer(bufferInterface sharedLocation){
        this.sharedLocation = sharedLocation;
    }
    
    //consumer run method gets parsed words from shared location
    //also adds words to array list for use at end of program
    //stops running when producer sends '@@@'
    public void run(){
        String n = null;
        do{
            try{
                int waitTime = generator.nextInt(1000);
                Thread.sleep(waitTime);
                n = sharedLocation.get();
                wordList.add(n);
                count+= waitTime;
            }
            catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }while(n != Sentinel);
        
        //print final summary and wait time to system
        String listString = String.join(" ", wordList);
        System.out.println("Consumer Summary: "+ listString);
        System.out.println("Total wait time: "+count+" milliseconds");
    }
}
