package model;


// Factory Design Pattern
public class ColorFactory {

	public Color getColor(String ColorType){
		if(ColorType == null){ return null; }

		if(ColorType.equalsIgnoreCase("Yellow")){
			return new Yellow();
		} else if(ColorType.equalsIgnoreCase("Blue")){
			return new Blue();
		} else if(ColorType.equalsIgnoreCase("Green")){
			return new Green();
		} else if(ColorType.equalsIgnoreCase("Red")){
			return new Red();
		}
		
		return null;
	}
	
}
