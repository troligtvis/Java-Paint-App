package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBarView extends JMenuBar{

	public MenuBarView(){
		JMenu file = new JMenu("File");
		JMenuItem menuItemNew = new JMenuItem("New");
		JMenuItem menuItemSave = new JMenuItem("Save");
		JMenuItem menuItemLoad = new JMenuItem("Load");
		
		JMenu edit = new JMenu("Edit");
		JMenuItem menuItemUndo = new JMenuItem("Undo");
		JMenuItem menuItemRedo = new JMenuItem("Redo"); 
		
		file.add(menuItemNew);
		file.add(menuItemSave);
		file.add(menuItemLoad);
		edit.add(menuItemUndo);
		edit.add(menuItemRedo);
		this.add(file);
		this.add(edit);
	}
	
}
