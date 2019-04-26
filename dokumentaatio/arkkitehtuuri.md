# Arkkitehtuurikuvaus

## Rakenne
Sovelluksella on toistaiseksi kolmitasoinen pakkausrakenne: 

<img src="https://raw.githubusercontent.com/Mieskalmari/ot-harjoitustyo/master/dokumentaatio/kuvat/pakkausrakenne.jpg">

Ui-pakkaus sisältää käyttöliittymän kokoavan koodin. Domain-pakkaus pitää sisällään sovelluslogiikasta vastaava nkoodin ja käsiteluokkia. Dao-pakkauksessa on koodia joka vastaa tietokannan kanssa kommunikoimisesta. 

## Käyttöliittymä

Käyttöliittymä sisältää neljä erilaista päänäkymää
 - Kirjautuminen
 - Harjoittelusession määrittely
 - Harjoittelusesssio
 - Palaute

 Lisäksi on erillinen admin-näkymä, joka saavutetaan kirjautumalla admin-tunnuksilla. Admin-näkymässä voi lisätä uuden sanan tai synonyymin tietokantaan. 

Näkymät on toteutettu omina [Scene](https://docs.oracle.com/javase/8/javafx/api/javafx/scene/Scene.html)-olioina. Näkymät ovat näkyvillä yksi kerrallaan sovelluksen [stagessa](https://docs.oracle.com/javase/8/javafx/api/javafx/stage/Stage.html). Näkymästä toiseen liikutaan näkymissä olevilla painikkeilla. Käyttöliittymä on toteutettu ohjelmallisesti luokassa [ganbare.ui.GanbareUi](https://github.com/Mieskalmari/ot-harjoitustyo/blob/master/ganbare_sanastotreeni/src/main/java/ganbare/ui/GanbareUi.java).



## Sovelluslogiikka
[GanbareService](https://github.com/Mieskalmari/ot-harjoitustyo/blob/master/ganbare_sanastotreeni/src/main/java/ganbare/domain/GanbareService.java)-luokka vastaa sovelluslogiikasta. Luokka toteuttaa käyttöliittymän vaatimat toiminnot ja esimerkiksi luo aina uuden harjoittelusession käyttäjälle [Session](https://github.com/Mieskalmari/ot-harjoitustyo/blob/master/ganbare_sanastotreeni/src/main/java/ganbare/domain/Session.java)-luokasta. Session-luokka generoi määrittelyjensä mukaisen sessiokohtaisen sanaston tietokannassa olevasta pääsanastosta. [Word](https://github.com/Mieskalmari/ot-harjoitustyo/blob/master/ganbare_sanastotreeni/src/main/java/ganbare/domain/Word.java)-luokka ylläpitää yksittäiseen sanaan liittyviä tietoja: suomenkieliset ja japaninkieliset kirjoitusasut, sanaluokka sekä kirjan kappale jossa se esiintyy ensikerran. [SqlParameters](https://github.com/Mieskalmari/ot-harjoitustyo/blob/master/ganbare_sanastotreeni/src/main/java/ganbare/domain/SqlParameters.java)-luokka sisältää lexiconDao-luokalle syötettävät sql-arvot ja kääntää esimerkiksi sanaluokkien merkkijonomuotoiset muuttujat tietokannan lukuarvo muotoisiin luokka-arvoihin. [LexiconDao](https://github.com/Mieskalmari/ot-harjoitustyo/blob/master/ganbare_sanastotreeni/src/main/java/ganbare/dao/LexiconDao.java)- ja [userDao](https://github.com/Mieskalmari/ot-harjoitustyo/blob/master/ganbare_sanastotreeni/src/main/java/ganbare/domain/UserDao.java)-luokat vastaavat kommunikoinnista sanasto- ja käyttäjätietokantojen kanssa.

Sovelluksen alustava pakkauskaavio:

<img src="https://raw.githubusercontent.com/Mieskalmari/ot-harjoitustyo/master/dokumentaatio/kuvat/pakkauskaavio.jpg">


## Tietojen pysyväistallennus
LexiconDao ja UserDao vastaavat tietojen tallennuksesta sanasto- ja käyttäjätietokantoihin. Käyttäjätietokantaan voi tallentaa uusia käyttäjiä sovelluksen peruskäyttäjät, mutta sanastotietokantaa voi muokata vain admin-tunnuksilla. 

## Toiminnallisuudet

### Kirjautuminen ja rekisteröityminen
Kirjautumisnäkymässä käyttäjä voi joko kirjautua, tai rekisteröidä uuden tunnuksen. Uusi tunnus luodaan, kun käyttäjä antaa käyttäjätunnukseksi uniikin nimen, sekä jonkin salasanan. Kirjautuminen onnistuu, mikäli käyttäjä antaa tietokannasta löytävät tunnukset. Mikäli käyttäjä kirjautuu admin-tunnuksilla, pääsee käyttäjä näkymään, jossa voi lisätä joko uuden sanan tietokantaan, tai uuden synonyymin tietokannasta löytyvälle sanalle.

### Halutun harjoittelusession luonti
Onnistuneen kirjautumisen tai rekisteröitymisen jälkeen, käyttäjä pääsee valitsemaan haluamansa harjoittelusession. Valikkonäkymässä valitaan sopivat parametrit, jonka jälkeen sovelluslogiikista vastaava GanbareService-luokka luo parametrejä vastaavat session. Mikäli käyttäjä antoi sopivat parametrit, avautuu seuraavaksi harjoittelusessionäkymä. 

### Kysymykseen vastaaminen

Oheinen sekvenssikaavio esittää mitä sovelluslogiikassa tapahtuu käyttäjän vastatessa kysymykseen: 

<img src="https://raw.githubusercontent.com/Mieskalmari/ot-harjoitustyo/master/dokumentaatio/kuvat/answersequence.png">

Kaaviossa ollaan tilanteessa, jossa käyttäjällä on jo edessään ensimmäinen kysymys. Kirjoitettuaan vastauksen vastauskenttään ja painettuaan _vastaa_ nappia, käyttöliittymä lähettää ganbareService-luokalle pyynnön tarkistuttaa vastauksen oikeellisuus. Luokka lähettää pyynnön edelleen kyseisestä harjoittelusessiota ylläpitävälle Session-luokalle, joka toteuttaa tarvittavat vertailut ja palauttaa palautteen ganbareServicen kautta takaisin käyttöliittymälle.

Samaa logiikkaa seuraten käyttöliittymä pyytää uutta kysymystä, jonka ganbareService välittää Session-luokalta. Käyttöliittymä asettaa uuden kysymyksen käyttäjälle, ja sykli voi alkaa alusta.