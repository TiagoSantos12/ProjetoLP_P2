package br.ufpb.vendas;

public class ItemDoPedido {
	
	private int quantidade;
	private Produto produto;
	

	public ItemDoPedido(int quantidade, Produto produto) {
		super();
		this.quantidade = quantidade;
		this.produto = produto;
	}
	
	public ItemDoPedido() {
		this(0,new Produto());
	}
	
	@Override
	public String toString() {
		return "ItemDoPedido [quantidade=" + quantidade + ", produto=" + produto.toString() + ", subtotal="
				+ calculaSubTotal() + "]";
	}

	public double calculaSubTotal() {
		return this.quantidade*produto.getPrecoVenda();
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	
}