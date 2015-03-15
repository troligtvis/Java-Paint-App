package controller;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JComboBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Ellipse;
import model.Line;
import model.PaintMainModel;
import model.Rectangle;
import model.ShapeEnum;
import model.ShapeFactory;
import view.JavaMainView;

import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class PaintController{
	
	private static final long serialVersionUID = 1L;
	
	private MyMouseAdapter myMouseAdapter = null;
	private int currentShape = 1;
	private float shapeThicknessVal=1;
	private DecimalFormat dec = new DecimalFormat("#.##");
	private Color paintColor = Color.BLACK;
	private ShapeFactory shapeFactory = new ShapeFactory();
	private String shapeInUse = "Line";
	private PaintMainModel model;
	private ArrayList<observer.Observer> observers = new ArrayList<observer.Observer>();
	private JavaMainView mainView;
	
	public PaintController(){
		model = new PaintMainModel();
		mainView = new JavaMainView(model);
		observers.add(mainView);
		
		ListenForSlider lstForSlid = new ListenForSlider();
		mainView.getShapeThickness().addChangeListener(lstForSlid);
		ActionListenForColorComboBox actLstForColorCombBox = new ActionListenForColorComboBox();
		mainView.getColorComboBox().addActionListener(actLstForColorCombBox);
		ActionListenForShapeComboBox actLstForShapeCombBox = new ActionListenForShapeComboBox();
		mainView.getShapeNameCombBox().addActionListener(actLstForShapeCombBox);
		
		myMouseAdapter = new MyMouseAdapter();
        mainView.setName("Drawing");
        mainView.addMouseListener(myMouseAdapter);
        mainView.addMouseMotionListener(myMouseAdapter);
        
	}

	
	
	public JavaMainView getMainView() {
		return mainView;
	}

	public void notifyAllObservers(){
		for(observer.Observer obs : observers){
			obs.update();
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
						Line tempLine = (Line)shapeFactory.getShape(ShapeEnum.LINE); 
						tempLine.setX(shapeStart.x);
						tempLine.setY(shapeStart.y);
						tempLine.setX2(me.getX());
						tempLine.setY2(me.getY());
						tempLine.setColor(paintColor);
						tempLine.setShapeThickness(shapeThicknessVal);
						model.addShape(tempLine);
						
						break;
					case "Ellipse":
						//Draw a ellipse
						Ellipse tempEllipse = (Ellipse) shapeFactory.getShape(ShapeEnum.ELLIPSE);
						tempEllipse.setX(Math.min(shapeStart.x, me.getX()));
						tempEllipse.setY(Math.min(shapeStart.y, me.getY()));
						tempEllipse.setWidth(Math.abs(shapeStart.x - me.getX()));
						tempEllipse.setHeight(Math.abs(shapeStart.y - me.getY()));
						tempEllipse.setColor(paintColor);
						tempEllipse.setShapeThickness(shapeThicknessVal);
						model.addShape(tempEllipse);
						break;
						
					case "Rectangle":
						//Draw a rect
						Rectangle tempRect = (Rectangle)shapeFactory.getShape(ShapeEnum.RECTANGLE);
						tempRect.setX(Math.min(shapeStart.x, me.getX()));
						tempRect.setY(Math.min(shapeStart.y, me.getY()));
						tempRect.setWidth(Math.abs(shapeStart.x - me.getX()));
						tempRect.setHeight(Math.abs(shapeStart.y - me.getY()));
						tempRect.setColor(paintColor);
						tempRect.setShapeThickness(shapeThicknessVal);
						model.addShape(tempRect);
						break;
					default:
						break;
					} 
			 }
			 
			 notifyAllObservers();
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
     		
     		if(e.getSource() == mainView.getShapeThickness()){
     	
     			mainView.getThicknessLabel().setText("  Thickness: " + dec.format(mainView.getShapeThickness().getValue() * .1) );
     			shapeThicknessVal = (float) (mainView.getShapeThickness().getValue() * .1);
     		}
     	}
     }
	
	
}
