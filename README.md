# Ganbare!!! 
_Ganbare!!!_ on harjoittelusovellus japanin kielen sanastolle. Sovelluksen käyttäjä voi harjoitella sanastoa joko japanista suomeen tai suomesta japaniin, ja valita haluamansa sanaluokat sekä harjoittelusession pituuden. Sanasto pohjautuu Helsingin yliopiston japanin kielen kurssien sanastoon. 

## Dokumentaatio
[Vaatimusmäärittely](https://github.com/Mieskalmari/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittelu.md)

[Arkkitehtuurikuvaus](https://github.com/Mieskalmari/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Työaikakirjanpito](https://github.com/Mieskalmari/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

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

Checkstyle-tiedoston luodaan komennolla

```
mvn jxr:jxr checkstyle:checkstyle
```

Checkstyle-raporttia voi tarkastella avaamalla selaimella tiedosto _target/site/checkstyle.html_
