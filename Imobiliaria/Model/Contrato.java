package Imobiliaria.Model;

import Imobiliaria.User.Locatario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Contrato implements Calculavel {

    private String id;
    private Imovel imovel;
    private Locatario locatario;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private boolean ativo;
    private double indiceReajuste; // percentual ex: 0.05 = 5%

    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Contrato(String id, Imovel imovel, Locatario locatario,
                    LocalDate dataInicio, LocalDate dataFim, double indiceReajuste) {
        this.id = id;
        this.imovel = imovel;
        this.locatario = locatario;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.indiceReajuste = indiceReajuste;
        this.ativo = true;
    }

    /**
     * RN4: Multa de 10% sobre o valor total restante em caso de rescisão antecipada.
     */
    public double calcularMulta() {
        if (!ativo || !LocalDate.now().isBefore(dataFim)) return 0;
        long mesesRestantes = dataInicio.until(dataFim).toTotalMonths()
                - dataInicio.until(LocalDate.now()).toTotalMonths();
        return imovel.calcularValorTotal() * mesesRestantes * 0.10;
    }


     // RN5: Aplica reajuste anual pelo IGP-M (ou índice configurado).

    public double calcularValorReajustado() {
        return imovel.calcularValorTotal() * (1 + indiceReajuste);
    }

    @Override
    public double calcularValorTotal() {
        return imovel.calcularValorTotal();
    }

    public void encerrarContrato() {
        this.ativo = false;
        imovel.setDisponivel(true);
    }

    public String getDetalhes() {
        return String.format(
                "Contrato ID: %s | Ativo: %s\n" +
                        "  Imóvel: %s (%s)\n" +
                        "  Locatário: %s\n" +
                        "  Período: %s até %s\n" +
                        "  Valor total: R$ %.2f | Reajuste (%s%%): R$ %.2f\n" +
                        "  Multa rescisão (se aplicável): R$ %.2f",
                id, ativo ? "Sim" : "Não",
                imovel.getId(), imovel.getEndereco(),
                locatario.getNome(),
                dataInicio.format(FMT), dataFim.format(FMT),
                calcularValorTotal(),
                (int)(indiceReajuste * 100),
                calcularValorReajustado(),
                calcularMulta()
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Locatario getLocatario() {
        return locatario;
    }

    public void setLocatario(Locatario locatario) {
        this.locatario = locatario;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public double getIndiceReajuste() {
        return indiceReajuste;
    }

    public void setIndiceReajuste(double indiceReajuste) {
        this.indiceReajuste = indiceReajuste;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
