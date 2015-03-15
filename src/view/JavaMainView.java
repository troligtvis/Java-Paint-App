package view;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
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
	
	
	public JavaMainView(PaintMainModel model){
		this.model = model;
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		drawingFrame = new DrawingFrame(model);
		menuBar = new MenuBarView();
		menuBarFrame.add(menuBar);
		JPanel drawingPanel = drawingFrame;

		btnPanel = btnMenView.getButtonPanel();
		shapeThickness = btnMenView.getShapeThicknessSlider();
		thicknessLabel = btnMenView.getThicknessLabel();
		colorComboBox = btnMenView.getColorList();
		shapeNameCombBox = btnMenView.getShapeNamesCombBox();
		
		
		this.add(menuBarFrame, BorderLayout.NORTH);
		this.add(drawingPanel, BorderLayout.CENTER);
		this.add(btnPanel,BorderLayout.SOUTH);
		
		
	}


	public MenuBarView getMenuBar() {
		return menuBar;
	}


	public PaintMainModel getModel() {
		return model;
	}


	public void setModel(PaintMainModel model) {
		this.model = model;
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
