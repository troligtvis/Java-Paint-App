package model;


public class ShapeFactory {

	public Shape getShape(ShapeEnum s){
		switch (s) {
		case RECTANGLE:
			return new Rectangle();
		case LINE:
			return new Line();
		case ELLIPSE:
			return new Ellipse();
		default:
			return null;
		}
	}
	
}
