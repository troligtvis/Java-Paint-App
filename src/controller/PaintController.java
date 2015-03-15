package controller;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JMenuBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.AbstractFactory;
import model.Ellipse;
import model.FactoryProducer;
import model.Line;
import model.PaintMainModel;
import model.Rectangle;
import model.Shape;
import model.ShapeColorEnum;
import view.JavaMainView;

import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class PaintController{
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	private MyMouseAdapter myMouseAdapter = null;
	private int currentShape = 1;
	private float shapeThicknessVal=1;
	private DecimalFormat dec = new DecimalFormat("#.##");
	private Color paintColor = Color.BLACK;
	private String shapeInUse = "Line";
	private PaintMainModel model;
	private ArrayList<observer.Observer> observers = new ArrayList<observer.Observer>();
	private JavaMainView mainView;
	private JMenuBar menuBar;
	private AbstractFactory shapeFactory;
	private AbstractFactory colorFactory;
	private JDialog jDialogEdit;
	
	public PaintController(){
		model = new PaintMainModel();
		mainView = new JavaMainView(model);
		observers.add(mainView);
		menuBar = mainView.getMenuBar();
		jDialogEdit = mainView.getjDialogEdit();
		
		
		setMenuBarItemListeners();
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
        
        //Create abstractfactory
        shapeFactory = FactoryProducer.getFactory("Shape");
        colorFactory = FactoryProducer.getFactory("Color");
        
	}

	
	
	public JavaMainView getMainView() {
		return mainView;
	}

	public void notifyAllObservers(){
		for(observer.Observer obs : observers){
			obs.update();
		}
	}
	
	public void setMenuBarItemListeners(){
		MenuBarListener menuBarListener = new MenuBarListener();
		menuBar.getMenu(0).getItem(0).addActionListener(menuBarListener);
		menuBar.getMenu(0).getItem(1).addActionListener(menuBarListener);
		menuBar.getMenu(0).getItem(2).addActionListener(menuBarListener);
		menuBar.getMenu(1).getItem(0).addActionListener(menuBarListener);
		menuBar.getMenu(1).getItem(1).addActionListener(menuBarListener);
	}
	
	
	private class MenuBarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String action = e.getActionCommand();
			
			switch (action) {
			case "New":
				model.newCanvas();
				notifyAllObservers();
				break;
			case "Save":
				//Save canvas, remember to save model so you can resume everything
				break;
			case "Load":
				
				break;
			case "Undo":
				model.undo();
				notifyAllObservers();
				break;
			case "Redo":
				model.redo();
				notifyAllObservers();
				break;
			default:
				break;
			}
		}

	}
	
	 private class MyMouseAdapter extends MouseAdapter{
		 
		 @SuppressWarnings("unused")
		Point shapeStart, shapeEnd;
		 
		 @Override
		 public void mousePressed(MouseEvent me) {
			 String compID = me.getComponent().getName();
			 System.out.println(me.getButton());
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
		 
		 Line line = new Line();
		 Ellipse ellipse = new Ellipse();
		 Rectangle rect = new Rectangle();
		 
		 @Override
		 public void mouseReleased(MouseEvent me){
			 if(shapeStart != null){ //Needed to prevent previous cords
				 switch (shapeInUse) {
					case "Line":
						//Draw a line
						Line tempLine = (Line)shapeFactory.getClone(line); 
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
						Ellipse tempEllipse = (Ellipse) shapeFactory.getClone(ellipse);
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
						Rectangle tempRect = (Rectangle)shapeFactory.getClone(rect);
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
		 
		 @Override
		 public void mouseClicked(MouseEvent me){
			 int xCord = me.getX();
			 int yCord = me.getY();
			 Shape shape;
			 int pos = 0;
			 for(Shape s : model.getShapeList()){
				 pos++;
				 if((xCord > s.getX() && xCord < (s.getX()+s.getWidth())) && (yCord > s.getY()+menuBar.getHeight() && yCord < s.getY()+s.getHeight()+menuBar.getHeight())){
					 mainView.editBox();
					 shape = s;
					 break;
				 }
			 }
			 
		 }
	 }
	 
	 
	 private class ActionListenForShapeComboBox implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			@SuppressWarnings("unchecked")
			JComboBox<String> tempCb = (JComboBox<String>)e.getSource();
			shapeInUse = (String) tempCb.getSelectedItem();
			System.out.println(shapeInUse);
		}
	 }
	 
	 private class ActionListenForColorComboBox implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			@SuppressWarnings("unchecked")
			JComboBox<String> tempCb = (JComboBox<String>)e.getSource();
			String color = (String) tempCb.getSelectedItem();
			switch (color) {
			case "Black":
				paintColor = colorFactory.getColor(ShapeColorEnum.BLACK).paint();
				break;
			case "Blue":
				paintColor = colorFactory.getColor(ShapeColorEnum.BLUE).paint();
				break;
			case "Green":
				paintColor = colorFactory.getColor(ShapeColorEnum.GREEN).paint();
				break;
			case "Yellow":
				paintColor = colorFactory.getColor(ShapeColorEnum.YELLOW).paint();
				break;
			case "Red":
				paintColor = colorFactory.getColor(ShapeColorEnum.RED).paint();
				break;
				
			default:
				break;
			}
			
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
