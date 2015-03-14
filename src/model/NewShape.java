package model;

import java.awt.Graphics2D;

public interface NewShape extends Cloneable {
	public NewShape makeCopy();
	public void paint(Graphics2D g); 
}
