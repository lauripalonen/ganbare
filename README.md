# Ganbare!!! 
_Ganbare!!!_ on harjoittelusovellus japanin kielen sanastolle. Sovelluksen käyttäjä voi harjoitella sanastoa joko japanista suomeen tai suomesta japaniin, ja valita haluamansa sanaluokat sekä harjoittelusession pituuden. Sanasto pohjautuu Helsingin yliopiston japanin kielen kurssien sanastoon. 

## Dokumentaatio
[Käyttöohje](https://github.com/Mieskalmari/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Vaatimusmäärittely](https://github.com/Mieskalmari/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittelu.md)

[Arkkitehtuurikuvaus](https://github.com/Mieskalmari/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Työaikakirjanpito](https://github.com/Mieskalmari/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

[Testausdokumentti](https://github.com/Mieskalmari/ot-harjoitustyo/blob/master/dokumentaatio/testaus.md)

## Releaset

[Loppupalautus](https://github.com/Mieskalmari/ot-harjoitustyo/releases/tag/loppupalautus)

## Komentorivitoiminnot

### Ohjelman suoritus
Ohjelman voi suorittaa komennolla

```
mvn compile exec:java -Dexec.mainClass=ganbare.ui.Main
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

### Checkstyle

Checkstyle-tiedoston luodaan komennolla

```
mvn jxr:jxr checkstyle:checkstyle
```

Checkstyle-raporttia voi tarkastella avaamalla selaimella tiedosto _target/site/checkstyle.html_

### JavaDoc

JavaDoc generoidaan komennolla

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimella tiedosto target/site/apidocs/index.html


