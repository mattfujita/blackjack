package com.libertymutual.goforcode.blackjack.models;

import java.util.ArrayList;

public class Hand {
	
	private ArrayList<Card> hand;
	private int handValue;
	private int aceCountInHand;
	
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
		aceCountInHand = 0;
		
		for (Card c : hand) {
			handValue += c.getValue();
			
			if(c.getValue() == 11) {
				aceCountInHand += 1;
			}
		}
		
		if(handValue > 21) {
			for(int i = 0; i < aceCountInHand; i+=1) {
				if(handValue > 21) {
					handValue -= 10;
				}
			}
		}
		
		return handValue;
	}
	
	public Card getOneCardFromHand(int index) {
		return hand.get(index);
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
