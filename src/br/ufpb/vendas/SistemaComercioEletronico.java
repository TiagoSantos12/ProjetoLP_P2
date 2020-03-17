package br.ufpb.vendas;

import java.util.List;

public interface SistemaComercioEletronico {

	boolean existeProduto(Produto produto);

	Produto pesquisaProduto(String codigoProduto);

	boolean cadastraProduto(Produto produto);

	boolean cadastraPedido(Pedido pedido);

	boolean existePedido(Pedido pedido);

	boolean cadastraCliente(Cliente cliente);

	boolean existeCliente(Cliente cliente);
	
	List<Cliente> getClientes();
	
	public void setClientes(List<Cliente>lista);
	
	List<Produto> getProdutos();
	
	public void setProdutos(List<Produto>lista);
	
	List<Pedido> getPedidos();
		
	public void setPedidos(List<Pedido>lista);
	/**
	 * Pesquisa no sistema o cliente com um dado identificador
	 * @param id O identificador do cliente a pesquisar
	 * @return O cliente do sistema que tem um dado identificador (id, e.g. CPF, CNPJ)
	 * @throws ClienteNaoExisteException Cliente não existe se não ouver o Id determinado
	 */
	Cliente pesquisaCliente(String id);

	/**
	 * Pesquisa os clientes cujo nome começa com um certo prefixo
	 * @param prefixo O prefixo a pesquisar
	 * @return a lista dos clientes cujo nome começa com certo prefixo
	 */
	List<Cliente> pesquisaClientesComNomeComecandoCom(String prefixo);

	/**
	 * Pesquisa os pedidos que incluem produtos cujo código é passado como parâmetro
	 * @param codigoProduto O código do produto a pesquisar nos itens de pedido
	 * @return a lista dos pedidos que incluem um dado produto.
	 */
	List<Pedido> pesquisaPedidosIncluindoProduto(String codigoProduto);
	/**
	* Pesquisa os nomes que são passado como parâmetro.
	* O Codigo Retorna uma Lista de nomes procurado.
 */
	List<Cliente> pesquisaClientePeloNome(String Nome);
	
	
}