# Käyttöohje

Lataa tiedosto ganbare.jar

## Konfigurointi
Sovellus olettaa, että käynnistyshakemistossa ovat tietokantatiedostot _lexicon.mv.db_ ja _user.mv.db_. 

## Ohjelman käynnistäminen
Ohjelma käynnistetään komentoriviltä komennolla

```
java -jar ganbare.jar
```

## Kirjautuminen

Sovellus käynnistyy kirjautumisnäkymään, jossa on mahdollisuus joko kirjautua, tai rekisteröityä. Tietokannassa on valmiina testikäyttäjätunns _testi_ salasanalla _123_.
Rekisteröityminen onnistuu, kun syöttää tunnuksen jota ei ole vielä tietokannassa.


## Harjoittelusession luonti
Valikkonäkymässä voi valita haluamansa harjoittelusession.
- Harjoittelukieli määrittää mikä on kyselykieli (oletusasetuksena japanista suomeen). 
- Sanaluokkia valitsemella sessioon otetaan mukaan sanoja kyseistä luokista.
- Session pituudella määritetään montako sanaa kysytään. Huomaa että aluksi tulee valita väh. yksi sanaluokka, jotta pituuden voi määrittää.

Sovellus aloittaa harjoittelusession kun valikossa on määritelty sopivat vaihtoehdot ja painetaan _Aloita_-nappia.

## Vastaaminen
Harjoittelusessionäkymässä sovellus esittää kysymyksen valitulla kielellä, johon käyttäjä vastaa kirjoittamalla vastauksensa tekstikenttään. Kun kyselykielenä on japani, vastataan tällöin suomeksi. Kun kyselykielenä on suomi, vastataan romajeilla, eli japanin kielen romanisoidulla ulkoasulla. Session voi halutessaan lopettaa kesken joko painamalla _lopeta_, jolloin näkymä vaihtuu palauteruutuun, tai paina _alkuvalikkoon_, josta palataan valikkonäkymään. Muussa tapauksessa sovellus esittää kysymyksiä määritellyn sanamäärän mittaisesti. 

Palauteruudussa näkee oikeiden vastausten määrä sekä kysymysten kokonaismäärä. Palauteruudusta voi sulkea sovelluksen tai palata alkuvalikkoon.

## Admin toiminnot
Mikäli sovellukseen kirjautuu admin-tunnuksilla (tunnus: _admin_, salasana: _admin_), pääsee admin-näkymään, jossa voi lisätä uuden sanan tai synonyymin. Lisäys tapahtuu vain paikalliseen tietokantaan. Huomaa että sovelluksessa ei voi toistaiseksi poistaa sanoja! Synonyymiä lisättäessä sanan alkuperäinen muoto tulee olla jo tietokannassa valmiina.