package br.ufpb.vendas;

import java.util.List;

public class Pedido {
	
	
	public static final String STATUS_REALIZADO = "Realizado";
	public static final String STATUS_AGUARDANDO_CONFIRMACAO_PAGAMENTO = "Aguardando confirmação de pagamento";
	public static final String STATUS_PAGAMENTO_CONFIRMADO = "Pagamento confirmado";
	
	public static final String PAGAMENTO_BOLETO = "Pagamento via Boleto";
	public static final String PAGAMENTO_CARTAO = "Pagamento via Cartão de Crédito";
	
	private String data;
	private String status;
	private String numero;
	private String formaPagamento;
	private List<ItemDoPedido> itens;
	private Cliente cliente;
	
	
	public Pedido(String data, String status, String numero, String formaPagamento, List<ItemDoPedido> itens, Cliente cliente) {
		super();
		this.data = data;
		this.status = status;
		this.numero = numero;
		this.formaPagamento = formaPagamento;
		this.itens = itens;
		this.setCliente(cliente);
	}

	@Override
	public String toString() {
		String msg =  "Pedido [data=" + data + ", status=" + status + ", numero=" + numero + ", formaPagamento="
				+ formaPagamento  + "] ITENS:\n";
		for (ItemDoPedido it: this.itens) {
			msg+= it.toString()+"\n";
		}
		return msg;
	}



	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getFormaPagamento() {
		return formaPagamento;
	}
	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	public List<ItemDoPedido> getItens() {
		return itens;
	}
	
	public void setItens(List<ItemDoPedido> itens) {
		this.itens = itens;
	}
	public void adicionaItem(ItemDoPedido item) {
		this.itens.add(item);
	}



	public Cliente getCliente() {
		return cliente;
	}



	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


}