package view;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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
	private String[] colors = {"Black", "Yellow", "Blue", "Green", "Red"};
	private JComboBox<String> colorList = new JComboBox(colors);
	private JLabel thicknessLabel = new JLabel();
	
	public ButtonMenuView(){
		btnLine.setText("Line");
		btnEllipse.setText("Ellipse");
		btnRect.setText("Rectangle");
		shapeThicknessSlider = new JSlider(10,20,10);
		thicknessLabel.setText(" Thickness: "+(shapeThicknessSlider.getValue()/10));
		colorList.setSelectedIndex(0);
		
		//ID for mouselistener
		btnLine.setName("Line");
		btnEllipse.setName("Ellipse");
		btnRect.setName("Rectangle");
		
		//Add button to array to get from controller, create listeners
		
		buttonArray[0] = btnLine;
		buttonArray[1] = btnEllipse;
		buttonArray[2] = btnRect;
		
		buttonBox.add(colorList);
		buttonBox.add(btnLine);
		buttonBox.add(btnEllipse);
		buttonBox.add(btnRect);
		buttonBox.add(thicknessLabel);
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

	public JComboBox getColorList() {
		return colorList;
	}

	public JLabel getThicknessLabel() {
		return thicknessLabel;
	}

	public String[] getColors() {
		return colors;
	}
	

}
