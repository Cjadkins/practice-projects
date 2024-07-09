/* 
* Author - Cort Adkins
*/

package asteroidgame;

import blobzx.Blob;
import blobzx.BlobProximity;


public class Missile extends Blob implements BlobProximity {
    
    private double speed = 5;
    private int diameter = 5;
    
    public Missile(int x, int y, double angle){
        super(0, 0, 0);
        setSize(diameter);
        
        int idx = (int) Math.round(speed * Math.cos(angle));
        int idy = (int) Math.round(speed * Math.sin(angle));
        
        setLoc(x,y);
        setDelta(idx, idy);
    }
}
