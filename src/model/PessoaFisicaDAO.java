/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.util.ConectorBD;

/**
 *
 * @author fel-f
 */

public class PessoaFisicaDAO {
    
    public ConectorBD connector;
    
    public PessoaFisicaDAO() {
        connector = new ConectorBD();
    }
    
    public PessoaFisica getPessoa(int id) throws SQLException {
        String sql = "SELECT pf.FK_Pessoa_id, pf.CPF, pe.Nome, pe.Endereco, pe.Cidade, pe.Estado, pe.Email, pe.Telefone FROM PessoaFisica pf INNER JOIN Pessoa pe ON pf.FK_Pessoa_id = pe.idPessoa WHERE pf.FK_Pessoa_id = ?";
        try (Connection con = connector.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1,id);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    PessoaFisica peF = new PessoaFisica(
                    res.getInt("FK_Pessoa_id"),
                    res.getString("Nome"),
                    res.getString("Endereco"),
                    res.getString("Cidade"),
                    res.getString("Estado"),
                    res.getString("Email"),
                    res.getString("Telefone"),
                    res.getString("CPF")
                    );
                    return peF;
                }
            }
        }
        return null;
    }
    
    public ArrayList<PessoaFisica> getPessoas() throws SQLException {
        ArrayList<PessoaFisica> list = new ArrayList<>();
        String sql = "SELECT pf.FK_Pessoa_id, pf.CPF, pe.Nome, pe.Endereco, pe.Cidade, pe.Estado, pe.Email, pe.Telefone FROM PessoaFisica pf INNER JOIN Pessoa pe ON pf.FK_Pessoa_id = pe.idPessoa";
        try (Connection con = connector.getConnection(); PreparedStatement pst = con.prepareStatement(sql); ResultSet res = pst.executeQuery()) {
            while (res.next()) {
                list.add(new PessoaFisica(
                res.getInt("FK_Pessoa_id"),
                res.getString("Nome"),
                res.getString("CPF"),
                res.getString("Endereco"),
                res.getString("Cidade"),
                res.getString("Estado"),
                res.getString("Email"),
                res.getString("Telefone")               
                ));
            }
        }
        return list;
    }
    
    public void incluir(PessoaFisica peF) throws SQLException {
        if (peF.getNome() == null || peF.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O campo Nome não pode estar vazio ou nulo.");
        }
        String sqlAddPessoa = "INSERT INTO Pessoa (idPessoa, Nome, Endereco, Cidade, Estado, Email, Telefone) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlAddPessoaFisica = "INSERT INTO PessoaFisica (FK_Pessoa_id, CPF) VALUES (?, ?)";
        try (Connection con = connector.getConnection(); PreparedStatement pstPessoa = con.prepareStatement(sqlAddPessoa)) {
            pstPessoa.setInt(1, peF.getID());
            pstPessoa.setString(2, peF.getNome());
            pstPessoa.setString(3, peF.getEndereco());
            pstPessoa.setString(4, peF.getCidade());
            pstPessoa.setString(5, peF.getEstado());
            pstPessoa.setString(6, peF.getEmail());
            pstPessoa.setString(7, peF.getTelefone());
            int rows = pstPessoa.executeUpdate();
            if (rows == 0) {
            throw new SQLException("Ocorreu um erro ao criar a Pessoa.");
            }
            try (PreparedStatement pstPessoaFisica = con.prepareStatement(sqlAddPessoaFisica)) {
                pstPessoaFisica.setInt(1, peF.getID());
                pstPessoaFisica.setString(2, peF.getCPF());
                pstPessoaFisica.executeUpdate();
            }

        }

    }
        
    
    
    public void alterar(PessoaFisica peF) throws SQLException {
        String sqlUpdatePessoa = "UPDATE Pessoa SET Nome = ?, Endereco = ?, Cidade = ?, Estado = ?, Email = ?, Telefone = ? WHERE idPessoa = ?;";
        String sqlUpdatePessoaFisica = "UPDATE PessoaFisica SET CPF = ? WHERE FK_Pessoa_id = ?";
        try (Connection con = connector.getConnection(); PreparedStatement pstPessoa = con.prepareStatement(sqlUpdatePessoa); PreparedStatement pstPessoaFisica = con.prepareStatement(sqlUpdatePessoaFisica)) {
            pstPessoa.setString(1, peF.getNome());
            pstPessoa.setString(2, peF.getEndereco());
            pstPessoa.setString(3, peF.getCidade());
            pstPessoa.setString(4, peF.getEstado());
            pstPessoa.setString(5, peF.getEmail());
            pstPessoa.setString(6, peF.getTelefone());
            pstPessoa.setInt(7, peF.getID());
            pstPessoa.executeUpdate();
            pstPessoaFisica.setString(1, peF.getCPF());
            pstPessoaFisica.setInt(2, peF.getID());
            pstPessoaFisica.executeUpdate();
        }
    }
    
    public void excluir(PessoaFisica peF) throws SQLException {
        String sqlDelPessoa = "DELETE FROM Pessoa WHERE idPessoa = ?;";
        String sqlDelPessoaFisica = "DELETE FROM PessoaFisica WHERE FK_Pessoa_id = ?;";
        try (Connection con = connector.getConnection(); PreparedStatement pstPessoa = con.prepareStatement(sqlDelPessoa); PreparedStatement pstPessoaFisica = con.prepareStatement(sqlDelPessoaFisica)) {
            pstPessoa.setInt(1, peF.getID());
            pstPessoa.executeUpdate();
            pstPessoaFisica.setInt(1, peF.getID());
            pstPessoaFisica.executeUpdate();
        }
    }
    
    public void close() throws SQLException {
        connector.close();
    }
}
