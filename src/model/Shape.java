package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public abstract class Shape {

	private double x;
	private double y;
	private Color color;
	private float shapeThickness;
	
	public Shape(double x, double y, Color color, float shapeThickness){
		this.x = x;
		this.y = y;
		this.color = color;
		this.shapeThickness = shapeThickness;
	}
	
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public float getShapeThickness() {
		return shapeThickness;
	}

	public void setShapeThickness(float shapeThickness) {
		this.shapeThickness = shapeThickness;
	}


	abstract public void paint(Graphics2D g);
	
}
