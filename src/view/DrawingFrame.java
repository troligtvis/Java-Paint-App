package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.PaintController;

import java.text.DecimalFormat;


public class DrawingFrame extends JFrame{

	JButton brushBut, lineBut, ellipseBut, rectBut, strokeBut, fillBut;
	
	JSlider sizeSlider;
	
	JLabel sizeLabel;
	
	int currentAction = 1;
	DecimalFormat dec = new DecimalFormat("#.##");
	
	float sizeVal = 1.0f;
	
	public DrawingFrame(){
         
         JPanel buttonPanel = new JPanel();
         
         // Swing box that will hold all the buttons
         
         Box theBox = Box.createHorizontalBox();
         
         // Make all the buttons in makeMeButtons by passing the
         // button icon. 
         
         lineBut = btnBuilder("Line", 1);
         ellipseBut = btnBuilder("Ellipse", 2);
         rectBut = btnBuilder("Rectangle", 3);
         
         // Make all the buttons in makeMeColorButton by passing the
         // button icon and true for stroke color or false for fill
         
         //strokeBut = makeMeColorButton("./src/Stroke.png", 5, true);
         //fillBut = makeMeColorButton("./src/Fill.png", 6, false);
         
         // Add the buttons to the box
         
         theBox.add(brushBut);
         theBox.add(lineBut);
         theBox.add(ellipseBut);
         theBox.add(rectBut);
         theBox.add(strokeBut);
         theBox.add(fillBut);
         
         // Add the transparent label and slider
         
         sizeLabel = new JLabel("Size: 1");
         
         // Min value, Max value and starting value for slider
         
         sizeSlider = new JSlider(1, 99, 99);
         
         // Create an instance of ListenForEvents to handle events
         
         ListenForSlider lForSlider = new ListenForSlider();
         
         // Tell Java that you want to be alerted when an event
         // occurs on the slider
        
         sizeSlider.addChangeListener(lForSlider);

         theBox.add(sizeLabel);
         theBox.add(sizeSlider);
         
         // Add the box of buttons to the panel
         
         buttonPanel.add(theBox);

         // Position the buttons in the bottom of the frame
         
         this.add(buttonPanel, BorderLayout.SOUTH);
	}
	
	
	public JButton btnBuilder(String btnName, int btnFunc){
		JButton jBtn = new JButton();
		jBtn.setText(btnName);
		
		jBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				currentAction = btnFunc;
				
			}
        });
		
		return jBtn;
		
	}
	
    private class ListenForSlider implements ChangeListener{
    	
    	// Called when the spinner is changed
    	
    	public void stateChanged(ChangeEvent e) {
    	
    		// Check if the source of the event was the button
    	
    		if(e.getSource() == sizeSlider){
    	
    			// Change the value for the label next to the spinner
    			// Use decimal format to make sure only 2 decimals are ever displayed
    	
    			sizeLabel.setText("Transparent: " + dec.format(sizeSlider.getValue() * .01) );
    			
    			// Set the value for transparency for every shape drawn after
    			
    			sizeVal = (float) (sizeSlider.getValue() * .01);
    			
    		}
    	
    	}
    	
    }
	
}
