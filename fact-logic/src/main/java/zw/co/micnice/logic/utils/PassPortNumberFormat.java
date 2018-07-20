/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.micnice.logic.utils;

/**
 *
 * @author simbabarry
 */
public class PassPortNumberFormat {
    
    public static String ZIMBABWE="^(\\d{2}-\\d{6,7}-\\w{1}-?\\d{2})$";

    public static String getZIMBABWE() {
        return ZIMBABWE;
    }
    
}
