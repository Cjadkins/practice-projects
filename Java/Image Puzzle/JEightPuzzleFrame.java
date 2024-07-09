package puzzle;

import javax.swing.JFrame;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Component;

public class JEightPuzzleFrame extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel[] = new JPanel[9];

	private String pathToImage;
	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton button6;
	private JButton button7;
	private JButton button8;
	
	private int width, height;	
	private int windowWidth, windowHeight;
	
	HashMap<String, Component> componentMap;
	Icon icon;

	JEightPuzzleFrame( String title, String path){
		
		this.pathToImage = path;	
		this.setWindowSize();
		this.setTitle(title);
		this.setResizable(false);
		setLayout(null);
		this.getContentPane().setPreferredSize(new Dimension(windowWidth, windowHeight));
		this.pack();
		createComponentMap();	
	}
	
	//sets size of puzzle window
	void setWindowSize(){
	
		BufferedImage image = null;
	    try{
	    	System.out.println();
	    	image = ImageIO.read( new File(pathToImage));
	    }catch(IOException e){
	    	System.out.println(e.getMessage());
	    }
	    windowWidth  = image.getWidth();
	    windowHeight = image.getHeight();
	    
	    width  = (int)windowWidth/3;
	    height = (int)windowHeight/3;
	       
	}
	
	//Creates icons out of existing image
	private Icon extractIcon(String path, int X, int Y ){
		
		BufferedImage image = null;
		try{
			image = ImageIO.read(new File(path));
		}catch(IOException e){
			System.err.println("Image not found");
			System.exit(1);
		}
		BufferedImage part = new BufferedImage(	width, height, BufferedImage.TYPE_4BYTE_ABGR );
		for(int i = 0; i < width; i++){
			for(int j = 0; j < height; j++){
				part.setRGB( i, j, image.getRGB( i+X, j+Y ));
			}
		}

		ImageIcon icon = new ImageIcon();
		icon.setImage(part);

		return icon;
	}

	//Places icons created from image in specified location in java frame
	private void createComponentMap() {
        HashMap<String, Component> componentMap = new HashMap<String,Component>();
        Component[] components = this.getContentPane().getComponents();
        for (int i = 0; i < components.length; i++) {
            componentMap.put(components[i].getName(), components[i]);
        }
	}
	
	public Component getComponentByName(String name) {
        if (componentMap.containsKey(name)) {
            return (Component) componentMap.get(name);
        }
        else 
        	return null;
	}
	//Initializes each button to create the puzzle
	public void initialize(){
		//First button
		icon = extractIcon( pathToImage, 0, 0 );
		button1 = new JButton(icon);
		button1.setPreferredSize(new Dimension(width, height));
        button1.addActionListener(this);      	
		
		panel[0] = new JPanel();
		panel[0].setSize(width, height);
		panel[0].setLocation(width, 0);
		panel[0].setLayout(new BorderLayout( 0, 0 ));
		panel[0].add(button1);

	
		//Second button
		icon = extractIcon( pathToImage, width, 0 ); 
		button2 = new JButton(icon);
		button2.setPreferredSize(new Dimension(width, height));
        button2.addActionListener(this);      	
		
		panel[1] = new JPanel();
		panel[1].setSize(width, height);
		panel[1].setLocation(2*width, 0);
		panel[1].setLayout(new BorderLayout( 0, 0 ));
		panel[1].add(button2);

		//Third button
		icon = extractIcon( pathToImage, 2*width, 0 ); 
		button3 = new JButton(icon);
		button3.setPreferredSize(new Dimension(width, height));
        button3.addActionListener(this);      	

        panel[2] = new JPanel();
        panel[2].setSize(width, height);
        panel[2].setLocation(2*width, height);
        panel[2].setLayout(new BorderLayout( 0, 0 ));
        panel[2].add(button3);
		
		//Fourth button
		icon = extractIcon( pathToImage, 0, height );
		button4 = new JButton(icon); 
		button4.setPreferredSize(new Dimension(width, height));
        button4.addActionListener(this);      	

        panel[3] = new JPanel();
        panel[3].setSize(width, height);
        panel[3].setLocation(0, 2*height);
        panel[3].setLayout(new BorderLayout( 0, 0 ));
        panel[3].add(button4);
		
		//Fifth button
		icon = extractIcon( pathToImage, width, height );
		button5 = new JButton(icon);
		button5.setPreferredSize(new Dimension(width, height));
        button5.addActionListener(this);      	

        panel[4] = new JPanel();
        panel[4].setSize(width, height);
        panel[4].setLocation(0, height);
        panel[4].setLayout(new BorderLayout( 0, 0 ));
        panel[4].add(button5);
		
        //Sixth button
		icon = extractIcon( pathToImage, 2*width, height );
		button6 = new JButton(icon);
		button6.setPreferredSize(new Dimension(width, height));
        button6.addActionListener(this);      	

		panel[5] = new JPanel();
		panel[5].setSize(width, height);
		panel[5].setLocation(width, height);
		panel[5].setLayout(new BorderLayout( 0, 0 ));
		panel[5].add(button6);
		
		//Seventh button
		icon = extractIcon( pathToImage, 0, 2*height );
		button7 = new JButton(icon);
		button7.setPreferredSize(new Dimension(width, height));
        button7.addActionListener(this);      	

		panel[6] = new JPanel();
		panel[6].setSize(width, height);
		panel[6].setLocation(width, 2*height);
		panel[6].setLayout(new BorderLayout( 0, 0 ));
		panel[6].add(button7);
		
		//Eighth button
		icon = extractIcon( pathToImage, width, 2*height );
		button8 = new JButton(icon);
		button8.setPreferredSize(new Dimension(width, height));
        button8.addActionListener(this);      	
		
		panel[7] = new JPanel();
		panel[7].setSize(width, height);
		panel[7].setLocation(2*width, 2*height);
		panel[7].setLayout(new BorderLayout( 0, 0 ));
		panel[7].add(button8);
		
		//Ninth button
		panel[8] = new JPanel();
		panel[8].setSize(width, height);
		panel[8].setLocation(0, 0);

		this.add(panel[0]);		
		this.add(panel[1]);
		this.add(panel[2]);
		this.add(panel[3]);
		this.add(panel[4]);
		this.add(panel[5]);
		this.add(panel[6]);
		this.add(panel[7]);
		this.add(panel[8]);
	}
	
	//Shuffles the buttons to create a puzzle
	public void shuffle(){
	
		ArrayList<JPanel> al = new ArrayList<JPanel>();
		for( int i = 0; i < panel.length; i++ ) 
			al.add( panel[i] ); 
	
		Collections.shuffle(al);

		al.get(0).setLocation(0, 0);
		al.get(1).setLocation(width, 0);
		al.get(2).setLocation(2*width, 0);
		al.get(3).setLocation(0, height);
		al.get(4).setLocation(width, height);
		al.get(5).setLocation(2*width, height);
		al.get(6).setLocation(0, 2*height);
		al.get(7).setLocation(width, 2*height);
		al.get(8).setLocation(2*width, 2*height);

		this.getContentPane().validate();
	}
	
	public boolean puzzleCheck(){
		
		int x1, x2, x3, x4, x5, x6, x7, x8, x9;
		int y1, y2, y3, y4, y5, y6, y7, y8, y9;
		
		boolean puzzleSolved = true;
		
		x1 = panel[0].getX();
		x2 = panel[1].getX();
		x3 = panel[2].getX();
		x4 = panel[3].getX();
		x5 = panel[4].getX();
		x6 = panel[5].getX();
		x7 = panel[6].getX();
		x8 = panel[7].getX();
		x9 = panel[8].getX();
		
		y1 = panel[0].getY();
		y2 = panel[1].getY();
		y3 = panel[2].getY();
		y4 = panel[3].getY();
		y5 = panel[4].getY();
		y6 = panel[5].getY();
		y7 = panel[6].getY();
		y8 = panel[7].getY();
		y9 = panel[8].getY();

		if( x1 != 0 || y1 != 0 )
			puzzleSolved = false;
		if( x2 != width || y2 != 0)
			puzzleSolved = false;
		if( x3 != 2*width || y3 != 0)
			puzzleSolved = false;
		if( x4 != 0 || y4 != height)
			puzzleSolved = false;
		if( x5 != width || y5 != height)
			puzzleSolved = false;
		if( x6 != 2*width || y6 != height)
			puzzleSolved = false;
		if(x7 != 0 || y7 != 2*height)
			puzzleSolved = false;
		if(x8 != width || y8 != 2*height)
			puzzleSolved = false;
		if(x9 != 2*width || y9 != 2*height)
			puzzleSolved = false;
		
		return puzzleSolved;
	}
	
	public void msgbox(String s){
	   JOptionPane.showMessageDialog(null, s, "Status", JOptionPane.INFORMATION_MESSAGE);
	}
	

	public void actionPerformed(ActionEvent i) {
		
		int areaX , areaY;
		int x1, y1;
		int temp_x, temp_y;
		
		if(i.getSource() == button1){
			
			x1 = panel[0].getX();
			y1 = panel[0].getY();
			
			areaX = panel[8].getX();
			areaY = panel[8].getY();
			
			if(areaX < x1 && (areaX + width) == x1 && (y1 == areaY)){
				
			    temp_x = x1;
				x1 = areaX;
				areaX = temp_x;
				
				panel[0].setLocation(x1, y1);
				panel[8].setLocation(areaX, areaY);				
							
			}else if (areaX > x1 && (areaX - width) == x1 && (y1 == areaY)){
				
				temp_x = x1;
				x1 = areaX;
				areaX = temp_x;
				
				panel[0].setLocation(x1, y1);
				panel[8].setLocation(areaX, areaY);
				
			}else if(areaY < y1 && (areaY + width) == y1 && (x1 == areaX)){
				
				temp_y = y1;
				y1 = areaY;
				areaY = temp_y;
				
				panel[0].setLocation(x1, y1);
				panel[8].setLocation(areaX, areaY);

			}else if(areaY > y1 && (areaY - width) == y1 && (x1 == areaX)){
				
				temp_y = y1;
				y1 = areaY;
				areaY = temp_y;
				
				panel[0].setLocation(x1, y1);
				panel[8].setLocation(areaX, areaY);

			}
			this.getContentPane().validate();
			if(this.puzzleCheck()){
				this.msgbox("Victory!");
				this.shuffle();
			}
		}
		
		
		if(i.getSource() == button2){
			x1 = panel[1].getX();
			y1 = panel[1].getY();
			
			areaX = panel[8].getX();
			areaY = panel[8].getY();
			
			if(areaX < x1 && (areaX + width) == x1 && (y1 == areaY)){
				
			    temp_x = x1;
				x1 = areaX;
				areaX = temp_x;
				
				panel[1].setLocation(x1, y1);
				panel[8].setLocation(areaX, areaY);				
							
			}else if (areaX > x1 && (areaX - width) == x1 && (y1 == areaY)){
				
				temp_x = x1;
				x1 = areaX;
				areaX = temp_x;
				
				panel[1].setLocation(x1, y1);
				panel[8].setLocation(areaX, areaY);
				
			}else if(areaY < y1 && (areaY + width) == y1 && (x1 == areaX)){
				
				temp_y = y1;
				y1 = areaY;
				areaY = temp_y;
				
				panel[1].setLocation(x1, y1);
				panel[8].setLocation(areaX, areaY);

			}else if(areaY > y1 && (areaY - width) == y1 && (x1 == areaX)){
				
				temp_y = y1;
				y1 = areaY;
				areaY = temp_y;
				
				panel[1].setLocation(x1, y1);
				panel[8].setLocation(areaX, areaY);

			}
			this.getContentPane().validate();
			if(this.puzzleCheck())
				this.msgbox("Victory!");
		}

		if(i.getSource() == button3){
			x1 = panel[2].getX();
			y1 = panel[2].getY();
			
			areaX = panel[8].getX();
			areaY = panel[8].getY();
			
			if(areaX < x1 && (areaX + width) == x1 && (y1 == areaY)){
				
			    temp_x = x1;
				x1 = areaX;
				areaX = temp_x;
				
				panel[2].setLocation(x1, y1);
				panel[8].setLocation(areaX, areaY);				
							
			}else if (areaX > x1 && (areaX - width) == x1 && (y1 == areaY)){
				
				temp_x = x1;
				x1 = areaX;
				areaX = temp_x;
				
				panel[2].setLocation(x1, y1);
				panel[8].setLocation(areaX, areaY);
				
			}else if(areaY < y1 && (areaY + width) == y1 && (x1 == areaX)){
				
				temp_y = y1;
				y1 = areaY;
				areaY = temp_y;
				
				panel[2].setLocation(x1, y1);
				panel[8].setLocation(areaX, areaY);

			}else if(areaY > y1 && (areaY - width) == y1 && (x1 == areaX)){
				
				temp_y = y1;
				y1 = areaY;
				areaY = temp_y;
				
				panel[2].setLocation(x1, y1);
				panel[8].setLocation(areaX, areaY);

			}
			this.getContentPane().validate();
			if(this.puzzleCheck())
				this.msgbox("Victory!");
		}

		
		if(i.getSource() == button4){
			x1 = panel[3].getX();
			y1 = panel[3].getY();
			
			areaX = panel[8].getX();
			areaY = panel[8].getY();
			
			if(areaX < x1 && (areaX + width) == x1 && (y1 == areaY)){
				
			    temp_x = x1;
				x1 = areaX;
				areaX = temp_x;
				
				panel[3].setLocation(x1, y1);
				panel[8].setLocation(areaX, areaY);				
							
			}else if (areaX > x1 && (areaX - width) == x1 && (y1 == areaY)){
				
				temp_x = x1;
				x1 = areaX;
				areaX = temp_x;
				
				panel[3].setLocation(x1, y1);
				panel[8].setLocation(areaX, areaY);
				
			}else if(areaY < y1 && (areaY + width) == y1 && (x1 == areaX)){
				
				temp_y = y1;
				y1 = areaY;
				areaY = temp_y;
				
				panel[3].setLocation(x1, y1);
				panel[8].setLocation(areaX, areaY);

			}else if(areaY > y1 && (areaY - width) == y1 && (x1 == areaX)){
				
				temp_y = y1;
				y1 = areaY;
				areaY = temp_y;
				
				panel[3].setLocation(x1, y1);
				panel[8].setLocation(areaX, areaY);

			}
			this.getContentPane().validate();
		
			if(this.puzzleCheck())
				this.msgbox("Victory!");
		}
		
		if(i.getSource() == button5){

			x1 = panel[4].getX();
			y1 = panel[4].getY();
			areaX = panel[8].getX();
			areaY = panel[8].getY();
			
			if(areaX < x1 && (areaX + width) == x1 && (y1 == areaY)){
				
			    temp_x = x1;
				x1 = areaX;
				areaX = temp_x;
				panel[4].setLocation(x1, y1);
				panel[8].setLocation(areaX, areaY);				
							
			}else if (areaX > x1 && (areaX - width) == x1 && (y1 == areaY)){
				
				temp_x = x1;
				x1 = areaX;
				areaX = temp_x;
				panel[4].setLocation(x1, y1);
				panel[8].setLocation(areaX, areaY);
				
			}else if(areaY < y1 && (areaY + width) == y1 && (x1 == areaX)){
				
				temp_y = y1;
				y1 = areaY;
				areaY = temp_y;
				panel[4].setLocation(x1, y1);
				panel[8].setLocation(areaX, areaY);
				
			}else if(areaY > y1 && (areaY - width) == y1 && (x1 == areaX)){
				
				temp_y = y1;
				y1 = areaY;
				areaY = temp_y;
				panel[4].setLocation(x1, y1);
				panel[8].setLocation(areaX, areaY);
			}
			this.getContentPane().validate();
			if(this.puzzleCheck())
				this.msgbox("Victory!");
		}

		if(i.getSource() == button6){
			x1 = panel[5].getX();
			y1 = panel[5].getY();
			areaX = panel[8].getX();
			areaY = panel[8].getY();
			
			if(areaX < x1 && (areaX + width) == x1 && (y1 == areaY)){
				
			    temp_x = x1;
				x1 = areaX;
				areaX = temp_x;
				panel[5].setLocation(x1, y1);
				panel[8].setLocation(areaX, areaY);				
							
			}else if (areaX > x1 && (areaX - width) == x1 && (y1 == areaY)){
				
				temp_x = x1;
				x1 = areaX;
				areaX = temp_x;
				panel[5].setLocation(x1, y1);
				panel[8].setLocation(areaX, areaY);
				
			}else if(areaY < y1 && (areaY + width) == y1 && (x1 == areaX)){
				
				temp_y = y1;
				y1 = areaY;
				areaY = temp_y;
				panel[5].setLocation(x1, y1);
				panel[8].setLocation(areaX, areaY);

			}else if(areaY > y1 && (areaY - width) == y1 && (x1 == areaX)){
				
				temp_y = y1;
				y1 = areaY;
				areaY = temp_y;
				panel[5].setLocation(x1, y1);
				panel[8].setLocation(areaX, areaY);

			}
			this.getContentPane().validate();
			if(this.puzzleCheck())
				this.msgbox("Victory!");
		}

		if(i.getSource() == button7){
			
			x1 = panel[6].getX();
			y1 = panel[6].getY();
			areaX = panel[8].getX();
			areaY = panel[8].getY();
			if(areaX < x1 && (areaX + width) == x1 && (y1 == areaY)){
				
			    temp_x = x1;
				x1 = areaX;
				areaX = temp_x;
				panel[6].setLocation(x1, y1);
				panel[8].setLocation(areaX, areaY);				
							
			}else if (areaX > x1 && (areaX - width) == x1 && (y1 == areaY)){
				
				temp_x = x1;
				x1 = areaX;
				areaX = temp_x;
				panel[6].setLocation(x1, y1);
				panel[8].setLocation(areaX, areaY);
				
			}else if(areaY < y1 && (areaY + width) == y1 && (x1 == areaX)){
				
				temp_y = y1;
				y1 = areaY;
				areaY = temp_y;
				panel[6].setLocation(x1, y1);
				panel[8].setLocation(areaX, areaY);

			}else if(areaY > y1 && (areaY - width) == y1 && (x1 == areaX)){
				
				temp_y = y1;
				y1 = areaY;
				areaY = temp_y;
				panel[6].setLocation(x1, y1);
				panel[8].setLocation(areaX, areaY);

			}
			this.getContentPane().validate();
			if(this.puzzleCheck())
				this.msgbox("Victory!");
		}

		if(i.getSource() == button8){
			
			x1 = panel[7].getX();
			y1 = panel[7].getY();
			areaX = panel[8].getX();
			areaY = panel[8].getY();
			
			if(areaX < x1 && (areaX + width) == x1 && (y1 == areaY)){
				
			    temp_x = x1;
				x1 = areaX;
				areaX = temp_x;
				panel[7].setLocation(x1, y1);
				panel[8].setLocation(areaX, areaY);				
							
			}else if (areaX > x1 && (areaX - width) == x1 && (y1 == areaY)){
				
				temp_x = x1;
				x1 = areaX;
				areaX = temp_x;
				panel[7].setLocation(x1, y1);
				panel[8].setLocation(areaX, areaY);
				
			}else if(areaY < y1 && (areaY + width) == y1 && (x1 == areaX)){
				
				temp_y = y1;
				y1 = areaY;
				areaY = temp_y;
				panel[7].setLocation(x1, y1);
				panel[8].setLocation(areaX, areaY);

			}else if(areaY > y1 && (areaY - width) == y1 && (x1 == areaX)){
				
				temp_y = y1;
				y1 = areaY;
				areaY = temp_y;
				panel[7].setLocation(x1, y1);
				panel[8].setLocation(areaX, areaY);

			}
			this.getContentPane().validate();
			if(this.puzzleCheck()){
				this.msgbox("Victory!");
				this.shuffle();
			}

		}

	}
}


