package dominion.card.actions;

import dominion.card.Card;
import dominion.card.type.Category;
import dominion.card.type.Type;
import dominion.game.player.Player;
import dominion.game.state.GameState;
import dominion.game.state.Pile;
import dominion.game.state.Turn;

public class Actions {
	
	public static void performAction(Card card) {
		performAction(card.getType());
	}
	
	public static void performAction(Type type) {
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
		case GARDENS:      gardens(); break;
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
	
	private static void adventurer() {
		Player player = getCurrentPlayer();
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
	
	private static void bureaucrat() {
		//TODO
	}
	
	private static void cellar() {
		int discarded = 0;
		//TODO allow player to discard cards from hand for a discarded++ each.
		for(int i = 0; i < discarded; i++) getCurrentPlayer().draw();
	}
	
	private static void chancellor() {
		boolean choice = true;
		//TODO uses chooses
		if(choice) {
			getCurrentPlayer().getDeck().drawAll(getCurrentPlayer().getDiscardPile());
		}
	}
	
	private static void chapel() {
		
	}
	
	private static void councilRoom() {
		
	}
	
	private static void feast() {
		
	}
	
	private static void festival() {
		
	}
	
	private static void gardens() {
		
	}
	
	private static void laboratory() {
		
	}
	
	private static void library() {
		
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
	private static Turn getCurrentTurn() {
		return GameState.getInstance().getCurrentTurn();
	}
	
	private static Player getCurrentPlayer() {
		return getCurrentTurn().getPlayer();
	}
	
	private static void cardsPlus(int bonus) {
		for(int i = 0; i < bonus; i++) {
			getCurrentPlayer().draw();
		}
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
