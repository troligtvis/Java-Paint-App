package model;

import java.awt.Color;

public class Red implements ShapeColor {

	@Override
	public Color paint() {
		System.out.println("Fix red color");
		
		return Color.RED;
	}
	
}
