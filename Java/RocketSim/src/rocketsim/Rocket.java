/* 
* Author - Cort Adkins
*/

package rocketsim;

import blobzx.BlobAction;
import blobzx.BlobUtils;
import blobzx.PolyBlob;
import blobzx.SandBox;
import java.awt.Point;
import java.awt.event.KeyEvent;



public class Rocket extends PolyBlob implements BlobAction {
    
    //useful program variables
    private final int x1[] = {10, -10, -3, -10};
    private final int y1[] = {0, -10, 0, 10};
    private double angle = 0.0;
    private final double delta = 0.15;
    private final double speed = 5.0;
    private int key;
    private final int xRef[] = {10, -10, -3, -10};
    private final int yRef[] = { 0,  -10,  0,   10};
    
    //rocket class constructor
    public Rocket(int xPos, int yPos, SandBox sb){
        super(0,0,0);
        setPolygon( x1, y1 );
        setLoc(xPos, yPos);
    }
    
    //read in keypress from user and assign a command
    public void keyAction(KeyEvent e){
        key = e.getKeyCode();
  
        if (key == 37){
            turn(key, angle, x1, y1);
        }else if (key == 38){
            turn(key, angle, x1, y1);
        }else if (key == 39){
            turn(key, angle, x1, y1);
        }
    }
    
    //method to handle left and right turns
    public double turn(int key, double angle, int[] x, int[] y){
        double newAngle;
        double twoPi = Math.PI * 2;
        
        //left turn
        if (key == 37){
            newAngle = angle - delta;
            if (newAngle < 0)
                newAngle += twoPi;
            
            for(int i = 0; i < x.length; i++){
                Point point = BlobUtils.rotatePoint(x[i], y[i], newAngle);
                x[i] = point.x;
                y[i] = point.y;
                setPolygon(x, y);
            } 
        }
        //forward motion
        else if (key == 38){
            for(int i = 0; i<x.length; i++){
                x[i] = x[i] + (int) Math.round(speed * Math.cos(angle));
                y[i] = y[i] + (int) Math.round(speed * Math.sin(angle));
                setPolygon(x, y);
            }
        }
        //right turn
        else if (key == 39){
            newAngle = angle + delta;
            if (newAngle > twoPi)
                newAngle -= twoPi;
            
            for(int i = 0; i < x.length; i++){
                Point point = BlobUtils.rotatePoint(x[i], y[i], newAngle);
                x[i] = point.x;
                y[i] = point.y;
                setPolygon(x, y);
            }
        }
        return 0;
    }
}
