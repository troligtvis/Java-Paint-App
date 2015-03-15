package model;

public class ShapeFactory {

	public Shape getClone(Shape s){
		return s.makeCopy();
	}
}
