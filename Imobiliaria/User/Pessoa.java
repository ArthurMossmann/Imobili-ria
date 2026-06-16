package Imobiliaria.User;

public class Pessoa {

    private String nome;
    private int idade;
    private String CPF;
    private String endereco;
    private Double rendaMensal;
    private String email;
    private String telefone;

    public Pessoa() {
    }

    public Pessoa(String nome, int idade, String CPF, String endereco, Double rendaMensal, String email, String telefone) {
        this.nome = nome;
        this.idade = idade;
        this.CPF = CPF;
        this.endereco = endereco;
        this.rendaMensal = rendaMensal;
        this.email = email;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCPF() {
        return CPF;
    }
    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Double getRendaMensal() {
        return rendaMensal;
    }
    public void setRendaMensal(Double rendaMensal) {
        this.rendaMensal = rendaMensal;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}