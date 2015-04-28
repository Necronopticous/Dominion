package com.necronopticous.splendor;

import java.util.ArrayList;
import java.util.List;

public abstract class Player {
	
	private String name;
	private boolean human;
	private int points;
	private List<Card> reserve;
	private int[] temp;
	private int[] perm;
	
	private Player() {}
	
	public Player(String name, boolean human) {
		this.points = 0;
		this.reserve = new ArrayList<Card>();
		this.temp = new int[]{0, 0, 0, 0, 0, 0};
		this.perm = new int[]{0, 0, 0, 0, 0};
		this.name = name;
		this.human = human;
	}
	
	public abstract void takeTurn(Game game);
	
	public abstract Color discardToken();
	
	public abstract Tile chooseTile(List<Tile> choices);

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public List<Card> getReserve() {
		return reserve;
	}

	public void setReserve(List<Card> reserve) {
		this.reserve = reserve;
	}

	public int[] getTemp() {
		return temp;
	}

	public void setTemp(int[] temp) {
		this.temp = temp;
	}

	public int[] getPerm() {
		return perm;
	}

	public void setPerm(int[] perm) {
		this.perm = perm;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isHuman() {
		return human;
	}

	public void setHuman(boolean human) {
		this.human = human;
	}

}
