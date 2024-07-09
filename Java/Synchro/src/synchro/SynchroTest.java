/* 
* Author - Cort Adkins
*/

package synchro;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class SynchroTest {

    //class variables to store information related to file and dialog boxes
    public static JFileChooser fileChooser = new JFileChooser();
    public static BufferedReader br = null;
    public static File inputFile = null;
    public static int length;
    public static String currentLine;

    public static void main(String[] args) throws InterruptedException {

        //set default directory for file chooser and open dialog box
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(fileChooser);
        if (result == JFileChooser.APPROVE_OPTION) {
            inputFile = fileChooser.getSelectedFile();
            try {
                br = new BufferedReader(new FileReader(inputFile));
                while ((currentLine = br.readLine()) != null) {
                    JOptionPane.showMessageDialog(null, "Input Sentence:\n" + currentLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //print header to system
        System.out.println("COP 3330 (Fall 2015) Program 8: Synchronized Buffer"
                + "\nAuthor: Cort Adkins\n");
        System.out.printf("%-30s\t\t%s\t\t%s%n%s%n%n", "Operation",
                "Buffer", "Occupied", "---------\t\t\t\t------\t\t--------");

        //start buffer interface and memory for threads
        bufferInterface sharedLocation = new SynchronizedBuffer();
        ExecutorService executorService = Executors.newCachedThreadPool();

        //start the producer and consumer threads
        executorService.execute(new Producer(inputFile, sharedLocation));
        executorService.execute(new Consumer(sharedLocation));

        //terminate threads when finished
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MILLISECONDS);

    }
}
