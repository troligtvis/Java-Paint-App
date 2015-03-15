package model;

import java.awt.Color;

public class Blue implements ShapeColor{

	@Override
	public Color paint() {
		System.out.println("Fix blue color");
		return Color.BLUE;
	}

}
