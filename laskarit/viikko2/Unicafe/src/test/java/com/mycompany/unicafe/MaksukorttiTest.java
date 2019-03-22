package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoAlussaOikein() {
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void kortilleLatautuuOikeaMaaraRahaa() {
        kortti.lataaRahaa(10);
        
        assertEquals("saldo: 0.20", kortti.toString());
    }
    
    @Test
    public void kortiltaVoiOttaaRahaa() {
        kortti.lataaRahaa(300);
        
        kortti.otaRahaa(240);
        
        assertEquals("saldo: 0.70", kortti.toString());
    }
    
    @Test
    public void kortinSaldoEiMeneNegatiiviseksi() {
        kortti.otaRahaa(240);
        
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void korttiPalauttaaTrueKunSaldoaOnTarpeeksi () {
        boolean maksu = kortti.otaRahaa(5);
        
        assertEquals(true, maksu);    
    }
    
    @Test
    public void korttiPalauttaaFalseKunSaldoEiRiita () {
        boolean maksu = kortti.otaRahaa(15);
        
        assertEquals(false, maksu);
    }
    
    @Test
    public void saldoPieneneeKunRahaaOnTasmalleenOikeaMaara () {
        kortti.otaRahaa(10);
        
        assertEquals("saldo: 0.0", kortti.toString());        
    }
    
    @Test
    public void korttiPalauttaaTrueKunRahaaOnTasmalleenOikeaMaara () {
        boolean maksu = kortti.otaRahaa(10);
        
        assertEquals(true, maksu);
    }
}
