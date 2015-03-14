package view;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class ButtonMenuView{
	private static final long serialVersionUID = 1L;
	
	private JPanel buttonPanel = new JPanel();
	private Box buttonBox = Box.createHorizontalBox();
	private JButton btnLine = new JButton();
	private JButton btnEllipse = new JButton();
	private JButton btnRect = new JButton();
	private JButton[] buttonArray = new JButton[3];
	private JSlider shapeThicknessSlider;
	
	public ButtonMenuView(){
		btnLine.setText("Line");
		btnEllipse.setText("Ellipse");
		btnRect.setText("Rectangle");
		shapeThicknessSlider = new JSlider(10,20,10);
		//ID for mouselistener
		btnLine.setName("Line");
		btnEllipse.setName("Ellipse");
		btnRect.setName("Rectangle");
		
		//Add button to array to get from controller, create listeners
		
		buttonArray[0] = btnLine;
		buttonArray[1] = btnEllipse;
		buttonArray[2] = btnRect;
		
		buttonBox.add(btnLine);
		buttonBox.add(btnEllipse);
		buttonBox.add(btnRect);
		buttonBox.add(shapeThicknessSlider);
		
		buttonPanel.add(buttonBox);
		
	}

	public JPanel getButtonPanel() {
		return buttonPanel;
	}

	public JButton[] getButtonArray() {
		return buttonArray;
	}

	public JSlider getShapeThicknessSlider() {
		return shapeThicknessSlider;
	}
	

}
