package view;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class EditPanelView extends JPanel{

	private JButton removeBtn = new JButton();
	private JButton changeBtn = new JButton();
	private JSlider thickness = new JSlider();
	private String[] colors = {"Black", "Yellow", "Blue", "Green", "Red"};
	private JComboBox<String> colorComboBox = new JComboBox<String>(colors);
	private JCheckBox fillable = new JCheckBox("Filled");
	
	public EditPanelView(){

		removeBtn.setText("Remove");
		changeBtn.setText("Change");
		
		thickness = new JSlider(10,20,10);
		
		this.add(removeBtn);
		this.add(changeBtn);
		this.add(colorComboBox);
		this.add(fillable);
	}
	
	
}
