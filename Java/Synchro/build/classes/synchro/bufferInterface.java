/* 
* University of Central Florida
* COP3330 - Fall 2015
* Author - Cort Adkins
*/

package synchro;

public interface bufferInterface {
    
    public void put(String value) throws InterruptedException;
    public String get() throws InterruptedException;
}
