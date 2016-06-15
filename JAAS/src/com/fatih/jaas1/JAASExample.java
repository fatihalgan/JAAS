/*
 * Created on 01.Kas.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.fatih.jaas1;

import java.security.AccessControlException;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

public class JAASExample {

	static LoginContext lc = null;
	
	public static void main( String[] args) {
		//Create a login context
		try {
			lc = new LoginContext("JAASExample", new UsernamePasswordCallbackHandler());
		} catch(LoginException le) {
			System.out.println( "Login Context Creation Error" );
		    System.exit(1);
		}
		
		//Login
		try {
			lc.login();
		} catch(LoginException le) {
			System.out.println( "\nOVERALL AUTHENTICATION FAILED\n" );
		    System.exit(1);
		}
		System.out.println( "\nOVERALL AUTHENTICATION SUCCEEDED\n" );
	    System.out.println( lc.getSubject() );
	    
	    //Call the sensitive PayrollAction code, which uses programmatic authorization
		try {
			Subject.doAs(lc.getSubject(), new PayrollAction());
		} catch(AccessControlException e) {
			System.out.println( "Payroll Access DENIED" );
		}
		
		//Call the sensitive PersonnelAction code, which uses declarative authorization.
		try {
			Subject.doAsPrivileged(lc.getSubject(), new PersonnelAction(), null);
		} catch (AccessControlException e) {
			System.out.println( "Personnel Access DENIED" );
		}
	    // Logout
	    try {
	    	lc.logout();
	    } catch (LoginException le) {
	        System.out.println( "Logout FAILED" );
	        System.exit(1);
	    }
	    System.exit(0);
	}

}
