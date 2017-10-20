package com.libertymutual.goforcode.blackjack.models;

import java.util.ArrayList;

public class Hand {
	
	private ArrayList<Card> hand;
	private int handValue;
	
	public Hand() {
		hand = new ArrayList<Card>();
	}
	
	public void addCardToHand(Card card) {
		hand.add(card);
	}

	public void removeCardFromHand(Card card) {
		hand.remove(card);
	}
	
	public int getHandValue() {
		handValue = 0;
		
		for (Card c : hand) {
			handValue += c.getValue();
		}
		
		return handValue;
	}
	
	public ArrayList<String> stringVersionOfHand() {
		
		ArrayList<String> hands = new ArrayList<String>();
		String card = "";
		
		for(Card c : hand) {
			card = c.getRank() + " of " + c.getSuit();
			hands.add(card);
		}
		
		return hands;
	}
	
}
