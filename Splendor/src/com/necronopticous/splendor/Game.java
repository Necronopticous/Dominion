package com.necronopticous.splendor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Game {
	
	private Player[] players;
	private Player currentPlayer;
	private int round;
	private List<Card> l3Deck;
	private List<Card> l2Deck;
	private List<Card> l1Deck;
	private List<Tile> tiles;
	private int[] tokens;
	
	Scanner in;
	
	public static void main(String[] args) {
		Game game = new Game(4);
		game.play();
	}
	
	public Game(int numPlayers) {
		numPlayers = Math.max(numPlayers, 2);
		numPlayers = Math.min(numPlayers, 4);
		this.players = new Player[numPlayers];
		for(int i = 0; i < numPlayers; i++) this.players[i] = new Player("Player " + i, true);
		this.currentPlayer = players[0];
		this.round = 0;
		
		this.l3Deck = generateL3Deck();
		this.l2Deck = generateL2Deck();
		this.l1Deck = generateL1Deck();
		
		int numTokens = 7;
		if(numPlayers < 4) numTokens -= 2;
		if(numPlayers < 3) numTokens -= 1;
		this.tokens = new int[]{numTokens, numTokens, numTokens, numTokens, numTokens, 5};
		
		int numTiles = numPlayers + 1;
		this.tiles = generateTiles(numTiles);
		
		in = new Scanner(System.in);
	}
	
	public void play() {
		while(winners().isEmpty()) {
			for(Player player : players) {
				if(player.isHuman()) {
					draw();
//					String input = "";
//					while(!isValid(input)) {
//					StringBuilder sb = new StringBuilder();
//					for(int i = 0; i < 5; i++) {
//						Color color = Color.getByIndex(i);
//						sb.append(player.getPerm()[i]);
//						sb.append(color.getSymbol());
//						sb.append(player.getTemp()[i]);
//						sb.append(" ");
//						}
//						
//					}
//					sb.deleteCharAt(sb.length()-1);
//					System.out.print(sb.toString() + ": ");
//					input = in.next();
				} else {
					// AI
				}
			}
		}
		for(Player winner : winners()) {
			System.out.println(winner.getName() + " wins!");
		}
	}
	
	private boolean purchase(Card card) {
		boolean valid = true;
		switch(card.getLevel()){
		case 1: valid = l1Deck.remove(card); break;
		case 2: valid = l2Deck.remove(card); break;
		case 3: valid = l3Deck.remove(card); break;
		}
		if(!valid) return false;
		Color color = card.getColor();
		//currentPlayer.getPerm()[color.getIndex()] = currentPlayer.getPerm()[color.getIndex()] + 1;
		currentPlayer.getPerm()[color.getIndex()]++;
		currentPlayer.setPoints(currentPlayer.getPoints() + card.getPoints());
		return true;
	}
	
	private boolean reserve(Card card) {
		if(currentPlayer.getReserve().size() >= 3) return false;
		boolean valid = true;
		switch(card.getLevel()){
		case 1: valid = l1Deck.remove(card); break;
		case 2: valid = l2Deck.remove(card); break;
		case 3: valid = l3Deck.remove(card); break;
		}
		currentPlayer.getReserve().add(card);
		if(tokens[Color.WILD.getIndex()] > 0) {
			tokens[Color.WILD.getIndex()]--;
			currentPlayer.getTemp()[Color.WILD.getIndex()]++;
		}
		return true;
	}
	
	private boolean take(Color... colors) {
		switch(colors.length) {
		case 2:
		case 3:
		default:
			
		}
		return true;
	}
	
	private Set<Player> winners() {
		Set<Player> winners = new HashSet<Player>();
		int topScore = 0;
		for(Player player : players) {
			if(player.getPoints() >= 15) {
			}
		}
		return winners;
	}
	
	public static char CORNER = '+';
	public static char H_EDGE = '-';
	public static char V_EDGE = '|';
	public static char VERTEX = '-';
	public static char H_INNER = '=';
	public static char V_INNER = '|';
	
	public void draw() {
		StringBuilder sb = new StringBuilder();
		
		// Top line
		sb.append(drawRule(CORNER, H_EDGE, VERTEX, 5, 5) + "\n");
		
		// Tiles
		sb.append(drawTiles() + "\n");
		
		// Mid line
		sb.append(drawRule(V_EDGE, H_INNER, H_INNER, 5, 5) + "\n");
		
		// L3 cards
		sb.append(drawCards(l3Deck, 'i', 'o') + "\n");
		
		// Mid line
		sb.append(drawRule(V_EDGE, H_INNER, H_INNER, 5, 5) + "\n");
		
		// L2 cards
		sb.append(drawCards(l2Deck, 'e', 'n') + "\n");
		
		// Mid line
		sb.append(drawRule(V_EDGE, H_INNER, H_INNER, 5, 5) + "\n");
		
		// L1 cards
		sb.append(drawCards(l1Deck, 'a', 'm') + "\n");
		
		// Mid line
		sb.append(drawRule(V_EDGE, H_INNER, H_INNER, 5, 5) + "\n");
		
		// Tokens
		sb.append(drawTokens() + "\n");
		
		// Bottom line
		sb.append(drawRule(CORNER, H_EDGE, VERTEX, 5, 5) + "\n");
		
		System.out.println(sb.toString());
	}
	
	private String drawTokens() {
		StringBuilder sb = new StringBuilder();
		sb.append(V_EDGE);
		for(int i = 0; i < 6; i++) {
			sb.append(" ");
			Color color = Color.getByIndex(i);
			sb.append(color.getSymbol());
			sb.append(tokens[i]);
			sb.append("  ");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(V_EDGE);
		return sb.toString();
	}
	
	private String drawTiles() {
		StringBuilder sb = new StringBuilder();
		
		// Line 1: cost[0] and cost[1]
		sb.append(V_EDGE);
		for(int i = 0; i < 5; i++) {
			if(i >= tiles.size()) sb.append("     " + V_INNER);
			else {
				Tile tile = tiles.get(i);
				sb.append(drawCostSegment(tile.getCost(), true));
				sb.append(V_INNER);
			}
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(V_EDGE);
		sb.append("\n");
		
		// Line 2: cost[2] and cost[3]
		sb.append(V_EDGE);
		for(int i = 0; i < 5; i++) {
			if(i >= tiles.size()) sb.append("     " + V_INNER);
			else {
				Tile tile = tiles.get(i);
				sb.append(drawCostSegment(tile.getCost(), false));
				sb.append(V_INNER);
			}
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(V_EDGE);
		
		return sb.toString();
	}
	
	private String drawCostSegment(int[] cost, boolean top) {
		StringBuilder sb = new StringBuilder();
		int skip = top ? 0 : 2;
		int count = 0;
		for(int i = 0; i < cost.length; i++) {
			if(cost[i] == 0) continue;
			if(skip-- > 0) continue;
			Color color = Color.getByIndex(i);
			sb.append(cost[i]);
			sb.append(color.getSymbol());
			if(++count == 1) sb.append(" ");
			else break;
		}
		if(count == 0) sb.append("     ");
		if(count == 1) sb.append("  ");
		return sb.toString();
	}
	
	private String drawCards(List<Card> deck, char baseSelector, char deckSelector) {
		StringBuilder sb = new StringBuilder();
		
		// Line 1: victory points, selection key, color
		sb.append(V_EDGE);
		if(deck.size() > 4) sb.append("  " + deckSelector + "  ");
		else sb.append("     ");
		for(int i = 0; i < 4; i++) {
			sb.append(V_INNER);
			if(i >= deck.size()) sb.append("     ");
			else {
				Card card = deck.get(i);
				sb.append(card.getPoints() > 0 ? card.getPoints() : " ");
				sb.append(" ");
				sb.append(Character.toChars(baseSelector + i));
				sb.append(" ");
				sb.append(card.getColor().getSymbol());
			}
		}
		sb.append(V_EDGE);
		sb.append("\n");
		
		// Line 2: blank / rule
		sb.append(V_EDGE);
		sb.append("     ");
		for(int i = 0; i < 4; i++) {
			sb.append(V_INNER);
			if(i >= deck.size()) sb.append("     ");
			else {
				for(int j = 0; j < 5; j++) sb.append(H_EDGE);
			}
		}
		sb.append(V_EDGE);
		sb.append("\n");
		
		// Line 3: Number left in deck / cost[0] and cost[1]
		sb.append(V_EDGE);
		if(deck.size() > 4) {
			sb.append(" ");
			if(deck.size() < 10) sb.append(" ");
			sb.append(deck.size() - 4);
			sb.append("  ");
		} else sb.append("     ");
		for(int i = 0; i < 4; i++) {
			sb.append(V_INNER);
			if(i >= deck.size()) sb.append("     ");
			else {
				Card card = deck.get(i);
				sb.append(drawCostSegment(card.getCost(), true));
			}
		}
		sb.append(V_EDGE);
		sb.append("\n");
		
		// Line 4: Level in dots / cost[2] and cost[3]
		sb.append(V_EDGE);
		if(deck.size() > 4) {
			sb.append(" ");
			int level = deck.get(0).getLevel();
			for(int i = 0; i < deck.get(0).getLevel(); i++) sb.append(".");
			for(int i = level; i < 4; i++) sb.append(" ");
		} else sb.append("     ");
		for(int i = 0; i < 4; i++) {
			sb.append(V_INNER);
			if(i >= deck.size()) sb.append("     ");
			else {
				Card card = deck.get(i);
				sb.append(drawCostSegment(card.getCost(), false));
			}
		}
		sb.append(V_EDGE);
		
		return sb.toString();
	}
	
	private String drawRule(char edge, char inner, char vertex, int numSegments, int numPerSegment) {
		StringBuilder sb = new StringBuilder();
		sb.append(edge);
		for(int i = 0; i < numSegments; i++) {
			for(int j = 0; j < numPerSegment; j++) sb.append(inner);
			sb.append(vertex);
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(edge);
		return sb.toString();
	}
	
	private List<Tile> generateTiles(int numTiles) {
		List<Tile> tiles = new ArrayList<Tile>(10);
		
		// POINTS, GREEN, BLUE, RED, WHITE, BLACK
		tiles.add(new Tile(3, 0, 3, 0, 3, 3));
		tiles.add(new Tile(3, 3, 3, 3, 0, 0));
		tiles.add(new Tile(3, 0, 0, 3, 3, 3));
		tiles.add(new Tile(3, 4, 0, 4, 0, 0));
		tiles.add(new Tile(3, 4, 4, 0, 0, 0));
		tiles.add(new Tile(3, 0, 0, 4, 0, 4));
		tiles.add(new Tile(3, 0, 0, 0, 4, 4));
		tiles.add(new Tile(3, 3, 3, 0, 3, 0));
		tiles.add(new Tile(3, 3, 0, 3, 0, 3));
		tiles.add(new Tile(3, 0, 4, 0, 4, 0));
		
		Collections.shuffle(tiles);
		
		return tiles.subList(0, numTiles);
	}
	
	private List<Card> generateL1Deck() {
		List<Card> l1Deck = new ArrayList<Card>(8*5);
		
		// LEVEL, TYPE, POINTS, GREEN, BLUE, RED, WHITE, BLACK
		l1Deck.add(new Card(1, Color.GREEN, 0, 0, 1, 0, 2, 0));
		l1Deck.add(new Card(1, Color.GREEN, 0, 0, 0, 3, 0, 0));
		l1Deck.add(new Card(1, Color.GREEN, 0, 0, 1, 1, 1, 1));
		l1Deck.add(new Card(1, Color.GREEN, 0, 0, 2, 2, 0, 0));
		l1Deck.add(new Card(1, Color.GREEN, 1, 0, 0, 0, 0, 4));
		l1Deck.add(new Card(1, Color.GREEN, 0, 0, 1, 1, 1, 2));
		l1Deck.add(new Card(1, Color.GREEN, 0, 0, 1, 2, 0, 2));
		l1Deck.add(new Card(1, Color.GREEN, 0, 1, 3, 0, 1, 0));
		
		// LEVEL, TYPE, POINTS, GREEN, BLUE, RED, WHITE, BLACK
		l1Deck.add(new Card(1, Color.BLUE, 0, 0, 0, 0, 1, 2));
		l1Deck.add(new Card(1, Color.BLUE, 0, 0, 0, 0, 0, 3));
		l1Deck.add(new Card(1, Color.BLUE, 0, 1, 0, 1, 1, 1));
		l1Deck.add(new Card(1, Color.BLUE, 0, 2, 0, 0, 0, 2));
		l1Deck.add(new Card(1, Color.BLUE, 1, 0, 0, 4, 0, 0));
		l1Deck.add(new Card(1, Color.BLUE, 0, 1, 0, 2, 1, 1));
		l1Deck.add(new Card(1, Color.BLUE, 0, 2, 0, 2, 1, 0));
		l1Deck.add(new Card(1, Color.BLUE, 0, 3, 1, 1, 0, 0));
		
		// LEVEL, TYPE, POINTS, GREEN, BLUE, RED, WHITE, BLACK
		l1Deck.add(new Card(1, Color.RED, 0, 1, 2, 0, 0, 0));
		l1Deck.add(new Card(1, Color.RED, 0, 0, 0, 0, 3, 0));
		l1Deck.add(new Card(1, Color.RED, 0, 1, 1, 0, 1, 1));
		l1Deck.add(new Card(1, Color.RED, 0, 0, 0, 2, 2, 0));
		l1Deck.add(new Card(1, Color.RED, 1, 0, 0, 0, 4, 0));
		l1Deck.add(new Card(1, Color.RED, 0, 1, 1, 0, 2, 1));
		l1Deck.add(new Card(1, Color.RED, 0, 1, 0, 0, 2, 2));
		l1Deck.add(new Card(1, Color.RED, 0, 0, 0, 1, 1, 3));
		
		// LEVEL, TYPE, POINTS, GREEN, BLUE, RED, WHITE, BLACK
		l1Deck.add(new Card(1, Color.WHITE, 0, 0, 3, 0, 0, 0));
		l1Deck.add(new Card(1, Color.WHITE, 0, 0, 0, 2, 0, 1));
		l1Deck.add(new Card(1, Color.WHITE, 0, 1, 1, 1, 0, 1));
		l1Deck.add(new Card(1, Color.WHITE, 0, 0, 2, 0, 0, 2));
		l1Deck.add(new Card(1, Color.WHITE, 1, 4, 0, 0, 0, 0));
		l1Deck.add(new Card(1, Color.WHITE, 0, 2, 1, 1, 0, 1));
		l1Deck.add(new Card(1, Color.WHITE, 0, 2, 2, 0, 0, 1));
		l1Deck.add(new Card(1, Color.WHITE, 0, 0, 1, 0, 3, 1));
		
		// LEVEL, TYPE, POINTS, GREEN, BLUE, RED, WHITE, BLACK
		l1Deck.add(new Card(1, Color.BLACK, 0, 2, 0, 1, 0, 0));
		l1Deck.add(new Card(1, Color.BLACK, 0, 3, 0, 0, 0, 0));
		l1Deck.add(new Card(1, Color.BLACK, 0, 1, 1, 1, 1, 0));
		l1Deck.add(new Card(1, Color.BLACK, 0, 2, 0, 0, 2, 0));
		l1Deck.add(new Card(1, Color.BLACK, 1, 0, 4, 0, 0, 0));
		l1Deck.add(new Card(1, Color.BLACK, 0, 1, 2, 1, 1, 0));
		l1Deck.add(new Card(1, Color.BLACK, 0, 0, 2, 1, 2, 0));
		l1Deck.add(new Card(1, Color.BLACK, 0, 1, 0, 3, 0, 1));
		
		Collections.shuffle(l1Deck);
		
		return l1Deck;
	}
	
	private List<Card> generateL2Deck() {
		List<Card> l2Deck = new ArrayList<Card>(6*5);
		
		// LEVEL, TYPE, POINTS, GREEN, BLUE, RED, WHITE, BLACK
		l2Deck.add(new Card(2, Color.GREEN, 2, 5, 0, 0, 0, 0));
		l2Deck.add(new Card(2, Color.GREEN, 3, 6, 0, 0, 0, 0));
		l2Deck.add(new Card(2, Color.GREEN, 1, 0, 3, 0, 2, 2));
		l2Deck.add(new Card(2, Color.GREEN, 2, 0, 2, 0, 4, 1));
		l2Deck.add(new Card(2, Color.GREEN, 1, 2, 0, 3, 3, 0));
		l2Deck.add(new Card(2, Color.GREEN, 2, 3, 5, 0, 0, 0));
		
		// LEVEL, TYPE, POINTS, GREEN, BLUE, RED, WHITE, BLACK
		l2Deck.add(new Card(2, Color.BLUE, 2, 0, 5, 0, 0, 0));
		l2Deck.add(new Card(2, Color.BLUE, 3, 0, 6, 0, 0, 0));
		l2Deck.add(new Card(2, Color.BLUE, 1, 2, 2, 3, 0, 0));
		l2Deck.add(new Card(2, Color.BLUE, 2, 0, 0, 1, 2, 4));
		l2Deck.add(new Card(2, Color.BLUE, 1, 3, 2, 0, 0, 3));
		l2Deck.add(new Card(2, Color.BLUE, 2, 0, 3, 0, 5, 0));
		
		// LEVEL, TYPE, POINTS, GREEN, BLUE, RED, WHITE, BLACK
		l2Deck.add(new Card(2, Color.RED, 2, 0, 0, 0, 0, 5));
		l2Deck.add(new Card(2, Color.RED, 3, 0, 0, 6, 0, 0));
		l2Deck.add(new Card(2, Color.RED, 1, 0, 0, 2, 2, 3));
		l2Deck.add(new Card(2, Color.RED, 2, 2, 4, 0, 1, 0));
		l2Deck.add(new Card(2, Color.RED, 1, 0, 3, 2, 0, 3));
		l2Deck.add(new Card(2, Color.RED, 2, 0, 0, 0, 3, 5));
		
		// LEVEL, TYPE, POINTS, GREEN, BLUE, RED, WHITE, BLACK
		l2Deck.add(new Card(2, Color.WHITE, 2, 0, 0, 5, 0, 0));
		l2Deck.add(new Card(2, Color.WHITE, 3, 0, 0, 0, 6, 0));
		l2Deck.add(new Card(2, Color.WHITE, 1, 3, 0, 2, 0, 2));
		l2Deck.add(new Card(2, Color.WHITE, 2, 1, 0, 4, 0, 2));
		l2Deck.add(new Card(2, Color.WHITE, 1, 0, 3, 3, 2, 0));
		l2Deck.add(new Card(2, Color.WHITE, 2, 0, 0, 5, 0, 3));
		
		// LEVEL, TYPE, POINTS, GREEN, BLUE, RED, WHITE, BLACK
		l2Deck.add(new Card(2, Color.BLACK, 2, 0, 0, 0, 5, 0));
		l2Deck.add(new Card(2, Color.BLACK, 3, 0, 0, 0, 0, 6));
		l2Deck.add(new Card(2, Color.BLACK, 1, 2, 2, 0, 3, 0));
		l2Deck.add(new Card(2, Color.BLACK, 2, 4, 1, 2, 0, 0));
		l2Deck.add(new Card(2, Color.BLACK, 1, 3, 0, 0, 3, 2));
		l2Deck.add(new Card(2, Color.BLACK, 2, 5, 0, 3, 0, 0));
		
		Collections.shuffle(l2Deck);
		
		return l2Deck;
	}
	
	private List<Card> generateL3Deck() {
		List<Card> l3Deck = new ArrayList<Card>(4*5);
		
		// LEVEL, TYPE, POINTS, GREEN, BLUE, RED, WHITE, BLACK
		l3Deck.add(new Card(3, Color.GREEN, 4, 0, 7, 0, 0, 0));
		l3Deck.add(new Card(3, Color.GREEN, 5, 3, 7, 0, 0, 0));
		l3Deck.add(new Card(3, Color.GREEN, 4, 3, 6, 0, 3, 0));
		l3Deck.add(new Card(3, Color.GREEN, 3, 0, 3, 3, 5, 3));
		
		// LEVEL, TYPE, POINTS, GREEN, BLUE, RED, WHITE, BLACK
		l3Deck.add(new Card(3, Color.BLUE, 4, 0, 0, 0, 7, 0));
		l3Deck.add(new Card(3, Color.BLUE, 5, 0, 3, 0, 7, 0));
		l3Deck.add(new Card(3, Color.BLUE, 4, 0, 3, 0, 6, 3));
		l3Deck.add(new Card(3, Color.BLUE, 3, 3, 0, 3, 3, 5));
		
		// LEVEL, TYPE, POINTS, GREEN, BLUE, RED, WHITE, BLACK
		l3Deck.add(new Card(3, Color.RED, 4, 7, 0, 0, 0, 0));
		l3Deck.add(new Card(3, Color.RED, 5, 7, 0, 3, 0, 0));
		l3Deck.add(new Card(3, Color.RED, 4, 6, 3, 3, 0, 0));
		l3Deck.add(new Card(3, Color.RED, 3, 3, 5, 0, 3, 3));
		
		// LEVEL, TYPE, POINTS, GREEN, BLUE, RED, WHITE, BLACK
		l3Deck.add(new Card(3, Color.WHITE, 4, 0, 0, 0, 0, 7));
		l3Deck.add(new Card(3, Color.WHITE, 5, 0, 0, 0, 3, 7));
		l3Deck.add(new Card(3, Color.WHITE, 4, 0, 0, 3, 3, 6));
		l3Deck.add(new Card(3, Color.WHITE, 3, 3, 3, 5, 0, 3));
		
		// LEVEL, TYPE, POINTS, GREEN, BLUE, RED, WHITE, BLACK
		l3Deck.add(new Card(3, Color.BLACK, 4, 0, 0, 7, 0, 0));
		l3Deck.add(new Card(3, Color.BLACK, 5, 0, 0, 7, 0, 3));
		l3Deck.add(new Card(3, Color.BLACK, 4, 3, 0, 6, 0, 3));
		l3Deck.add(new Card(3, Color.BLACK, 3, 5, 3, 3, 3, 0));
		
		Collections.shuffle(l3Deck);
		
		return l3Deck;
	}

}
