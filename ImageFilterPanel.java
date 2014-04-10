//CS201 Lab9
//Nicole Fella

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JPanel;

import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;



/**
 * This class creates the panel to hold the image on 
 * @author nicole
 *
 */
public class ImageFilterPanel extends JPanel //implements ActionListener
{
	/**
	 * Instance Field
	 */
	private ImageComponent imageComp;
	private JTextArea textArea;
	
	/**
	 * Constructor for panel
	 */
	public ImageFilterPanel()
	{		
		super();
		//instantiate the component to hold the yoshi picture
		imageComp = new ImageComponent( "yoshi.png" );
		//add the JLabel
		add( createSetTextArea(), "North");
		//add the component to the panel
		add( imageComp , "Center");
		//add the button
		add( createFilterButton(), "South");
	}
	
	/**
	 * Method to create a JTextArea which will be updated when the button is pressed
	 */
	public JTextArea createSetTextArea()
	{
		//create the JLabel which will be updated later
		JTextArea words =  new JTextArea("Click on the image to pick colors that will be preserved. " +
				"Then, click the button to filter everything else to grayscale.");
		//wrap text so that it will go on multiple lines if necessary
		words.setLineWrap(true);
		//assign this to the instance field
		textArea = words;
		//return the text area to the method
		return words;
	}
	
	/**
	 * Method to create button which will filter image after the 
	 */
	public JButton createFilterButton()
	{
		//create a temporary button with label 'Filter image'
		JButton tempButton = new JButton("Filter image");
		//add action listener to the button 
		tempButton.addActionListener(new ActionListener()
		{
			//when the button is pressed
			public void actionPerformed(ActionEvent e)
			{
				//filter the image for all pixels
				for(int x =0 ; x<imageComp.getImg().getWidth(); x++)
				{
					for(int y=0 ; y<imageComp.getImg().getHeight(); y++)
					{
									
						//Get the RGB of the pixel and convert it to grayscale by calling helper method 
						Color pixelRGB = new Color( imageComp.getImg().getRGB( x,y) );
						//if the pixel color is in the set, go to the next pixel (Skip the grayscale)
						if(imageComp.getSet().contains(pixelRGB)){
							continue;
						}
						
						//set the RGB for the image at pixel location (pixelX,pixelY) to grayscale determined by helper method
						imageComp.getImg().setRGB( x,y, imageComp.grayscale( pixelRGB ) );
						
					}
				}
				//update the JLabel
				repaint();
				
				//update the JTextArea call helper method 
				textArea.setText(textSet(imageComp.getSet()));
				
			}
		});
		return tempButton;
	}
	
	/**
	 * Helper method to get string to update JTextArea to
	 */
	public String textSet(Set<Color> setParam)
	{
		//temporary string
		String tempString;
		
		//make iterator
		Iterator<Color> iterator = setParam.iterator();
		Color current;
		
		tempString = "Preserved Colors: {";
		
		// as long as iterator has another element
		while ( iterator.hasNext() )
		{
			// note: iterator.next() returns an Object
			// this must be cast if you need the specific type
			current = iterator.next(); // get next element
			// if not last element
			if ( iterator.hasNext() )
				 tempString += current.toString() + ", " ;
			// otherwise, don't print ,
			else
				 tempString += current.toString() ;
		}
		 
		 tempString += "}";
		 
		 //return the string to update the JTextArea
		 return tempString;
	}
}