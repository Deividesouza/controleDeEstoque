package controleDeEstoque;

// Classe Produto representa um item genérico no estoque
public class Produto implements Comparable<Produto> {
    private String nome;
    private int codigo;
    private int quantidadeEstoque;
    private double preco;
    private String descricao;

    public Produto(String nome, int codigo, int quantidadeEstoque, double preco, String descricao) {
        this.nome = nome;
        this.codigo = codigo;
        this.quantidadeEstoque = quantidadeEstoque;
        this.preco = preco;
        this.descricao = descricao;
    }

    // Getters e setters para os atributos
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int compareTo(Produto outroProduto) {
        return this.nome.compareTo(outroProduto.getNome());
    }

    @Override
    public String toString() {
        return String.format("%-20s | %-10d | %-10d | %-10.2f | %-30s",
                nome, codigo, quantidadeEstoque, preco, descricao);
    }
}
