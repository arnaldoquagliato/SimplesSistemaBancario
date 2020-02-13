package entities;


import java.awt.SystemColor;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;



public class Client extends Usuario {
	
	public ArrayList<ContasBancarias> listaContasBancarias;
	private ArrayList<Notificacao> listaNotificacao = new ArrayList<Notificacao>();
	Random random = new Random();
	int agenciaConta = random.nextInt(8999) + 1000;
	
	public ArrayList<ContasBancarias> getListaContasBancarias() {
		return listaContasBancarias;
	}

	public void setListaContasBancarias(ArrayList<ContasBancarias> listaContasBancarias) {
		this.listaContasBancarias = listaContasBancarias;
	}

	public int getAgenciaConta() {
		return agenciaConta;
	}

	public void setAgenciaConta(int agenciaConta) {
		this.agenciaConta = agenciaConta;
	}

	int chooseOption;


	public Client(String login, String password, String nome, String telefone, String cpf) {
		setId(Banco.ID++);
		setLogin(login);
		setPassword(password);
		setNome(nome);
		setTelefone(telefone);
		listaContasBancarias = new ArrayList<ContasBancarias>();
		setCpf(cpf);
		
	}

	public int menu() {
		int verifica = 0;
		do {
			System.out.println("*****Client Menu*****");
			System.out.println("1- Abrir Conta Corrente");
			System.out.println("2- Abrir Conta Poupança");
			System.out.println("3- Abrir Conta Conjunta");
			System.out.println("4- Entrar na Conta Poupança");
			System.out.println("5- Entrar na Conta Corrente");
			System.out.println("6- Entrar na Conta Conjunta");
			System.out.println("7- Logout");
			System.out.println("8- Close the Application");
			System.out.println("9- Notificação");
			System.out.println("10-Informações das Contas");
			System.out.println("Escolha alguma opção:");
			verifica = chooseOption();
		} while (verifica != 7 && verifica != 8);
		if (verifica == 7) {
			return 1;
		} else {
			return 0;
		}
	}

	public int chooseOption() {
		Scanner sc = new Scanner(System.in);
		System.out.println("***Escolha uma opção:***");
		String op = sc.nextLine();
		switch (op) {
		case "1":
			abrirContaCorrente();
			break;
		case "2": 
			abrirContaPoupança(); // colocar no cliente
			break;
		case "3":
	//		abrirContaConjunta();
			break;
		case "4":
			ContaPoupanca cc = null;
			for (ContasBancarias contasBancarias : listaContasBancarias) {
				if (contasBancarias.getId() == 0) {
					cc = (ContaPoupanca)contasBancarias;
					break;
				}
			}
			if (cc == null) {
				System.out.println("Conta não existe");
			}else {
				cc.menuConta(listaContasBancarias);				
			}
			break;
		case "5":
			ContaCorrente c = null;
			for (ContasBancarias contasBancarias : listaContasBancarias) {
				if (contasBancarias.getId() == 1) {
					c = (ContaCorrente)contasBancarias;
					break;
				}
			}
			if (c == null) {
				System.out.println("Conta não existe");
			}else {
				c.menuConta(listaContasBancarias);				
			}
			break;
		case "6":
	//		entrarContaConjunta();
			break;
		case "7":
			System.out.println("Retornando para tela de login");
			return 7;
		case "8":
			System.out.println("Aplicação encerrada");
			return 8;
		case "9":
		//	consultarNotf(); // informações das contas existentes
			break;
		case "10":
			infoContas(); // informações das contas existentes
				break;
		default:
			System.out.println("Incorrect Option");
			break;
		}
		return 1;
		// sc.close();
	}
	
	
	public void consultarNotf(int id, int agenciaSolicitado) {
		System.out.println("Lista de Notificações:");
		mostrarNotif();
		System.out.println("0 - Para voltar ao menu anterior");
		System.out.println("Escolha um opção:");
		Scanner sc = new Scanner(System.in);
		int idComp = sc.nextInt();
		if(id == 1) {
			System.out.println("Deseja abrir conta?");
			System.out.println("S - SIM // N - NÃO");
			String confirma = sc.nextLine();
			if(confirma.equalsIgnoreCase("S")) {
				System.out.println("Confirmando abertura de conta conjunta");
			}else if(confirma.equalsIgnoreCase("N")) {
				Client clienteValido = retornaCliente(agenciaSolicitado);
				clienteValido.listaContasBancarias.remove(2);
				listaContasBancarias.remove(2);
				for(Gerente gerente: Banco.listaGerente) {
					for(Client cliente: gerente.getListaClienteGerente()) {
						if(cliente.getAgenciaConta() == getAgenciaConta() || cliente.agenciaConta == agenciaSolicitado) {
							cliente.getListaContasBancarias().remove(cliente);
						}
					}
				}
			}	
		}if(id == 2){
			
		}
	}
	
	public void abrirContaConjunta() {
		System.out.println("Digite a agencia do Cliente que deseja abrir a conta");
		Scanner sc = new Scanner(System.in);
		int agenciaOC = sc.nextInt();
		Client valiarClienteContaCj = validarClienteCJ(getAgenciaConta(),agenciaOC);
		if(valiarClienteContaCj ==null){
			System.out.println("Cliente invalido para deposito");
		}else {
			ContasBancarias contaConjuntaValidada = (ContaConjunta) validarContaConjunta();
			if(contaConjuntaValidada != null) {
				System.out.println("Ja existe uma conta Conjunta");
			}else {
				Random random = new Random();
				String numeroConta;
				int numeroAgencia;
				int saldoConta;
				numeroConta = "2" + random.nextInt(8999);
				numeroAgencia = agenciaConta;
				saldoConta = 200;
				this.listaContasBancarias.add(new ContaConjunta(2,numeroConta, saldoConta, numeroAgencia));
				valiarClienteContaCj.listaContasBancarias.add(new ContaConjunta(2,numeroConta, saldoConta, numeroAgencia));
				String solic = "Deseja abrir conta conjunta com o Cliente " + getNome() + " e " + getAgenciaConta();
				valiarClienteContaCj.listaNotificacao.add(new Notificacao(solic, getAgenciaConta()));
				for(Gerente gerente: Banco.listaGerente) {
					for(Client cliente: gerente.getListaClienteGerente()) {
						if(cliente.getAgenciaConta() == getAgenciaConta() || cliente.agenciaConta == agenciaOC) {
							cliente.getListaContasBancarias().add(new ContaConjunta(2,numeroConta, saldoConta, numeroAgencia));
						}
					}
				}
			}
		}
		
	}
	public void mostrarNotif() {
		for(Notificacao notific: listaNotificacao) {
			System.out.println("A notificação:" + notific.getId());
			System.out.println(notific.getResposta());
		}
	}
	public Client validarClienteCJ(int agencia,int agenciaContaAssociada) {
		for(Gerente gerente: Banco.listaGerente) {
			for(Client cliente: gerente.getListaClienteGerente()) {
				if(cliente.getAgenciaConta() == agenciaContaAssociada) {
					for(Client cliente2: gerente.getListaClienteGerente()) {
						if(cliente.getAgenciaConta() == agencia)
							return cliente;						
					}
				}
			}
		}
		return null;
	}
	public void abrirContaPoupança() {
		ContaPoupanca contaPoupancaValidada = (ContaPoupanca) validarPoup();
		if(contaPoupancaValidada != null) {
			System.out.println("Ja existe uma conta poupança");
		}else {
			Random random = new Random();
			String numeroConta;
			int numeroAgencia;
			int saldoConta;
			numeroConta = "0" + random.nextInt(8999);
			numeroAgencia = agenciaConta;
			saldoConta = 200;
		    this.listaContasBancarias.add(new ContaPoupanca(0,numeroConta, saldoConta, numeroAgencia));
			System.out.println("Conta aberta com sucesso");
		}
		
	}
	
	public void abrirContaCorrente() {
		ContaCorrente contaCorrenteValidada = (ContaCorrente) validarCorrente();
		if(contaCorrenteValidada != null) {
			System.out.println("Ja existe uma conta Corrente");
		}else {
			Random random = new Random();
			String numeroConta;
			int saldoConta;
			numeroConta = "1" + random.nextInt(8999);
			int numeroAgencia = agenciaConta;
			saldoConta = 500;
			this.listaContasBancarias.add(new ContaCorrente(1,numeroConta, saldoConta,numeroAgencia));
			System.out.println("Conta aberta com sucesso");
		}
		
	}
	
	public Client retornaCliente(int agencia) {
		for(Client cliente: Banco.listaCliente) {
			if(cliente.getAgenciaConta() == agencia) {
				return cliente;
			}
		}
		return null;
	}
	private ContasBancarias validarContaConjunta() {
		for (ContasBancarias contaBancaria : listaContasBancarias) {
			if (contaBancaria.getId() == 1) {
				return  contaBancaria;
			}
		}
		return null;
	}
	
	private ContasBancarias validarCorrente() {
		for (ContasBancarias contaBancaria : listaContasBancarias) {
			if (contaBancaria.getId() == 1) {
				return  contaBancaria;
			}
		}
		return null;
	}

	public ContasBancarias validarPoup() {
		for (ContasBancarias contaBancaria : listaContasBancarias) {
			if (contaBancaria.getId() == 0) {
				return  contaBancaria;
			}
		}
		return null;
	}
	
	public void infoContas(){
		int i = 0;
		System.out.println("Agencia das contas:" + getAgenciaConta());
		for (ContasBancarias contaBanc: listaContasBancarias) {
			if(contaBanc.getId() == 0) {
				System.out.println("Conta Poupança:");
				System.out.println("Numero:" + contaBanc.getNumeroConta());
				System.out.println("Id:" + contaBanc.getId());
				System.out.println("Saldo da Conta:" + contaBanc.getSaldoConta());
			}else if(contaBanc.getId() == 1) {
				System.out.println("Conta Corrente:");
				System.out.println("Numero:" + contaBanc.getNumeroConta());
				System.out.println("Id:" + contaBanc.getId());
				System.out.println("Saldo da Conta:" + contaBanc.getSaldoConta());
			}else {
				System.out.println("Conta Conta Conjunta:");
				System.out.println("Numero:" + contaBanc.getNumeroConta());
				System.out.println("I:d" + contaBanc.getId());
				System.out.println("Saldo da Conta:" + contaBanc.getSaldoConta());
			}
		}
	}
	
	public void mostrarInfo() {
		System.out.println("Id:" + getId());
		System.out.println("Nome:" + getNome());
		System.out.println("Cpf:" + getCpf());
		System.out.println("Telefone:" + getTelefone());
		System.out.println("Login:" + getLogin());
		System.out.println("Listas de contas bancarias: ");
	}

	public void mostrarInfoCliente() {
		System.out.println("Id: "+ getId());
		System.out.println("Nome:" + getNome());
		System.out.println("Telefone: "+ getTelefone());
		System.out.println("Cpf: "+ getCpf());
		System.out.println("Login:" + getLogin());
		System.out.println("Contas Bancarias:" + listaContasBancarias.size());
		System.out.println("*****************************************");
	}
}