package ganbare.dao;

import java.sql.*;
import java.util.*;
import ganbare.domain.SqlParameters;

public class LexiconDao {

    private ArrayList<String[]> lexicon;

    public ArrayList<String[]> createLexicon(SqlParameters sqlParams) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:./lexicon", "sa", "");

        PreparedStatement stmt = connection.prepareStatement("SELECT id, finnish, kana, romaji FROM Lexicon WHERE class = ? OR class = ? OR class = ? OR class = ?");

        stmt.setInt(1, sqlParams.getSubstantives());
        stmt.setInt(2, sqlParams.getAdjectives());
        stmt.setInt(3, sqlParams.getVerbs());
        stmt.setInt(4, sqlParams.getAdverbs());

        ResultSet rs = stmt.executeQuery();

        this.lexicon = new ArrayList<>();

        while (rs.next()) {
            String[] word = new String[3];
            word[0] = rs.getString("finnish");
            word[1] = rs.getString("kana");
            word[2] = rs.getString("romaji");

            this.lexicon.add(word);
        }

        stmt.close();
        rs.close();

        connection.close();

        return this.lexicon;

    }

    public ArrayList<String[]> getWords() {
        return this.lexicon;
    }

    public void testConnection() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:h2:./lexicon", "sa", "");
        System.out.println("Connection established to: " + connection.getCatalog());
        System.out.println("URL: " + connection.getMetaData());
        System.out.println("");

        PreparedStatement stmt = connection.prepareStatement("SHOW TABLES");

        ResultSet rs = stmt.executeQuery();

        System.out.println("AVAILABLE TABLES: ");
        while (rs.next()) {

            System.out.println(rs.getString(1));
        }

        rs.close();
        stmt.close();
        connection.close();

    }

    public boolean addWord(String finnish, String kana, String romaji, int wordClass, int chapter) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:h2:./lexicon", "sa", "");

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

        Connection connection = DriverManager.getConnection("jdbc:h2:./lexicon", "sa", "");

        PreparedStatement stmt = connection.prepareStatement("SELECT id FROM Lexicon WHERE finnish = ?;");

        stmt.setString(1, original);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {

            int lexicon_id = rs.getInt(1);

            System.out.println("sana löydetty, id: " + lexicon_id);

            stmt = connection.prepareStatement("INSERT INTO SynonymFi(finnish)VALUES(?);");

            stmt.setString(1, synonym);

            stmt.executeUpdate();

            stmt = connection.prepareStatement("SELECT id FROM SynonymFi WHERE finnish = ?");

            stmt.setString(1, synonym);

            rs = stmt.executeQuery();

            if (rs.next()) {

                int synonymfi_id = rs.getInt(1);

                System.out.println("Synonyymi lisätty, id: " + synonymfi_id);

                stmt = connection.prepareStatement("INSERT INTO LexiconSynonymFI(lexicon_id, synonymfi_id)VALUES(?, ?);");

                stmt.setInt(1, lexicon_id);
                stmt.setInt(2, synonymfi_id);

                int update = stmt.executeUpdate();

                if (update == 1) {
                    System.out.println("Synonyymi yhdistetty originaaliin onnistuneesti!");

                    rs.close();
                    stmt.close();
                    connection.close();

                    return true;
                }

            }

        }

        rs.close();
        stmt.close();
        connection.close();

        return false;
    }

    public ArrayList<String> getFinnishSynonyms(String word) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:h2:./lexicon", "sa", "");

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

        Connection connection = DriverManager.getConnection("jdbc:h2:./lexicon", "sa", "");

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

}
