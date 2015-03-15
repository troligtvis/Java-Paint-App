package model;

import java.awt.Color;

public class Green implements ShapeColor{

	@Override
	public Color paint() {
		System.out.println("Fix green color");
		
		return Color.GREEN;
		
	}

}
