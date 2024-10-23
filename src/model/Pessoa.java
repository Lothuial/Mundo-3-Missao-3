/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package model;

/**
 *
 * @author fel-f
 */

public class Pessoa {
    
    private Integer id;
    private String nome;
    private String endereco;
    private String cidade;
    private String estado;
    private String email;
    private String telefone;
    
    public Pessoa() {
        
    }
    
    public Pessoa(Integer id, String nome, String endereco, String cidade, String estado, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.email = email;
        this.telefone = telefone;
    }
    
    public void exibir() {
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Endereco: " + endereco);
        System.out.println("Cidade: " + cidade);
        System.out.println("Estado: " + estado);
        System.out.println("E-mail: " + email);
        System.out.println("Telefone: " + telefone);
    }
     
    public void setID(int id) {
        this.id = id;
    }
    
    public int getID() {
        return id;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public String getEndereco() {
        return endereco;
    }
    
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    
    public String getCidade() {
        return cidade;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String getEstado() {
        return estado;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    public String getTelefone() {
        return telefone;
    }
}
