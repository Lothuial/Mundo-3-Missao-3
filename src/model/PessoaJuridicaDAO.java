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
public class PessoaJuridicaDAO {
    
    public ConectorBD connector;
    
    public PessoaJuridicaDAO() {
        connector = new ConectorBD();
    }
    
    public PessoaJuridica getPessoa(int id) throws SQLException {
        String sql = "SELECT pj.FK_Pessoa_id, pj.CNPJ, pe.Nome, pe.Endereco, pe.Cidade, pe.Estado, pe.Email, pe.Telefone FROM PessoaJuridica pj INNER JOIN Pessoa pe ON pj.FK_Pessoa_id = pe.idPessoa WHERE pj.FK_Pessoa_id = ?";
        try (Connection con = connector.getConnection(); PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1,id);
            try (ResultSet res = pst.executeQuery()) {
                if (res.next()) {
                    PessoaJuridica peJ = new PessoaJuridica(
                    res.getInt("FK_Pessoa_id"),
                    res.getString("Nome"),
                    res.getString("Endereco"),
                    res.getString("Cidade"),
                    res.getString("Estado"),
                    res.getString("Email"),
                    res.getString("Telefone"),
                    res.getString("CNPJ")
                    );
                    return peJ;
                }
            }
        }
        return null;
    }
    
    public ArrayList<PessoaJuridica> getPessoas() throws SQLException {
        ArrayList<PessoaJuridica> list = new ArrayList<>();
        String sql = "SELECT pj.FK_Pessoa_id, pj.CNPJ, pe.Nome, pe.Endereco, pe.Cidade, pe.Estado, pe.Email, pe.Telefone FROM PessoaJuridica pj INNER JOIN Pessoa pe ON pj.FK_Pessoa_id = pe.idPessoa";
        try (Connection con = connector.getConnection(); PreparedStatement pst = con.prepareStatement(sql); ResultSet res = pst.executeQuery()) {
            while (res.next()) {
                list.add(new PessoaJuridica(
                res.getInt("FK_Pessoa_id"),
                res.getString("Nome"),
                res.getString("CNPJ"),
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
    
    public void incluir(PessoaJuridica peJ) throws SQLException {
        if (peJ.getNome() == null || peJ.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O campo Nome não pode estar vazio ou nulo.");
        }
        String sqlAddPessoa = "INSERT INTO Pessoa (idPessoa, Nome, Endereco, Cidade, Estado, Email, Telefone) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlAddPessoaJuridica = "INSERT INTO PessoaJuridica (FK_Pessoa_id, CNPJ) VALUES (?, ?)";
        try (Connection con = connector.getConnection(); PreparedStatement pstPessoa = con.prepareStatement(sqlAddPessoa)) {
            pstPessoa.setInt(1, peJ.getID());
            pstPessoa.setString(2, peJ.getNome());
            pstPessoa.setString(3, peJ.getEndereco());
            pstPessoa.setString(4, peJ.getCidade());
            pstPessoa.setString(5, peJ.getEstado());
            pstPessoa.setString(6, peJ.getEmail());
            pstPessoa.setString(7, peJ.getTelefone());
            int rows = pstPessoa.executeUpdate();
            if (rows == 0) {
            throw new SQLException("Ocorreu um erro ao criar a Pessoa.");
            }
            try (PreparedStatement pstPessoaFisica = con.prepareStatement(sqlAddPessoaJuridica)) {
                pstPessoaFisica.setInt(1, peJ.getID());
                pstPessoaFisica.setString(2, peJ.getCNPJ());
                pstPessoaFisica.executeUpdate();
            }

        }

    }
    
    
    public void alterar(PessoaJuridica peJ) throws SQLException {
        String sqlUpdatePessoa = "UPDATE Pessoa SET Nome = ?, Endereco = ?, Cidade = ?, Estado = ?, Email = ?, Telefone = ? WHERE idPessoa = ?;";
        String sqlUpdatePessoaFisica = "UPDATE PessoaJuridica SET CNPJ = ? WHERE FK_Pessoa_id = ?";
        try (Connection con = connector.getConnection(); PreparedStatement pstPessoa = con.prepareStatement(sqlUpdatePessoa); PreparedStatement pstPessoaFisica = con.prepareStatement(sqlUpdatePessoaFisica)) {
            pstPessoa.setString(1, peJ.getNome());
            pstPessoa.setString(2, peJ.getEndereco());
            pstPessoa.setString(3, peJ.getCidade());
            pstPessoa.setString(4, peJ.getEstado());
            pstPessoa.setString(5, peJ.getEmail());
            pstPessoa.setString(6, peJ.getTelefone());
            pstPessoa.setInt(7, peJ.getID());
            pstPessoa.executeUpdate();
            pstPessoaFisica.setString(1, peJ.getCNPJ());
            pstPessoaFisica.setInt(2, peJ.getID());
            pstPessoaFisica.executeUpdate();
        }
    }
    
    public void excluir(PessoaJuridica peJ) throws SQLException {
        String sqlDelPessoa = "DELETE FROM Pessoa WHERE idPessoa = ?;";
        String sqlDelPessoaFisica = "DELETE FROM PessoaJuridica WHERE FK_Pessoa_id = ?;";
        try (Connection con = connector.getConnection(); PreparedStatement pstPessoa = con.prepareStatement(sqlDelPessoa); PreparedStatement pstPessoaFisica = con.prepareStatement(sqlDelPessoaFisica)) {
            pstPessoa.setInt(1, peJ.getID());
            pstPessoa.executeUpdate();
            pstPessoaFisica.setInt(1, peJ.getID());
            pstPessoaFisica.executeUpdate();
        }
    }
    
    public void close() throws SQLException {
        connector.close();
    }
}
