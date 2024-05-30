package controleDeEstoque;

import java.util.ArrayList;
import java.util.List;

public class Setor {
    private String nome;
    private List<Produto> produtos;

    public Setor(String nome) {
        this.nome = nome;
        this.produtos = new ArrayList<>();
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    // Adicionar produto
    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    // Remover produto
    public void removerProduto(int codigo) {
        produtos.removeIf(produto -> produto.getCodigo() == codigo);
    }

    // Buscar produto por código
    public Produto buscarProduto(int codigo) {
        for (Produto produto : produtos) {
            if (produto.getCodigo() == codigo) {
                return produto;
            }
        }
        return null;
    }

    // Editar produto
    public void editarProduto(int codigo, Produto produtoEditado) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getCodigo() == codigo) {
                produtos.set(i, produtoEditado);
                return;
            }
        }
    }

    // Calcular valor total dos produtos no setor
    public double calcularValorTotal() {
        double valorTotal = 0;
        for (Produto produto : produtos) {
            valorTotal += produto.getPreco() * produto.getQuantidadeEstoque();
        }
        return valorTotal;
    }

    // Método para depreciar todos os produtos do setor em uma porcentagem especificada
    public void depreciarProdutos(double porcentagem) {
        for (Produto produto : produtos) {
            produto.depreciar(porcentagem);
        }
    }
}
