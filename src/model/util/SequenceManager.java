/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.util;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author fel-f
 */

public class SequenceManager {
    
    public int getValue (String sequenceName) throws SQLException {
        ResultSet res = new ConectorBD().getSelect("SELECT NEXT VALUE FOR " + sequenceName);
        if (res.next()) {
            return res.getInt(1);
        } else {
            throw new SQLException ("Ocorreu um erro ao obter o proximo valor da sequencia" + sequenceName);
        }
    }
}
