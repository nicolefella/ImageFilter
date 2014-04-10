//CS201 Lab9
//Nicole Fella

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;


/**
 * This class will take an image and convert a clicked pixel in that image to grayscale
 * @author nicole
 *
 */
public class ImageComponent extends JComponent implements MouseListener
{
	/**
	 * Instance fields 
	 */
	private BufferedImage img;
	private int scale;
	Set<Color> s;

	/** 
	 * Constructor sets scale and image 
	 */
	public ImageComponent(String imagePath)
	{
		scale = 2;
		//try to read the image 
		try
		{
			//read image from filepath
			img = ImageIO.read(new File(imagePath));
			//add mouse listener to the image
			addMouseListener( this );
		} catch (IOException e)
		{
			System.out.println("file IO exception");
			e.printStackTrace();
		}
		
		s = new HashSet<Color>();
	}
	
	/**
	 * Getter method to return the image
	 */
	public BufferedImage getImg()
	{
		return this.img;
	}
	
	/**
	 * Getter method to return the set
	 */
	public Set getSet()
	{
		return this.s;
	}
	
	/**
	 * Paint the image graphics 
	 */
	public void paint(Graphics g)
	{
		g.drawImage(img, 0, 0, scale*img.getWidth(), scale*img.getHeight(),
				0, 0, img.getWidth(), img.getHeight(), null);
	}
	/**
	 * Method to get the dimension of a component (width and height)
	 */
	public Dimension getPreferredSize()
	{
		//if there is no image
		if (img == null)
		{
			return new Dimension(100, 100);
		} 
		//set new dimension according to scale we determined -- makes pixels bigger
		else
		{
			return new Dimension( scale*img.getWidth(null), scale*img.getHeight(null));
		}
	}
	
	/**
	 * Create gray scale of image 
	 */
	public int grayscale( Color c )
	{
		//get red, green, and blue components of the image in default sRGB space
		int r = c.getRed();
		int g = c.getGreen();
		int b = c.getBlue();
		
		int avg = (int) Math.round( ( r + g + b )/ 3.0 );
		//getRGB() returns RGB value representing color in default sRGB
		return new Color( avg, avg, avg ).getRGB();
	}
	/**
	 * This method will specify what happens when the image is clicked 
	 */
	public void mouseClicked(MouseEvent e)
	{
		
		//Determine the position of the click
		int xClicked = e.getX();
		int yClicked = e.getY();
	
		// Take the clicked position and scale it ot find the pixel location
		int pixelX = xClicked/scale;
		int pixelY = yClicked/scale;
			
		//Get the RGB of the pixel and convert it to grayscale by calling helper method 
		Color pixelRGB = new Color( img.getRGB( pixelX, pixelY) );

		//add color to set
		s.add(pixelRGB);
		
		//set the RGB for the image at pixel location (pixelX,pixelY) to grayscale determined by helper method
		img.setRGB( pixelX, pixelY, grayscale( pixelRGB ) );
		
		//always repaint
		repaint();
	}
	
	/**
	 * Below methods are not needed for this program
	 */
	public void mousePressed(MouseEvent e) {}	
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}	
	public void mouseExited(MouseEvent e) {}
}