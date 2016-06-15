/*
 * Created on 05.Tem.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package com.fatih.jaas1;

import java.io.Serializable;
import java.security.Principal;

/**
 * @author db2admin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class PrincipalImpl implements Principal, Serializable {
	
	private String name;
	/**
	 * 
	 */
	public PrincipalImpl(String name) {
		super();
		this.name = name;
	}
	/* (non-Javadoc)
	 * @see java.security.Principal#getName()
	 */
	public String getName() {
		return name;
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof PrincipalImpl)) return false;
		PrincipalImpl pobj = (PrincipalImpl)o;
		if(name.equals(pobj.getName())) return true;
		return false;
	}
	
	public int hashCode() {
		return name.hashCode();
	}
	
	public String toString() {
		return getName();
	}
}
