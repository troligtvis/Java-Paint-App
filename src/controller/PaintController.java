package controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JSlider;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	private Shape tempShape;
	private int arrayPos=0;
	private Color tempPaintColor;
	
	public PaintController(){
		model = new PaintMainModel();
		mainView = new JavaMainView(model);
		observers.add(mainView);
		menuBar = mainView.getMenuBar();
		
		ActionListenerEditShape listenerEditShape = new ActionListenerEditShape();
		mainView.getDeleteBtn().addActionListener(listenerEditShape);
		mainView.getConfirmChangeBtn().addActionListener(listenerEditShape);
		
		
		setMenuBarItemListeners();
		ListenForSlider lstForSlid = new ListenForSlider();
		mainView.getShapeThickness().addChangeListener(lstForSlid);
		ActionListenForColorComboBox actLstForColorCombBox = new ActionListenForColorComboBox();
		mainView.getColorComboBoxEdit().addActionListener(actLstForColorCombBox);
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
				
				JFileChooser chooser = new JFileChooser();
				chooser.setCurrentDirectory(new File("C:\\Users\\Dancii\\Desktop"));
				int retrival = chooser.showSaveDialog(null);
				
				if(retrival == JFileChooser.APPROVE_OPTION){
					ObjectOutputStream oos = null;
					try{
						
					
						try{
							FileOutputStream fop = new FileOutputStream(chooser.getSelectedFile()+".save");
							oos = new ObjectOutputStream(fop);
							oos.writeObject(model); //Maybe not work
						}finally{
							oos.close();
						}
					}catch(Exception e1){
						System.out.println(e1);
					}
				}
				
				break;
			case "Load":
				
				JFileChooser choose = new JFileChooser();
				
				int returnname = choose.showOpenDialog(null);
				
				if(returnname == JFileChooser.APPROVE_OPTION){
					ObjectInputStream ois = null;
					try{
						try{
							FileInputStream fin = new FileInputStream(choose.getSelectedFile().getPath());
							ois = new ObjectInputStream(fin);
							model = (PaintMainModel) ois.readObject();
							mainView.setModel(model);
							notifyAllObservers();
						}finally{
							if(ois!=null){
								ois.close();
							}
						}
					}catch(Exception e2){
						System.out.println(e2);
					}
				}
				
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
						Shape tempEllipse = (Ellipse) shapeFactory.getClone(ellipse);
						tempEllipse.setX(Math.min(shapeStart.x, me.getX()));
						tempEllipse.setY(Math.min(shapeStart.y, me.getY()));
						tempEllipse.setWidth(Math.abs(shapeStart.x - me.getX()));
						tempEllipse.setHeight(Math.abs(shapeStart.y - me.getY()));
						tempEllipse.setColor(paintColor);
						tempEllipse.setShapeThickness(shapeThicknessVal);
						tempEllipse.setFilled(mainView.getIsFilledNew().isSelected());
						model.addShape(tempEllipse);
						break;
						
					case "Rectangle":
						//Draw a rect
						Shape tempRect = (Rectangle)shapeFactory.getClone(rect);
						tempRect.setX(Math.min(shapeStart.x, me.getX()));
						tempRect.setY(Math.min(shapeStart.y, me.getY()));
						tempRect.setWidth(Math.abs(shapeStart.x - me.getX()));
						tempRect.setHeight(Math.abs(shapeStart.y - me.getY()));
						tempRect.setColor(paintColor);
						tempRect.setShapeThickness(shapeThicknessVal);
						tempRect.setFilled(mainView.getIsFilledNew().isSelected());
						model.addShape(tempRect);
						break;
					default:
						break;
					} 
			 }
			 System.out.println("drawing shapes");
			 notifyAllObservers();
		 }
		 
		 @Override
		 public void mouseClicked(MouseEvent me){
			 int xCord = me.getX();
			 int yCord = me.getY();
			 arrayPos = 0;
			 for(Shape s : model.getShapeList()){
				 arrayPos++;
				 if((xCord > s.getX() && xCord < (s.getX()+s.getWidth())) && (yCord > s.getY()+menuBar.getHeight() && yCord < s.getY()+s.getHeight()+menuBar.getHeight())){
					 tempShape = s;
					 
					 mainView.editBox();
					 mainView.setDialogEditValues(tempShape.getColor(), tempShape.isFilled(), tempShape.getShapeThickness());
					 System.out.println(tempShape.isFilled());
					 break;
				 }
			 }
			 
		 }
	 }
	 
	 private class ActionListenerEditShape implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton tempBtn = (JButton) e.getSource();
				String btnPressed = tempBtn.getText();
				
				switch (btnPressed) {
				case "Change":
					Shape tempShape = model.getShapeList().get(arrayPos-1);
					tempShape.setColor(paintColor);
					tempShape.setShapeThickness((float)(mainView.getThicknessEdit().getValue() * .1));
					tempShape.setFilled(mainView.getIsFilledEdit().isSelected());
					notifyAllObservers();
					paintColor = tempPaintColor;
					mainView.getjDialogEdit().dispose();
					break;
				case "Remove":
					model.undoWithIndex(arrayPos-1);
					notifyAllObservers();
					mainView.getjDialogEdit().dispose();
					break;

				default:
					break;
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
			String instanceOfCB = tempCb.getName();
			tempPaintColor = paintColor;
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
