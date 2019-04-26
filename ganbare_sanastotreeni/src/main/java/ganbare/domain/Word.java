package ganbare.domain;

import java.util.*;

/**
 * Represents a word of the lexicon. Includes it's different writing forms,
 * synonyms, class and chapter in which it is first encountered.
 */
public class Word {

    private String finnish;
    private String kana;
    private String romaji;
    private ArrayList<String> finnishSynonyms;
    private int classNum;
    private String wordClass;
    private int chapter;

    /**
     * 
     * @param finnish Finnish form of the word
     * @param kana Hiragana or Katakana form of the word
     * @param romaji Romaji form of the word
     * @param classNum Number corresponding to the database class value
     * @param chapter Number corresponding to the chapter in which the word
     * is first in countered Japanese course of the University of Helsinki
     */
    public Word(String finnish, String kana, String romaji, int classNum, int chapter) {
        this.finnishSynonyms = new ArrayList<>();

        this.finnish = finnish;
        this.kana = kana;
        this.romaji = romaji;
        this.classNum = classNum;
        this.chapter = chapter;

        if (this.classNum == 1) {
            this.wordClass = "substantive";
        } else if (this.classNum == 2) {
            this.wordClass = "adjective";
        } else if (this.classNum == 3) {
            this.wordClass = "verb";
        } else if (this.classNum == 4) {
            this.wordClass = "adverb";
        }

    }

    /**
     * 
     * @param finnish Finnish form of the word
     * @param kana Hiragana or Katakana form of the word
     * @param romaji Romaji form of the word
     * @param classNum Number corresponding to the database class value
     * @param chapter Number corresponding to the chapter in which the word
     * is first in countered Japanese course of the University of Helsinki
     * @param finnishSynonyms list of the synonyms of the Finnish word
     */
    public Word(String finnish, String kana, String romaji, int classNum, int chapter, ArrayList<String> finnishSynonyms) {
        this.finnishSynonyms = finnishSynonyms;

        this.finnish = finnish;
        this.kana = kana;
        this.romaji = romaji;
        this.classNum = classNum;
        this.chapter = chapter;

        if (this.classNum == 1) {
            this.wordClass = "substantive";
        } else if (this.classNum == 2) {
            this.wordClass = "adjective";
        } else if (this.classNum == 3) {
            this.wordClass = "verb";
        } else if (this.classNum == 4) {
            this.wordClass = "adverb";
        }

    }

    /**
     * 
     * @return word in Finnish
     */
    public String getFinnish() {
        return this.finnish;
    }

    /**
     * 
     * @return hiragana or katakana form of the word
     */
    public String getKana() {
        return this.kana;
    }

    /**
     * 
     * @return romaji form of the word
     */
    public String getRomaji() {
        return this.romaji;
    }

    /**
     * 
     * @return list of the synonyms of the word
     */
    public ArrayList<String> getFinnishSynonyms() {
        return this.finnishSynonyms;
    }

    /**
     * 
     * @return Database value of the class of the word
     */
    public int getClassNum() {
        return this.classNum;
    }

    /**
     * 
     * @return String value of the class of the word
     */
    public String getWordClass() {
        return this.wordClass;
    }

    /**
     * 
     * @return Chapter in which the word is first encountered
     */
    public int getChapter() {
        return this.chapter;
    }
}
