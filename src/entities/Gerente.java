package entities;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Gerente extends Administrador {
	
	private ArrayList<Client> listaClienteGerente = new ArrayList<Client>();
	
	public Gerente(String nome, String telefone, String cpf, String login, String password) {
		super(nome, telefone, cpf, login, password);
	}
	public Gerente() {
	
	}
	
	public int menu() {
		int verifica = 0;
		do {
			System.out.println("*****Menu Admin*****");
			System.out.println("1- Register Client");
			System.out.println("2- Remove Client");
			System.out.println("3- Search Client");
			System.out.println("4- Ver Clientes");
			//System.out.println("5- Consultar Notificação");
			System.out.println("6- Logout");
			System.out.println("7- Finish application");
			verifica = chooseOption();
		} while (verifica != 6 && verifica != 7);
		if (verifica == 6)
			return 1;
		else
			return 0;
	}
	
	public int chooseOption(){
		Scanner sc = new Scanner(System.in);
		System.out.println("***Escolha uma opção:***");
		String op = sc.nextLine();
		switch (op) {
		case "1":
			registerClient();
			break;
		case "2":
			removeClient();
			break;
		case "3":
			searchClient();
			break;
		case "4":
			visualizarClientes();	
			return 4;
		case "5":
		//	consultarNotificacoes();
			return 5;
		case "6":
			System.out.println("Retornando para tela de login");
			return 6;
		case "7":
			System.out.println("Aplicação encerrada");
			return 7;
		default:
			System.out.println("Incorrect Option");
			break;
		}
		return 1; // volta pro menu
	}
	
	public void vizualizarListaClientes() {
		if(Banco.listaCliente.size()==0) {
			System.out.println("Sem clintes disponiveis");
		}else {
			for (Client cliente : Banco.listaCliente) {
				cliente.mostrarInfoCliente();
			}			
		}
	}
		
	public void registerClient() {
		Scanner sc = new Scanner(System.in);
		String login;
		String password;
		String nome;
		String telefone;
		String cpf;	
		System.out.print("Digite o login do cliente: ");
		login = (sc.nextLine());
		System.out.print("Digite a senha do cliente: ");
		password = sc.nextLine();
		System.out.print("Digite o nome do cliente: ");
		nome = sc.nextLine();
		System.out.print("Telefone para contato: ");
		telefone = sc.nextLine();
		System.out.print("CPF do cliente: ");
		cpf = sc.nextLine();
		Usuario validarLogin = Banco.validarLoginClient(login);
		if (Banco.buscarPorCpf(cpf) != null || Banco.buscarPorLogin(login) != null) { //caso exista cliente esntra nessa condicional
			System.out.println("Cliente ja cadastrado");
		}else if(validarLogin != null) {
			System.out.println("Login Invalido");
		}else {
			Client c  = new Client(login, password, nome, telefone, cpf);
			Banco.listaCliente.add(c);
			getListaClienteGerente().add(c);
			System.out.println("Cliente cadastrado no Gerente");	
		}
	}


	public void removeClient() {
		Scanner sc = new Scanner(System.in);
		String rmv;
		int teste = -1;
		Client validarClient = validarContaClient();
		if(validarClient  == null) {
			System.out.println("Não há clientes");
		}else {			
			do {			
				System.out.println("Remover por CPF - 1");
				System.out.println("Remover por ID - 2");
				System.out.println("Voltar pro menu anterior- 0");
				System.out.println("Escolha a forma de remover:");
				rmv = sc.nextLine();
				if (rmv.equals("1")) {
					String cpf;
					System.out.println("Digite o cpf do cliente:");
					cpf = sc.nextLine();
					removerClienteGerenteCpf(cpf);
					Banco.removerPorCpf(cpf);
					teste = -1;
				} else if (rmv.equals("2")) {
					int id;
					System.out.println("Digite o id do cliente:");
					id = sc.nextInt();
					removerClienteGerenteId(id);
					Banco.removerPorId(id);
					teste = -1;
				}else if(rmv.equals("0")){
					System.out.println("Voltando para menu anteior");
					teste = 0;
				}else if(!rmv.equals("0")){
					System.out.println("Opção Invalida");
					teste = -1;
				}
			}while(teste != 0);
		}
	}

	
	public void searchClient() {
		String sch;
		int teste = -1;
		Client validarClient = validarContaClient();
		if(validarClient  == null) {
			System.out.println("Não há clientes");
		}else {
			Scanner sc = new Scanner(System.in);
			do {			
				System.out.println("Buscar por CPF - 1");
				System.out.println("Buscar por  ID - 2");
				System.out.println("Buscar por  NOME - 3");
				System.out.println("Voltar pro menu anterior- 0");
				System.out.println("Escolha a forma de buscar:");
				sch = sc.nextLine();
				Client c = null; // "instanciando c" com Client
				if (sch.equals("1")) {
					String cpf;
					System.out.println("Digite o cpf do cliente:");
					cpf = sc.nextLine();
					c = Banco.buscarPorCpf(cpf);
					if(c == null) {
						System.out.println("Cliente não encontrado");
					}else {
						buscarPorCpf(cpf);
					}
					teste = -1;
				} else if (sch.equals("2")) {
					int id;
					System.out.println("Digite o id do cliente:");
					id = sc.nextInt();
					sc.nextLine();
					c = Banco.buscarPorId(id);
					if(c == null) {
						System.out.println("Cliente não encontrado");
					}else {
						buscarPorId(id);
					}
				}else if (sch.equals("3")) {
					String nome;
					System.out.println("Digite o nome do cliente:");
					sc.nextLine();
					nome = sc.nextLine();
					System.out.println(nome);
					c = Banco.buscarPorNome(nome);
					if(c == null) {
						System.out.println("Cliente não encontrado");
					}else {
						buscarPorNome(nome);
					}
				}else if (sch.equals("0")){ //função que retorna para a tela anterior
					System.out.println("Encaminhando para tela anterior");
					teste = 0;
				}else { // caso nao seja encontrado o cliente
					System.out.println("Não encontrado");
				}
			}while(teste != 0);	
		}
		
	}
	
	public void visualizarClientes() {
		int i;
		System.out.println("Lista de clientes:");
		for (i = 0; i < listaClienteGerente.size(); i++) {
			System.out.println("As informações do Cliente: " + i);
			listaClienteGerente.get(i).mostrarInfoCliente();
		}
	}
	
	public void consultarNotificacoes() {
		
	}
	
	public int retornaIndexGerente(int id) {
		int i;
		for(i = 0; i<Banco.listaGerente.size(); i++) {
			if(Banco.listaGerente.get(i).getId() == id) {
				return i;
			}
		}
		return -1;
	}


	public ArrayList<Client> getListaClienteGerente() {
		return listaClienteGerente;
	}


	public void setListaClienteGerente(ArrayList<Client> listaClienteGerente) {
		this.listaClienteGerente = listaClienteGerente;
	}

	public void removerClienteGerenteCpf(String cpf) {
		for(Client cliente: listaClienteGerente) {
			if (cliente.getCpf().equals(cpf)) {
				listaClienteGerente.remove(cliente);
			}
		}
	}
	
	public void removerClienteGerenteId(int id) {
		for(Client cliente: listaClienteGerente) {
			if (cliente.getId() == id) {
				listaClienteGerente.remove(cliente);
			}
		}
	}
	
	public void removerClienteGerenteNome(String nome) {
		for(Client cliente: listaClienteGerente) {
			if (cliente.getNome().equals(nome)) {
				listaClienteGerente.remove(cliente);
			}
		}
	}
	
	public void buscarPorId(int id) {
		for (Client cliente : listaClienteGerente) {
			if (cliente.getId() == id) {
				cliente.mostrarInfoCliente();
			}
		}
	}
	
	public void buscarPorCpf(String cpf) {
		for (Client cliente : listaClienteGerente) {
			if (cliente.getCpf().equals(cpf)) {
				cliente.mostrarInfoCliente();
			}
		}
	}
	
	public void buscarPorNome(String nome) {
		for (Client cliente : listaClienteGerente) {
			if (cliente.getNome().equals(nome)) {
				cliente.mostrarInfoCliente();
			}
		}
	}
}
