package controleDeEstoque;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Setor {
    private String nome;
    private List<Produto> produtos;

    public Setor(String nome) {
        this.nome = nome;
        this.produtos = new ArrayList<>();
    }

       // Método para alterar o nome do setor
    public void setNome(String novoNome) {
        this.nome = novoNome;
    }

    public String getNome() {
        return nome;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public Produto buscarProduto(int codigo) {
        for (Produto produto : produtos) {
            if (produto.getCodigo() == codigo) {
                return produto;
            }
        }
        return null;
    }

    public void removerProduto(int codigo) {
        produtos.removeIf(produto -> produto.getCodigo() == codigo);
    }

    public void depreciarProdutos(double porcentagem) {
        for (Produto produto : produtos) {
            double novoPreco = produto.getPreco() * (1 - porcentagem / 100);
            produto.setPreco(novoPreco);
        }
    }

    public void listarProdutosOrdenados() {
        Collections.sort(produtos);
        for (Produto produto : produtos) {
            System.out.println(produto);
        }
    }
}
