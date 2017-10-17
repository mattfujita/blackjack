package com.libertymutual.goforcode.blackjack.models;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	
	private ArrayList<Card> deck;
	private String[] suit = {"Diamonds", "Hearts", "Spades", "Clubs"};
	private String[] rank = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
	
	public Deck() {
		deck = new ArrayList<Card>();
		createADeck();
		shuffleDeck();
	}
	
	public void createADeck() {
		for(String s : suit) {
			for(String r : rank) {
				Card card = new Card(r, s);
				deck.add(card);
			}
		}
	}
	
	public void shuffleDeck() {
		for(int i = 0; i < 7; i+= 1) {
			Collections.shuffle(deck);
		}
	}
	
	public Card draw() {
		Card card = deck.remove(0);
		return card;
	}

}
