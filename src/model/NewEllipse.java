package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class NewEllipse implements NewShape{
	
	private double x;
	private double y;
	private double width;
	private double height;
	private Color color;
	private float shapeThickness;
	
	public NewEllipse(){
		System.out.println("New Ellipse is made");
	}
	
	@Override
	public NewShape makeCopy() {
		System.out.println("New Ellipse is being made");
		NewEllipse ellipseObj = null;
		try{
			ellipseObj = (NewEllipse) super.clone();
		} catch(CloneNotSupportedException e){
			e.printStackTrace();
		}

		return ellipseObj;
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
		g.draw(new Ellipse2D.Float((int)x, (int)y, (int)width, (int)height));
	}

}
