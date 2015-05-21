/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.avans.library.main;

import edu.avans.library.businesslogic.MemberAdminManagerImpl;
import edu.avans.library.presentation.MemberAdminUI;

/**
 *
 * @author ppthgast
 */
public class Main {

    
    /**
     * private c'tor to prevent instantiation
     */
    private Main() {
       // deliberately empty
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        MemberAdminUI ui = new MemberAdminUI(new MemberAdminManagerImpl());
        ui.setVisible(true);
    }
}
