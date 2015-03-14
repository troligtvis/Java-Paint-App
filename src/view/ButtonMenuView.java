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
	private JSlider shapeThicknessSlider;
	private String[] colors = {"Black", "Yellow", "Blue", "Green", "Red"};
	private JComboBox<String> colorList = new JComboBox(colors);
	private JLabel thicknessLabel = new JLabel();
	private String[] shapeNames = {"Line", "Ellipse","Rectangle"};
	private JComboBox<String> shapeNamesCombBox = new JComboBox(shapeNames);
	
	
	public ButtonMenuView(){
		
		shapeThicknessSlider = new JSlider(10,20,10);
		thicknessLabel.setText(" Thickness: "+(shapeThicknessSlider.getValue()/10));
		colorList.setSelectedIndex(0);
		
		//Add button to array to get from controller, create listeners
		
		
		buttonBox.add(colorList);
		buttonBox.add(shapeNamesCombBox);
		buttonBox.add(thicknessLabel);
		buttonBox.add(shapeThicknessSlider);
		
		
		buttonPanel.add(buttonBox);
		
	}

	public JPanel getButtonPanel() {
		return buttonPanel;
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

	public String[] getShapeNames() {
		return shapeNames;
	}

	public JComboBox<String> getShapeNamesCombBox() {
		return shapeNamesCombBox;
	}


}
