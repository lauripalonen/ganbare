# Arkkitehtuurikuvaus

## Rakenne
Toistaiseksi sovelluksella on hyvin yksinkertainen, kaksitasoisen pakkausrakenne: 

<img src="https://raw.githubusercontent.com/Mieskalmari/ot-harjoitustyo/master/dokumentaatio/kuvat/pakkausrakenne.jpg>

Rakenne saa lisää kerroksia tulevaisuudessa, kun sovellukseen lisätään käyttäjätoiminnallisuudet ja sanastolle luodaan oma tietokanta.

## Käyttöliittymä

Käyttöliittymä sisältää neljä erilaista näkymää
 - Kirjautuminen
 - Harjoittelusession määrittely
 - Harjoittelusesssio
 - Palaute

Näkymät on toteutettu omina [Scene](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html)-olioina. Näkymät ovat näkyvillä yksi kerrallaan sovelluksen [stagessa](https://docs.oracle.com/javase/8/javafx/api/javafx/stage/Stage.html). Näkymästä toiseen liikutaan näkymissä olevilla painikkeilla. Käyttöliittymä on toteutettu ohjelmallisesti luokassa [ganbare.ui.GanbareUi](https://github.com/Mieskalmari/ot-harjoitustyo/blob/master/ganbare_sanastotreeni/src/main/java/ganbare/ui/GanbareUi.java).

## Sovelluslogiikka
[GanbareService](https://github.com/Mieskalmari/ot-harjoitustyo/blob/master/ganbare_sanastotreeni/src/main/java/ganbare/domain/GanbareService.java)-luokka vastaa sovelluslogiikasta. Luokka toteuttaa käyttöliittymän vaatimat toiminnot ja esimerkiksi luo aina uuden harjoittelusession käyttäjälle [Session](https://github.com/Mieskalmari/ot-harjoitustyo/blob/master/ganbare_sanastotreeni/src/main/java/ganbare/domain/Session.java)-luokasta. Session-luokka generoi määrittelyjensä mukaisen sessiokohtaisen sanaston tietokannassa olevasta pääsanastosta. 

Sovelluksen alustava pakkauskaavio:
<img src="https://raw.githubusercontent.com/Mieskalmari/ot-harjoitustyo/master/dokumentaatio/kuvat/pakkauskaavio.jpg>

Kaaviossa harmaalla on sovelluksen luokat ja pakkaukset, joita ei ole vielä toteutettu. 