/*
 * Created on 06.Tem.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.fatih.jaas1;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

/**
 * @author db2admin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class PasswordLoginModule implements LoginModule {
	
	private Subject subject;
	private Principal principal;
	private CallbackHandler callbackHandler;
	private String userName;
	private char[] password;
	private boolean loginSuccess;
	
	/**
	 * 
	 */
	public PasswordLoginModule() {
		super();
		// TODO Auto-generated constructor stub
	}
	/* (non-Javadoc)
	 * @see javax.security.auth.spi.LoginModule#abort()
	 */
	public boolean abort() throws LoginException {
		if(loginSuccess == false) {
			System.out.println("Abort: PasswordLoginModule FAIL");
			principal = null;
			userName = null;
			return false;
		}
		System.out.println("Abort: PasswordLoginModule SUCCESS");
		logout();
		return true;
	}
	/* (non-Javadoc)
	 * @see javax.security.auth.spi.LoginModule#commit()
	 */
	public boolean commit() throws LoginException {
		if(loginSuccess == false) {
			System.out.println("Commit: PasswordLoginModule FAIL");
			return false;
		}
		principal = new PrincipalImpl(userName);
		if(!(subject.getPrincipals().contains(principal)))
			subject.getPrincipals().add(principal);
		userName = null;
		System.out.println("Commit: PasswordLoginModule SUCCESS");
		return true;
	}
	/* (non-Javadoc)
	 * @see javax.security.auth.spi.LoginModule#login()
	 */
	public boolean login() throws LoginException {
		if(callbackHandler == null) throw new LoginException("No CallbackHandler Defined!");
		Callback[] callbacks = new Callback[2];
		callbacks[0] = new NameCallback("UserName");
		callbacks[1] = new PasswordCallback("Password", false);
		try {
			System.out.println("\nPasswordLoginModule Login");
			callbackHandler.handle(callbacks);
			userName = ((NameCallback)callbacks[0]).getName();
			char[] temp = ((PasswordCallback)callbacks[1]).getPassword();
			password = new char[temp.length];
			System.arraycopy(temp, 0, password, 0, temp.length);
			((PasswordCallback)callbacks[1]).clearPassword();
		} catch(IOException ioe) {
			throw new LoginException(ioe.getMessage());
		} catch(UnsupportedCallbackException uce) {
			throw new LoginException(uce.getMessage());
		}
		System.out.println();
		if("joeuser".equals(userName)) {
			System.out.println("Login: Password Login Module Username Matches");
			if(password.length == 5 && password[0] == 'j' &&
					password[1] == 'o' && password[2] == 'e' &&
					password[3] == 'p' && password[4] == 'w') {
				System.out.println("Login: PasswordLoginModule Password Matches");
				loginSuccess = true;
				System.out.println("Login: PasswordLoginModule SUCCESS");			
				clearPassword();
				return true;
			} else {
				System.out.println("Login: PasswordLoginModule Password Mismatch");
			} 
		} else {
			System.out.println("Login: PasswordLoginModule Password Mismatch");
		}
		loginSuccess = false;
		userName = null;
		clearPassword();
		System.out.println("Login: PasswordLoginModule FAIL");
		throw new FailedLoginException();
	}
	/* (non-Javadoc)
	 * @see javax.security.auth.spi.LoginModule#logout()
	 */
	public boolean logout() throws LoginException {
		subject.getPrincipals().remove(principal);
		loginSuccess = false;
		userName = null;
		principal = null;
		System.out.println("Logout: PasswordLoginModule SUCCESS");
		return true;
	}
	/* (non-Javadoc)
	 * @see javax.security.auth.spi.LoginModule#initialize(javax.security.auth.Subject, javax.security.auth.callback.CallbackHandler, java.util.Map, java.util.Map)
	 */
	public void initialize(Subject arg0, CallbackHandler arg1, Map arg2,
			Map arg3) {
		subject = arg0;
		callbackHandler = arg1;
		loginSuccess = false;
		userName = null;
		clearPassword();
	}
	
	private void clearPassword() {
		if(password == null) return;
		for(int i = 0; i < password.length; i++) {
			password[i] = ' ';
		}
		password = null;
	}
}
