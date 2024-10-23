/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author fel-f
 */

public class PessoaFisica extends Pessoa {
    
    private String cpf;
    
    public PessoaFisica() {
        super();
    }
    
    public PessoaFisica(int id, String nome, String endereco, String cidade, String estado, String email, String telefone, String cpf) {
        super(id, nome, endereco, cidade, estado, email, telefone);
        this.cpf = cpf;
    }
    
    public void setCPF(String cpf) {
        this.cpf = cpf;
    }
    
    public String getCPF() {
        return cpf;
    }
    
    @Override
    public void exibir() {
        super.exibir();
        System.out.println("CPF: " + cpf);
    }
}
