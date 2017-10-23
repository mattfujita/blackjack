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
	private boolean blackjack;
	private boolean endOfGame;

	public Game() {

		playerHand = new Hand();
		dealerHand = new Hand();
		playerWallet = 0;
		bet = 0;
		loss = false;
		win = false;
		push = false;
		blackjack = false;
		endOfGame = false;

		playerHand.addCardToHand(deck.draw());
		dealerHand.addCardToHand(deck.draw());
		playerHand.addCardToHand(deck.draw());
		dealerHand.addCardToHand(deck.draw());
		
		blackjackCheck();
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
		
		blackjackCheck();
	}
	
	public void createANewUser(String name, int wallet, int bet) {
		player = new Player(name, wallet);
		this.playerWallet = wallet;
		this.bet = bet;
		if(this.bet <= playerWallet) {
			this.playerWallet -= this.bet;
		} else {
			bet = this.playerWallet;
			this.playerWallet -= bet;
		}
		
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
		
		if(playerWallet <= 0 && loss) {
			endOfGame = true;
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
		
		setWalletBasedOnBets();
		
		if(playerWallet <= 0 && loss) {
			endOfGame = true;
		}
	}
	
	public void doubleDown() {
		if(this.bet <= this.playerWallet) {
			this.playerWallet -= this.bet;
			this.bet *= 2;
			playerHand.addCardToHand(deck.draw());
		}
		
		stand();
		
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
	
	public void blackjackCheck() {
		if(playerHand.getHandValue() == 21) {
			blackjack = true;
			if(dealerHand.getHandValue() != 21) {
				setWalletBasedOnBets();
				win = true;
			} else {
				push = true;
				setWalletBasedOnBets();
			}
		}
	}
	
	public void setWalletBasedOnBets() {
		
		if(win && blackjack) {
			this.playerWallet += bet * 1.5;
			win = false;
		} else if(win) {
			this.playerWallet += bet * 2;
		}  else if (push) {
			this.playerWallet += bet;
		} 
	}
	
	public void setWalletForNewRound(int bet) {
		this.bet = bet;
		
		if(this.bet <= playerWallet) {
			this.playerWallet -= this.bet;
		} else {
			bet = this.playerWallet;
			this.playerWallet -= bet;
		}
	}
	
	public Game startANewRound(int bet) {
		
		Game game = new Game(getPlayerWallet());
		game.setPlayerToNewRound(player);
		game.setWalletForNewRound(bet);

		return game;
	}

}
