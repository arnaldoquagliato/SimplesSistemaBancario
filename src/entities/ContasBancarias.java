package entities;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public  class ContasBancarias {
	private String numeroConta;
	private double saldoConta;
	private int id;
	private int agencia;
	
	public int getAgencia() {
		return agencia;
	}
	public void setAgencia(int agencia) {
		this.agencia = agencia;
	}
	public ContasBancarias(int id,String numeroConta, double saldoConta, int agencia) {
		this.setId(id);
		this.numeroConta = numeroConta;
		this.saldoConta = saldoConta;
		this.agencia = agencia;
	}
	public ContasBancarias() {
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNumeroConta() {
		return numeroConta;
	}
	
	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	
	public  void menuConta() {
		
	}
	
	public  int chooseOption() {
		return 0;
	}


	public double getSaldoConta() {
		return saldoConta;
	}

	public void setSaldoConta(double saldoConta) {
		this.saldoConta = saldoConta;
	}


/*
	
	public void checkBalance(ArrayList<ContasBancarias>listaContasBancarias) {
		Scanner sc = new Scanner(System.in);
		ContaCorrente contaCorrente = validarContasBancarias(listaContasBancarias);
		if (contaCorrente == null) {
			System.out.println("Conta não existe");
		} else {
			int agenciaConta = 0;
			String numeroConta ;
			System.out.print("Informe a agencia:");
			agenciaConta = sc.nextInt();
			System.out.print("Informe o numero da conta:");
			numeroConta = sc.nextLine();
			ContasBancarias contaCorrenteClient = validarTransferenciaContaBancaria(listaContasBancarias, numeroConta);
			if (contaCorrenteClient == null) {
				System.out.println("Conta ou agencia não encontrados");
			} else {
				contaCorrenteClient.mostrarSaldo();
			}
		}

	}
	
	public void transfer(ArrayList<ContasBancarias>listaContasBancarias) {
		Scanner sc = new Scanner(System.in);
		ContasBancarias contaCorrente = validarTransferenciaContaBancaria(listaContasBancarias, numeroConta);
		if (contaCorrente == null) {
			System.out.println("Conta não existe");
		} else {
			int agenciaConta = 0;
			int numeroConta = 0;
			String nomeCliente;
			long cpf;
			double valorTransferencia = 0;
			System.out.print("Informe de qual agencia deseja transferir:");
			agenciaConta = sc.nextInt();
			System.out.print("Informe de qual numero da conta deseja transferir:");
			numeroConta = sc.nextInt();
			ContaCorrente contaCorrenteCliente = validarContaCorrente(listaContasBancarias,agenciaConta, numeroConta);
			if (contaCorrenteCliente == null) {
				System.out.println("Conta ou agencia não encontrados.");
				System.out.println("Não é possivel trasnferir.");
			} else {
				System.out.print("Informe o valor que deseja transferir: ");
				valorTransferencia = sc.nextDouble();
				if (valorTransferencia > contaCorrenteCliente.getSaldoConta()) {
					System.out.println("Saldo indisponivel para transferencia");
					System.out.println("Saldo disponivel: " + contaCorrenteCliente.getSaldoConta());
				} else if (valorTransferencia < 0) {
					System.out.println("Valor para transferencia indisponivel");
				} else {
					System.out.print("Informe a agencia que deseja transferir:");
					agenciaConta = sc.nextInt();
					System.out.print("Informe o numero da conta que deseja transferir:");
					numeroConta = sc.nextInt();
					System.out.print("Informe o nome do cliente que deseja transferir:");
					sc.nextLine();// fica em observação se vai dar erro
					nomeCliente = sc.nextLine();
					System.out.print("Informe o numero do cpf que deseja transferir:");
					cpf = sc.nextLong();
					ContaCorrente contaCorrenteDestinatario = validarTransferencia(listaContasBancarias,agenciaConta, numeroConta,
							nomeCliente, cpf);
					if (contaCorrenteDestinatario == null) {
						System.out.println("Usuário que deseja tranferir não foi encontrado");
					} else {
						contaCorrenteDestinatario
								.setSaldoConta(contaCorrenteDestinatario.getSaldoConta() + valorTransferencia);
						contaCorrenteCliente.setSaldoConta(contaCorrenteCliente.getSaldoConta() - valorTransferencia);
						System.out.println("Valor trasnferido com sucesso");
						System.out.println("Saldo atual: " + contaCorrenteCliente.getSaldoConta());
					}
				}
			}
		}
	}
	
	public void payBill(ArrayList<ContasBancarias>listaContasBancarias) {
		Scanner sc = new Scanner(System.in);
		ContaCorrente contaCorrente = validarContasBancarias();
		if (contaCorrente == null) {
			System.out.println("Conta não existe");
		} else {
			int agenciaConta = 0;
			String numeroConta ;
			double valorPagamento = 0;
			System.out.print("Informe sua agencia:");
			agenciaConta = sc.nextInt();
			System.out.print("Informe sua conta:");
			numeroConta = sc.nextLine();
			ContaCorrente contaCorrenteCliente = validarContaCorrente(listaContasBancarias,  agencia, numeroConta);
			if (contaCorrenteCliente == null) {
				System.out.println("Conta ou agencia não encontrados");
			} else {
				System.out.print("Digite o valor de pagamento:");
				valorPagamento = sc.nextDouble();
				if (valorPagamento > contaCorrenteCliente.getSaldoConta()) {
					System.out.println("Não há saldo disponivel para pagamento");
				} else if (valorPagamento < 0) {
					System.out.println("Valor para pagamento é invalido");
				} else {
					contaCorrenteCliente.setSaldoConta(contaCorrenteCliente.getSaldoConta() - valorPagamento);
					System.out.println("Foi pago:" + valorPagamento);
					System.out.println("Seu saldo atual: " + contaCorrenteCliente.getSaldoConta());
					System.out.println("");
				}
			}

		}
	}
	
	public void deposit() {
		ContaCorrente contaCorrente = validarContasBancarias(ArrayList<ContasBancarias> listaContasBancarias);
		if (contaCorrente == null) {
			System.out.println("Conta não existe");
		} else {
			Scanner sc = new Scanner(System.in);
			int agenciaConta = 0;
			int numeroConta = 0;
			double valorDeposito = 0;
			vizualizarContasBancarias();
			System.out.print("Digite o valor que deseja depositar:");
			valorDeposito = sc.nextDouble();
			if (valorDeposito < 0) {
				System.out.println("Valor para deposito é invalido");
			} else {
				System.out.print("Informe de qual agencia deseja depositar:");
				agenciaConta = sc.nextInt();
				System.out.print("Informe de qual numero da conta deseja depositar:");
				numeroConta = sc.nextInt();
				ContaCorrente contaCorrenteDestinatario = validarDeposito(listaContasBancarias, agenciaConta, numeroConta);
				if (contaCorrenteDestinatario == null) {
					System.out.println("Usuario não encontrado não é possivel fazer o deposito");
				} else {
					contaCorrenteDestinatario.setSaldoConta(contaCorrenteDestinatario.getSaldoConta() + valorDeposito);
					System.out.println("Valor depositado com sucesso");
					System.out.print("Saldo atual: " + contaCorrenteDestinatario.getSaldoConta());
				}
			}
		}
	}
	
	public void closeAccount() {
	//	if (this.listaContasBancarias.size() == 0) {
			System.out.println("Conta não existe");
	//	} else {
			Scanner sc = new Scanner(System.in);
			int rmvAgenciaConta = 0;
			int rmvNumeroConta = 0;
			System.out.print("Informe a agencia:");
			rmvAgenciaConta = sc.nextInt();
			System.out.print("Informe o numero da conta:");
			rmvNumeroConta = sc.nextInt();
			ContaCorrente contaCorrente = validarContaCorrente(rmvAgenciaConta, rmvNumeroConta);
			if (contaCorrente == null) {
				System.out.println("Conta não encotrada");
			} else {
				System.out.println("A seguinte conta será removida:");
				contaCorrente.mostrarContaComSenha();
			}
			removerConta(rmvAgenciaConta, rmvNumeroConta);
		}
	

	//utilizando agencia e conta para validar o deposito
	public ContaCorrente validarDeposito(ArrayList<ContasBancarias> listaContasBancarias, int agenciaConta, int numeroConta) {
		for (Client cliente : Banco.listaCliente) {
			for (ContaCorrente contaClient : listaContasBancarias) {
				if (contaClient.getNumeroConta() == numeroConta && contaClient.getAgencia() == agenciaConta)
					return contaClient;
			}
		}
		return null;
	}
	
	//utilizando agencia, conta, nome e cpf para validar o transferencia
	public ContaCorrente validarTransferencia(ArrayList<ContasBancarias> listaContasBancarias, int agenciaConta, int numeroConta, String nomeCliente, long cpf) {
		for (Client cliente : Banco.listaCliente) {
			for (ContaCorrente contaClient : listaContasBancarias) {
				if (contaClient.getNumeroConta() == numeroConta && contaClient.getAgencia() == agenciaConta
						&& cliente.getNome().equals(nomeCliente) && cliente.getCpf() == cpf)
					return contaClient;
			}
		}
		return null;
	}

	// função valida se a conta corrente existe
	public ContaCorrente validarContaCorrente(ArrayList<ContasBancarias>listaContasBancarias, int agencia, String numero) {
		for (ContasBancarias c : listaContasBancarias) {
			if (c instanceof ContaCorrente && ((ContaCorrente)c).getNumeroConta().equals(numero) && ((ContaCorrente)c).getAgencia() == agencia) {
				return (ContaCorrente)c;
			}
		}
		// retorna nulo caso não encontre agencia ou num_conta
		return null;
	}
	*/
	//vizualizar contas Bancarias
	public void vizualizarContasBancarias(ArrayList<ContasBancarias>listaContasBancarias) {
		int verifica = 0;
		for (ContasBancarias c : listaContasBancarias) {
			c.mostrarContaSemSaldo();
			verifica = 1;
		}
		if (verifica == 0) {
			System.out.println("Não ha contas");
		}
	}

	// valizualizar contas
	public ContaCorrente validarContasBancarias(ArrayList<ContasBancarias>listaContasBancarias) {
		for (ContasBancarias c : listaContasBancarias ) {
			if(c instanceof ContaCorrente &&  c.getId() == 2){
				return (ContaCorrente)c;				
			}
		}
		return null;
	}

	public ContaCorrente removerConta(ArrayList<ContasBancarias> listaContasBancarias, int agencia, String numero) {
		for (ContasBancarias c : listaContasBancarias) {
			if (c instanceof ContaCorrente && c.getNumeroConta().equals(numero) && c.getAgencia() == agencia) {
				listaContasBancarias.remove(c);
				System.out.println("O cliente foi removido com sucesso");
				return (ContaCorrente)c;
			}
		}
		System.out.println("Conta ou agencia não encontrados");
		return null;
	}
	
	public ContasBancarias validarTransferenciaContaBancaria
	(ArrayList<ContasBancarias>listaContasBancarias, String numeroConta) {
		for (ContasBancarias c : listaContasBancarias ) {
			if(c instanceof ContaCorrente &&  c.getId() == 1 && c.getNumeroConta() == numeroConta){
				return (ContaCorrente)c;				
			}
		}
		return null;
	} 

	
 

	public void mostrarContaSemSaldo() {
		System.out.println("Numero da conta: " + this.numeroConta);
		System.out.println("****************************************");
	}

	public void mostrarSaldo() {
		System.out.println("****************************************");
		System.out.println("Saldo da conta: " + getSaldoConta());
		System.out.println("****************************************");
	}
	
}
