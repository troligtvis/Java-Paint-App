package start;

import java.awt.Color;

import javax.swing.JFrame;

import controller.PaintController;


public class StartProgram {

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	createAndShowUI();
            }
        });
	}
	
	private static void createAndShowUI(){
		JFrame frame = new JFrame();
		frame.setResizable(true);
		frame.setSize(800,600);
		frame.setTitle("Java Paint");
		frame.setBackground(Color.WHITE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(new PaintController());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}

}
