# Arkkitehtuurikuvaus

## Rakenne
Sovelluksella on toistaiseksi kolmitasoinen pakkausrakenne: 

<img src="https://raw.githubusercontent.com/Mieskalmari/ot-harjoitustyo/master/dokumentaatio/kuvat/pakkausrakenne.jpg">

Ui-pakkaus sisältää käyttöliittymän kokoavan koodin. Domain-pakkaus pitää sisällään sovelluslogiikasta vastaava nkoodin ja käsiteluokkia. Dao-pakkauksessa on koodia joka vastaa tietokannan kanssa kommunikoimisesta. 

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

<img src="https://raw.githubusercontent.com/Mieskalmari/ot-harjoitustyo/master/dokumentaatio/kuvat/pakkauskaavio.jpg">

Kaavion harmaat luokat ja pakkaukset ovat vielä toteuttamattomia. 

## Toiminnallisuudet

### Kysymykseen vastaaminen

Oheinen sekvenssikaavio esittää mitä sovelluslogiikassa tapahtuu käyttäjän vastatessa kysymykseen: 

<img src="https://raw.githubusercontent.com/Mieskalmari/ot-harjoitustyo/master/dokumentaatio/kuvat/answersequence.png">

Kaaviossa ollaan tilanteessa, jossa käyttäjällä on jo edessään ensimmäinen kysymys. Kirjoitettuaan vastauksen vastauskenttään ja painettuaan _vastaa_ nappia, käyttöliittymä lähettää ganbareService-luokalle pyynnön tarkistuttaa vastauksen oikeellisuus. Luokka lähettää pyynnön edelleen kyseisestä harjoittelusessiota ylläpitävälle Session-luokalle, joka toteuttaa tarvittavat vertailut ja palauttaa palautteen ganbareServicen kautta takaisin käyttöliittymälle.

Samaa logiikkaa seuraten käyttöliittymä pyytää uutta kysymystä, jonka ganbareService välittää Session-luokalta. Käyttöliittymä asettaa uuden kysymyksen käyttäjälle, ja sykli voi alkaa alusta.