package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Ellipse;
import model.Line;
import model.Shape;
import model.Rectangle;
import model.ShapeEnum;
import model.ShapeFactory;
import view.ButtonMenuView;

import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class PaintController extends JLayeredPane{
	
	private static final long serialVersionUID = 1L;
	
	private ButtonMenuView btnMenView = new ButtonMenuView();
	private JPanel btnPanel;
	private MyMouseAdapter myMouseAdapter = null;
	private int currentShape = 1;
	private JSlider shapeThickness;
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private JLabel thicknessLabel;
	private float shapeThicknessVal=1;
	private DecimalFormat dec = new DecimalFormat("#.##");
	private JComboBox<String> colorComboBox;
	private Color paintColor = Color.BLACK;
	private ShapeFactory shapeFactory = new ShapeFactory();
	private JComboBox<String> shapeNameCombBox;
	private String shapeInUse;
	
	public PaintController(){
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		
		btnPanel = btnMenView.getButtonPanel();
		shapeThickness = btnMenView.getShapeThicknessSlider();
		thicknessLabel = btnMenView.getThicknessLabel();
		colorComboBox = btnMenView.getColorList();
		shapeNameCombBox = btnMenView.getShapeNamesCombBox();
		
		
		ListenForSlider lstForSlid = new ListenForSlider();
		shapeThickness.addChangeListener(lstForSlid);
		ActionListenForColorComboBox actLstForColorCombBox = new ActionListenForColorComboBox();
		colorComboBox.addActionListener(actLstForColorCombBox);
		ActionListenForShapeComboBox actLstForShapeCombBox = new ActionListenForShapeComboBox();
		shapeNameCombBox.addActionListener(actLstForShapeCombBox);
		
		
		this.add(btnPanel,BorderLayout.SOUTH);
		
		myMouseAdapter = new MyMouseAdapter();
		this.setName("Drawing");
        addMouseListener(myMouseAdapter);
        addMouseMotionListener(myMouseAdapter);
        
		
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for(Shape s: shapes){
        	s.paint(g2);
        }
    }

	
	 private class MyMouseAdapter extends MouseAdapter{
		 
		 Point shapeStart, shapeEnd;
		 
		 @Override
		 public void mousePressed(MouseEvent me) {
			 String compID = me.getComponent().getName();
			 
			 switch (compID) {
		 	case "Drawing":
				System.out.println("Drawing shape: "+currentShape);
				shapeStart = new Point(me.getX(), me.getY());
				shapeEnd = shapeStart;
				break;
			default:
				break;
			}
			 
		 }
		 
		 @Override
		 public void mouseReleased(MouseEvent me){
			 if(shapeStart != null){ //Needed to prevent previous cords
				 switch (shapeInUse) {
					case "Line":
						//Draw a line
						System.out.println("X1: "+shapeStart.x+" Y1: "+shapeStart.y+" X2: "+me.getX()+" Y2: "+me.getY());
						Line tempRect = (Line)shapeFactory.getShape(ShapeEnum.LINE); 
						tempRect.setX(shapeStart.x);
						tempRect.setY(shapeStart.y);
						tempRect.setX2(me.getX());
						tempRect.setY2(me.getY());
						tempRect.setColor(paintColor);
						tempRect.setShapeThickness(shapeThicknessVal);
						shapes.add(tempRect);
						
						break;
					case "Ellipse":
						//Draw a ellipse
						double widthEllipse = Math.abs(shapeStart.x - me.getX());
		                double heightEllipse = Math.abs(shapeStart.y - me.getY());
		                int xEllipse = Math.min(shapeStart.x, me.getX());
                        int yEllipse = Math.min(shapeStart.y, me.getY());
						shapes.add(new Ellipse(xEllipse,yEllipse, widthEllipse, heightEllipse, paintColor, shapeThicknessVal));
						break;
						
					case "Rectangle":
						//Draw a rect
						double width = Math.abs(shapeStart.x - me.getX());
		                double height = Math.abs(shapeStart.y - me.getY());
		                int xRect = Math.min(shapeStart.x, me.getX());
                        int yRect = Math.min(shapeStart.y, me.getY());
						shapes.add(new Rectangle(xRect,yRect, width, height,paintColor, shapeThicknessVal));
						break;
					default:
						break;
					} 
			 }
			 
			 repaint();
		 }
		 
		 
	 }
	 
	 public void checkWhatColor(String strColor){
		 switch (strColor) {
		case "Black":
			paintColor = Color.BLACK;
			break;
		case "Yellow":
			paintColor = Color.YELLOW;
			break;
		case "Blue":
			paintColor = Color.BLUE;
			break;
		case "Green":
			paintColor = Color.GREEN;
			break;
		case "Red":
			paintColor = Color.RED;
			break;
		default:
			paintColor = Color.BLACK;
			break;
		}
	 }
	 
	 
	 private class ActionListenForShapeComboBox implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox<String> tempCb = (JComboBox<String>)e.getSource();
			shapeInUse = (String) tempCb.getSelectedItem();
			System.out.println(shapeInUse);
		}
	 }
	 
	 private class ActionListenForColorComboBox implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox<String> tempCb = (JComboBox<String>)e.getSource();
			String color = (String) tempCb.getSelectedItem();
			checkWhatColor(color);
			
		}
		 
	 }
	 
     private class ListenForSlider implements ChangeListener{
    	 
     	public void stateChanged(ChangeEvent e) {
     		
     		if(e.getSource() == shapeThickness){
     	
     			thicknessLabel.setText("  Thickness: " + dec.format(shapeThickness.getValue() * .1) );
     			shapeThicknessVal = (float) (shapeThickness.getValue() * .1);
     		}
     	}
     }
	
	
}
