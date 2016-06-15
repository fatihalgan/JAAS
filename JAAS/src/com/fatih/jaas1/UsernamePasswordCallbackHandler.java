/*
 * Created on 06.Tem.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.fatih.jaas1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

/**
 * @author db2admin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class UsernamePasswordCallbackHandler implements CallbackHandler {
	/**
	 * 
	 */
	public UsernamePasswordCallbackHandler() {
		super();
	}
	/* (non-Javadoc)
	 * @see javax.security.auth.callback.CallbackHandler#handle(javax.security.auth.callback.Callback[])
	 */
	public void handle(Callback[] arg0) throws IOException,
			UnsupportedCallbackException {
		
		for(int i = 0; i < arg0.length; i++) {
			Callback cb = arg0[i];
			if(cb instanceof NameCallback) {
				NameCallback nameCallback = (NameCallback)cb;
				System.out.print(nameCallback.getPrompt() + "? ");
				System.out.flush();
				String userName = new BufferedReader(new InputStreamReader(System.in)).readLine();
				nameCallback.setName(userName);
			} else if(cb instanceof PasswordCallback) {
				PasswordCallback passwordCallback = (PasswordCallback)cb;
				System.out.print(passwordCallback.getPrompt() + "? ");
				System.out.flush();
				String password = new BufferedReader(new InputStreamReader(System.in)).readLine();
				passwordCallback.setPassword(password.toCharArray());
				password = null;
			} else {
				throw new UnsupportedCallbackException(cb, "Unsupported Callback Type!");
			}
		}
	}
}
