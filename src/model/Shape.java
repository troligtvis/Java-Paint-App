package model;

import java.awt.Graphics2D;

public interface Shape extends Cloneable {
	public Shape makeCopy();
	public void paint(Graphics2D g); 
}