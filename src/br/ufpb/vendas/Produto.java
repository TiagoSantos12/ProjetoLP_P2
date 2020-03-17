package br.ufpb.vendas;

public class Produto {
	
	
	private String codigo;
	private String descricao;
	private double precoVenda;
	private int quantidadeEmEstoque;
	private String categoria;
	
	
	public Produto(String codigo, String descricao, double precoVenda, int quantidadeEmEstoque, String categoria) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.precoVenda = precoVenda;
		this.quantidadeEmEstoque = quantidadeEmEstoque;
		this.categoria = categoria;
	}
	
	public Produto() {
		this("","",0.0, 0, "");
	}
	
	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", descricao=" + descricao + ", precoVenda=" + precoVenda+"]";
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getPrecoVenda() {
		return precoVenda;
	}
	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}
	public int getQuantidadeEmEstoque() {
		return quantidadeEmEstoque;
	}
	public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
		this.quantidadeEmEstoque = quantidadeEmEstoque;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	
}