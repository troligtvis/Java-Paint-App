package model;

import java.awt.*;


public class TheModel {

	private int pointCount;
	private Point[] points;
	private Color selectedColor;
	
	public TheModel(){
		pointCount = 0;
		points = new Point[100000];
		selectedColor = Color.BLACK;
	}
	
	public void addPoint(Point point){
		points[pointCount] = point;
		pointCount++;
	}
	
	public Point getPoint(int i){
		if(i >= 0 && i < pointCount){
			return points[i];
		}
		return null;
	}
	
	public void setSelectedColor(Color color){
		selectedColor = color;
	}
	
	public Color getSelectedColor(){
		return selectedColor;
	}
}
