package model;

public class ShapeFactory extends AbstractFactory{

	@Override
	public Shape getClone(Shape s){
		return s.makeCopy();
	}

	@Override
	public ShapeColor getColor(ShapeColorEnum c) {
		return null;
	}

}
