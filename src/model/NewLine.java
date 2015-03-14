package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

public class NewLine implements NewShape{
	
	private double x;
	private double y;
	private double x2;
	private double y2;
	private Color color;
	private float shapeThickness;
	
	public NewLine(){
		System.out.println("New Line is made");
	}
	
	@Override
	public NewShape makeCopy() {
		System.out.println("New Line is being made");
		NewLine lineObj = null;
		try{
			lineObj = (NewLine) super.clone();
		} catch(CloneNotSupportedException e){
			e.printStackTrace();
		}

		return lineObj;
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

	@Override
	public void paint(Graphics2D g) {
		g.setColor(color);
		g.setStroke(new BasicStroke(shapeThickness));
		g.draw(new Line2D.Float((int)x, (int)y, (int)x2, (int)y2));
	}

}

