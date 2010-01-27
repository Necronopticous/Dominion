package dominion.game.player;

import dominion.card.Card;
import dominion.game.state.Pile;

public class Player {
	
	private boolean human;
	
	private Pile deck;
	private Pile hand;
	private Pile discardPile; //TODO: Top card in discardPile is visible to all players
	private Pile playArea; //TODO: All cards in playArea are visible
	
	public Player(boolean human) {
		this.human = human;
		deck = new Pile();
		hand = new Pile();
		discardPile = new Pile();
		playArea = new Pile();
	}
	
	public void draw(int amount) {
		for(int i = 0; i < amount; i++) draw();
	}
	
	public Card draw() {
		if(deck.isEmpty()) shuffle();
		return deck.draw();
	}
	
	public void draw(Pile pile) {
		this.draw(pile);
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
	
	public Pile getPlayArea() {
		return playArea;
	}

	public boolean isHuman() {
		return human;
	}
	
}
