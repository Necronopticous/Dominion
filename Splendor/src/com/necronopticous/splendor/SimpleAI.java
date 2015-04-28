package com.necronopticous.splendor;

import java.util.ArrayList;
import java.util.List;

public class SimpleAI extends Player {

	public SimpleAI() {
		super("Simple AI", false);
	}

	@Override
	public void takeTurn(Game game) {
		boolean success = false;
		for (Card card : getReserve()) {
			success = game.purchase(card);
			if (success) return;
		}
		for (Card card : game.getL3Deck()) {
			success = game.purchase(card);
			if (success) return;
		}
		for (Card card : game.getL2Deck()) {
			success = game.purchase(card);
			if (success) return;
		}
		for (Card card : game.getL1Deck()) {
			success = game.purchase(card);
			if (success) return;
		}
		
		int topCount = 0;
		int topIndex = getTemp().length - 1;
		int secondCount = 0;
		int secondIndex = getTemp().length - 2;
		for (int i = 0; i < getTemp().length; i++) {
			int count = getTemp()[i];
			if (count > topCount) {
				secondCount = topCount;
				secondIndex = topIndex;
				topCount = count;
				topIndex = i;
			} else if (count > secondCount) {
				secondCount = count;
				secondIndex = i;
			}
		}
		List<Color> choices = new ArrayList<Color>();
		for (int i = 0; i < getTemp().length; i++) {
			if (i != topIndex && i != secondIndex) {
				choices.add(Color.getByIndex(i));
			}
		}
		success = game.take(choices.toArray(new Color[0]));
		if (success) return;
		
		success = game.reserve(game.getL2Deck().get(0));
	}

	@Override
	public Color discardToken() {
		int topCount = 0;
		int topIndex = getTemp().length - 1;
		for (int i = 0; i < getTemp().length; i++) {
			int count = getTemp()[i];
			if (count > topCount) {
				topCount = count;
				topIndex = i;
			}
		}
		return Color.getByIndex(topIndex);
	}

	@Override
	public Tile chooseTile(List<Tile> choices) {
		return choices.get(0);
	}

}
