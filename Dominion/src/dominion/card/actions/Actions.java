package dominion.card.actions;

import java.util.ArrayList;
import java.util.List;

import dominion.card.Card;
import dominion.card.type.Action;
import dominion.card.type.Category;
import dominion.card.type.Type;
import dominion.game.player.Player;
import dominion.game.state.GameState;
import dominion.game.state.Pile;
import dominion.game.state.Turn;

public class Actions {
	
	private static Card playedCard;
	
	public static void performAction(Card card) {
		playedCard = card;
		performAction(card.getType());
	}
	
	private static void performAction(Type type) {
		cardsPlus(type.cardBonus());
		actionsPlus(type.actionBonus());
		buysPlus(type.buyBonus());
		coinsPlus(type.coinBonus());
		
		switch(type) {
		case ADVENTURER:   adventurer(); break;
		case BUREAUCRAT:   bureaucrat(); break;
		case CELLAR:       cellar(); break;
		case CHANCELLOR:   chancellor(); break;
		case CHAPEL:       chapel(); break;
		case COUNCIL_ROOM: councilRoom(); break;
		case FEAST:        feast(); break;
		case FESTIVAL:     festival(); break;
		case LABORATORY:   laboratory(); break;
		case LIBRARY:      library(); break;
		case MARKET:       market(); break;
		case MILITIA:      militia(); break;
		case MINE:         mine(); break;
		case MOAT:         moat(); break;
		case MONEYLENDER:  moneylender(); break;
		case REMODEL:      remodel(); break;
		case SMITHY:       smithy(); break;
		case SPY:          spy(); break;
		case THIEF:        thief(); break;
		case THRONE_ROOM:  throneRoom(); break;
		case VILLAGE:      village(); break;
		case WITCH:        witch(); break;
		case WOODCUTTER:   woodcutter(); break;
		case WORKSHOP:     workshop(); break;
		default: break;
		}
	}
	
	//----- Action Methods -----//
	/**
	 * Reveal cards from your deck until you reveal 2 Treasure cards.
	 * Put those Treasure cards in your hand and discard the other revealed cards.
	 */
	private static void adventurer() {
		Player player = getCurrentPlayer();
		//TODO: Actually make revealed cards revealed
		Pile revealedCards = new Pile();
		int treasureRevealed = 0;
		while(treasureRevealed < 2) {
			if(player.getDeck().isEmpty()) {
				if(player.getDiscardPile().isEmpty()) break;
				else player.shuffle();
			}
			if(player.getDeck().peek().getType().category() == Category.TREASURE) {
				player.getDeck().draw(player.getHand());
				treasureRevealed++;
			} else {
				player.getDeck().draw(revealedCards);
			}
		}
		revealedCards.drawAll(getCurrentPlayer().getDiscardPile());
	}
	
	/**
	 * Gain a silver card; put it on top of your deck.
	 * Each other player reveals a Victory card from his hand and puts it on his deck (or reveals a hand with no Victory cards).
	 */
	private static void bureaucrat() {
		getGameState().getSupplies().get(Type.SILVER).draw(getCurrentPlayer().getDeck());
		
		for(Player player : getOtherPlayers()) {
			if(player.getHand().contains(Category.VICTORY)) {
				//TODO: Player chooses a victory card to reveal and draw to deck
			} else {
				//TODO: Player reveals hand
			}
		}
	}
	
	/**
	 * Discard any number of cards.
	 * +1 Card per card discarded.
	 */
	private static void cellar() {
		int discarded = 0;
		//TODO allow player to discard cards from hand for a discarded++ each.
		for(int i = 0; i < discarded; i++) getCurrentPlayer().draw();
	}
	
	/**
	 * You may immediately put your deck into your discard pile.
	 */
	private static void chancellor() {
		boolean choice = true;
		//TODO user chooses
		if(choice) {
			getCurrentPlayer().getDeck().drawAll(getCurrentPlayer().getDiscardPile());
		}
	}
	
	/**
	 * Trash up to 4 cards from your hand.
	 */
	private static void chapel() {
		//TODO: user chooses cards from hand. These cards get drawn to trash
	}
	
	/**
	 * Each other player draws a card.
	 */
	private static void councilRoom() {
		for(Player player : getOtherPlayers()) {
			player.draw();
		}
	}
	
	/**
	 * Trash this card. Gain a card costing up to 5 coins.
	 */
	private static void feast() {
		getCurrentPlayer().getPlayArea().move(playedCard, getGameState().getTrash());
		Type selected = null;
		//TODO: user selects a type from buyOptions
		getGameState().getBuyOptions(5);
		getGameState().getSupplies().get(selected).draw(getCurrentPlayer().getDiscardPile());
	}
	
	/**
	 * No extended action.
	 */
	private static void festival() {
		
	}
	
	/**
	 * No extended action.
	 */
	private static void laboratory() {
		
	}
	
	/**
	 * Draw until you have 7 cards in hand. 
	 * You may set aside any Action cards drawn this way, as you draw them;
	 * discard the set aside cards after you finish drawing.
	 */
	private static void library() {
		Pile setAside = new Pile();
		while(getCurrentPlayer().getHand().size() < 7) {
			if(getCurrentPlayer().getDeck().peek().getType().action() != Action.NONE) {
				//TODO: user chooses whether to draw to hand or setAside
			} else {
				getCurrentPlayer().getDeck().draw(getCurrentPlayer().getHand());
			}
		}
		setAside.drawAll(getCurrentPlayer().getDiscardPile());
	}
	
	private static void market() {
		
	}
	
	private static void militia() {
		
	}
	
	private static void mine() {
		
	}
	
	private static void moat() {
		
	}
	
	private static void moneylender() {
		
	}
	
	private static void remodel() {
		
	}
	
	private static void smithy() {
		
	}
	
	private static void spy() {
		
	}
	
	private static void thief() {
		
	}
	
	private static void throneRoom() {
		
	}
	
	private static void village() {
		
	}
	
	private static void witch() {
		
	}
	
	private static void woodcutter() {
		
	}
	
	private static void workshop() {
		
	}
	
	//----- Convenience Methods -----//
	private static GameState getGameState() {
		return GameState.getInstance();
	}
	
	private static Turn getCurrentTurn() {
		return GameState.getInstance().getCurrentTurn();
	}
	
	private static Player getCurrentPlayer() {
		return getCurrentTurn().getPlayer();
	}
	
	private static List<Player> getOtherPlayers() {
		List<Player> otherPlayers = new ArrayList<Player>();
		//TODO: Get other players;
		return otherPlayers;
	}
	
	private static void cardsPlus(int bonus) {
		getCurrentPlayer().draw(bonus);
	}
	
	private static void actionsPlus(int bonus) {
		getCurrentTurn().actionsPlus(bonus);
	}
	
	private static void buysPlus(int bonus) {
		getCurrentTurn().buysPlus(bonus);
	}
	
	private static void coinsPlus(int bonus) {
		getCurrentTurn().coinsPlus(bonus);
	}
	
}
