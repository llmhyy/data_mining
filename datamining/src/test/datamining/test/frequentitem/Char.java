package datamining.test.frequentitem;

import datamining.frequentitem.IItem;

public class Char implements IItem{

	private char character;
	
	/**
	 * @param character
	 */
	public Char(char character) {
		super();
		this.character = character;
	}
	
	public String toString(){
		return String.valueOf(this.character);
	}
	
	/**
	 * @return the character
	 */
	public char getCharacter() {
		return character;
	}

	/**
	 * @param character the character to set
	 */
	public void setCharacter(char character) {
		this.character = character;
	}

	@Override
	public boolean equalItem(IItem item) {
		return equals(item);
	}

	@Override
	public boolean equals(Object item) {
		if(item instanceof Char){
			Char thatChar = (Char)item;
			return thatChar.getCharacter() == this.character;
		}
		
		return false;
	}
}
