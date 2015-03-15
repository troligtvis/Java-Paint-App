package model;

public abstract class AbstractFactory {

	public abstract ShapeColor getColor(ShapeColorEnum c);
	public abstract Shape getClone(Shape s);
	
}
