package model;

public class FactoryProducer {

	public static AbstractFactory getFactory(String choice){
		
		switch (choice) {
		case "Shape":
			return new ShapeFactory();
		case "Color":
			return new ColorFactory();
		default:
			return null;
		}
		
	}
	
}
