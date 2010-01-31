package dominion.game.state;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import dominion.card.Card;
import dominion.card.type.Type;
import dominion.card.type.Category;
import dominion.game.player.Player;
import dominion.settings.Constants;

public class GameState {
	
	private Random random;
	
	private List<Player> players;
	private List<Turn> turns;
	private Map<Type, Pile> supplies;
	private Pile trash;
	
	private GameState() {
		random = new Random(System.currentTimeMillis());
	}
	
	public void reset(int numberOfPlayers) {
		resetTurns();
		resetSupplies();
		resetPlayers(numberOfPlayers);
	}
	
	private void resetTurns() {
		turns = new ArrayList<Turn>();
	}
	
	private void resetSupplies() {
		trash = new Pile();
		supplies = new HashMap<Type, Pile>();
		
		List<Type> treasureTypes = new ArrayList<Type>();
		List<Type> victoryTypes = new ArrayList<Type>();
		List<Type> curseTypes = new ArrayList<Type>();
		List<Type> kingdomTypes = new ArrayList<Type>();
		
		for(Type type : Type.values()) {
			switch(type.category()) {
			case TREASURE: treasureTypes.add(type); break;
			case VICTORY: victoryTypes.add(type); break;
			case CURSE: curseTypes.add(type); break;
			case KINGDOM: kingdomTypes.add(type); break;
			}
		}
		
		Collections.shuffle(kingdomTypes, random);
		kingdomTypes = kingdomTypes.subList(0, Constants.KINGDOM_SUPPLY_VARIETIES);
		
		List<Type> typesInPlay = new ArrayList<Type>();
		typesInPlay.addAll(treasureTypes);
		typesInPlay.addAll(victoryTypes);
		typesInPlay.addAll(curseTypes);
		typesInPlay.addAll(kingdomTypes);
		
		for(Type type : typesInPlay) {
			resetSupply(type);
		}
	}
	
	private void resetSupply(Type type) {
		Pile supply = new Pile();
		for(int i = 0; i < type.amount(); i++) {
			supply.add(new Card(type));
		}
		supplies.put(type, supply);
	}
	
	private void resetPlayers(int numberOfPlayers) {
		numberOfPlayers = fix(numberOfPlayers);
		players = new ArrayList<Player>(numberOfPlayers);
		
		//Create human player
		players.add(new Player(true));
		
		//Create computer players
		for(int i = 1; i < numberOfPlayers; i++) {
			players.add(new Player(false));
		}
		
		for(Player player : players) {
			supplies.get(Type.COPPER).draw(player.getDiscardPile(), Constants.COPPER_START_AMOUNT);
			supplies.get(Type.ESTATE).draw(player.getDiscardPile(), Constants.ESTATE_START_AMOUNT);
			player.getDiscardPile().shuffle();
			player.draw(Constants.DEFAULT_HAND_SIZE);
		}
	}
	
	private int fix(int numberOfPlayers) {
		if(numberOfPlayers < Constants.MIN_PLAYERS) numberOfPlayers = Constants.MIN_PLAYERS;
		else if(numberOfPlayers > Constants.MAX_PLAYERS) numberOfPlayers = Constants.MAX_PLAYERS;
		return numberOfPlayers;
	}
	
	private static GameState instance = null;
	
	public static GameState getInstance() {
		if(instance == null) instance = new GameState();
		return instance;
	}
	
	public Turn getCurrentTurn() {
		return turns.get(turns.size()-1);
	}

	public List<Player> getPlayers() {
		return players;
	}
	
	public List<Turn> getTurns() {
		return turns;
	}

	public Map<Type, Pile> getSupplies() {
		return supplies;
	}
	
	public List<Pile> getSupplies(Category category) {
		List<Pile> supplyCategory = new ArrayList<Pile>();
		for (Type type : supplies.keySet()) {
			if (type.category() == category) {
				supplyCategory.add(supplies.get(type));
			}
		}
		return supplyCategory;
	}
	
	public Random getRandom() {
		return random;
	}
	
}
