package com.libertymutual.goforcode.blackjack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.libertymutual.goforcode.blackjack.models.Game;
import com.libertymutual.goforcode.blackjack.models.Quote;

@Controller
@RequestMapping("/blackjack")
public class BlackjackController {

	private boolean userExists = false;
	private boolean userEndsTurn = false;
	private boolean ranOutOfMoney = false;
	private Game game;
	
	RestTemplate restTemplate = new RestTemplate();
	private Quote quote;
	
	public BlackjackController() {
		game = new Game();
		quote = restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
	}
	
	@GetMapping("")
	public String renderLoginPage(Model model) {
		model.addAttribute("quote", quote.getValue().getQuote().toString());
		model.addAttribute("userExists", userExists);
		model.addAttribute("newUser", !userExists);
		model.addAttribute("ranOutOfMoney", ranOutOfMoney);
		model.addAttribute("name", game.getAPlayer().getName());
		model.addAttribute("wallet", game.getPlayerWallet());
		model.addAttribute("handValue", game.getPlayerHand().getHandValue());
		model.addAttribute("playerHand", game.getPlayerHand().stringVersionOfHand());
		model.addAttribute("dealerHand", game.getDealerHand().stringVersionOfHand());
		model.addAttribute("showOneDealerCard", !userEndsTurn);
		model.addAttribute("userStands", userEndsTurn);
		model.addAttribute("hideHitAndStand", userEndsTurn == false);
		model.addAttribute("hideDoubleDown", game.getPlayerWallet() >= game.getBet());
		model.addAttribute("hideSplit", game.getPlayerHand().getOneCardFromHand(0).getRank().equals(game.getPlayerHand().getOneCardFromHand(1).getRank()));
		model.addAttribute("hideDependingOnPlayerHand", game.getPlayerHand().getHandValue() <= 21);
		model.addAttribute("userLost", game.determineIfUserLost() == true);
		model.addAttribute("userWon", game.determineIfUserWins() == true);
		model.addAttribute("push", game.determineATie() == true);
		return "/blackjack/index";
	}
	
	@PostMapping("/user")
	public String createUser(String name, int wallet, int bet) {
		game.createANewUser(name, wallet, bet);
		userExists = true;
		ranOutOfMoney = false;

		return "redirect:/blackjack";
	}
	
	@PostMapping("/hit")
	public String hit() {
		game.hit();
		if(game.determineIfANewGameNeedsToBeCreated() == true) {
			userExists = false;
			ranOutOfMoney = true;
			game = new Game();
			quote = restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
		}
		return "redirect:/blackjack";
	}
	
	@PostMapping("/stand")
	public String stand() {
		game.stand();
		userEndsTurn = true;
		if(game.determineIfANewGameNeedsToBeCreated() == true) {
			userExists = false;
			ranOutOfMoney = true;
			userEndsTurn = false;
			game = new Game();
			quote = restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
		}
		
		return "redirect:/blackjack";
	}
	
	@PostMapping("/doubleDown")
	public String doubleDown() {
		game.doubleDown();
		userEndsTurn = true;
		if(game.determineIfANewGameNeedsToBeCreated() == true) {
			userExists = false;
			ranOutOfMoney = true;
			userEndsTurn = false;
			game = new Game();
			quote = restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
		}
		
		return "redirect:/blackjack";
	}
	
	@PostMapping("/split")
	public String split() {
		game.split();
		userEndsTurn = true;
		if(game.determineIfANewGameNeedsToBeCreated() == true) {
			userExists = false;
			ranOutOfMoney = true;
			userEndsTurn = false;
			game = new Game();
			quote = restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
		}
		
		return "redirect:/blackjack";
	}
	
	@PostMapping("/bet")
	public String bet(int bet) {
		
		game = game.startANewRound(bet);
		userEndsTurn = false;
		
		return "redirect:/blackjack";
	}

}
