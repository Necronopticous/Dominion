package com.necronopticous.splendor;

import java.util.List;

public class HumanPlayer extends Player {

	public HumanPlayer(String name, boolean human) {
		super(name, human);
	}

	@Override
	public void takeTurn(Game game) {
		game.draw();
//		String input = "";
//		while(!isValid(input)) {
//		StringBuilder sb = new StringBuilder();
//		for(int i = 0; i < 5; i++) {
//			Color color = Color.getByIndex(i);
//			sb.append(player.getPerm()[i]);
//			sb.append(color.getSymbol());
//			sb.append(player.getTemp()[i]);
//			sb.append(" ");
//			}
//			
//		}
//		sb.deleteCharAt(sb.length()-1);
//		System.out.print(sb.toString() + ": ");
//		input = in.next();
	}

	@Override
	public Color discardToken() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tile chooseTile(List<Tile> choices) {
		// TODO Auto-generated method stub
		return null;
	}

}
