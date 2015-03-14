package model;

public class Undo implements Action{

	private Shape s;
	
	public Undo(Shape s){
		this.s = s;
	}
	
	@Override
	public void execute() {
		
	}

}
