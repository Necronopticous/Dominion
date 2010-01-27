package dominion.card;

import dominion.card.actions.Actions;
import dominion.card.type.Type;
import dominion.game.player.Player;
import dominion.game.state.GameState;
import dominion.game.state.Turn;

public class Card {
	
	private Type type;
	
	public Card(Type type) {
		this.type = type;
	}
	
	public void play() {
		switch(this.type.category()) {
		case TREASURE: playTreasure(); break;
		case KINGDOM: playKingdom(); break;
		default: break;
		}
	}
	
	private void playTreasure() {
		this.moveToPlayArea();
		getCurrentTurn().coinsPlus(this.type.treasure());
	}
	
	private void playKingdom() {
		this.moveToPlayArea();
		Actions.performAction(this);
	}
	
	private void moveToPlayArea() {
		getCurrentPlayer().getHand().move(this, getCurrentPlayer().getPlayArea());
	}
	
	private GameState getGameState() {
		return GameState.getInstance();
	}
	
	private Turn getCurrentTurn() {
		return getGameState().getCurrentTurn();
	}
	
	private Player getCurrentPlayer() {
		return getGameState().getCurrentPlayer();
	}
	
	public String toString() {
		return this.type.toString();
	}
	
	public Type getType() {
		return type;
	}
	
}
