/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.univubs.inf1603.visitcard.unittest;

import fr.univubs.inf1603.visitcard.engine.Address;
import fr.univubs.inf1603.visitcard.engine.AddressObserver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author noemi
 */
public class AddressTest {
    
    /**
     * testEmptyAddress - Test des différents méthodes avec une liste vide
     * 
     * @param address L'adresse vide passé en paramettre
     */
    public static void testEmptyAddress(Address address){
        Address addressTested = address; //Pourquoi???????
        assertEquals(new String(""),addressTested.getStreet());
        assertEquals(new String(""),addressTested.getCity());
        assertEquals(new String(""),addressTested.getZipCode());
        assertEquals(new String(""),addressTested.getCountry());
    }
    
    /**
     * testCompletedAddress - Test des différentes méthodes avec l'adresse complété. 
     *                 On passe en paramètre une adresse vide qui sera remplie en début de test. 
     * 
     * @param address L'adresse vide passé en paramettre
     */
    public static void testCompletedAddress(Address address){
        
        //On créé une classe interne d'observer
        /**
         * AddressObserverTest - Classe interne. 
         *                       Implémentation de l'interface AddressObserver de l'engine. 
         *                       N'est utilisé que pour les test unitaires de l'adresse
         * 
         */
        class AddressObserverTest implements AddressObserver {
            
            /**
             * notifiedStreetChange - Passe à vrai si la méthode streetChanged est appelé
             */
            public boolean notifiedStreetChange;
            
            /**
             * notifiedCityChanged - Passe à vrai si la méthode cityChanged est appelé
             */
            public boolean notifiedCityChanged;
            
            /**
             * notifiedZipCodeChanged - Passe à vrai si la méthode zipCodeChanged est appelé
             */
            public boolean notifiedZipCodeChanged;
            
            /**
             * notifiedCountryChanged - Passe à vrai si la méthode countryChanged est appelé
             */
             public boolean notifiedCountryChanged;
            
            public AddressObserverTest(){
                this.notifiedStreetChange = false ;
                this.notifiedCityChanged = false ;
                this.notifiedZipCodeChanged = false ;
                this.notifiedCountryChanged = false ;
            }
            
            @Override
            public void streetChanged(Address ad, String oldStreet, String newStreet) {
                this.notifiedStreetChange=true;
            }

            @Override
            public void cityChanged(Address ad, String oldCity, String newCity) {
                this.notifiedCityChanged=true;
            }

            @Override
            public void zipCodeChanged(Address ad, String oldZip, String newZip) {
                this.notifiedZipCodeChanged=true;
            }

            @Override
            public void countryChanged(Address ad, String oldCountry, String newCountry) {
                this.notifiedCountryChanged=true;
            }
            
        }
        
        Address addressTested = address;
        AddressObserverTest addressObserver = new AddressObserverTest();
        
        //On remplie l'adresse
        //Ajout d'observer
        addressTested.addAddressObserver(addressObserver);
        //Modification de la rue
        addressTested.setStreet("13 rue Victor Hugo");
        assertTrue(addressObserver.notifiedStreetChange);
        assertFalse(addressObserver.notifiedCityChanged);
        assertFalse(addressObserver.notifiedZipCodeChanged);
        assertFalse(addressObserver.notifiedCountryChanged);
        assertEquals(new String("13 rue Victor Hugo"),addressTested.getStreet());
        assertEquals(new String(""),addressTested.getCity());
        assertEquals(new String(""),addressTested.getZipCode());
        assertEquals(new String(""),addressTested.getCountry());
        //Modification de la ville
        addressObserver.notifiedStreetChange=false;
        addressTested.setCity("Vannes");
        assertFalse(addressObserver.notifiedStreetChange);
        assertTrue(addressObserver.notifiedCityChanged);
        assertFalse(addressObserver.notifiedZipCodeChanged);
        assertFalse(addressObserver.notifiedCountryChanged);
        assertEquals(new String("13 rue Victor Hugo"),addressTested.getStreet());
        assertEquals(new String("Vannes"),addressTested.getCity());
        assertEquals(new String(""),addressTested.getZipCode());
        assertEquals(new String(""),addressTested.getCountry());
        //Modification du code postal
        addressObserver.notifiedCityChanged=false;
        addressTested.setZipCode("56000");
        assertFalse(addressObserver.notifiedStreetChange);
        assertFalse(addressObserver.notifiedCityChanged);
        assertTrue(addressObserver.notifiedZipCodeChanged);
        assertFalse(addressObserver.notifiedCountryChanged);
        assertEquals(new String("13 rue Victor Hugo"),addressTested.getStreet());
        assertEquals(new String("Vannes"),addressTested.getCity());
        assertEquals(new String("56000"),addressTested.getZipCode());
        assertEquals(new String(""),addressTested.getCountry());
        //Modification du pays
        addressObserver.notifiedZipCodeChanged=false;
        addressTested.setCountry("FRANCE");
        assertFalse(addressObserver.notifiedStreetChange);
        assertFalse(addressObserver.notifiedCityChanged);
        assertFalse(addressObserver.notifiedZipCodeChanged);
        assertTrue(addressObserver.notifiedCountryChanged);
        assertEquals(new String("13 rue Victor Hugo"),addressTested.getStreet());
        assertEquals(new String("Vannes"),addressTested.getCity());
        assertEquals(new String("56000"),addressTested.getZipCode());
        assertEquals(new String("FRANCE"),addressTested.getCountry());
        //Supression d'observer
        addressObserver.notifiedCountryChanged=false;
        addressTested.removeAddressObserver(addressObserver);
        addressTested.setStreet("18 rue Victor Hugo");
        assertFalse(addressObserver.notifiedStreetChange);
        assertFalse(addressObserver.notifiedCityChanged);
        assertFalse(addressObserver.notifiedZipCodeChanged);
        assertFalse(addressObserver.notifiedCountryChanged);
        assertEquals(new String("18 rue Victor Hugo"),addressTested.getStreet());
        assertEquals(new String("Vannes"),addressTested.getCity());
        assertEquals(addressTested.getZipCode(),new String("56000"));
        assertEquals(new String("FRANCE"),addressTested.getCountry());
        //Toutes les méthodes sont testés !
    }    
    
}
