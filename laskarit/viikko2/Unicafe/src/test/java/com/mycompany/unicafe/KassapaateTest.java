/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author palolaur
 */
public class KassapaateTest {

    Kassapaate kassapaate;
    Maksukortti korttiVaraa;
    Maksukortti korttiEiVaraa;

    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
        korttiVaraa = new Maksukortti(1000);
        korttiEiVaraa = new Maksukortti(100);
    }

    @Test
    public void uudenKassanPohjakassaOnOikein() {
        assertEquals(100000, kassapaate.kassassaRahaa());
    }

    @Test
    public void uudellaKassallaEiOleMyynteja() {
        assertEquals(0, (kassapaate.maukkaitaLounaitaMyyty() + kassapaate.edullisiaLounaitaMyyty()));
    }

    @Test
    public void kassanSaldoKasvaaOikeinEdullisellaMyynnilla() {

        kassapaate.syoEdullisesti(240);

        assertEquals(100240, kassapaate.kassassaRahaa());
    }

    @Test
    public void kassaPalauttaaVaihtorahanOikeinEdullisellaMyynnilla() {

        assertEquals(60, kassapaate.syoEdullisesti(300));
    }

    @Test
    public void myytyjenEdullistenLounaidenMaaraKasvaa() {

        kassapaate.syoEdullisesti(240);

        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }

    @Test
    public void kassanSaldoKasvaaOikeinMaukkaallaMyynnilla() {

        kassapaate.syoMaukkaasti(400);

        assertEquals(100400, kassapaate.kassassaRahaa());
    }

    @Test
    public void kassaPalauttaaVaihtrahanOikeinMaukkaallaMyynnilla() {

        assertEquals(100, kassapaate.syoMaukkaasti(500));
    }

    @Test
    public void myytyjenMaukkaidenLounaidenMaaraKasvaa() {

        kassapaate.syoMaukkaasti(400);

        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void kassanSaldoEiKasvaKunEdullinenMaksuEiRiita() {
        kassapaate.syoEdullisesti(100);

        assertEquals(100000, kassapaate.kassassaRahaa());
    }

    @Test
    public void kassanSaldoEiKasvaKunMaukasMaksuEiRiita() {
        kassapaate.syoMaukkaasti(300);

        assertEquals(100000, kassapaate.kassassaRahaa());
    }

    @Test
    public void riittamatonRahaEdullisessaMyynnissaPalautetetaan() {
        assertEquals(200, kassapaate.syoEdullisesti(200));
    }

    @Test
    public void riittamatonRahaMaukkaassaMyynnissaPalautetaan() {
        assertEquals(300, kassapaate.syoMaukkaasti(300));
    }

    @Test
    public void myytyjenEdullistenLounaidenMaaraEiKasvaKunRahaEiRiita() {
        kassapaate.syoEdullisesti(100);

        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }

    @Test
    public void myytyjenMaukkaidenLounaidenMaaraEiKasvaKunRahaEiRiita() {
        kassapaate.syoMaukkaasti(300);

        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void kortiltaVeloitetaanEdullinenLounas() {
        kassapaate.syoEdullisesti(korttiVaraa);

        assertEquals(760, korttiVaraa.saldo());
    }

    @Test
    public void kortiltaVeloitetaanMaukasLounas() {
        kassapaate.syoMaukkaasti(korttiVaraa);

        assertEquals(600, korttiVaraa.saldo());
    }

    @Test
    public void kassaPalauttaaTrueEdullisellaOstolla() {
        assertEquals(true, kassapaate.syoEdullisesti(korttiVaraa));

    }

    @Test
    public void kassaPalauttaaTrueMaukkaallaOstolla() {
        assertEquals(true, kassapaate.syoMaukkaasti(korttiVaraa));
    }

    @Test
    public void korttiostoKasvattaaMyytyjenEdullistenLounaidenMaaraa() {
        kassapaate.syoEdullisesti(korttiVaraa);

        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }

    @Test
    public void korttiostoKasvattaaMyytyjenMaukkaidenLounaidenMaaraa() {
        kassapaate.syoMaukkaasti(korttiVaraa);

        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void korttiaEiVeloitetaJosSaldoEiRiitaEdulliseenLounaaseen() {

        kassapaate.syoEdullisesti(korttiEiVaraa);

        assertEquals(100, korttiEiVaraa.saldo());
    }

    @Test
    public void korttiaEiVeloitetaJosSaldoEiRiitaMaukkaaseenLounaaseen() {

        kassapaate.syoMaukkaasti(korttiEiVaraa);

        assertEquals(100, korttiEiVaraa.saldo());
    }

    @Test
    public void myydytEdullisetLounaatEiKasvaJosKortillaEiVaraa() {

        kassapaate.syoEdullisesti(korttiEiVaraa);

        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());

    }

    @Test
    public void myydytMaukkaatLounaatEiKasvaJosKortillaEiVaraa() {

        kassapaate.syoMaukkaasti(korttiEiVaraa);

        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void kassaPalauttaaFalseJosKortillaEiVaraaEdulliseen() {

        assertEquals(false, kassapaate.syoEdullisesti(korttiEiVaraa));
    }

    @Test
    public void kassaPalauttaaFalseJosKortillaEiVaraaMaukkaaseen() {

        assertEquals(false, kassapaate.syoMaukkaasti(korttiEiVaraa));
    }

    @Test
    public void kassanSaldoEiKasvaJosKorttiaVeloittaessaEdullisesti() {
        kassapaate.syoEdullisesti(korttiVaraa);

        assertEquals(100000, kassapaate.kassassaRahaa());
    }

    @Test
    public void kassanSaldoEiKasvaJosKorttiaVeloittaessaMaukkaasti() {
        kassapaate.syoMaukkaasti(korttiVaraa);

        assertEquals(100000, kassapaate.kassassaRahaa());
    }

    @Test
    public void kortinSaldoKasvaaKortilleLadattaessa() {
        kassapaate.lataaRahaaKortille(korttiEiVaraa, 300);

        assertEquals(400, korttiEiVaraa.saldo());

    }
    
    @Test
    public void kassanSaldoKasvaaKortilleLadattaessa() {
        kassapaate.lataaRahaaKortille(korttiEiVaraa, 300);
        
        assertEquals(100300, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kortilleEiVoiLadataNegatiivistaSummaa() {
        kassapaate.lataaRahaaKortille(korttiEiVaraa, -100);
        
        assertEquals(100, korttiEiVaraa.saldo());
    }

}
