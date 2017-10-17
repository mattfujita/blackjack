package com.libertymutual.goforcode.blackjack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.libertymutual.goforcode.blackjack.models.Game;
import com.libertymutual.goforcode.blackjack.models.Player;

@Controller
@RequestMapping("/blackjack")
public class BlackjackController {

	private Player user = new Player();
	private boolean userExists = false;
	private boolean userStands = false;
	private Game game;
	
	public BlackjackController() {
		game = new Game();
	}
	
	@GetMapping("")
	public String renderLoginPage(Model model) {
		model.addAttribute("userExists", userExists);
		model.addAttribute("newUser", !userExists);
		model.addAttribute("name", user.getName());
		model.addAttribute("wallet", user.getWallet());
		model.addAttribute("playerHand", game.getPlayerHand().stringVersionOfHand());
		model.addAttribute("dealerHand", game.getDealerHand().stringVersionOfHand());
		model.addAttribute("showOneDealerCard", !userStands);
		model.addAttribute("userStands", userStands);
		return "/blackjack/index";
	}
	
	@PostMapping("/user")
	public String createUser(String name, int wallet) {
		user = new Player(name, wallet);
		userExists = true;

		return "redirect:/blackjack";
	}
	
	@PostMapping("/hit")
	public String hit() {
		game.hit();
		return "redirect:/blackjack";
	}
	
	@PostMapping("/stand")
	public String stand() {
		game.stand();
		userStands = true;
		
		return "redirect:/blackjack";
	}

}
