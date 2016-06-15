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
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

/**
 * @author db2admin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class AlwaysLoginModule implements LoginModule {
	
	private Subject subject;
	private Principal principal;
	private CallbackHandler callbackHandler;
	private String userName;
	private boolean loginSuccess;
	
	/**
	 * 
	 */
	public AlwaysLoginModule() {
		super();
	}
	/* (non-Javadoc)
	 * @see javax.security.auth.spi.LoginModule#abort()
	 */
	public boolean abort() throws LoginException {
		if(loginSuccess == false) {
			System.out.println("Abort: AlwaysLoginModule FAIL");
			principal = null;
			return false;
		}
		System.out.println("Abort: AlwaysLoginModule SUCCESS");
		logout();
		return true;
	}
	/* (non-Javadoc)
	 * @see javax.security.auth.spi.LoginModule#commit()
	 */
	public boolean commit() throws LoginException {
		if(loginSuccess == false) {
			System.out.println("Commit: AlwaysLoginModule FAIL");
			return false;
		}
		principal = new PrincipalImpl(userName);
		if(!(subject.getPrincipals().contains(principal))) 
			subject.getPrincipals().add(principal);
		System.out.println("Commit: AlwaysLoginModule SUCCESS");
		return true;
	}
	/* (non-Javadoc)
	 * @see javax.security.auth.spi.LoginModule#login()
	 */
	public boolean login() throws LoginException {
		if(callbackHandler == null) throw new LoginException("No CallbackHandler Defined!");
		Callback[] callbacks = new Callback[1];
		callbacks[0] = new NameCallback("Username");
		try {
			System.out.println("\nAlwaysLoginModule Login");
			callbackHandler.handle(callbacks);
			userName = ((NameCallback)callbacks[0]).getName();
		} catch(IOException ioe) {
			throw new LoginException(ioe.toString());
		} catch(UnsupportedCallbackException uce) {
			throw new LoginException(uce.toString());
		}
		loginSuccess = true;
		System.out.println("\nLogin: AlwaysLoginModule SUCCESS");
		return true;
	}
	/* (non-Javadoc)
	 * @see javax.security.auth.spi.LoginModule#logout()
	 */
	public boolean logout() throws LoginException {
		subject.getPrincipals().remove(principal);
		loginSuccess = false;
		principal = null;
		System.out.println("Logout: AlwaysLoginModule: SUCCESS");
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
	}
}
