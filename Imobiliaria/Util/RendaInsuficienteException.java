package Imobiliaria.Util;

// Criando uma exceção própria da empresa que herda de Exception
public class RendaInsuficienteException extends Exception {
    public RendaInsuficienteException(String mensagem) {
        super(mensagem);
    }
}