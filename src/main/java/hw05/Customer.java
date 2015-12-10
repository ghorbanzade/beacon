//
// CS680: Object Oriented Design and Programming
// Copyright 2015 Pejman Ghorbanzade <mail@ghorbanzade.com>
// Released under the terms of MIT License
// https://github.com/ghorbanzade/UMB-CS680-2015F/blob/master/LICENSE
//

package edu.umb.cs680.hw05;

import java.util.ArrayList;

/**
*
*
* @author Pejman Ghorbanzade
*/
public final class Customer {
	private String name;
	private ArrayList<CreditAccount> accounts = new ArrayList<CreditAccount>();

	public Customer(String name) {
		this.name = name;
	}

	public ArrayList<CreditAccount> getAccounts() {
		return this.accounts;
	}
}
