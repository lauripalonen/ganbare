/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import java.util.*;
import java.sql.*;

/**
 *
 * @author palolaur
 */
public class LexiconDao {

    public String read(Integer key) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:./lexicon.db", "sa", "");

        PreparedStatement stmt = connection.prepareStatement("SELECT finnish, kana FROM Lexicon WHERE id = ?");
        
        stmt.setInt(1, key);
        
        ResultSet rs = stmt.executeQuery();
        
        if (!rs.next()){
            return null;
        }
        
        String word = rs.getString("finnish");
        String translation = rs.getString("kana");
        
        stmt.close();
        rs.close();
        connection.close();
        
        System.out.println(word + ": " + translation);
        
        return word + ": " + translation;
        
    }

}
