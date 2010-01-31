package dominion.game.player;

import java.util.Collections;

import dominion.game.state.Pile;

public class Player {
	
	private boolean human;
	
	private Pile deck;
	private Pile hand;
	private Pile discardPile;
	
	public Player(boolean human) {
		this.human = human;
		deck = new Pile();
		hand = new Pile();
		discardPile = new Pile();
	}
	
	public void draw(int amount) {
		for(int i = 0; i < amount; i++) draw();
	}
	
	public void draw() {
		if(deck.isEmpty()) shuffle();
		deck.draw(hand);
	}
	
	public void shuffle() {
		discardPile.shuffle();
		discardPile.drawAll(deck);
	}

	public Pile getDeck() {
		return deck;
	}

	public Pile getHand() {
		return hand;
	}

	public Pile getDiscardPile() {
		return discardPile;
	}

	public boolean isHuman() {
		return human;
	}
	
}
