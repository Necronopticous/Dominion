package com.necronopticous.splendor;


public enum Color {
	
	GREEN(0,'@'),
	BLUE(1,'#'),
	RED(2,'$'),
	WHITE(3,'%'),
	BLACK(4,'&'),
	WILD(5,'*');
	
	private int index;
	private char symbol;
	
	private Color(int index, char symbol) {
		this.index = index;
		this.symbol = symbol;
	}

	public int getIndex() {
		return index;
	}

	public char getSymbol() {
		return symbol;
	}
	
	public static Color getByIndex(int index) {
		switch(index) {
		case 0: return GREEN;
		case 1: return BLUE;
		case 2: return RED;
		case 3: return WHITE;
		case 4: return BLACK;
		default: return WILD;
		}
	}
	
	public static Color getBySymbol(char symbol) {
		switch(symbol) {
		case '@': return GREEN;
		case '#': return BLUE;
		case '$': return RED;
		case '%': return WHITE;
		case '&': return BLACK;
		default: return WILD;
		}
	}
	
}
