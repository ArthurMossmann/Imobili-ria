package Imobiliaria.Util;

public class Leitor {

    // Método estático para desenhar linhas divisórias nos menus da Main
    public static void exibirLinhaSeparadora() {
        System.out.println("=================================================================");
    }

    // Exibe feedbacks positivos destacando o texto em VERDE no terminal
    public static void exibirMensagemSucesso(String mensagem) {
        System.out.println("\u001B[32m[SUCESSO] " + mensagem + "\u001B[0m");
    }

    // Exibe feedbacks de erro ou validações reprovadas em VERMELHO no terminal
    public static void exibirMensagemErro(String mensagem) {
        System.out.println("\u001B[31m[ERRO] " + mensagem + "\u001B[0m");
    }
}