package dominion.game.state;

import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;

import dominion.card.Card;
import dominion.card.type.Action;
import dominion.card.type.Category;
import dominion.card.type.Type;

public class Pile extends Stack<Card> {
	
	private static final long serialVersionUID = 7155923307343748517L;
	
	/**
	 * Draw a Card from the top of this pile and place it on the top of @param pile.
	 */
	public void draw(Pile pile) {
		if(!this.isEmpty()) pile.push(this.pop());
	}
	
	/**
	 * Draw Cards from the top of this pile and place them on the top of @param pile
	 * until the specified @param amount is reached, or this pile is emptied.
	 */
	public void draw(Pile pile, int amount) {
		for(int i = 0; i < amount; i++) {
			if(this.isEmpty()) break;
			this.draw(pile);
		}
	}
	
	public void drawAll(Pile pile) {
		while(!this.isEmpty()) this.draw(pile);
	}
	
	/**
	 * Extract all Cards from this Pile of @param type into a new @return Pile.
	 */
	public Pile extract(Type type) {
		Pile pile = new Pile();
		Iterator<Card> iterator = this.iterator();
		while(iterator.hasNext()) {
			Card card = iterator.next();
			if(card.getType() == type) {
				iterator.remove();
				pile.add(card);
			}
		}
		return pile;
	}
	
	/**
	 * Extract all Cards from this Pile of @param category into a new @return Pile.
	 */
	public Pile extract(Category category) {
		Pile pile = new Pile();
		Iterator<Card> iterator = this.iterator();
		while(iterator.hasNext()) {
			Card card = iterator.next();
			if(card.getType().category() == category) {
				iterator.remove();
				pile.add(card);
			}
		}
		return pile;
	}
	
	/**
	 * Extract all Cards from this Pile having an action into a new @return Pile.
	 */
	public Pile extractActionCards() {
		Pile pile = new Pile();
		Iterator<Card> iterator = this.iterator();
		while(iterator.hasNext()) {
			Card card = iterator.next();
			if(card.getType().action() != Action.NONE) {
				iterator.remove();
				pile.add(card);
			}
		}
		return pile;
	}
	
	public void shuffle() {
		Collections.shuffle(this, GameState.getInstance().getRandom());
	}
	
	public String toString() {
		String str = "";
		for(int i = 0; i < this.size(); i++) {
			str += i + ". " + this.get(i) + "\n";
		}
		return str;
	}

}
