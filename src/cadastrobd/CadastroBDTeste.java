/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package cadastrobd;

import java.sql.SQLException;
import java.util.ArrayList;
import model.PessoaFisicaDAO;
import model.PessoaJuridicaDAO;
import model.PessoaFisica;
import model.PessoaJuridica;

/**
 *
 * @author fel-f
 */

public class CadastroBDTeste {
    
    private final PessoaFisicaDAO PFD;
    private final PessoaJuridicaDAO PJD;
    
    public CadastroBDTeste() {
        PFD = new PessoaFisicaDAO();
        PJD = new PessoaJuridicaDAO();
    }
    
    private void run() {
        PessoaFisica peF = new PessoaFisica(5, "Vinicius Rocha Lima", "Travessa Sao Joao, 186", "Rio de Janeiro", "Rio de Janeiro", "ViniciusRochaLima@dayrep.com", "21 6391-4762", "127.460.858-95");
        
        if (peF.getNome() == null || peF.getNome().trim().isEmpty()) {
            System.out.println("O campo Nome não pode estar vazio.");
            return;
        }
        
        try {
            PFD.incluir(peF);
            System.out.println("A Pessoa Fisica foi incluida com sucesso");
            peF.exibir();
            peF.setEndereco("Rua Armandino Gonzaga, 386");
            peF.setCidade("Florianopolis");
            peF.setEstado("Santa Catarina");
            PFD.alterar(peF);
            System.out.println("Pessoa Fisica alterada com sucesso.");
            peF.exibir();
            ArrayList<PessoaFisica> listaPF = PFD.getPessoas();
            System.out.println("Exibindo todas as Pessoas Fisicas:");
            for (PessoaFisica pessoa : listaPF) {
                pessoa.exibir();
            }
            PFD.excluir(peF);
            System.out.println("Pessoa Fisica excluida com sucesso");
            PFD.close();
        }
        catch (SQLException e) {
                System.out.println("Ocorreu o seguinte erro: " + e.getMessage());
        }
        
        PessoaJuridica peJ = new PessoaJuridica(6, "Patterson-Fletcher", "Travessa das Cravinas, 103", "Salvador", "Bahia", "assistenciaPFjourrapide.com", "71 8036-9442", "81.669.950/0001-05");
        
        if (peJ.getNome() == null || peJ.getNome().trim().isEmpty()) {
            System.out.println("O campo Nome não pode estar vazio");
            return;
        }
        
        try {
            PJD.incluir(peJ);
            System.out.println("A Pessoa Juridica foi incluida com sucesso");
            peJ.exibir();
            peJ.setNome("Fletcher Enterprises");
            peJ.setEmail("Helpdesk.FletcherEnterprises@domain.com");
            PJD.alterar(peJ);
            System.out.println("Pessoa Juridica Alterada com sucesso.");
            peJ.exibir();
            ArrayList<PessoaJuridica> listaPJ = PJD.getPessoas();
            System.out.println("Exibindo toas as Pessoas Juridicas:");
            for (PessoaJuridica pessoa : listaPJ) {
                pessoa.exibir();
            }
            PJD.excluir(peJ);
            System.out.println("Pessoa Juridica excluida com sucesso.");
            PJD.close();
        }
        catch (SQLException e) {
                System.out.println("Ocorreu o seguinte erro: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        new CadastroBDTeste().run();
    }
}
