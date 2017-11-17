package service;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import base.ServiceBase;
import controller.AccountController;
import model_data.Account;

@RestController
public class AccountService extends ServiceBase<AccountController,Account> {
	
	@RequestMapping("/accounts")
	public ArrayList<Account> queryAll() {
		return super.queryAll();
    }
}