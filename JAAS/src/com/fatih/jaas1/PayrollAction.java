package com.fatih.jaas1;
import java.security.*;
import javax.security.auth.*;
import java.util.*;
//
// This class is a sensitive Payroll function that demonstrates the
// use of programmatic authorization which only allows a subject
// that contains the principal "joeuser" in
class PayrollAction implements PrivilegedAction {
  public Object run() {
    // Get the passed in subject from the DoAs
    AccessControlContext context = AccessController.getContext();
    Subject subject = Subject.getSubject( context );
    if (subject == null ) {
      throw new AccessControlException("Denied");
    }
    //
    // Interate through the principal set looking for joeuser.  If
    // he is not found, 
    Set principals = subject.getPrincipals();
    Iterator iterator = principals.iterator();
    while (iterator.hasNext()) {
      PrincipalImpl principal = (PrincipalImpl)iterator.next();
      if (principal.getName().equals( "joeuser" )) {
        System.out.println("joeuser has Payroll access\n");
        return new Integer(0);
      }
    }
    throw new AccessControlException("Denied");
  }
}
