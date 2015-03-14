package model;

public class NewShapeFactory {

	public NewShape getClone(NewShape s){
		return s.makeCopy();
	}
}
