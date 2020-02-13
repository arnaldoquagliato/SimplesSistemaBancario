package entities;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ContaCorrente extends ContasBancarias {
	
	private double limite = 100;
	
	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

	private ArrayList<String> extrato = new ArrayList<String>();
	
	public ContaCorrente(int id, String numeroConta, double saldoConta, int agencia) {
		super (id, numeroConta, saldoConta, agencia);
	}
	
	public void menuConta(ArrayList<ContasBancarias> listaContasBancarias) {
		int verifica = 0;
		do {
			System.out.println("*****Client Menu*****");
			System.out.println("1- Ver Saldo");
			System.out.println("2- Transferir");
			System.out.println("3- Fechar Conta");
			System.out.println("4- Deposito");
			System.out.println("5- Extrato");
			System.out.println("6 -Pagar Conta");
			System.out.println("0 -Voltar para o Menu Anterior");
			System.out.println("Escolha alguma opção:");
			verifica = chooseOption(listaContasBancarias);
		} while (verifica != 0);
		
	}

	public int chooseOption(ArrayList<ContasBancarias> listaContasBancarias) {
		Scanner sc = new Scanner(System.in);
		System.out.println("***Escolha uma opção:***");
		String op = sc.nextLine();
		switch (op) {
		case "1":
			verSaldo(listaContasBancarias) ;
			break;
		case "2":
			transferir(listaContasBancarias);
			break;
		case "3":
			fecharConta(listaContasBancarias);
			return 0;
		case "4":
			deposito(listaContasBancarias);
			break;
		case "5":
			extrato();
			break;
		case "6":
			pagarConta(listaContasBancarias);
			break;
		case "0":
			System.out.println("Voltar para menu Anterior");
			return 0;
		default:
			System.out.println("Incorrect Option");
			break;
		}
		return -1;
	}

	public void verSaldo(ArrayList<ContasBancarias> listaContasBancarias) {
		int verifica = 0;
		for (ContasBancarias contaBancaria : listaContasBancarias) {
			if (contaBancaria.getId() == 1) {
				contaBancaria.mostrarSaldo();
				verifica = 1;
			}
		}
		if(verifica == 0) {
			System.out.println("Saldo indiponivel");
		}
	}

	public void transferir(ArrayList<ContasBancarias> listaContasBancarias) {
		String agenciaConta;
		String numeroConta;
		Scanner sc = new Scanner(System.in);
		double valorTransferencia = 0;
		System.out.print("Informe o valor que deseja transferir: ");
		valorTransferencia = sc.nextDouble();
		if (valorTransferencia > contaCorrenteValidada(listaContasBancarias).getSaldoConta()+getLimite()) {
			System.out.println("Saldo indisponivel para transferencia");
			System.out.println("Saldo disponivel: " + contaCorrenteValidada(listaContasBancarias).getSaldoConta()+getLimite());
			} else if (valorTransferencia <= 0) {
				System.out.println("Valor para transferencia indisponivel");
			} else {
				System.out.print("Informe a agencia que deseja transferir: ");
				sc.next();				
				agenciaConta = sc.nextLine();
				System.out.print("Informe o numero da conta que deseja transferir:");
				numeroConta = sc.nextLine();
				System.out.print("Informe o nome do cliente que deseja transferir:");
				sc.nextLine();// fica em observação se vai dar erro
				ContasBancarias contaCorrenteDestinatario = validarTransferenciaContaBancaria(listaContasBancarias, numeroConta);
				ContasBancarias contaPoupanca= validarTransferenciaContaBancaria(listaContasBancarias, numeroConta);
				if (contaCorrenteDestinatario == null) {
					System.out.println("Não é possivel transferir");
				} else {
						contaCorrenteDestinatario.setSaldoConta(contaCorrenteDestinatario.getSaldoConta() + valorTransferencia);
						if(contaCorrenteValidada(listaContasBancarias).getSaldoConta() > valorTransferencia) {
							contaCorrenteValidada(listaContasBancarias).setSaldoConta(contaCorrenteValidada(listaContasBancarias).getSaldoConta() - valorTransferencia);							
						}else {
							contaCorrenteValidada(listaContasBancarias).setSaldoConta(contaCorrenteValidada(listaContasBancarias).getSaldoConta() - valorTransferencia);
							setLimite(valorTransferencia - this.getSaldoConta());
							setSaldoConta(0);
						}
						String movimentacaoDeposito = "Foi transferido " + valorTransferencia + " seu saldo atual: " + contaCorrenteValidada(listaContasBancarias).getSaldoConta(); 
						extrato.add(movimentacaoDeposito);
						System.out.println("Valor trasnferido com sucesso");
						System.out.println("Saldo atual: " + contaCorrenteValidada(listaContasBancarias).getSaldoConta());
				}
		}
	}
	
	public void pagarConta(ArrayList<ContasBancarias> listaContasBancarias) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Digite o valor de pagamento:");
		double valorPagamento = sc.nextDouble();
		if (valorPagamento > contaCorrenteValidada(listaContasBancarias).getSaldoConta()+ getLimite()) {
				System.out.println("Não há saldo disponivel para pagamento");
		} else if (valorPagamento < 0) {
				System.out.println("Valor para pagamento é invalido");
		} else {
				if(contaCorrenteValidada(listaContasBancarias).getSaldoConta() > valorPagamento) {
					contaCorrenteValidada(listaContasBancarias).setSaldoConta(contaCorrenteValidada(listaContasBancarias).getSaldoConta() - valorPagamento);							
				}else {
					contaCorrenteValidada(listaContasBancarias).setSaldoConta(contaCorrenteValidada(listaContasBancarias).getSaldoConta() - valorPagamento);
					setLimite(valorPagamento - this.getSaldoConta());
					setSaldoConta(0);
				}
				String movimentacaoDeposito = "Foi transferido " + valorPagamento + " seu saldo atual: " + contaCorrenteValidada(listaContasBancarias).getSaldoConta()+getLimite(); 
				extrato.add(movimentacaoDeposito);
				System.out.println("Foi pago:" + valorPagamento);
				System.out.println("Seu saldo atual: " + contaCorrenteValidada(listaContasBancarias).getSaldoConta());
			}
	}
	
		public void deposito(ArrayList<ContasBancarias>listaContasBancarias) {
			//this é a contas do clinete
			Scanner sc = new Scanner(System.in);
			double valorDeposito = 0;
			double limiteConta = 0;
			System.out.print("Digite o valor que deseja depositar:");
			valorDeposito = sc.nextDouble();
			if (valorDeposito <= 0) {
				System.out.println("Valor para deposito é invalido");
			} else {
				if(getLimite()<100) {
					limiteConta = 100 - getLimite();
					setLimite(getLimite()+(valorDeposito - limiteConta)); 
					limiteConta = valorDeposito - limiteConta;
					contaCorrenteValidada(listaContasBancarias).setSaldoConta(contaCorrenteValidada(listaContasBancarias).getSaldoConta() + limiteConta);
					String movimentacaoDeposito = "Foi depositado " + valorDeposito+ " seu saldo atual: "+contaCorrenteValidada(listaContasBancarias).getSaldoConta(); 
					extrato.add(movimentacaoDeposito);
					System.out.println("Valor depositado com sucesso");
					System.out.print("Saldo atual: " + contaCorrenteValidada(listaContasBancarias).getSaldoConta());	
				}else {
					contaCorrenteValidada(listaContasBancarias).setSaldoConta(contaCorrenteValidada(listaContasBancarias).getSaldoConta() + valorDeposito);
					String movimentacaoDeposito = "Foi depositado " + valorDeposito+ " seu saldo atual: "+contaCorrenteValidada(listaContasBancarias).getSaldoConta(); 
					extrato.add(movimentacaoDeposito);
					System.out.println("Valor depositado com sucesso");
					System.out.print("Saldo atual: " + contaCorrenteValidada(listaContasBancarias).getSaldoConta());					
				}
			}
		}
		
		public int fecharConta(ArrayList<ContasBancarias> listaContasBancarias) {
			ContasBancarias contaCorrenteValidada = contaCorrenteValidada(listaContasBancarias);
			if(contaCorrenteValidada.getSaldoConta() > 0) {
				System.out.println("Não é possivel fechar conta, saldo disponivel de:" + contaCorrenteValidada.getSaldoConta());
			} else {
				System.out.println("A seguinte conta será removida:");
				contaCorrenteValidada.mostrarContaSemSaldo();
				removerContaPoupanca(listaContasBancarias);
			}
			return 0;
		}
		
		public void extrato() {
			for(String extrato: extrato) {
				System.out.println(extrato);
			}
		}
		
		public void removerContaPoupanca(ArrayList<ContasBancarias> listaContasBancarias) {
			for (ContasBancarias contaBancaria : listaContasBancarias) {
				if (contaBancaria.getId() == 1) {
					if(contaBancaria.getSaldoConta()>0) {
						System.out.println("Saldo disponivel na conta");
					}else {
						listaContasBancarias.remove(contaBancaria);
					}
				}
			}
		}


	public ContasBancarias contaCorrenteValidada(ArrayList<ContasBancarias> listaContasBancarias) {
		for (ContasBancarias cp: listaContasBancarias) {
			if(cp.getId() == 1) {
				return (ContaCorrente)cp;
			}
		}
		return null;
	}
	


}


