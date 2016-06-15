/*
 * Created on 08.Tem.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.fatih.jaas1;

import java.security.BasicPermission;

/**
 * @author db2admin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class PersonnelPermission extends BasicPermission {
	/**
	 * @param arg0
	 */
	public PersonnelPermission(String arg0) {
		super(arg0);
	}
	/**
	 * @param arg0
	 * @param arg1
	 */
	public PersonnelPermission(String arg0, String arg1) {
		super(arg0, arg1);
	}
}
