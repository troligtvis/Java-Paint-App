package model;

import java.awt.Color;

public class Yellow implements ShapeColor {

	@Override
	public Color paint() {
		System.out.println("Fix yellow color");
		
		return Color.YELLOW;
	}

}
