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
	
	public ArrayList<Shape> getUndo() {
		return undo;
	}

	public void addShape(Shape shape){
		shapes.add(shape);
	}
	
	@Override
	public void undo(){
		if(shapes.size() > 0){
			undo.add(shapes.remove(shapes.size()-1));
		}
	}
	
	@Override
	public void redo(){
		if(undo.size() > 0){
			shapes.add(undo.remove(undo.size()-1));
		}

	}
	
	public void newCanvas(){
		shapes.clear();
		undo.clear();
	}

	@Override
	public void undoWithIndex(int shapePos) {
		undo.add(shapes.remove(shapePos));
	}

}
