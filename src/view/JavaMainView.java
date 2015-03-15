package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;

import observer.Observer;
import model.PaintMainModel;

public class JavaMainView  extends JLayeredPane implements Observer{

	private static final long serialVersionUID = 1L;

	private PaintMainModel model;
	
	private ButtonMenuView btnMenView = new ButtonMenuView();
	private JPanel btnPanel;
	private JSlider shapeThickness;
	private JLabel thicknessLabel;
	private JComboBox<String> colorComboBox;
	private JComboBox<String> shapeNameCombBox;
	private DrawingFrame drawingFrame;
	private MenuBarView menuBar;
	private JPanel menuBarFrame = new JPanel();
	private EditPanelView editPanel;
	private JDialog jDialogEdit = new JDialog();
	private JSlider thicknessEdit;
	private JComboBox<String> colorComboBoxEdit;
	private JCheckBox isFilledEdit;
	private JButton confirmChangeBtn;
	private JButton deleteBtn;
	
	public JavaMainView(PaintMainModel model){
		this.model = model;
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		drawingFrame = new DrawingFrame(model);
		menuBar = new MenuBarView();
		menuBarFrame.add(menuBar);
		JPanel drawingPanel = drawingFrame;
		
		editPanel = new EditPanelView();
		thicknessEdit = editPanel.getThickness();
		colorComboBoxEdit = editPanel.getColorComboBox();
		isFilledEdit = editPanel.getFillable();
		confirmChangeBtn = editPanel.getChangeBtn();
		deleteBtn = editPanel.getRemoveBtn();
		
		btnPanel = btnMenView.getButtonPanel();
		shapeThickness = btnMenView.getShapeThicknessSlider();
		thicknessLabel = btnMenView.getThicknessLabel();
		colorComboBox = btnMenView.getColorList();
		shapeNameCombBox = btnMenView.getShapeNamesCombBox();
		
		
		this.add(menuBarFrame, BorderLayout.NORTH);
		this.add(drawingPanel, BorderLayout.CENTER);
		this.add(btnPanel,BorderLayout.SOUTH);
		
		
	}
	
	public void editBox(){
		jDialogEdit.setSize(600, 100);
		jDialogEdit.setResizable(false);
		jDialogEdit.setLocationRelativeTo(null);
		jDialogEdit.getContentPane().add(editPanel, BorderLayout.SOUTH);
		jDialogEdit.setVisible(true);
	}
	
	public JComboBox<String> getColorComboBoxEdit() {
		return colorComboBoxEdit;
	}

	public void setColorComboBoxEdit(JComboBox<String> colorComboBoxEdit) {
		this.colorComboBoxEdit = colorComboBoxEdit;
	}

	public JSlider getThicknessEdit() {
		return thicknessEdit;
	}

	public void setThicknessEdit(JSlider thicknessEdit) {
		this.thicknessEdit = thicknessEdit;
	}

	public JCheckBox getIsFilledEdit() {
		return isFilledEdit;
	}

	public void setIsFilledEdit(JCheckBox isFilledEdit) {
		this.isFilledEdit = isFilledEdit;
	}

	public JButton getConfirmChangeBtn() {
		return confirmChangeBtn;
	}

	public void setConfirmChangeBtn(JButton confirmChangeBtn) {
		this.confirmChangeBtn = confirmChangeBtn;
	}

	public JButton getDeleteBtn() {
		return deleteBtn;
	}

	public void setDeleteBtn(JButton deleteBtn) {
		this.deleteBtn = deleteBtn;
	}

	public JCheckBox getIsFilledNew(){
		return btnMenView.getFilled();
	}

	public void setDialogEditValues(Color selectedNameColor, boolean isFilled, float thicknessVal){
		editPanel.editEditbox(selectedNameColor, isFilled, thicknessVal);
	}

	public JDialog getjDialogEdit() {
		return jDialogEdit;
	}

	public MenuBarView getMenuBar() {
		return menuBar;
	}


	public PaintMainModel getModel() {
		return model;
	}

	public void setModel(PaintMainModel model) {
		this.model = model;
		drawingFrame.setModel(model);
	}


	public ButtonMenuView getBtnMenView() {
		return btnMenView;
	}


	public void setBtnMenView(ButtonMenuView btnMenView) {
		this.btnMenView = btnMenView;
	}


	public JPanel getBtnPanel() {
		return btnPanel;
	}


	public void setBtnPanel(JPanel btnPanel) {
		this.btnPanel = btnPanel;
	}


	public JSlider getShapeThickness() {
		return shapeThickness;
	}


	public void setShapeThickness(JSlider shapeThickness) {
		this.shapeThickness = shapeThickness;
	}


	public JLabel getThicknessLabel() {
		return thicknessLabel;
	}


	public void setThicknessLabel(JLabel thicknessLabel) {
		this.thicknessLabel = thicknessLabel;
	}


	public JComboBox<String> getColorComboBox() {
		return colorComboBox;
	}


	public void setColorComboBox(JComboBox<String> colorComboBox) {
		this.colorComboBox = colorComboBox;
	}


	public JComboBox<String> getShapeNameCombBox() {
		return shapeNameCombBox;
	}


	public void setShapeNameCombBox(JComboBox<String> shapeNameCombBox) {
		this.shapeNameCombBox = shapeNameCombBox;
	}


	public DrawingFrame getDrawingFrame() {
		return drawingFrame;
	}


	public void setDrawingFrame(DrawingFrame drawingFrame) {
		this.drawingFrame = drawingFrame;
	}


	@Override
	public void update() {
		drawingFrame.repaint();
	}
	
	
	
}
