package ganbare.dao;

import java.sql.*;
import java.util.*;
import ganbare.domain.SqlParameters;
import ganbare.domain.Word;

public class LexiconDao {

    private String address;

    public LexiconDao(String address) {
        this.address = address;
    }

    private ArrayList<Word> lexicon;

    public ArrayList<Word> createLexicon(SqlParameters sqlParams) throws SQLException {
        Connection connection = DriverManager.getConnection(this.address, "sa", "");

        PreparedStatement stmt = connection.prepareStatement("SELECT id, finnish, kana, romaji, class, chapter FROM Lexicon WHERE class = ? OR class = ? OR class = ? OR class = ?");

        stmt.setInt(1, sqlParams.getSubstantives());
        stmt.setInt(2, sqlParams.getAdjectives());
        stmt.setInt(3, sqlParams.getVerbs());
        stmt.setInt(4, sqlParams.getAdverbs());

        ResultSet rs = stmt.executeQuery();

        this.lexicon = new ArrayList<>();

        while (rs.next()) {

            ArrayList<String> synonyms = getFinnishSynonyms(rs.getString("finnish"));

            Word word = new Word(rs.getString("finnish"), rs.getString("kana"), rs.getString("romaji"), rs.getInt("class"), rs.getInt("chapter"), synonyms);

            this.lexicon.add(word);

        }

        stmt.close();
        rs.close();

        connection.close();

        return this.lexicon;

    }

    public ArrayList<Word> getWords() {
        return this.lexicon;
    }

    public boolean addWord(String finnish, String kana, String romaji, int wordClass, int chapter) throws SQLException {

        Connection connection = DriverManager.getConnection(this.address, "sa", "");

        PreparedStatement stmt = connection.prepareStatement("INSERT INTO Lexicon(finnish, kana, romaji, class, chapter) "
                + "VALUES(?, ?, ?, ?, ?);");

        stmt.setString(1, finnish);
        stmt.setString(2, kana);
        stmt.setString(3, romaji);
        stmt.setInt(4, wordClass);
        stmt.setInt(5, chapter);

        int changedRows = stmt.executeUpdate();

        if (changedRows == 1) {
            stmt.close();
            connection.close();
            return true;
        }

        stmt.close();
        connection.close();
        return false;
    }

    public boolean addFinnishSynonym(String original, String synonym) throws SQLException {

        Connection connection = DriverManager.getConnection(this.address, "sa", "");

        int originalId = getFinnishId(original, connection);

        if (originalId == -1) {
            return false;
        }
        int synonymId = addWordToSynonymFi(synonym, connection);

        if (synonymId == -1) {
            return false;
        }

        return linkOriginalAndSynonym(originalId, synonymId, connection);

    }

    public int getFinnishId(String word, Connection connection) throws SQLException {

        PreparedStatement stmt = connection.prepareStatement("SELECT id FROM Lexicon WHERE finnish = ?;");

        stmt.setString(1, word);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {

            return rs.getInt(1);
        }

        return -1;

    }

    public int addWordToSynonymFi(String synonym, Connection connection) throws SQLException {

        PreparedStatement stmt = connection.prepareStatement("INSERT INTO SynonymFi(finnish)VALUES(?);");

        stmt.setString(1, synonym);

        stmt.executeUpdate();

        stmt = connection.prepareStatement("SELECT id FROM SynonymFi WHERE finnish = ?");

        stmt.setString(1, synonym);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getInt(1);
        }

        return -1;
    }

    public boolean linkOriginalAndSynonym(int originalId, int synonymId, Connection connection) throws SQLException {

        PreparedStatement stmt = connection.prepareStatement("INSERT INTO LexiconSynonymFI(lexicon_id, synonymfi_id)VALUES(?, ?);");

        stmt.setInt(1, originalId);
        stmt.setInt(2, synonymId);

        int update = stmt.executeUpdate();

        return update == 1;
    }

    public ArrayList<String> getFinnishSynonyms(String word) throws SQLException {

        Connection connection = DriverManager.getConnection(this.address, "sa", "");

        PreparedStatement stmt = connection.prepareStatement("SELECT SynonymFi.finnish FROM SynonymFi "
                + "JOIN LexiconSynonymFi ON LexiconSynonymFi.synonymfi_id = SynonymFi.id "
                + "JOIN Lexicon ON LexiconSynonymFi.lexicon_id = Lexicon.id "
                + "WHERE lexicon.finnish = ?");

        stmt.setString(1, word);

        ResultSet rs = stmt.executeQuery();

        ArrayList<String> words = new ArrayList<>();

        while (rs.next()) {
            words.add(rs.getString(1));
        }

        rs.close();
        stmt.close();
        connection.close();

        return words;

    }

    public int getCount(int wordClass) throws SQLException {

        Connection connection = DriverManager.getConnection(this.address, "sa", "");

        PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM Lexicon WHERE class = ?");

        stmt.setInt(1, wordClass);

        ResultSet rs = stmt.executeQuery();

        int wordCount = 0;

        if (rs.next()) {
            wordCount = rs.getInt(1);
        }

        rs.close();
        stmt.close();
        connection.close();

        return wordCount;
    }

    public void formatTestLexicon() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:h2:./lexiconTest", "sa", "");

        PreparedStatement stmt = connection.prepareStatement("DELETE FROM Lexicon WHERE id > 25");

        stmt.executeUpdate();

        connection.close();

        stmt.close();

    }

    public int getTotalCount() throws SQLException {

        Connection connection = DriverManager.getConnection(this.address, "sa", "");

        PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM Lexicon;");

        ResultSet rs = stmt.executeQuery();


        if (rs.next()) {
            int count = rs.getInt(1);

            rs.close();
            stmt.close();
            connection.close();

            return count;
        }

        rs.close();
        stmt.close();
        connection.close();

        return -1;
    }

}
