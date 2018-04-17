package service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import base.ServiceBase;
import controller.AccountController;
import model_data.Account;

@RestController
public class AccountService extends ServiceBase<AccountController,Account> {
	
	@RequestMapping("/accounts")
	public ArrayList<Account> queryAll() {
//		return super.queryAll();
		Account account01 = new Account();
		account01.account="account01";
		account01.password="password01";
		account01.reg_datetime="2020/1/1";
		
		Account account02 = new Account();
		account02.account="account02";
		account02.password="password02";
		account02.reg_datetime="2020/2/2";
		
		ArrayList<Account> accountList = new ArrayList<Account>();
		accountList.add(account01);
		accountList.add(account02);
		return accountList;
    }
	
	@RequestMapping("/all")
	public ArrayList<Account> queryAll(HttpServletRequest request) {
		
		System.out.println(request.getLocalAddr());
		System.out.println(request.getLocalName());
		System.out.println(request.getLocalPort());
		System.out.println(request.getRemoteAddr());
		System.out.println(request.getRemoteHost());
		System.out.println(request.getRemotePort());
		System.out.println(request.getRemoteUser());
		System.out.println(request.getServerName());
		
		
		Account account01 = new Account();
		account01.account="account01";
		account01.password="password01";
		account01.reg_datetime="2020/1/1";
		
		Account account02 = new Account();
		account02.account="account02";
		account02.password="password02";
		account02.reg_datetime="2020/2/2";
		
		ArrayList<Account> accountList = new ArrayList<Account>();
		accountList.add(account01);
		accountList.add(account02);
		return accountList;
	}
}