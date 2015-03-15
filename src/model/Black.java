package model;

import java.awt.Color;

public class Black implements ShapeColor{

	@Override
	public Color paint() {
		System.out.println("fix black color");
		return Color.BLACK;
	}

}
