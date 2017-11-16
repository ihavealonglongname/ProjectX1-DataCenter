package controller;

import java.util.ArrayList;
import org.springframework.web.bind.annotation.*;

import base.ControllerBase;
import model.Account;

@RestController
public class AccountController extends ControllerBase<Account> {
	
	@RequestMapping("/accounts")
	public ArrayList<Account> greeting() {
		return super.queryAll();
    }
}
