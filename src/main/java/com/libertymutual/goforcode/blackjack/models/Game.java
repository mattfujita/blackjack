package com.libertymutual.goforcode.blackjack.models;

public class Game {
	
	private Deck deck = new Deck();
	private Hand playerHand;
	private Hand dealerHand;

	public Game() {

		playerHand = new Hand();
		dealerHand = new Hand();

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
		
	}
	
	public void stand() {
		while(dealerHand.getHandValue() < 17) {
			dealerHand.addCardToHand(deck.draw());
		}
	}

}
