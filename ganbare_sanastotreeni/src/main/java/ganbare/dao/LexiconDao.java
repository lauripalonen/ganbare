package ganbare.dao;

import java.sql.*;
import java.util.*;
import ganbare.domain.SqlParameters;

public class LexiconDao {

    private ArrayList<String[]> lexicon;

    public ArrayList<String[]> createLexicon(SqlParameters sqlParams) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:./lexicon.db", "sa", "");

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

        return this.lexicon;

    }

    public ArrayList<String[]> getWords() {
        return this.lexicon;
    }

    public int totalWordCount(SqlParameters sqlParams) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:h2:./lexicon.db", "sa", "");

        PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) FROM Lexicon WHERE class = ? OR class = ? OR class = ? OR class = ?;");

        stmt.setInt(1, sqlParams.getSubstantives());
        stmt.setInt(2, sqlParams.getAdjectives());
        stmt.setInt(3, sqlParams.getVerbs());
        stmt.setInt(4, sqlParams.getAdverbs());

        ResultSet rs = stmt.executeQuery();

        int wordCount = 0;

        while (rs.next()) {

            wordCount = rs.getInt(1);

        }

        return wordCount;

    }

}
