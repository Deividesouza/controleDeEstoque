package controleDeEstoque;

public class Produto {
    private String nome;
    private int codigo;
    private int quantidadeEstoque;
    private double preco;
    private String descricao;

    // Construtor
    public Produto(String nome, int codigo, int quantidadeEstoque, double preco, String descricao) {
        this.nome = nome;
        this.codigo = codigo;
        this.quantidadeEstoque = quantidadeEstoque;
        this.preco = preco;
        this.descricao = descricao;
    }

    // Getters e Setters
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

    // Método para depreciar o valor do produto em uma porcentagem especificada
    public void depreciar(double porcentagem) {
        this.preco -= this.preco * (porcentagem / 100);
    }
}
