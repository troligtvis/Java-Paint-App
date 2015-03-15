package model;

public class ColorFactory extends AbstractFactory{

	@Override
	public ShapeColor getColor(ShapeColorEnum c){
		switch (c) {
		case BLACK:
			return new Black();
		case GREEN:
			return new Green();
		case RED:
			return new Red();
		case YELLOW:
			return new Yellow();
		case BLUE:
			return new Blue();
		default:
			return null;
		}
	}

	@Override
	public Shape getClone(Shape s) {
		return null;
	}
	
}
