/* 
* Author - Cort Adkins
*/

package asteroidgame;

import blobzx.BlobAction;
import blobzx.BlobProximity;
import blobzx.BlobUtils;
import blobzx.PolyBlob;
import blobzx.SandBox;
import java.awt.Point;
import java.awt.event.KeyEvent;


public class Rocket extends PolyBlob implements BlobAction, BlobProximity {
    
    //useful program variables
    private final int x1[] = {10, -10, -3, -10};
    private final int y1[] = {0, -10, 0, 10};
    
    private double angle = 0.0;
    private final double delta = 0.15;
    private final double speed = 5.0;
    
    private final int xRef[] = {10, -10, -3, -10};
    private final int yRef[] = { 0,  -10,  0,   10};
    
    private final SandBox sandbox;
    
    //rocket class constructor
    public Rocket(int xPos, int yPos, SandBox sb){
        super(0,0,0);
        setPolygon( x1, y1 );
        setLoc(xPos, yPos);
        angle = 0; 
        sandbox = sb;
    }
    
    //read in keypress from user and assign a command
    public void keyAction(KeyEvent e){
  
        if (e.getKeyCode() == 39){
            angle += delta;
            if(angle > 0){
                angle -= Math.PI * 2;
            }
            turn();
        }else if (e.getKeyCode() == 37){
            angle -= delta;
            if(angle < 0){
                angle += Math.PI * 2;
            }
            turn();
        }else if (e.getKeyCode() == 38){
            int xPos = getLoc().x;
            int yPos = getLoc().y;
            
            xPos = xPos + (int) Math.round(speed * Math.cos(angle));
            yPos = yPos + (int) Math.round(speed * Math.sin(angle));
            setLoc(xPos, yPos);
        }else if(e.getKeyCode() == 32){
            launch(sandbox);
            BlobUtils.playSound();
        }
    }
    
    private void turn(){
        for(int i = 0; i < 4; i++){
            Point p = BlobUtils.rotatePoint(xRef[i], yRef[i], angle);
            x1[i] = p.x;
            y1[i] = p.y;
        }
    }
    
    public void launch(SandBox sb){
        int radius = (getSize()/2 + 5);
        Point point = BlobUtils.rotatePoint(radius, angle);
        int x  = point.x;
        int y = point.y;
        int xPos = getLoc().x + x;
        int yPos = getLoc().y + y;
        //getDelta();
        Missile miss = new Missile(xPos, yPos, angle);
        sandbox.addBlob(miss);
    }
}
