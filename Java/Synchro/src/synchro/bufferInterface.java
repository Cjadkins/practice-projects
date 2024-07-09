/* 
* Author - Cort Adkins
*/

package synchro;

public interface bufferInterface {
    
    public void put(String value) throws InterruptedException;
    public String get() throws InterruptedException;
}
