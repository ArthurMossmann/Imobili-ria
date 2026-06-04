package Imobiliaria.Util;
import java.util.Scanner;

public class Leitor {

    private static final Scanner sc = new Scanner(System.in);

    public static String texto(String mensagem) {
        System.out.print(mensagem);
        return sc.nextLine().trim();
    }

    public static int inteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                int v = Integer.parseInt(sc.nextLine().trim());
                return v;
            } catch (NumberFormatException e) {
                System.out.println("  Valor inválido. Digite um número inteiro.");
            }
        }
    }

    public static double decimal(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                double v = Double.parseDouble(sc.nextLine().trim().replace(",", "."));
                return v;
            } catch (NumberFormatException e) {
                System.out.println("  Valor inválido. Ex: 1500.00 ou 1500,00");
            }
        }
    }

    public static boolean simNao(String mensagem) {
        while (true) {
            System.out.print(mensagem + " (s/n): ");
            String r = sc.nextLine().trim().toLowerCase();
            if (r.equals("s")) return true;
            if (r.equals("n")) return false;
            System.out.println("  Digite s ou n.");
        }
    }
}
