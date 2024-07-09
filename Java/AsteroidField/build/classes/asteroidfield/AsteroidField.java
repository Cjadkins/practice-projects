/* 
* University of Central Florida
* COP3330 - Fall 2015
* Author - Cort Adkins
*/
package asteroidfield;

import blobzx.BlobGUI;
import blobzx.SandBox;
import blobzx.SandBoxMode;
import java.util.Random;


public class AsteroidField implements BlobGUI {
    
    private static Random random = new Random();
    public static SandBox sandbox = new SandBox();
    
    public static void main(String[] args){       
        AsteroidField asteroidField = new AsteroidField();
    }
    
    //asteroid field constructor
    public AsteroidField(){
        sandbox.setSandBoxMode(SandBoxMode.FLOW);
        sandbox.setFrameRate(15);
        sandbox.init(this);
    }

    //method to generate 20 asteroid blobs
    public void generate(){ 
        int x, y;
        double rot;
        double max = 0.1;
        double min = -0.1;
        
        for(int i = 0; i < 20; i++){
            x = (random.nextInt(7) - 3);
            y = (random.nextInt(7) - 3);
            rot = (Math.random() * ((max - min) + 0.1)) + min;
            while(rot == 0.0){
                rot = (Math.random() * ((max - min) + 0.1)) + min;
            }
            sandbox.addBlob(new Asteroid(x, y, rot));
        }
    }
}