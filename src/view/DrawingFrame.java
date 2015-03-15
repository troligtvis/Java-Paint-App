package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import observer.Observer;
import model.PaintMainModel;
import model.Shape;

public class DrawingFrame extends JPanel{
	
	private static final long serialVersionUID = 1L;

	PaintMainModel model;
	
	public DrawingFrame(PaintMainModel model){
		this.model = model;
		setBackground(Color.WHITE);
	}
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for(Shape s: model.getShapeList()){
        	s.paint(g2);
        }
    }
	
}
