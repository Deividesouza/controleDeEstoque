package controleDeEstoque;

import java.time.LocalDate;
import java.time.Period;

public class ProdutoPerecivel extends Produto {
    private LocalDate dataValidade;
    private boolean refrigerado;

    public ProdutoPerecivel(String nome, int codigo, int quantidadeEstoque, double preco, String descricao, LocalDate dataValidade, boolean refrigerado) {
        super(nome, codigo, quantidadeEstoque, preco, descricao);
        this.dataValidade = dataValidade;
        this.refrigerado = refrigerado;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public boolean isRefrigerado() {
        return refrigerado;
    }

    public void setRefrigerado(boolean refrigerado) {
        this.refrigerado = refrigerado;
    }

    @Override
    public String getDescricao() {
        String descricao = super.getDescricao();
        int diasParaVencer = Period.between(LocalDate.now(), dataValidade).getDays();
        if (diasParaVencer <= 30) {
            descricao += " - Faltam " + diasParaVencer + " dias para vencer";
        }
        return descricao;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | %-10s | %-15s",
                dataValidade, refrigerado ? "Sim" : "Não");
    }
}
