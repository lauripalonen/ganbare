package ganbare.domain;

import java.util.*;

public class Word {

    private String finnish;
    private String kana;
    private String romaji;
    private ArrayList<String> finnishSynonyms;
    private int classNum;
    private String wordClass;
    private int chapter;

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

    public String getFinnish() {
        return this.finnish;
    }

    public String getKana() {
        return this.kana;
    }

    public String getRomaji() {
        return this.romaji;
    }

    public ArrayList<String> getFinnishSynonyms() {
        return this.finnishSynonyms;
    }

    public int getClassNum() {
        return this.classNum;
    }

    public String getWordClass() {
        return this.wordClass;
    }

    public int getChapter() {
        return this.chapter;
    }
}
