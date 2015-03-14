package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;

import controller.TheController;
import model.TheModel;


public class TheView extends JFrame {
	private TheModel model;
	private PaintPanel mousePanel;
	private JList colorList;
	private JPanel listPanel;
	
	private static final String[] colorNames = {"Black", "Blue", "Cyan",
		"Dark Gray", "Gray", "Green", "Light Gray", "Magenta",
		"Orange", "Pink", "Red", "White", "Yellow"};
	
	private static final Color[] colors = {Color.BLACK, Color.BLUE,
		Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN, 
		Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, 
		Color.RED, Color.WHITE, Color.YELLOW};
	
	public TheView(TheModel model) {
		super("Illustrate Model-View-Controller");
		this.model = model;

		/* CENTER: Add a panel that the user can draw on. */
		mousePanel = new PaintPanel(model);
		mousePanel.setBackground(Color.WHITE);
		add(mousePanel, BorderLayout.CENTER);

		/* WEST: Add a list so the user can select a color. */
		listPanel = new JPanel();
		add(listPanel, BorderLayout.WEST);
		colorList = new JList(colorNames);
		colorList.setVisibleRowCount(5);
		colorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listPanel.add(new JScrollPane(colorList), BorderLayout.CENTER);
	}
	
	public void registerListener(TheController listener) {
		colorList.addListSelectionListener(listener);
		mousePanel.addMouseMotionListener(listener);
	}
	
	public Color getSelectedColor() {
		return colors[colorList.getSelectedIndex()];
	}
	
	public void paint(Graphics g) {
		listPanel.setBackground(model.getSelectedColor());
		super.paint(g); // This will paint the components.
	}
	
	
}
