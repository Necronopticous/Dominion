package com.necronopticous.splendor;

public class Card {
	
	private int level;
	private Color color;
	private int points;
	private int[] cost;
	
	private Card() {}
	
	public Card(int level, Color color, int points, int greenCost, int blueCost, int redCost, int whiteCost, int blackCost) {
		this.level = level;
		this.color = color;
		this.points = points;
		this.cost = new int[]{greenCost, blueCost, redCost, whiteCost, blackCost};
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int[] getCost() {
		return cost;
	}

	public void setCost(int[] cost) {
		this.cost = cost;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
