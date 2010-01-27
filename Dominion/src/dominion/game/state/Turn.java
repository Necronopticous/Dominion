package dominion.game.state;

import dominion.game.player.Player;

public class Turn {
	
	private Player player;
	
	private int actions;
	private int buys;
	private int coins;
	
	public Turn(Player player) {
		this.player = player;
		this.actions = 1;
		this.buys = 1;
		this.coins = 0;
	}
	
	public void actionsPlus(int bonus) {
		this.actions += bonus;
	}
	
	public void buysPlus(int bonus) {
		this.buys += bonus;
	}
	
	public void coinsPlus(int bonus) {
		this.coins += bonus;
	}

	public int getActions() {
		return actions;
	}

	public int getBuys() {
		return buys;
	}

	public int getCoins() {
		return coins;
	}

	public Player getPlayer() {
		return player;
	}
	
}
