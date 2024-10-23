/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author fel-f
 */

public class PessoaJuridica extends Pessoa {
    
    private String cnpj;
    
    public PessoaJuridica() {
        super();
    }
    
    public PessoaJuridica(int id, String nome, String endereco, String cidade, String estado, String email, String telefone, String cnpj) {
        super(id, nome, endereco, cidade, estado, email, telefone);
        this.cnpj = cnpj;
    }
    
    public void setCNPJ(String cnpj) {
        this.cnpj = cnpj;
    }
    
    public String getCNPJ() {
        return cnpj;
    }
    
    @Override
    public void exibir() {
        super.exibir();
        System.out.println("CNPJ: " + cnpj);
    }
}
