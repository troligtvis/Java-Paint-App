package view;

import java.awt.Color;
import java.awt.Component;

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
	private Component[] dialogComp = new Component[5];
	
	public EditPanelView(){

		
		
		removeBtn.setText("Remove");
		changeBtn.setText("Change");
		
		thickness = new JSlider(10,20,10);
		
		colorComboBox.setName("Edit");
		
		dialogComp[0] = removeBtn;
		dialogComp[1] = changeBtn;
		dialogComp[2] = thickness;
		dialogComp[3] = colorComboBox;
		dialogComp[4] = fillable;
		
		this.add(removeBtn);
		this.add(changeBtn);
		this.add(thickness);
		this.add(colorComboBox);
		this.add(fillable);
	}
	
	public void editEditbox(Color selectedNameColor, boolean isFilled, float thicknessVal){
		colorComboBox.setSelectedIndex(colorToIndex(selectedNameColor));
		fillable.setSelected(isFilled);
		System.out.println("Thick: "+thicknessVal*10);
		thickness.setValue((int) (thicknessVal*10));
	}
	
	public int colorToIndex(Color color){
		int colorInt=Math.abs(color.getRGB());
		switch (colorInt) {
		case 16777216:
			
			return 0;
		case 256:
			
			return 1;
		case 16776961:
			
			return 2;
		case 16711936:
			
			return 3;
		case 65536:
			
			return 4;

		default:
			return 0;
		}
	}

	public JButton getRemoveBtn() {
		return removeBtn;
	}

	public void setRemoveBtn(JButton removeBtn) {
		this.removeBtn = removeBtn;
	}

	public JButton getChangeBtn() {
		return changeBtn;
	}

	public void setChangeBtn(JButton changeBtn) {
		this.changeBtn = changeBtn;
	}

	public JSlider getThickness() {
		return thickness;
	}

	public void setThickness(JSlider thickness) {
		this.thickness = thickness;
	}

	public JComboBox<String> getColorComboBox() {
		return colorComboBox;
	}

	public void setColorComboBox(JComboBox<String> colorComboBox) {
		this.colorComboBox = colorComboBox;
	}

	public JCheckBox getFillable() {
		return fillable;
	}

	public void setFillable(JCheckBox fillable) {
		this.fillable = fillable;
	}
	
	public Component[] getDialogComp() {
		return dialogComp;
	}
	public void setDialogComp(Component[] dialogComp) {
		this.dialogComp = dialogComp;
	}
	
	
}
