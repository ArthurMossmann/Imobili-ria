package Imobiliaria.Model;

public class Endereco {
    private String logradouro;
    private String numero;
    private String bairro;
    private String cep;

    public Endereco(String logradouro, String numero, String bairro, String cep) {
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        this.cep = cep;
    }

    // Método especial toString() para que, quando imprimirmos o endereço, ele apareça formatado de forma bonita
    @Override
    public String toString() {
        return logradouro + ", Nº " + numero + " - " + bairro + " (CEP: " + cep + ")";
    }
}