/* 
* University of Central Florida
* COP3330 - Fall 2015
* Author - Cort Adkins
*/

package asteroidgame;

import blobzx.BlobUtils;
import blobzx.PolyBlob;
import java.awt.Point;
import java.util.Random;


public class Asteroid extends PolyBlob {

    private static Random random = new Random();

    public Asteroid(int x, int y, double rot) {
        super(-100, -100, rot);
        setDelta(x, y);
        
        //declare variable to shape polygon
        int sides = random.nextInt((11 - 5) + 1) + 5;
        double region = (Math.PI * 2) / sides;
        int vertDist;
        
        //instantiate x y arrays along with array to store angles
        double angle[] = new double[sides]; 
        int[] arrX = new int[sides]; 
        int[] arrY = new int[sides]; 
        
        //get angle for each vertex
        for (int i = 0; i < angle.length; i++){
            angle[i] = (i * region) + (Math.random() * region);
        }
        
        //store points in x and y arrays
        for (int j = 0; j < sides; j++){
            vertDist = random.nextInt((30 - 5) + 1) + 5;
            Point point = BlobUtils.rotatePoint(vertDist, angle[j]);
            arrX[j] = point.x;
            arrY[j] = point.y;
        }
  
        setPolygon(arrX, arrY);
    }
}