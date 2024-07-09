//This program takes an image specified in the path below and converts into a solvable puzzle. When the puzzle is solved, the user
//is rewarded with a victory message and the puzzle is shuffled randomly when the message is closed. 
package puzzle;

import java.awt.Toolkit;
import java.awt.Point;
import javax.swing.JFrame;
import java.awt.Dimension;

public class HW1_9557 {
	
	public static void main(String[] args) {
		
		JEightPuzzleFrame puzzle = new JEightPuzzleFrame("FGCU Puzzle", "C:\\Users\\Cort\\workspace\\puzzle\\bin\\FGCU_logo.png" );
		puzzle.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
		Point size = new Point(windowSize.width / 2, windowSize.height / 3);
		Point location = new Point(size.x - (puzzle.getWidth() / 2), size.y - (puzzle.getHeight() / 2));
		puzzle.initialize();
		puzzle.setLocation(location);
		puzzle.setVisible(true);
	}

}
