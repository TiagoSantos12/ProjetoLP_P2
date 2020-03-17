package br.ufpb.vendas;

public class ClienteNaoExisteException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ClienteNaoExisteException(String msg) {
		super(msg);
	}

}
