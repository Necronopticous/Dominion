package com.necronopticous.splendor;

public class Tile {
	
	private int points;
	private int[] cost;
	
	private Tile() {}
	
	public Tile(int points, int greenCost, int blueCost, int redCost, int whiteCost, int blackCost) {
		this.points = points;
		this.cost = new int[]{greenCost, blueCost, redCost, whiteCost, blackCost};
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
	
}
