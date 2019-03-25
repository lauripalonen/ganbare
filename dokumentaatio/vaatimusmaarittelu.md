# Vaatimusmäärittely
Sovelluksella voi harjoitella japaninkielen sanastoa.
Sanasto perustuu Helsingin yliopiston japaninkielen kursseilla käytettävään
sanastoon. Jokainen sovelluksen käyttäjä luo oman yksilöllisen
käyttäjätunnuksen. Sanasto ja käyttäjät ovat omissa tietokannoissaan.

## Käyttäjät
Lähtökohtaisesti sovelluksella on vain yksi käyttäjärooli, eli normaali
käyttäjä. Mahdollinen myöhemmin lisättävä toiminnallisuus on
admin-käyttäjärooli, jolla voi esimerkiksi muokata sanastoa suoraan
käyttöliittymästä. 

## Käyttöliittymäluonnos
Sovelluksessa on seuraavat näkymät:

1. Kirjautusmisruutu - käyttäjätunnuksen luonti tai kirjautuminen
2. Valikkoruutu - harjoitussession määrittely
3. Tehtäväruutu - kysyttävä sana ja vastauslomake
4. Yhteenvetoruutu -  palaute suoritetusta tehtäväsessiosta

## Perusversion toiminnallisuuksia

### Kirjautumisruutu
- käyttäjä voi luoda uuden tunnuksen, tai kirjautua sovellukseen
- epäonnistunut kirjautuminen tai tunnuksen luonti antaa virheilmoituksen
- onnistunut kirjautuminen vie valikkoruutuun ja antaa onnistumisilmoituksen
- käyttäjätunnuksien tulee olla uniikkeja

### Valikkoruutu
- valikkoruudussa käyttäjä luo itselleen mieluisan harjoittelusession
- käyttäjän voi muokata seuraavia vaihtoehtoja:
  - session pituus: numeraaliarvo viidestä koko sanastokannan kokoon 
  - kysymysten suunta: suomi-japani tai japani-suomi
  - mitä sanastoa kysytään (rasti ruutuun): verbit, adjektiivit, substantiivit,
jne. 
- napit session käynnistämiselle ja sovelluksesta poistumiselle

### Tehtäväruutu
- ruudussa aina yksi kysyttävä sana kerrallaan
  - suomeksi kysymys on länsimaisilla aakkosilla
  - japaniksi kysymys on kana-merkistöllä
- näyttää session etenemisen (esim. 3/10 kysymykseen vastattu)
- lomakekenttä käyttäjän vastaukselle
- painikkeet vastaamiselle ja lopettamiselle
- seuraavalla kysymysruudulla lyhyt palaute oliko edeltävä vastaus oikein vai
väärin, ja oikea vastaus
- session sanasto koostuu satunnaisista sanastokannan sanoista, jotka
täsmäävät käyttäjän valikkoruudussa antamiin määrittelyihin

### Yhteenvetoruutu
- antaa palautteen sessiosta (esim. 9/10 vastausta oikein)
- painikkeet lopettamiselle ja valikkoruutuun palaamiselle

## Jatkokehitysideoita
- sovelluksen visuaalisen ilmeen kehittäminen
- audioelementtien lisääminen
- session optimointi käyttäjän edellisten suoritusten perusteella
  - käyttäjälle vaikeiden kysymysten kysyminen useammin
  - helppojen kysymysten kysyminen harvemmin
   - mahdollisuus myös käyttäjälle valita helppojen kysymysten pudottaminen
pois tulevista sessioista
- admin-käyttäjärooli
  - sanaston muokkaus suoraan sovelluksessa
- highscore-taulu
- sanojen tarkempi luokittelu:
  - kurssin aihealueiden mukaan (esim. Helsingin Yliopistolla käytettävän
 oppikirjan luvun 4 sanasto)
  - vaikeusaste, määräytyy sen mukaan miten eri sessioissa kysymykseen on vastattu
- kanjilaajennus
  - mahdollisuus kanjimerkistön harjoitteluun
- henkilökohtaisen sessiohistorian tarkastelu
  - esim. sessiopäivät, onnistumisprosentti, vaikeimmat sanat
- päivämäärän näyttäminen japaniksi aloitusruudussa
- session jatkaminen kunnes kaikki vastaukset oikein 
- palautejärjestelmän kehittäminen
  - käyttäjän palkitseminen eri tavoitteiden saavuttamisesta
