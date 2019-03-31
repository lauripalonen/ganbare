# Ganbare!!! -sanastotreeni
Ganbare!!! -sanastotreeni on harjoittelusovellus japanin kielen sanastoa varten. Sanasto perustuu Helsingin Yliopiston japanin kielen kurssien sanastoon.  

## Dokumentaatio
[Vaatimusmäärittely](https://github.com/Mieskalmari/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittelu.md)

[Työaikakirjanpito](https://github.com/Mieskalmari/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

## Versiot
*29.3.2014 (Nykyinen)*

Raaka tekstikäyttöliittymärunko. Sovelluksella pystyy määrittelemään harjoittelusession pituuden ja vastaamaan session arpomiin kysymyksiin suomeksi. Sovellus ylläpitää tietoa oikeiden vastausten määrästä. Sanasto on tekstitiedostossa lexicon.txt. 

Session pituutta lukuunottamatta sessiomäärittelyn muut kohdat (kysymyskieli, sanaluokat) eivät vaikuta vielä generoitavaan harjoittelusessioon. 

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

