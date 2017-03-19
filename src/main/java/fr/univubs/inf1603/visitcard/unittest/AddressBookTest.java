/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univubs.inf1603.visitcard.unittest;

import fr.univubs.inf1603.visitcard.engine.AddressBook;
import static org.junit.Assert.*;

/**
 *
 * @author noemi
 */
public class AddressBookTest {

    public static void testEmptyAddressBook(AddressBook addressBook) {
        AddressBook addressBookTested = addressBook;
        assertEquals("", addressBookTested.getName());
        assertEquals(0, addressBookTested.getSize());
    }
    
}
