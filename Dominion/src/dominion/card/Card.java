package dominion.card;

import dominion.card.actions.Actions;
import dominion.card.type.Type;

public class Card {
	
	private Type type;
	
	public Card(Type type) {
		this.type = type;
	}
	
	public void performAction() {
		Actions.performAction(this);
	}
	
	public String toString() {
		return this.type.toString();
	}
	
	public Type getType() {
		return type;
	}
	
}
