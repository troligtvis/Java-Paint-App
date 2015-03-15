package model;

public interface Action {

	public void undo();
	
	public void redo();
	
	public void undoWithIndex(int shapePos);
	
}
