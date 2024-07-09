/* 
* University of Central Florida
* COP3330 - Fall 2015
* Author - Cort Adkins
*/

package synchro;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Scanner;

public class Producer implements Runnable{
    
    private static final SecureRandom generator = new SecureRandom();
    private final bufferInterface sharedLocation;
    private final File file;
    private final String Sentinel = "@@@";
    private ArrayList<String> wordList = new ArrayList<String>();
    private int count = 0;
    
    //producer constructor takes file and shared location as input params
    public Producer(final File inputFile, bufferInterface sharedLocation){
        this.sharedLocation = sharedLocation;
        file = inputFile;
    }
    
    //run method parses each word from file to send to consumer using scanner
    //then adds each word to array list for use later
    public void run(){
        String next = null;
        try(Scanner in = new Scanner(new FileReader(file))){
            while (in.hasNext()) {
                int waitTime = generator.nextInt(1000);
                Thread.sleep(waitTime);
                next = in.next();
                sharedLocation.put(next);
                count+= waitTime;
                wordList.add(next);
            }
            sharedLocation.put(Sentinel);
            wordList.add(Sentinel);
        }
        catch (FileNotFoundException ex) {
            System.err.printf("Error finding File%n%s%n", ex);
        }
        catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }
        
        //print final summary and wait time
        String listString = String.join(" ", wordList);
        System.out.println("Producer Summary: "+ listString);
        System.out.println("Total wait time: "+count+" milliseconds\n");
    }
}
