package controller;

import java.awt.*;
import java.awt.event.*;

import javax.swing.event.*;

import view.TheView;
import model.TheModel;


public class TheController implements ListSelectionListener, MouseMotionListener {

	private TheModel model;
	private TheView view;
	
	public TheController(TheModel model, TheView view){
		this.model = model;
		this.view = view;
	}
	
	public void mouseDragged(MouseEvent event){
		Point point = event.getPoint();
		model.addPoint(point);
		view.repaint();
	}
	
	public void mouseMoved(MouseEvent event){
		
	}

	
	public void mouseReleased(MouseEvent e) {
		System.out.println("Something RELEASED");
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		Color color = view.getSelectedColor();
		model.setSelectedColor(color);
		view.repaint();
	}

}
