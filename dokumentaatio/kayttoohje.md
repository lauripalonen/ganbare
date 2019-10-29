# Käyttöohje

Lataa tiedosto [ganbare.zip](https://github.com/Mieskalmari/ot-harjoitustyo/releases/tag/v1.1). Pura .zip-tiedosto.

## Konfigurointi
Sovellus olettaa, että käynnistyshakemistossa ovat tietokantatiedostot _lexicon.mv.db_ ja _user.mv.db_. Tiedostot tulevat ganbare.zip -tiedoston mukana.

## Ohjelman käynnistäminen
Ohjelma käynnistetään komentoriviltä komennolla

```
java -jar ganbare.jar
```

## Kirjautuminen

Sovellus käynnistyy kirjautumisnäkymään, jossa on mahdollisuus joko kirjautua tai rekisteröityä. Tietokannassa on valmiina testikäyttäjätunnus _testi_ salasanalla _123_. Rekisteröityminen onnistuu, kun syöttää tunnuksen jota ei ole vielä tietokannassa.

<img src="https://raw.githubusercontent.com/Mieskalmari/ot-harjoitustyo/master/dokumentaatio/kuvat/sovelluskuvat/kirjautumisruutu.png">


## Harjoittelusession luonti
Valikkonäkymässä voi valita haluamansa harjoittelusession.
- Harjoittelukieli määrittää mikä on kyselykieli (oletusasetuksena japanista suomeen). 
- Sanaluokkia valitsemalla sessioon otetaan mukaan sanoja kyseistä luokista.
- Session pituudella määritetään montako sanaa kysytään. Huomaa että aluksi tulee valita vähintään yksi sanaluokka, jotta pituuden voi määrittää.

Sovellus aloittaa harjoittelusession kun valikossa on määritelty sopivat vaihtoehdot ja painetaan _Aloita_-nappia.

<img src="https://raw.githubusercontent.com/Mieskalmari/ot-harjoitustyo/master/dokumentaatio/kuvat/sovelluskuvat/valikkoruutu.png">

## Vastaaminen
Harjoittelusessionäkymässä sovellus esittää kysymyksen valitulla kielellä, johon käyttäjä vastaa kirjoittamalla vastauksensa tekstikenttään. Kun kyselykielenä on japani, vastataan tällöin suomeksi. Kun kyselykielenä on suomi, vastataan romajeilla, eli japanin kielen romanisoidulla ulkoasulla. Session voi halutessaan lopettaa kesken joko painamalla _lopeta_, jolloin näkymä vaihtuu palauteruutuun, tai paina _alkuvalikkoon_, josta palataan valikkonäkymään. Muussa tapauksessa sovellus esittää kysymyksiä määritellyn sanamäärän mittaisesti. 

<img src="https://raw.githubusercontent.com/Mieskalmari/ot-harjoitustyo/master/dokumentaatio/kuvat/sovelluskuvat/tehtavaruutu.png">

Palauteruudussa näkee oikeiden vastausten määrä sekä kysymysten kokonaismäärä. Palauteruudusta voi sulkea sovelluksen tai palata alkuvalikkoon.

<img src="https://raw.githubusercontent.com/Mieskalmari/ot-harjoitustyo/master/dokumentaatio/kuvat/sovelluskuvat/palauteruutu.png">

## Admin toiminnot
Mikäli sovellukseen kirjautuu admin-tunnuksilla (tunnus: _admin_, salasana: _admin_), pääsee admin-näkymään, josta voi siirtyä lisäämään uuden sanan tai synonyymin.

<img src="https://raw.githubusercontent.com/Mieskalmari/ot-harjoitustyo/master/dokumentaatio/kuvat/sovelluskuvat/admintila.png">

### Uuden sanan lisääminen
Jokainen tekstikentän ruutu on täytettävä, ja sanalle tulee valita pudotusvalikosta sen sanaluokka ja kappale jossa se esiintyy. Sana lisätään vain paikalliseen tietokantaan. Sovelluksessa ei voi toistaiseksi poistaa sanoja.

<img src="https://raw.githubusercontent.com/Mieskalmari/ot-harjoitustyo/master/dokumentaatio/kuvat/sovelluskuvat/lisaasana.png">


### Uuden synonyymin lisääminen
Synonyymiä lisättäessä tulee tietää tietokannan suomenkielinen sana (_Alkuperäinen sana_) jolle lisätään synonyymi. Synonyymi lisätään vain paikalliseen tietokantaan. 

<img src="https://raw.githubusercontent.com/Mieskalmari/ot-harjoitustyo/master/dokumentaatio/kuvat/sovelluskuvat/lisaasynonyymi.png">
