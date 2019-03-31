# Ganbare!!! 
Ganbare!!! on japanin kielisen sanaston harjoittelusovellus. Sovelluksen käyttäjä voi harjoitella sanastoa joko japanista suomeen tai suomesta japaniin, ja valita haluamansa sanaluokat sekä harjoittelusession pituuden. Sanasto pohjautuu Helsingin yliopiston japanin kielen kurssien sanastoon. 

## Dokumentaatio
[Vaatimusmäärittely](https://github.com/Mieskalmari/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittelu.md)

[Työaikakirjanpito](https://github.com/Mieskalmari/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

## Versiot
**29.3.2014 (Nykyinen)**

Raaka tekstikäyttöliittymärunko. Sovelluksella pystyy määrittelemään harjoittelusession pituuden ja vastaamaan arvottuihin kysymyksiin suomeksi. Sovellus ylläpitää tietoa oikeiden vastausten määrästä. Sanasto on tekstitiedostossa _lexicon.txt_. 

Kirjautumistiedot, sekä sessiomäärittelyn kysymyskieli ja sanaluokat eivät vielä toteuta mitään varsinaisia toiminnallisuuksia sovelluksessa. Sovelluksella on yksi testi joka testaa sanaston generoitumista.   

## Komentorivitoiminnot

### Ohjelman suoritus
Ohjelman voi suorittaa komennolla

```
mvn compile exec:java -Dexec.mainClass=ganbare_trainer.ui.Main
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

