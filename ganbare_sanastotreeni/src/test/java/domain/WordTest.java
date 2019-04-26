package domain;

import ganbare.domain.Word;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class WordTest {

    Word testWord;

    public WordTest() {

    }

    @Before
    public void setUp() {
        testWord = new Word("test", "テスト", "tesuto", 1, 0);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void constructorFormsCorrectWordClass() {

        String wordClass = testWord.getWordClass();

        assertEquals("substantive", wordClass);

    }

    @Test
    public void constructorCreatesEmptyArray() {
        ArrayList<String> synonyms = testWord.getFinnishSynonyms();

        assertTrue(synonyms.isEmpty());
    }

    @Test
    public void constructorCreatesUnEmptyArray() {
        ArrayList<String> synonyms = new ArrayList<>();
        synonyms.add("koe");
        synonyms.add("kokeilu");

        Word anotherWord = new Word("test", "テスト", "tesuto", 1, 0, synonyms);

        assertTrue(!anotherWord.getFinnishSynonyms().isEmpty());

    }
}
