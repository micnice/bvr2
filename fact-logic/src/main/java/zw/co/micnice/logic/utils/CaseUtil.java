/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.utils;

/**
 *
 * @author tdhlakama
 */
public final class CaseUtil {
    
 static Boolean uppercaseSet = Boolean.TRUE;

    public static String upperCase(String c) {
           if (c != null && uppercaseSet) {
            return c.toUpperCase();
        }
        return c;
    }

    public static String lowerCase(String c) {
        if (c != null) {
            return c.toLowerCase();
        }
        return c;
    }
}
