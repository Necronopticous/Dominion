package dominion.card.type;


public enum Type {
	
	//----- Treasure -----//
	COPPER       (Category.TREASURE, Action.NONE,     60, 0, 1, 0, 0, 0, 0, 0),
	SILVER       (Category.TREASURE, Action.NONE,     40, 3, 2, 0, 0, 0, 0, 0),
	GOLD         (Category.TREASURE, Action.NONE,     30, 6, 3, 0, 0, 0, 0, 0),
	
	//----- Victory -----//
	ESTATE       (Category.VICTORY,  Action.NONE,     24, 2, 0, 1, 0, 0, 0, 0),
	DUCHY        (Category.VICTORY,  Action.NONE,     12, 5, 0, 3, 0, 0, 0, 0),
	PROVINCE     (Category.VICTORY,  Action.NONE,     12, 8, 0, 6, 0, 0, 0, 0),
	
	//----- Curse -----//
	CURSE        (Category.CURSE,    Action.NONE,     30, 0, 0,-1, 0, 0, 0, 0),
	
	//----- Kingdom -----//
	ADVENTURER   (Category.KINGDOM,  Action.ACTION,   10, 6, 0, 0, 0, 0, 0, 0),
	BUREAUCRAT   (Category.KINGDOM,  Action.ATTACK,   10, 4, 0, 0, 0, 0, 0, 0),
	CELLAR       (Category.KINGDOM,  Action.ACTION,   10, 2, 0, 0, 0, 1, 0, 0),
	CHANCELLOR   (Category.KINGDOM,  Action.ACTION,   10, 3, 0, 0, 0, 0, 0, 2),
	CHAPEL       (Category.KINGDOM,  Action.ACTION,   10, 2, 0, 0, 0, 0, 0, 0),
	COUNCIL_ROOM (Category.KINGDOM,  Action.ACTION,   10, 5, 0, 0, 4, 0, 1, 0),
	FEAST        (Category.KINGDOM,  Action.ACTION,   10, 4, 0, 0, 0, 0, 0, 0),
	FESTIVAL     (Category.KINGDOM,  Action.ACTION,   10, 5, 0, 0, 0, 2, 1, 2),
	GARDENS      (Category.KINGDOM,  Action.NONE,     12, 4, 0, 0, 0, 0, 0, 0),
	LABORATORY   (Category.KINGDOM,  Action.ACTION,   10, 5, 0, 0, 2, 1, 0, 0),
	LIBRARY      (Category.KINGDOM,  Action.ACTION,   10, 5, 0, 0, 0, 0, 0, 0),
	MARKET       (Category.KINGDOM,  Action.ACTION,   10, 5, 0, 0, 1, 1, 1, 1),
	MILITIA      (Category.KINGDOM,  Action.ATTACK,   10, 4, 0, 0, 0, 0, 0, 2),
	MINE         (Category.KINGDOM,  Action.ACTION,   10, 5, 0, 0, 0, 0, 0, 0),
	MOAT         (Category.KINGDOM,  Action.REACTION, 10, 2, 0, 0, 2, 0, 0, 0),
	MONEYLENDER  (Category.KINGDOM,  Action.ACTION,   10, 4, 0, 0, 0, 0, 0, 0),
	REMODEL      (Category.KINGDOM,  Action.ACTION,   10, 4, 0, 0, 0, 0, 0, 0),
	SMITHY       (Category.KINGDOM,  Action.ACTION,   10, 4, 0, 0, 3, 0, 0, 0),
	SPY          (Category.KINGDOM,  Action.ATTACK,   10, 4, 0, 0, 1, 1, 0, 0),
	THIEF        (Category.KINGDOM,  Action.ATTACK,   10, 4, 0, 0, 0, 0, 0, 0),
	THRONE_ROOM  (Category.KINGDOM,  Action.ACTION,   10, 4, 0, 0, 0, 0, 0, 0),
	VILLAGE      (Category.KINGDOM,  Action.ACTION,   10, 3, 0, 0, 1, 2, 0, 0),
	WITCH        (Category.KINGDOM,  Action.ATTACK,   10, 5, 0, 0, 2, 0, 0, 0),
	WOODCUTTER   (Category.KINGDOM,  Action.ACTION,   10, 3, 0, 0, 0, 0, 1, 2),
	WORKSHOP     (Category.KINGDOM,  Action.ACTION,   10, 3, 0, 0, 0, 0, 0, 0);
	
	private Category category;
	private Action action;
	
	private int amount;
	private int cost;
	private int treasure;
	private int victory;
	private int cardBonus;
	private int actionBonus;
	private int buyBonus;
	private int coinBonus;
	
	private Type(Category category, Action action, int amount, int cost, int treasure, int victory, int cardBonus, int actionBonus, int buyBonus, int coinBonus) {
		this.category = category;
		this.action = action;
		this.amount = amount;
		this.cost = cost;
		this.treasure = treasure;
		this.victory = victory;
		this.cardBonus = cardBonus;
		this.actionBonus = actionBonus;
		this.buyBonus = buyBonus;
		this.coinBonus = coinBonus;
	}
	
	public Category category() {
		return category;
	}
	
	public Action action() {
		return action;
	}
	
	public int amount() {
		return amount;
	}
	
	public int cost() {
		return cost;
	}
	
	public int treasure() {
		return treasure;
	}
	
	public int victory() {
		return victory;
	}
	
	public int cardBonus() {
		return cardBonus;
	}
	
	public int actionBonus() {
		return actionBonus;
	}
	
	public int buyBonus() {
		return buyBonus;
	}
	
	public int coinBonus() {
		return coinBonus;
	}

}
