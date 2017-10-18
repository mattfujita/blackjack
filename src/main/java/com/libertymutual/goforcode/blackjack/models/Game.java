package com.libertymutual.goforcode.blackjack.models;

public class Game {
	
	private Deck deck = new Deck();
	private Hand playerHand;
	private Hand dealerHand;
	private boolean win;
	private boolean loss;
	private boolean push;

	public Game() {

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
	}

	public boolean getWin() {
		return win;
	}

	public void setWin(boolean win) {
		this.win = win;
	}

	public boolean getLoss() {
		return loss;
	}

	public void setLoss(boolean loss) {
		this.loss = loss;
	}

}
