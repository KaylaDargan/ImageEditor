import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Master2 extends Application{
	
	/*
	 * functions to implement:
	 * random color shifter (select random pixels and randomly affect them)
	 * change just R, B, G independently
	 * black and white? may be hard to do tho. check for a method that converts to grayscale equivalent
	 * 
	 * etc.
	 * 
	 * NOTE: I may be able to do this for all pixels but probably not. not linearly anyway.
	 * so consider doing just a subsection of the photo.
	 * 
	 * also be sure to reset the imageview to be the new image, not the old one
	 * */
	
	
	public void resize(String inputImagePath,
            String outputImagePath, int scaledWidth, int scaledHeight)
            throws IOException {
        // reads input image
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
        
        double width  = 0;
        double height = 0;
        double aspect_ratio = 0;
        
        width  = inputImage.getWidth();
        height = inputImage.getHeight();
        
        
        aspect_ratio = width/height;
        scaledHeight = (int)(scaledHeight * aspect_ratio);
        
      //  System.out.println(aspect_ratio + " "+ scaledWidth + " " + scaledHeight);
        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth,
                scaledHeight, inputImage.getType());
 
        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();
 
        // extracts extension of output file
        String formatName = outputImagePath.substring(outputImagePath
                .lastIndexOf(".") + 1);
 
        // writes to output file
        ImageIO.write(outputImage, formatName, new File(outputImagePath));
    }

	@Override
    public void start(Stage primaryStage) throws IOException {
     
		Scanner keyboard = new Scanner(System.in);
		System.out.println("What would you like to do? \n1.Randomize Pixel Colors\n2.Edit Red Values\n3.Grayscale");
		int option = keyboard.nextInt();
		
		
				
		
		//perform user input for image src here
		//then create the image object
		
		this.resize("src/flower.JPG", "src/scaledFlower.JPG", 50, 50);
		
        // Create Image and ImageView objects
        Image image = new Image("file:src/scaledFlower.JPG");
        
        //Create ImageView to display picture in Scene
        ImageView imageView = new ImageView();
        
      
        // Obtain PixelReader
        PixelReader pixelReader = image.getPixelReader();
        WritableImage writeableImage = new WritableImage(pixelReader, (int)image.getWidth(), (int)image.getHeight());
        PixelWriter pixelWriter = writeableImage.getPixelWriter();
        System.out.println("Image Width: "+image.getWidth());
        System.out.println("Image Height: "+image.getHeight());
       // System.out.println("Pixel Format: "+pixelReader.getPixelFormat());
        
        
        Random generator = new Random();
        int xCoord = 0;
        int yCoord = 0;
		if (option == 1){
			
			for(int i = 0; i < 1000; i++){
				
				xCoord = generator.nextInt((int)image.getWidth());
				yCoord = generator.nextInt((int)image.getHeight());
				
				double r = generator.nextDouble();
				double g = generator.nextDouble();
				double b = generator.nextDouble();
				
				Color newColor = new Color(r,g,b,1.0);
				
				pixelWriter.setColor(xCoord, yCoord, newColor);
		
			
				 
			}//for
			
			
			//set imageview with modified picture
			imageView.setImage(writeableImage);
	       imageView.setFitWidth(250);
	        imageView.setPreserveRatio(true);
	        imageView.setSmooth(true);
	        imageView.setCache(true);
			
			/*
			
			
			//Random generator = new Random();
	          // Determine the color of each pixel in the image
	        for (int i = 0; i < image.getHeight(); i++) {
	            for (int j = 0; j < image.getWidth(); j++) {
	                Color color = pixelReader.getColor(i, j);
	                System.out.println("\nPixel color at coordinates ("
	                        + i + "," + j + ") "
	                        + color.toString());
	                System.out.println("R = " + color.getRed());
	                System.out.println("G = " + color.getGreen());
	                System.out.println("B = " + color.getBlue());
	                System.out.println("Opacity = " + color.getOpacity());
	                System.out.println("Saturation = " + color.getSaturation());
	            }
	        }
	         
			*/
			
		}//if option 1
		
		if (option == 2){
			
			System.out.println("Give new value on a scale of 0.0-1.0");
			double r = keyboard.nextDouble();
			
			for (int i = 0; i < image.getWidth(); i++){
				for(int j =0; j < image.getHeight(); j++){
					
					Color color = pixelReader.getColor(i, j);
					double g = color.getGreen();
					double b = color.getBlue();
					Color newColor = new Color(r,g,b,1.0);
					pixelWriter.setColor(i, j, newColor);
					
				}
			}
			
			//set imageview with modified picture
			imageView.setImage(writeableImage);
	       imageView.setFitWidth(250);
	        imageView.setPreserveRatio(true);
	        imageView.setSmooth(true);
	        imageView.setCache(true);
			
			
		}//if option 2
        
        
        
        
        /*
         * // Determine the color of each pixel in the image
        for (int readY = 0; readY < image.getHeight(); readY++) {
            for (int readX = 0; readX < image.getWidth(); readX++) {
                Color color = pixelReader.getColor(readX, readY);
                System.out.println("\nPixel color at coordinates ("
                        + readX + "," + readY + ") "
                        + color.toString());
                System.out.println("R = " + color.getRed());
                System.out.println("G = " + color.getGreen());
                System.out.println("B = " + color.getBlue());
                System.out.println("Opacity = " + color.getOpacity());
                System.out.println("Saturation = " + color.getSaturation());
            }
        }
         * */
        
        
        
           
        // Display image on screen
        StackPane root = new StackPane();
        root.getChildren().add(imageView);
        Scene scene = new Scene(root, 250, 250);
        primaryStage.setTitle("Image Read Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		launch(args);
	}

}
