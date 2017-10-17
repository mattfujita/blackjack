package com.libertymutual.goforcode.blackjack.models;

public class Card {
	
	protected int value;
	private String rank;
	private String suit;
	
	public Card() {}
	
	public Card(String rank, String suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public int getValue() {
		
		if(rank.equals("Ace")) {
			value = 11;
		} else if(rank.equals("Jack") || rank.equals("Queen") || rank.equals("King")) {
			value = 10;
		} else {
			value = Integer.parseInt(rank);
		}
		
		return value;
	}
	
	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getSuit() {
		return suit;
	}

	public void setSuit(String suit) {
		this.suit = suit;
	}
	
	@Override
	public String toString() {
		return this.rank + " of " + this.suit;
	}
	
}
