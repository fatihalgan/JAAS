package com.fatih.jaas1;
import java.security.*;
//
// This class is a sensitive Personnel function that demonstrates
// the use of declarative authorization using the user defined
// permission PersonnelPermission, which throws an exception
// if it not granted
class PersonnelAction implements PrivilegedAction {
  public Object run() {
    AccessController.checkPermission(new PersonnelPermission("access"));
    System.out.println( "Subject has Personnel access\n");
    return new Integer(0);
  }
}
