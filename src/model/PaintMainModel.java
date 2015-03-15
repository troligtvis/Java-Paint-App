package model;

import java.util.ArrayList;

public class PaintMainModel implements Action{
	
	private ArrayList<Shape> shapes;
	private ArrayList<Shape> undo;
	
	public PaintMainModel(){
		shapes = new ArrayList<Shape>();
		undo = new ArrayList<Shape>();
	}
	
	public ArrayList<Shape> getShapeList(){
		return shapes;
	}
	
	public void addShape(Shape shape){
		shapes.add(shape);
	}
	
	@Override
	public void undo(){
		undo.add(shapes.remove(shapes.size()-1));
	}
	
	@Override
	public void redo(){
		shapes.add(undo.remove(undo.size()-1));
	}
	
	public void newCanvas(){
		shapes.clear();
		undo.clear();
	}

}
