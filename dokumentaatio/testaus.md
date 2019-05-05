# Testausdokumentti

Ohjelman testauksessa on käytetty automaattisia JUnit-testejä, sekä manuaalisesti havainnoiden järjestelmätasolla.

## Testauskattavuus

Käyttöliittymäluokkaa lukuunottamatta testauksen rivikattavuus on 72% ja haarautumakattavuus 51%

<img src="https://raw.githubusercontent.com/Mieskalmari/ot-harjoitustyo/master/dokumentaatio/kuvat/testikattavuus.png">

Testauksen ulkopuolelle jäi pääasiassa metodeja, jotka ovat tekemisessä tietokantojen kanssa.

## Sovellukseen jääneet laatuongelmat

Sovellus ei etene kirjautumisruudusta
- Ongelma todettu ainakin W10-käyttöjärjestelmissä
- Sovellus ei todennäköisesti tällöin yhdistä onnistuneesti tietokantatiedostoihin
