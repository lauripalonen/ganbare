# Vaatimusmäärittely
Sovelluksella voi harjoitella japaninkielen sanastoa.
Sanasto perustuu Helsingin yliopiston japaninkielen kursseilla käytettävään
sanastoon. Jokainen sovelluksen käyttäjä luo oman yksilöllisen
käyttäjätunnuksen. Sanasto ja käyttäjät ovat omissa tietokannoissaan.

## Käyttäjät
Sovelluksella on kaksi käyttäjäroolia: peruskäyttäjä ja admin-käyttäjä. Peruskäyttäjä voi kirjautua sovellukseen ja tehdä sanastoharjoituksia. Admin-käyttäjä voi lisätä uusia sanoja ja synonyymejä sanastoon.

## Käyttöliittymäluonnos
Sovelluksessa on seuraavat päänäkymät:

1. Kirjautusmisruutu - käyttäjätunnuksen luonti tai kirjautuminen
2. Valikkoruutu - harjoitussession määrittely
3. Tehtäväruutu - kysyttävä sana ja vastauslomake
4. Yhteenvetoruutu -  palaute suoritetusta tehtäväsessiosta

Lisäksi admin-käyttäjä pääsee seuraaviin näkymiin:

5. Admin-päävalikko - navigointi uuden sanan tai synonyymin lisäämiseen
6. Uusi sana -valikko - määrittelyt uudelle sanalle
7. Uusi synonyymi -valikko - määrittelyt uudelle synonyymille


## Perusversion toiminnallisuuksia

### Kirjautumisruutu
- käyttäjä voi luoda uuden tunnuksen, tai kirjautua sovellukseen
- epäonnistunut kirjautuminen tai tunnuksen luonti antaa virheilmoituksen
- käyttäjätunnuksien tulee olla uniikkeja
- admin-tunnuksella pääsee admin-tilaan

### Valikkoruutu
- valikkoruudussa käyttäjä luo itselleen mieluisan harjoittelusession
- käyttäjän voi muokata seuraavia vaihtoehtoja:
  - session pituus: numeraaliarvo yhdestä saatavilla olevien sanojen määrään
  - kysymysten suunta: suomi-japani tai japani-suomi
  - mitä sanastoa kysytään (rasti ruutuun): substantiivit, adjektiivit, verbit, adverbit

### Tehtäväruutu
- ruudussa aina yksi kysyttävä sana kerrallaan
  - suomeksi kysymys on länsimaisilla aakkosilla
  - japaniksi kysymys on kana-merkistöllä
- näyttää session etenemisen (esim. 3/10 kysymykseen vastattu)
- lomakekenttä käyttäjän vastaukselle
- seuraavalla kysymysruudulla lyhyt palaute oliko edeltävä vastaus oikein vai väärin, ja oikea vastaus
- session sanasto koostuu satunnaisista sanastokannan sanoista, jotka
täsmäävät käyttäjän valikkoruudussa antamiin määrittelyihin

### Yhteenvetoruutu
- antaa palautteen sessiosta (esim. Oikeita vastauksia: 9, kysymyksiä yhteensä: 10)
- Ruudusta voi palata alkuvalikkoon tai sulkea sovelluksen

## Jatkokehitysideoita
- sovelluksen visuaalisen ilmeen kehittäminen
- audioelementtien lisääminen
- session optimointi käyttäjän edellisten suoritusten perusteella
  - käyttäjälle vaikeiden kysymysten kysyminen useammin
  - helppojen kysymysten kysyminen harvemmin
   - mahdollisuus myös käyttäjälle valita helppojen kysymysten pudottaminen
pois tulevista sessioista
- highscore-taulu
- sanojen tarkempi luokittelu:
  - kurssin aihealueiden mukaan (esim. Helsingin Yliopistolla käytettävän oppikirjan lukujen mukaisesti)
  - vaikeusaste, määräytyy sen mukaan miten eri sessioissa kysymykseen on vastattu
- kanjilaajennus
  - mahdollisuus kanjimerkistön harjoitteluun
- henkilökohtaisen sessiohistorian tarkastelu
  - esim. sessiopäivät, onnistumisprosentti, vaikeimmat sanat
- päivämäärän näyttäminen japaniksi aloitusruudussa
- session jatkaminen kunnes kaikki vastaukset oikein 
- palautejärjestelmän kehittäminen
  - käyttäjän palkitseminen eri tavoitteiden saavuttamisesta
