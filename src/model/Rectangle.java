package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Shape{

	private double x;
	private double y;
	private double width;
	private double height;
	private Color color;
	private float shapeThickness;
	
	public Rectangle(double x, double y, double width, double height, Color color, float shapeThickness) {
		super(x, y, color, shapeThickness);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		this.shapeThickness = shapeThickness;
	}

	public double getWidth() {
		return width;
	}


	public void setWidth(double width) {
		this.width = width;
	}


	public double getHeight() {
		return height;
	}


	public void setHeight(double height) {
		this.height = height;
	}


	@Override
	public void paint(Graphics2D g) {
		g.setColor(color);
		g.setStroke(new BasicStroke(shapeThickness));
		g.draw(new Rectangle2D.Float((int)x, (int)y, (int)width, (int)height));
		
	}
	
}
