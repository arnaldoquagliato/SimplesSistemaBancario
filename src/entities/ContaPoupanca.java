package entities;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class ContaPoupanca extends ContasBancarias{
	public ContaPoupanca(int id,String numeroConta, double saldoConta, int agenciaConta) {
		super(id,numeroConta, saldoConta, agenciaConta);
	}
	
	public ContaPoupanca() {
	}
	
	private ArrayList<String> extrato = new ArrayList<String>();

	

	public void menuConta(ArrayList<ContasBancarias> listaContasBancarias) {
		int verifica = 0;
		do {
			System.out.println("*****Client Menu*****");
			System.out.println("1- Ver Saldo");
			System.out.println("2- Transferir");
			System.out.println("3- Fechar Conta");
			System.out.println("4- Deposito");
			System.out.println("5- Extrato");
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
			break;
		case "4":
			deposito(listaContasBancarias);
			break;
		case "5":
			extrato(listaContasBancarias);
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
			if (contaBancaria.getId() == 0) {
				contaBancaria.mostrarSaldo();
				verifica = 1;
			}
		}
		if(verifica == 0) {
			System.out.println("Saldo indiponivel");
		}
	}
	
	public void transferir(ArrayList<ContasBancarias> listaContasBancarias) {
		String numeroConta;
		Scanner sc = new Scanner(System.in);
		double valorTransferencia = 0;
		System.out.print("Informe o valor que deseja transferir: ");
		valorTransferencia = sc.nextDouble();
		if (valorTransferencia > contaPoupancaValidada(listaContasBancarias).getSaldoConta()) {
			System.out.println("Saldo indisponivel para transferencia");
			System.out.println("Saldo disponivel: " + contaPoupancaValidada(listaContasBancarias).getSaldoConta());
			} else if (valorTransferencia < 0) {
				System.out.println("Valor para transferencia indisponivel");
			} else {
				System.out.print("Informe o numero da conta que deseja transferir:");
				sc.next();
				numeroConta = sc.nextLine();
				ContasBancarias contaCorrenteDestinatario = validarTransferenciaContaBancaria(listaContasBancarias, numeroConta);
				ContasBancarias contaPoupanca= validarTransferenciaContaBancaria(listaContasBancarias,numeroConta);
				if (contaCorrenteDestinatario == null) {
					System.out.println("Não é possivel transferir");
				} else {
						contaCorrenteDestinatario.setSaldoConta(contaCorrenteDestinatario.getSaldoConta() + valorTransferencia);
						contaPoupancaValidada(listaContasBancarias).setSaldoConta(contaPoupancaValidada(listaContasBancarias).getSaldoConta() - valorTransferencia);
						String movimentacaoDeposito = "Foi transferido " + valorTransferencia + " seu saldo atual: " + contaPoupancaValidada(listaContasBancarias).getSaldoConta(); 
						extrato.add(movimentacaoDeposito);
						System.out.println("Valor trasnferido com sucesso");
						System.out.println("Saldo atual: " + contaPoupancaValidada(listaContasBancarias).getSaldoConta());
				}
		}
	}
	
	public ContasBancarias contaPoupancaValidada(ArrayList<ContasBancarias> listaContasBancarias) {
		for (ContasBancarias cp: listaContasBancarias) {
			if(cp.getId() == 0) {
				return (ContaPoupanca)cp;
			}
		}
		return null;
	}
	
	public void deposito(ArrayList<ContasBancarias> listaContasBancarias) {
		Scanner sc = new Scanner(System.in);
		double valorDeposito = 0;
		System.out.print("Digite o valor que deseja depositar:");
		valorDeposito = sc.nextDouble();
		if (valorDeposito <= 0) {
			System.out.println("Valor para deposito é invalido");
		} else {
			contaPoupancaValidada( listaContasBancarias).setSaldoConta(contaPoupancaValidada(listaContasBancarias).getSaldoConta() + valorDeposito);
			String movimentacaoDeposito = "Foi depositado " + valorDeposito+ " seu saldo atual: "+contaPoupancaValidada(listaContasBancarias).getSaldoConta(); 
			extrato.add(movimentacaoDeposito);
			System.out.println("Valor depositado com sucesso");
			System.out.print("Saldo atual: " + contaPoupancaValidada(listaContasBancarias).getSaldoConta());
		}
	}
	
	public int fecharConta(ArrayList<ContasBancarias> listaContasBancarias) {
		ContasBancarias contaPoupancaValidada = contaPoupancaValidada(listaContasBancarias);
		if(contaPoupancaValidada.getSaldoConta() > 0) {
			System.out.println("Não é possivel fechar conta, saldo disponivel de:" + contaPoupancaValidada.getSaldoConta());
		} else {
			System.out.println("A seguinte conta será removida:");
			contaPoupancaValidada.mostrarContaSemSaldo();
			removerContaPoupanca(listaContasBancarias);
		}
		return 0;
	}
	
	public void extrato(ArrayList<ContasBancarias> listaContasBancarias) {
		if(extrato.size() == 0) {
			System.out.println("Não ha extrato");
		}
		for(String extrato: extrato) {
			System.out.println(extrato);
		}
	}


	public void removerContaPoupanca(ArrayList<ContasBancarias> listaContasBancarias) {
		for (ContasBancarias contaBancaria : listaContasBancarias) {
			if (contaBancaria.getId() == 0) {
				if(contaBancaria.getSaldoConta()>0) {
					System.out.println("Saldo disponivel na conta");
				}else {
					listaContasBancarias.remove(contaBancaria);
				}
			}
		}
	}
	

}
