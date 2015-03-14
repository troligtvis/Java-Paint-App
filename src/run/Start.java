package run;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import controller.TheController;
import view.TheView;
import model.TheModel;

public class Start {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TheModel model = new TheModel();
		TheView view = new TheView(model);
		TheController controller = new TheController(model, view);
		
		view.registerListener(controller);
		
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.setSize(800, 600);
		view.setVisible(true);
	}

}
