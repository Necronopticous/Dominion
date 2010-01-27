package dominion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dominion.card.Card;
import dominion.card.type.Type;
import dominion.game.player.Player;
import dominion.game.state.GameState;
import dominion.game.state.Pile;
import dominion.game.state.Turn;
import dominion.settings.Constants;

public class Dominion {
	
	public static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		getGameState().reset(4);
		
		boolean endGame = false;
		
		while(!endGame) {
			for(Player player : getGameState().getPlayers()) {
				System.out.println("Player " + player);
				getGameState().getTurns().add(new Turn(player));
				action();
				buy();
				cleanup();
			}
		}
	}
	
	public static void action() {
		System.out.println("----- Action Phase -----");
		Pile actionCards = getCurrentPlayer().getHand().extractActionCards();
		
		int performed = 0;
		
		while(!actionCards.isEmpty() && performed < getCurrentTurn().getActions()) {
			System.out.println((getCurrentTurn().getActions()-performed) + " action(s) remaining.");
			System.out.print(actionCards + "\nPlease select your action (-1 to end): ");
			int selection = scanner.nextInt();
			Card card = actionCards.remove(selection);
			card.performAction();
			getCurrentPlayer().getDiscardPile().push(card);
			performed++;
		}
	}
	
	public static void buy() {
		System.out.println("----- Buy Phase -----");
		List<Type> buyOptions = new ArrayList<Type>();
		
		//Add all treasure value to current turn
		for(Card card : getCurrentPlayer().getHand()) {
			getCurrentTurn().coinsPlus(card.getType().treasure());
		}
		
		int spent = 0;
		
		for(int i = 0; i < getCurrentTurn().getBuys(); i++) {
			System.out.println(getCurrentTurn().getBuys() + " buy(s) remaining.");
			//Compile possible options for this buy
			for(Type type : getGameState().getSupplies().keySet()) {
				if(type.cost() <= getCurrentTurn().getCoins()-spent && !getGameState().getSupplies().get(type).isEmpty()) {
					buyOptions.add(type);
				}
			}
			System.out.print(toString(buyOptions) + "\nYou have " + (getCurrentTurn().getCoins()-spent) + " coins. Please select a purchase (-1 to end): ");
			int selection = scanner.nextInt();
			if(selection == -1) break;
			Type selected = buyOptions.get(selection);
			getGameState().getSupplies().get(selected).draw(getCurrentPlayer().getDiscardPile());
			spent += buyOptions.get(selection).cost();
		}
	}
	
	public static String toString(List<Type> buyOptions) {
		String str = "";
		for(int i = 0; i < buyOptions.size(); i++) {
			str += i + ". " + buyOptions.get(i) + " (" + buyOptions.get(i).cost() + ")\n";
		}
		return str;
	}
	
	public static void cleanup() {
		System.out.println("----- Cleanup Phase -----");
		getCurrentPlayer().getHand().drawAll(getCurrentPlayer().getDiscardPile());
		getCurrentPlayer().draw(Constants.DEFAULT_HAND_SIZE);
		System.out.println("Drew: " + getCurrentPlayer().getHand());
	}
	
	public static GameState getGameState() {
		return GameState.getInstance();
	}
	
	public static Turn getCurrentTurn() {
		return getGameState().getCurrentTurn();
	}
	
	public static Player getCurrentPlayer() {
		return getCurrentTurn().getPlayer();
	}
	
}
