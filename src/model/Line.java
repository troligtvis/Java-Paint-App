package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class Line extends Shape{

	private double x;
	private double y;
	private double x2;
	private double y2;
	private Color color;
	private float shapeThickness;
	
	public Line(double x, double y, double x2, double y2, Color color, float shapeThickness) {
		super(x, y, color, shapeThickness);
		this.x = x;
		this.y = y;
		this.x2 = x2;
		this.y2 = y2;
		this.color = color;
		this.shapeThickness = shapeThickness;
	}
	
	public double getX2() {
		return x2;
	}

	public void setX2(double x2) {
		this.x2 = x2;
	}

	public double getY2() {
		return y2;
	}

	public void setY2(double y2) {
		this.y2 = y2;
	}

	@Override
	public void paint(Graphics2D g) {
		g.setColor(color);
		g.setStroke(new BasicStroke(shapeThickness));
		g.draw(new Line2D.Float((int)x, (int)y, (int)x2, (int)y2));
		
	}

}
