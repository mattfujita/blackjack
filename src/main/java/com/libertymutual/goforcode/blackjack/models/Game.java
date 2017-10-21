package com.libertymutual.goforcode.blackjack.models;

public class Game {

	private Player player = new Player();
	private Deck deck = new Deck();
	private Hand playerHand;
	private Hand dealerHand;
	private int playerWallet;
	private int bet;
	private boolean win;
	private boolean loss;
	private boolean push;
	private boolean endOfGame;

	public Game() {

		playerHand = new Hand();
		dealerHand = new Hand();
		loss = false;
		win = false;
		push = false;
		endOfGame = false;

		playerHand.addCardToHand(deck.draw());
		dealerHand.addCardToHand(deck.draw());
		playerHand.addCardToHand(deck.draw());
		dealerHand.addCardToHand(deck.draw());
	}

	public Game(int playerWallet) {
		this.playerWallet = playerWallet;
		
		playerHand = new Hand();
		dealerHand = new Hand();
		loss = false;
		win = false;
		push = false;

		playerHand.addCardToHand(deck.draw());
		dealerHand.addCardToHand(deck.draw());
		playerHand.addCardToHand(deck.draw());
		dealerHand.addCardToHand(deck.draw());
	}
	
	public void createANewUser(String name, int wallet, int bet) {
		player = new Player(name, wallet);
		this.playerWallet = wallet;
		this.bet = bet;
		this.playerWallet -= this.bet;
		
	}

	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public Hand getPlayerHand() {
		return playerHand;
	}

	public void setPlayerHand(Hand player) {
		this.playerHand = player;
	}

	public Hand getDealerHand() {
		return dealerHand;
	}

	public void setDealerHand(Hand dealer) {
		this.dealerHand = dealer;
	}

	public void hit() {
		playerHand.addCardToHand(deck.draw());
		if(playerHand.getHandValue() > 21) {
			loss = true;
		}
		
	}
	
	public void stand() {
		while(dealerHand.getHandValue() < 17) {
			dealerHand.addCardToHand(deck.draw());
		}  
		
		if(dealerHand.getHandValue() > 21) {
			win = true;
		} else if(playerHand.getHandValue() > dealerHand.getHandValue()) {
			win = true;
		} else if(playerHand.getHandValue() == dealerHand.getHandValue()){
			push = true;
		} else {
			loss = true;
		}
		
		setWalletBasedOnBets(bet);
		
		if(playerWallet <= 0) {
			endOfGame = true;
		}
	}
	
	public boolean determineIfANewGameNeedsToBeCreated() {
		return endOfGame;
	}

	public boolean determineIfUserWins() {
		return win;
	}

	public void setWin(boolean win) {
		this.win = win;
	}

	public boolean determineIfUserLost() {
		return loss;
	}
	
	public boolean determineATie() {
		return push;
	}

	public void setLoss(boolean loss) {
		this.loss = loss;
	}
	
	public int getPlayerWallet() {
		return playerWallet;
	}
	
	public Player getAPlayer() {
		return player;
	}
	
	private void setPlayerToNewRound(Player player) {
		this.player = player;
	}
	
	public void setWalletBasedOnBets(int bet) {
		//still need to add payout for a blackjack
		
		if(win) {
			playerWallet += bet * 2;
		}  else if (push) {
			playerWallet += bet;
		} 
	}
	
	public Game startANewRound(int bet) {
		
		Game game = new Game(getPlayerWallet());
		
		game.setPlayerToNewRound(player);
		game.setWalletBasedOnBets(bet);

		return game;
	}

}
