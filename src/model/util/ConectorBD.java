/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 *
 * @author fel-f
 */

public class ConectorBD {
    
    public Connection con;
    public PreparedStatement pst;
    public ResultSet res;
    
    
    public Connection getConnection() throws SQLException {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Loja;encrypt=true;trustServerCertificate=true";
        String user = "loja";
        String password = "loja";
        con = DriverManager.getConnection(url, user, password);
        return con;
    }
    
    public ResultSet getSelect(String sql) throws SQLException {
        pst = getConnection().prepareStatement(sql);
        res = pst.executeQuery();
        return res;       
    }
    
    public int insert(String sql) throws SQLException {
        pst = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        pst.executeUpdate();
        res = pst.getGeneratedKeys();
        if (res.next()) {
            return res.getInt(1);
        } else {
            throw new SQLException("Ocorreu um erro ao inserir os dados.");
        }
    }
    
    public boolean update(String sql) throws SQLException {
        pst = getConnection().prepareStatement(sql);
        int rows = pst.executeUpdate();
        return rows > 0;
    }
    
    public void close() throws SQLException {
        if (pst != null && !pst.isClosed()) {
            pst.close();
        }
        if (res != null && !res.isClosed()) {
            res.close();
        }
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }
}
