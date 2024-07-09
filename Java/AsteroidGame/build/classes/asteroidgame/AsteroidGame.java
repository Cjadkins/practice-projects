/* 
* University of Central Florida
* COP3330 - Fall 2015
* Author - Cort Adkins
*/

package asteroidgame;

import blobzx.BlobGUI;
import blobzx.SandBox;
import blobzx.SandBoxMode;
import java.awt.Dimension;
import java.util.Random;


public class AsteroidGame implements BlobGUI {
    
    private static Random random = new Random();
    public static SandBox sandbox = new SandBox();
    
    public static void main(String[] args) {
        new AsteroidGame();
    }
    
    public AsteroidGame(){
        sandbox.setSandBoxMode(SandBoxMode.FLOW);
        sandbox.setFrameRate(15);
        sandbox.init(this);
    }
    
    @Override
    public void generate(){
        int x, y;
        double rot;
        double max = 0.1;
        double min = -0.1;
        
        //add rocket
        Dimension dim = sandbox.getPanelBounds();
        sandbox.addBlob(new Rocket(dim.width/2, dim.height/2, sandbox));
        
        //add asteroids
        for(int i = 0; i < 10; i++){
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
