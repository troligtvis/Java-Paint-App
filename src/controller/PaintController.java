package controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSlider;

import model.Ellipse;
import model.Line;
import model.Shape;
import model.Rectangle;
import view.ButtonMenuView;

import java.awt.event.*;
import java.util.ArrayList;

public class PaintController extends JLayeredPane{
	
	private static final long serialVersionUID = 1L;
	
	private ButtonMenuView btnMenView = new ButtonMenuView();
	private JButton[] btnArray = new JButton[3];
	private JPanel btnPanel = new JPanel();
	private MyMouseAdapter myMouseAdapter = null;
	private int currentShape = 1;
	private JSlider shapeThickness;
	ArrayList<Shape> shapes = new ArrayList<Shape>();
	
	public PaintController(){
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		
		btnArray = btnMenView.getButtonArray();
		btnPanel = btnMenView.getButtonPanel();
		shapeThickness = btnMenView.getShapeThicknessSlider();
		this.add(btnPanel,BorderLayout.SOUTH);
		
		myMouseAdapter = new MyMouseAdapter();
		setBtnListener();
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

	
	private void setBtnListener(){
		for(int i=0;i<3;i++){
			btnArray[i].addMouseListener(myMouseAdapter);
		}
	}
	
	 private class MyMouseAdapter extends MouseAdapter{
		 
		 Point shapeStart, shapeEnd;
		 
		 @Override
		 public void mousePressed(MouseEvent me) {
			 String compID = me.getComponent().getName();
			 
			 switch (compID) {
			case "Line":
				System.out.println("This is a line");
				currentShape = 1;
				shapeStart = null;
				break;
			case "Ellipse":
				System.out.println("This is a ellipse");
				currentShape = 2;
				shapeStart = null;
				break;
				
			case "Rectangle":
				System.out.println("This is a rect");
				currentShape = 3;
				shapeStart = null;
				break;
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
			 float shapeThicknessVal = shapeThickness.getValue();
			 if(shapeStart != null){ //Needed to prevent previous cords
				 switch (currentShape) {
					case 1:
						//Draw a line
						shapes.add(new Line(shapeStart.x, shapeStart.y, me.getX(), me.getY(), Color.BLACK, (float)(shapeThicknessVal/10)));
						
						break;
					case 2:
						//Draw a ellipse
						double widthEllipse = Math.abs(shapeStart.x - me.getX());
		                double heightEllipse = Math.abs(shapeStart.y - me.getY());
		                int xEllipse = Math.min(shapeStart.x, me.getX());
                        int yEllipse = Math.min(shapeStart.y, me.getY());
						shapes.add(new Ellipse(xEllipse,yEllipse, widthEllipse, heightEllipse, Color.BLACK, (float)(shapeThicknessVal/10)));
						break;
						
					case 3:
						//Draw a rect
						double width = Math.abs(shapeStart.x - me.getX());
		                double height = Math.abs(shapeStart.y - me.getY());
		                int xRect = Math.min(shapeStart.x, me.getX());
                        int yRect = Math.min(shapeStart.y, me.getY());
						shapes.add(new Rectangle(xRect,yRect, width, height, Color.BLACK, (float)(shapeThicknessVal/10)));
						break;
					default:
						break;
					} 
			 }
			 
			 repaint();
		 }
		 
		 
	 }
	
	
}
