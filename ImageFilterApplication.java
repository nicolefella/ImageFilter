//CS201 Lab9
//Nicole Fella

import javax.swing.JFrame;

/**
 * This class will display the image in a JFrame
 * @author nicole
 *
 */
public class ImageFilterApplication
{
	/**
	 * Main Method
	 */
	public static void main(String[] args)
	{
		//create a JFrame
		JFrame imageFilterFrame = new JFrame("Image filtering");
		//add image filter panel to the frame
		imageFilterFrame.getContentPane().add(new ImageFilterPanel());
		//set the size of the frame
		imageFilterFrame.setSize(700, 600);
		//set default close operation 
		imageFilterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//make the frame visible
		imageFilterFrame.setVisible(true);
	}
}