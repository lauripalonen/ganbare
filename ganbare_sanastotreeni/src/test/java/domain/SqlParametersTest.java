package domain;

import ganbare.domain.SqlParameters;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SqlParametersTest {

    SqlParameters sqlParams;

    @Before
    public void setUp() {
        this.sqlParams = new SqlParameters("suomi", true, true, true, true, 5);

    }

    @Test
    public void contructorGivesCorrespondingValuesToWordClasses() {
        int subs = sqlParams.getSubstantives();
        int adjs = sqlParams.getAdjectives();
        int verbs = sqlParams.getVerbs();
        int adverbs = sqlParams.getAdverbs();

        boolean allCorrect = true;

        if (subs != 1 || adjs != 2 || verbs != 3 || adverbs != 4) {
            allCorrect = false;
        }

        assertTrue(allCorrect);

    }

    @Test
    public void classValueIsZeroIfNotSelected() {
        SqlParameters newParams = new SqlParameters("suomi", true, false, true, false, 5);
        int subs = newParams.getSubstantives();
        int adjs = newParams.getAdjectives();
        int verbs = newParams.getVerbs();
        int adverbs = newParams.getAdverbs();

        boolean allCorrect = true;

        if (subs != 1 || adjs != 0 || verbs != 3 || adverbs != 0) {
            allCorrect = false;
        }

        assertTrue(allCorrect);

    }

    @Test
    public void constructorSetsLanguageCorrectly() {
        String language = this.sqlParams.getLanguage();

        assertEquals("suomi", language);
    }

    @Test
    public void constructorSetLengthCorrectly() {
        int length = this.sqlParams.getSessionLength();

        assertEquals(5, length);

    }

}
