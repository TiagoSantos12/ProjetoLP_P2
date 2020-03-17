package br.ufpb.vendas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class ProgramaVendas {

	public static void main(String[] args) {
		SistemaComercioEletronico sistema = new SistemaComercioEletronicoList();
		boolean sair = false;
		try {
			recuperarDados(sistema);
			recuperarDadosDoProduto(sistema);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Erro ao recuperar dados");
			e.printStackTrace();
		}
		while (!sair) {
			int opcao = Integer.parseInt(JOptionPane.showInputDialog(
					"Digite uma opção:\n1.Cadastrar cliente\n"
							+"2.Cadastrar produto\n"
							+ "3.Pesquisar cliente\n"
							+ "4.Pesquisar clientes pelo nome\n"
							+ "5.Pesquisar pedidos com produto\n"
							+ "6.Cadastrar pedido"
							+ "7.Alterar dados do cliente"
							+ "8.Deletar um cliente"
							+ "9.Deletar um pedido"
							+"\n10.Sair\n"));
			switch(opcao) {
			  case 1:
				String nome = JOptionPane.showInputDialog("Qual o nome do cliente?");
				String endereco = JOptionPane.showInputDialog("Qual o endereco do cliente?");
				String email = JOptionPane.showInputDialog("Qual o email do cliente?");		
				String tipo = JOptionPane.showInputDialog("Qual o tipo do cliente?1. Pessoa Física ou 2.Pessoa Jurídica");
				Cliente cliente = null;
				if (tipo.equals("1")) {
					String cpf = JOptionPane.showInputDialog("Qual o CPF do cliente?");
					cliente = new ClientePF(nome, endereco, email, cpf );
				} else if (tipo.equals("2")) {
					String cnpj = JOptionPane.showInputDialog("Qual o CNPJ do cliente?");
					cliente = new ClientePJ(nome, endereco, email, cnpj );
				} else {
					JOptionPane.showMessageDialog(null, "Opção Inválida");
				}
				if (cliente!=null) {
					boolean cadastrou = sistema.cadastraCliente(cliente);
					if (cadastrou) {
						JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso");
					} else {
						JOptionPane.showMessageDialog(null, "Cliente já estava cadastrado");
					}
				} 
				break;
			  case 2:
				String codigo = JOptionPane.showInputDialog("Qual o codigo do produto?");
				String descricao = JOptionPane.showInputDialog("Qual a descrição do produto?");
				double precoVenda = Double.parseDouble(JOptionPane.showInputDialog("Qual o preco do produto?"));		
				int quantidadeEmEstoque = Integer.parseInt(JOptionPane.showInputDialog("Qual a quantidade do produto em estoque?"));
				String categoria = JOptionPane.showInputDialog("Qual a categoriado produto?");
				Produto produto = null;
				produto = new Produto(codigo, descricao,precoVenda, quantidadeEmEstoque, categoria);
				if (produto!=null) {
					boolean cadastrou = sistema.cadastraProduto(produto);
					if (cadastrou) {
						JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso");
					} else {
						JOptionPane.showMessageDialog(null, "Produto já estava cadastrado");
					}
				} 
				break;
			  case 3:
				String idAPesquisar = JOptionPane.showInputDialog("Qual o CNPJ ou CPF do cliente a pesquisar");
				Cliente clienteAchado = sistema.pesquisaCliente(idAPesquisar);
				JOptionPane.showMessageDialog(null, "Cliente achado:"+clienteAchado.toString());
				break;
			  case 4:
				  String nomePesquisar = JOptionPane.showInputDialog("Qual o nome do cliente?");
				List<Cliente> nomeDoClientePesq = sistema.pesquisaClientePeloNome(nomePesquisar);
				  System.out.println(nomeDoClientePesq);
				  for (Cliente p: nomeDoClientePesq) {
				  	JOptionPane.showMessageDialog(null, p.toString());
				  	}
				break;
			  case 5:
				String codigoPesq = JOptionPane.showInputDialog("Qual o código do produto a pesquisar?");
				List<Pedido> pedidosAchados = sistema.pesquisaPedidosIncluindoProduto(codigoPesq);
				JOptionPane.showMessageDialog(null, "PEDIDOS ACHADOS:");
				for (Pedido p: pedidosAchados) {
						JOptionPane.showMessageDialog(null, p.toString());
				}
				break;
			  case 6: //Cadastrar pedido
				  String idCliente = JOptionPane.showInputDialog("Qual o ID do cliente (e.g. CNPJ/CPF?");
				  Cliente clientePedido = sistema.pesquisaCliente(idCliente);
				  String dataPedido = JOptionPane.showInputDialog("Qual a data do pedido?");
				  String numero = JOptionPane.showInputDialog("Qual o numero do pedido?");		
				  String formaPagamento = JOptionPane.showInputDialog("Qual a forma de pagamento?1. Boleto ou 2.Cartão de crédito");
				  String formaPagamentoStr = "";
				  if (formaPagamento.equals("1")) {
						formaPagamentoStr= Pedido.PAGAMENTO_BOLETO;
				  } else if (formaPagamento.equals("2")) {
						formaPagamentoStr= Pedido.PAGAMENTO_CARTAO;
				  } 
				  int quantItens = Integer.parseInt(JOptionPane.showInputDialog("Quantos tipos de itens há no pedido?"));
				  List<ItemDoPedido> itens = new ArrayList<ItemDoPedido>();
				  for (int k=0; k<quantItens; k++) {
					  String codigoProd = JOptionPane.showInputDialog("Qual o código do produto?");
					  int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Qual a quantidade de itens deste produto?"));
					  ItemDoPedido item = new ItemDoPedido(quantidade, sistema.pesquisaProduto(codigoProd));
					  itens.add(item);
				  }
				  Pedido ped = new Pedido(dataPedido, Pedido.STATUS_REALIZADO, numero, formaPagamentoStr, itens, clientePedido);
				  sistema.cadastraPedido(ped);
				break;
			  case 7:
				  break;
				  
				  
				  
			  case 8:
				  break;
				  
				  
				  
				  
			  case 9:
				  break;
				  
				  
			  case 10:
					try {
						gravarDados(sistema);
						gravarDadosDoProduto(sistema);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Erro ao gravar dados");
						e.printStackTrace();
					}
					sair = true;
					break;
				}
			}
		JOptionPane.showMessageDialog(null, "FIM DO PROGRAMA");	
		}
	



	public static void gravarDados(SistemaComercioEletronico sistema) throws IOException {
		GravadorDeDados gravador = new GravadorDeDados();
		List<Cliente> clientes = sistema.getClientes();
		List<String> linhasPF = new ArrayList<String>();
		List<String> linhasPJ = new ArrayList<String>();
		
		
		for (Cliente u : clientes) {
			if(u instanceof ClientePF) {
				String linha = u.getNome() + "#" + u.getEndereco() + "#" + u.getEmail() + "#" + u.getId() + "#";
				linhasPF.add(linha);
			}
		}
		gravador.gravaTextoEmArquivo(linhasPF, "clientesPF.txt");
		
		for (Cliente u : clientes) {
			if(u instanceof ClientePJ) {
				String linha = u.getNome() + "#" + u.getEndereco() + "#" + u.getEmail() + "#" + u.getId() + "#";
				linhasPJ.add(linha);
			}
		}
		gravador.gravaTextoEmArquivo(linhasPJ, "clientesPJ.txt");
			
	}

	public static void recuperarDados(SistemaComercioEletronico sistema) throws IOException {
		GravadorDeDados gravador = new GravadorDeDados();
		List<Cliente> clientes = new ArrayList<Cliente>();
		List<String> linhasPF = gravador.recuperaTextoEmArquivo("clientesPF.txt");
		List<String> linhasPJ = gravador.recuperaTextoEmArquivo("clientesPJ.txt");
		for (String linha : linhasPF) {
			String[] dados = linha.split("#");
			Cliente u = new ClientePF(dados[0], dados[1], dados[2], dados[3]);
			clientes.add(u);
		}
		sistema.setClientes(clientes);
		for (String linha : linhasPJ) {
			String[] dados = linha.split("#");
			Cliente u = new ClientePJ(dados[0], dados[1], dados[2], dados[3]);
			clientes.add(u);
		}
		sistema.setClientes(clientes);
	}

	public static void gravarDadosDoProduto(SistemaComercioEletronico sistema) throws IOException{
		GravadorDeDados gravadorDeDados = new GravadorDeDados();
		List<Produto> produtos = sistema.getProdutos();
		List<String> produtosCadastrados = new ArrayList<String>();
		for(Produto p: produtos) {
				String linhaDeProdutos = p.getCodigo() + "#" + p.getDescricao() + "#" + p.getPrecoVenda() + "#" + p.getQuantidadeEmEstoque() + "#" + p.getCategoria() + "#";
				produtosCadastrados.add(linhaDeProdutos);
			
		}
		gravadorDeDados.gravaTextoEmArquivo(produtosCadastrados, "ProdutosDoCliente.txt");
	}
	
	public static void recuperarDadosDoProduto(SistemaComercioEletronico sistema) throws IOException {
		GravadorDeDados gravador = new GravadorDeDados();
		List<Produto> listaDeProdutos = new ArrayList<Produto>();
		List<String> produtosCadastrados = gravador.recuperaTextoEmArquivo("ProdutosDoCliente.txt");
		for (String linha : produtosCadastrados) {
			String[] dados = linha.split("#");
			Produto u = new Produto(dados[0],dados[1],Double.parseDouble(dados[2]), Integer.parseInt(dados[3]), dados[5]);
			listaDeProdutos.add(u);
		}
		sistema.setProdutos(listaDeProdutos);
}

	public static void gravarDadosDePedido(SistemaComercioEletronico sistema) throws IOException{
		GravadorDeDados gravadorDeDados = new GravadorDeDados();
		List<Pedido> pedidos = sistema.getPedidos();
		List<String> pedidosCadastrados = new ArrayList<String>();
		for(Pedido p: pedidos) {
				String linhaDePedidos = p.getData() + "#" + p.getFormaPagamento() + "#" + p.getNumero()+ "#" + p.getStatus() + "#" + p.getItens() + "#" + p.getCliente() + "#";
				pedidosCadastrados.add(linhaDePedidos);
			
		}
		gravadorDeDados.gravaTextoEmArquivo(pedidosCadastrados, "PedidosDoCliente.txt");
	}
	
	public static void recuperarDadosDePedido(SistemaComercioEletronico sistema) throws IOException{
	GravadorDeDados gravador = new GravadorDeDados();
	List<Pedido> listaDePedidos = new ArrayList<Pedido>();
	List<String> pedidosCadastrados = gravador.recuperaTextoEmArquivo("PedidosDoCliente.txt");
	for (String linha : pedidosCadastrados) {
		String[] dados = linha.split("#");
		Pedido u = new Pedido(dados[0],dados[1],dados[2], dados[3],String.class.dados[4],Cliente.class.cast(dados[5]));
		listaDePedidos.add(u);
	}
	sistema.setPedidos(listaDePedidos);
}
	
	

	
	
	
	
}