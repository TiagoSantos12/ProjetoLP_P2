package br.ufpb.vendas;

import java.util.ArrayList;
import java.util.List;


public class SistemaComercioEletronicoList implements SistemaComercioEletronico {
	private List<Cliente> clientes;
	private List<Pedido> pedidos;
	private List<Produto> produtos;
	
	public SistemaComercioEletronicoList() {
		this.clientes = new ArrayList<Cliente>();
		this.produtos = new ArrayList<Produto>();
		this.pedidos = new ArrayList<Pedido>();
	}
	
	@Override
	public boolean existeProduto(Produto produto) {
		for(Produto p: this.produtos) {
			if (p.getCodigo().equals(produto.getCodigo())) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Produto pesquisaProduto(String codigoProduto) {
		for(Produto p: this.produtos) {
			if (p.getCodigo().equals(codigoProduto)) {
				return p;
			}
		}
		return null;
	}
	
	@Override
	public boolean cadastraProduto(Produto produto) {
		if (existeProduto(produto)) {
			return false;
		} else {
			this.produtos.add(produto);
			return true;
		}
	}
	
	@Override
	public boolean cadastraPedido(Pedido pedido) {

		if (existePedido(pedido)) {
			return false;
		} else {
			this.pedidos.add(pedido);
			return true;
		}
	}
	
	@Override
	public boolean existePedido(Pedido pedido) {
		for (Pedido p: this.pedidos) {
			if (p.getNumero().equals(pedido.getNumero())) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean cadastraCliente(Cliente cliente) {

		if (existeCliente(cliente)) {
			return false;
		} else {
			this.clientes.add(cliente);
			return true;
		}
	}
	
	@Override
	public boolean existeCliente(Cliente cliente) {
		for (Cliente c: this.clientes) {
			if (c.getId().equals(cliente.getId())) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public Cliente pesquisaCliente(String id){
		try {
			for (Cliente c : this.clientes) {
				if(c.getId() == id) {
					toString();
				}
				
			}
		}catch(Exception e) {
			System.out.println("Cliente não cadastrado");
		}
		return null;
	}
	
	
	public List<Cliente> pesquisaClientePeloNome(String Nome){
		ArrayList<Cliente> ClienteComNomePesquisado = new ArrayList<>();
		for(Cliente c: this.clientes) {
			if(c.getNome().equals(Nome)){
				ClienteComNomePesquisado.add(c);
			}
		}
		return ClienteComNomePesquisado;
}

	@Override
	public List<Cliente> pesquisaClientesComNomeComecandoCom(String prefixo){
		ArrayList<Cliente> ClientesComInicial = new ArrayList<>();
		for(Cliente c : this.clientes) {
			if(c.getNome().startsWith(prefixo)) {
				ClientesComInicial.add(c);
			}
		}
		return ClientesComInicial;
}
	
	public List<Pedido> pesquisaPedidosIncluindoProduto(String codigoProduto){
			ArrayList<Pedido> listaDeProdutos = new ArrayList<Pedido>();
			for(Pedido p: this.pedidos ) {
				List<ItemDoPedido> itens = p.getItens();
					for(ItemDoPedido item: itens) {
						if(item.getProduto().getCodigo(). equals(codigoProduto)) {
							listaDeProdutos.add(p);
						}
					}
			}
		return listaDeProdutos;
		
	}
		
	public List<Cliente> getClientes(){
		return this.clientes;
	}
	public void setClientes(List<Cliente>clientes) {
		this.clientes = clientes;
	}
	
	public List<Produto> getProdutos(){
		return this.produtos;
	}
	public void setProdutos(List<Produto>produtos) {
		this.produtos = produtos;
	}
	public List<Pedido> getPeditos(){
		return this.pedidos;
	}
	public void setPedidos(List<Pedido>pedidos) {
		this.pedidos = pedidos;
	}

	@Override
	public List<Pedido> getPedidos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

	
				
	
