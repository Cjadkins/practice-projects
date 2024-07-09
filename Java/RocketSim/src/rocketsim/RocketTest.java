/* 
* Author - Cort Adkins
*/

package rocketsim;

import blobzx.BlobGUI;
import blobzx.SandBox;
import blobzx.SandBoxMode;

public class RocketTest implements BlobGUI{
    
    public static SandBox sandbox = new SandBox();
    
    //instantiate rocket class in main method
    public static void main(String[] args) {
        new RocketTest();
    }
    
    //initialize sandbox 
    public RocketTest(){
        sandbox.setSandBoxMode(SandBoxMode.FLOW);
        sandbox.setFrameRate(15);
        sandbox.init(this);
    }
    
    //add rocket ship to the sandbox 
    public void generate(){ 
        Rocket rocket = new Rocket(300, 300, sandbox);
        sandbox.addBlob(rocket);
    } 
}
