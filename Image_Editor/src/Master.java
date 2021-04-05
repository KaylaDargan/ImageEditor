import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/*This class allows user to perform transformations on raw image data
	Ideally mimicking the functionality of an image editor*/
public class Master {

	public int width, height = 0;
	public BufferedImage image = null;
	public int image_type = 0;
	
	public void upload(String filePath){
		
		//BufferedImage img = null;
		try 
		{
		    this.image = ImageIO.read(new File(filePath));
		    System.out.println("Successfully uploaded image located at: \"" + filePath + "\"");
		    this.width = image.getWidth();
		   // System.out.println(this.width);
		    this.height = image.getHeight();
		   // System.out.println(this.height);
		    this.image_type = image.getType();
		} //try
		
		catch (IOException e) 
		{
			System.out.println("Error Retrieving Image");
			System.exit(0);
		} //catch
		
		
		
	}//upload
	
	//displays RGB pixel values in the given image
	public void displayPx(){
		
		for (int i = 0; i < 10; i++){
			
			for(int j = 0; j < 10; j++){
				
				 Color c = new Color(this.image.getRGB(i, j), true);
				    System.out.print("("+c.getRed() + " ");
				    System.out.print(c.getGreen() + " ");
				    System.out.print(c.getBlue() + ") ");
				    
				//System.out.print(this.image.getRGB(i,j) + " ");
				
				
			}//for height
			
			System.out.println();
		}//for width
		
		
	}//displayPx
	
	
	public void display(BufferedImage img){
		
		
		
	}//display
	
	
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Master obj = new Master();
		obj.upload("src/flower.jpg");
		
		
		//print statements for verification
		System.out.print("width:" + obj.width + "px " + "height:" + obj.height + "px\n");
		System.out.println("image type: " + obj.image_type);
		
		//obj.displayPx();
		
		
		SwingUtilities.invokeLater(new Runnable()
	    {
	      public void run()
	      {
	        JFrame editorFrame = new JFrame("Image Demo");
	        editorFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        
	        
	        ImageIcon imageIcon = new ImageIcon(obj.image);
	        JLabel jLabel = new JLabel();
	        jLabel.setIcon(imageIcon);
	        editorFrame.getContentPane().add(jLabel, BorderLayout.CENTER);

	        editorFrame.pack();
	        editorFrame.setLocationRelativeTo(null);
	        editorFrame.setVisible(true);
	      }
	    });
		
		
		
		
	}//main
*/
}//class
