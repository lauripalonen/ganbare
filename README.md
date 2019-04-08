# Ganbare!!! 
_Ganbare!!!_ on harjoittelusovellus japanin kielen sanastolle. Sovelluksen käyttäjä voi harjoitella sanastoa joko japanista suomeen tai suomesta japaniin, ja valita haluamansa sanaluokat sekä harjoittelusession pituuden. Sanasto pohjautuu Helsingin yliopiston japanin kielen kurssien sanastoon. 

## Dokumentaatio
[Vaatimusmäärittely](https://github.com/Mieskalmari/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittelu.md)

[Työaikakirjanpito](https://github.com/Mieskalmari/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

## Tuorein versio - 08.04.2019
Sovelluksen käyttöliittymä on päivitetty tekstikäyttöliittymästä graafiseen. Kirjautumistoiminnallisuuksia ei vielä ole (sovelluksessa pääsee eteenpäin painamalla _kirjaudu_ tai _rekisteriöidy_ -nappeja). Valikkonäkymässä harjoittelukielen ja sessiopituuden valinta vaikuttavat generoitavaan sessioon (sanaluokat eivät vielä toimi). Sovellus pitää yllä oikeiden vastausten määrää ja antaa session päätteeksi siihen perustuvan palautteen. Palauteruudusta voi palata valikkonäkymään, tai poistua sovelluksesta.  

## Komentorivitoiminnot

### Ohjelman suoritus
Ohjelman voi suorittaa komennolla

```
mvn compile exec:java -Dexec.mainClass=ganbare.ui.GanbareUi
```

### Testaus
Testit suoritetaan komennolla

```
mvn test
```

Testikattavuusraportti luodaan komennolla

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avamaalla selaimella tiedosto _target/site/jacoco/index.html_

