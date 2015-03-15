package model;

import java.awt.Color;
import java.awt.Graphics2D;

public interface Shape extends Cloneable {
	public Shape makeCopy();
	public void paint(Graphics2D g); 
	public double getX();
	public double getY();
	public double getWidth();
	public double getHeight();
	public Color getColor();
	public float getShapeThickness();
	public boolean isFilled();
	public void setFilled(boolean isFilled);
	public void setX(double X);
	public void setY(double Y);
	public void setWidth(double width);
	public void setHeight(double height);
	public void setColor(Color color);
	public void setShapeThickness(float shapeThickness);
}