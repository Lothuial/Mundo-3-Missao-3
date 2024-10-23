/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

package cadastrobd;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.Scanner;
import model.PessoaFisica;
import model.PessoaFisicaDAO;
import model.PessoaJuridica;
import model.PessoaJuridicaDAO;

/**
 *
 * @author fel-f
 */

public class CadastroBD {

    private Scanner scan;
    private PessoaFisicaDAO PFD;
    private PessoaJuridicaDAO PJD;
    private static final Logger lgr = Logger.getLogger(CadastroBD.class.getName());
    
    public CadastroBD() {
        scan = new Scanner(System.in);
        PFD = new PessoaFisicaDAO();
        PJD = new PessoaJuridicaDAO();
    }
    
    private void menu() {
        System.out.println("-------------------------");
        System.out.println(" 1 - Incluir Pessoa");
        System.out.println(" 2 - Alterar Pessoa");
        System.out.println(" 3 - Excluir Pessoa");
        System.out.println(" 4 - Busca por ID");
        System.out.println(" 5 - Exibir todos");
        System.out.println(" 0 - Encerrar programa");
        System.out.println("-------------------------");
    }
    
    public void run() {
        int opcaoMenu = -1;
        while (opcaoMenu != '0') {
            menu();
            opcaoMenu = scan.next().charAt(0);
            scan.nextLine();
            try {
                switch (opcaoMenu) {
                    case '1' -> {
                        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
                        char tipoPessoa = scan.next().charAt(0);
                        scan.nextLine();
                        switch (tipoPessoa) {
                            case 'F' -> {
                                cadastroPF(PFD, scan);
                            }
                            case 'J' -> {
                                cadastroPJ(PJD, scan);
                            }
                            default -> {
                                System.out.println("Tipo de cadastro invalido.");
                            }
                        }
                        break;
                    }
                    case '2' -> {
                        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
                        char tipoPessoa = scan.next().charAt(0);
                        scan.nextLine();
                        switch (tipoPessoa) {
                            case 'F' -> {
                                alterarPF(PFD, scan);
                            }
                            case 'J' -> {
                                alterarPJ(PJD, scan);
                            }
                            default -> {
                                System.out.println("Tipo de cadastro invalido.");
                            }
                        }
                        break;
                    }
                    case '3' -> {
                        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
                        char tipoPessoa = scan.next().charAt(0);
                        scan.nextLine();
                        switch (tipoPessoa) {
                            case 'F' -> {
                                try {
                                    System.out.print("Digite o ID da Pessoa Fisica: ");
                                    int exclusao = Integer.parseInt(scan.nextLine());
                                    PessoaFisica peF = PFD.getPessoa(exclusao);
                                    if (peF != null){
                                        PFD.excluir(peF);
                                        System.out.println("A Pessoa Fisica foi excluida");
                                    } else {
                                        System.out.println("O ID nao foi encontrado.");
                                    }
                                }
                                catch (NullPointerException | SQLException e) {
                                    lgr.log(Level.SEVERE, e.toString(), e);
                                }
                            }
                            case 'J' -> {
                                try {
                                    System.out.print("Digite o ID da Pessoa Juridica: ");
                                    int exclusao = Integer.parseInt(scan.nextLine());
                                    PessoaJuridica peJ = PJD.getPessoa(exclusao);
                                    if (peJ != null){
                                        PJD.excluir(peJ);
                                        System.out.println("A Pessoa Juridica foi excluida");
                                    } else {
                                        System.out.println("O ID nao foi encontrado.");
                                    }
                                }
                                catch (NullPointerException | SQLException e) {
                                    lgr.log(Level.SEVERE, e.toString(), e);
                                }
                            }
                            default -> {
                                System.out.println("Tipo de cadastro invalido.");
                            }
                        }
                        break;
                    }
                    case '4' -> {
                        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
                        char tipoPessoa = scan.next().charAt(0);
                        scan.nextLine();
                        switch (tipoPessoa) {
                            case 'F' -> {
                                buscaPF(PFD, scan);
                            }
                            case 'J' -> {
                                buscaPJ(PJD, scan);
                            }
                            default -> {
                                System.out.println("Tipo de cadastro invalido.");
                            }
                        }
                        break;
                    }
                    case '5' -> {
                        System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
                        char tipoPessoa = scan.next().charAt(0);
                        scan.nextLine();
                        switch (tipoPessoa) {
                            case 'F' -> {
                                exibirPF(PFD);
                            }
                            case 'J' -> {
                                exibirPJ(PJD);
                            }
                            default -> {
                                System.out.println("Tipo de cadastro invalido.");
                            }
                        }
                        break;
                    }
                    case '0' -> {
                        System.out.println("Encerrando o programa...");
                    }
                    default -> {
                        System.out.println("Por favor digite uma opção válida.");
                    }
                }
            }
            catch (SQLException e) {
                System.out.println("Ocorreu o seguinte erro: " + e.getMessage());
            }
        }
    }
    
    private static void cadastroPF (PessoaFisicaDAO PFD, Scanner scan) throws SQLException {
        System.out.print("Digite o proximo ID: ");
        int id = Integer.parseInt(scan.nextLine());
        System.out.print("Digite o Nome: ");
        String nome = scan.nextLine();
        System.out.print("Digite o Endereco: ");
        String endereco = scan.nextLine();
        System.out.print("Digite a Cidade: ");
        String cidade = scan.nextLine();
        System.out.print("Digite o Estado: ");
        String estado = scan.nextLine();
        System.out.print("Digite o E-mail: ");
        String email = scan.nextLine();
        System.out.print("Digite o Telefone: ");
        String telefone = scan.nextLine();
        System.out.print("Digite o CPF: ");
        String cpf = scan.nextLine();
        PessoaFisica novaPF = new PessoaFisica(id, nome, endereco, cidade, estado, email, telefone, cpf);
        PFD.incluir(novaPF);
        System.out.println("Pessoa Fisica cadastrada com sucesso.");
    }
    
    private static void cadastroPJ (PessoaJuridicaDAO PJD, Scanner scan) throws SQLException {
        System.out.print("Digite o proximo ID: ");
        int id = Integer.parseInt(scan.nextLine());
        System.out.print("Digite o Nome: ");
        String nome = scan.nextLine();
        System.out.print("Digite o Endereco: ");
        String endereco = scan.nextLine();
        System.out.print("Digite a Cidade: ");
        String cidade = scan.nextLine();
        System.out.print("Digite o Estado: ");
        String estado = scan.nextLine();
        System.out.print("Digite o E-mail: ");
        String email = scan.nextLine();
        System.out.print("Digite o Telefone: ");
        String telefone = scan.nextLine();
        System.out.print("Digite o CNPJ: ");
        String cnpj = scan.nextLine();
        PessoaJuridica novaPJ = new PessoaJuridica(id, nome, endereco, cidade, estado, email, telefone, cnpj);
        PJD.incluir(novaPJ);
        System.out.println("Pessoa Juridica cadastrada com sucesso.");
    }
    
    private static void alterarPF (PessoaFisicaDAO PFD, Scanner scan) throws SQLException {
        System.out.println("Digite o ID da Pessoa Fisica: ");
        int id = Integer.parseInt(scan.nextLine());
        PessoaFisica pessoaAlter = PFD.getPessoa(id);
        if (pessoaAlter != null) {
            System.out.print("Digite o novo Nome: ");
            String novoNome = scan.nextLine();
            System.out.print("Digite o novo Endereco: ");
            String novoEndereco = scan.nextLine();
            System.out.print("Digite a nova Cidade: ");
            String novaCidade = scan.nextLine();
            System.out.print("Digite o novo Estado: ");
            String novoEstado = scan.nextLine();
            System.out.print("Digite o novo E-mail: ");
            String novoEmail = scan.nextLine();
            System.out.print("Digite o novo Telefone: ");
            String novoTelefone = scan.nextLine();
            System.out.print("Digite o novo CPF: ");
            String novoCPF = scan.nextLine();
            PessoaFisica pessoaTemp = new PessoaFisica(id, novoNome, novoEndereco, novaCidade, novoEstado, novoEmail, novoTelefone, novoCPF);
            PFD.alterar(pessoaTemp);
            System.out.println("O cadastro da Pessoa Fisica foi alterado");
        } else {
            System.out.println("Pessoa Fisica nao encontrada.");
        }
    }
    
    private static void alterarPJ (PessoaJuridicaDAO PJD, Scanner scan) throws SQLException {
        System.out.println("Digite o ID da Pessoa Juridica: ");
        int id = Integer.parseInt(scan.nextLine());
        PessoaJuridica pessoaAlter = PJD.getPessoa(id);
        if (pessoaAlter != null) {
            System.out.print("Digite o novo Nome: ");
            String novoNome = scan.nextLine();
            System.out.print("Digite o novo Endereco: ");
            String novoEndereco = scan.nextLine();
            System.out.print("Digite a nova Cidade: ");
            String novaCidade = scan.nextLine();
            System.out.print("Digite o novo Estado: ");
            String novoEstado = scan.nextLine();
            System.out.print("Digite o novo E-mail: ");
            String novoEmail = scan.nextLine();
            System.out.print("Digite o novo Telefone: ");
            String novoTelefone = scan.nextLine();
            System.out.print("Digite o novo CNPJ: ");
            String novoCNPJ = scan.nextLine();
            PessoaJuridica pessoaTemp = new PessoaJuridica(id, novoNome, novoEndereco, novaCidade, novoEstado, novoEmail, novoTelefone, novoCNPJ);
            PJD.alterar(pessoaTemp);
            System.out.println("O cadastro da Pessoa Juridica foi alterado");
        } else {
            System.out.println("Pessoa Juridica nao encontrada.");
        }
    }

    private static void buscaPF(PessoaFisicaDAO PFD, Scanner scan) throws SQLException {
        System.out.print("Digite o ID da Pessoa Fisica: ");
        int id = Integer.parseInt(scan.nextLine());
        PessoaFisica pessoa = PFD.getPessoa(id);
        if (pessoa != null) {
            System.out.println("-- Exibindo cadastro de Pessoa Fisica --");
            System.out.println("ID: " + pessoa.getID());
            System.out.println("Nome: " + pessoa.getNome());
            System.out.println("Endereco: " + pessoa.getEndereco());
            System.out.println("Cidade: " + pessoa.getCidade());
            System.out.println("Estado: " + pessoa.getEstado());
            System.out.println("E-mail: " + pessoa.getEmail());
            System.out.println("Telefone: " + pessoa.getTelefone());
            System.out.println("CPF: " + pessoa.getCPF());
        } else {
            System.out.println("Pessoa Fisica nao encontrada.");
        }
    }
    
    private static void buscaPJ(PessoaJuridicaDAO PJD, Scanner scan) throws SQLException {
        System.out.print("Digite o ID da Pessoa Juridica: ");
        int id = Integer.parseInt(scan.nextLine());
        PessoaJuridica pessoa = PJD.getPessoa(id);
        if (pessoa != null) {
            System.out.println("-- Exibindo cadastro de Pessoa Juridica --");
            System.out.println("ID: " + pessoa.getID());
            System.out.println("Nome: " + pessoa.getNome());
            System.out.println("Endereco: " + pessoa.getEndereco());
            System.out.println("Cidade: " + pessoa.getCidade());
            System.out.println("Estado: " + pessoa.getEstado());
            System.out.println("E-mail: " + pessoa.getEmail());
            System.out.println("Telefone: " + pessoa.getTelefone());
            System.out.println("CNPJ: " + pessoa.getCNPJ());
        } else {
            System.out.println("Pessoa Juridica nao encontrada.");
        }
    }
    
    private static void exibirPF(PessoaFisicaDAO PFD) throws SQLException {
        List<PessoaFisica> pessoas = PFD.getPessoas();
        if (pessoas.isEmpty()) {
            System.out.println("Não foram encontrados cadastros de Pessoas Fisicas.");
        } else {
            System.out.println("-- Exibindo todas as Pessoas Fisicas --");
            for (PessoaFisica PF : pessoas) {
                System.out.println("ID: " + PF.getID());
                System.out.println("Nome: " + PF.getNome());
                System.out.println("Endereco: " + PF.getEndereco());
                System.out.println("Cidade: " + PF.getCidade());
                System.out.println("Estado: " + PF.getEstado());
                System.out.println("E-mail: " + PF.getEmail());
                System.out.println("Telefone: " + PF.getTelefone());
                System.out.println("CPF: " + PF.getCPF());
                System.out.println();
            }
        }
    }
    
    private static void exibirPJ(PessoaJuridicaDAO PJD) throws SQLException {
        List<PessoaJuridica> pessoas = PJD.getPessoas();
        if (pessoas.isEmpty()) {
            System.out.println("Não foram encontrados cadastros de Pessoas Juridicas.");
        } else {
            System.out.println("-- Exibindo todas as Pessoas Juridicas --");
            for (PessoaJuridica PJ : pessoas) {
                System.out.println("ID: " + PJ.getID());
                System.out.println("Nome: " + PJ.getNome());
                System.out.println("Endereco: " + PJ.getEndereco());
                System.out.println("Cidade: " + PJ.getCidade());
                System.out.println("Estado: " + PJ.getEstado());
                System.out.println("E-mail: " + PJ.getEmail());
                System.out.println("Telefone: " + PJ.getTelefone());
                System.out.println("CNPJ: " + PJ.getCNPJ());
                System.out.println();
            }
        }
    }    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new CadastroBD().run();
    }
}
