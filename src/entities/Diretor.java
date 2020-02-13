package entities;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Diretor extends Administrador {
	
	
	public Diretor (String nome, String telefone, String cpf, String login, String password) {
		super(nome, telefone, cpf, login, password);
	}
	
	public int menu(){
		int verifica = 0;
		do {
			System.out.println("*****Menu Admin*****");
			System.out.println("1-  Registrar Cliente");
			System.out.println("2-  Procurar Cliente");
			System.out.println("3-  Remover Cliente");
			System.out.println("4-  Registrar Gerente");
			System.out.println("5-  Procurar Gerente");
			System.out.println("6-  Remover Gerente");
			System.out.println("7   Registrar Diretor");
			System.out.println("8-  Procurar Diretor");
			System.out.println("9-  Remover Diretor");
			System.out.println("10- Vizualizar Clientes");
			System.out.println("11- Logout");
			System.out.println("12- Finish application");
			verifica = chooseOption();
		} while (verifica != 11 && verifica != 12);
		if (verifica == 11)
			return 1;
		else
			return 0;
	}

	
	public int chooseOption() {
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
			registerGerente();
			break;
		case "5":
			searchGerente();
			break;
		case "6":
			removeGerente();
			break;
		case "7":
			registerDiretor();
			break;
		case "8":
			searchDiretor();
			break;
		case "9":
			removeDiretor();
			break;
		case "10":
			visualizarClientes();
			return 10;
		case "11":
			System.out.println("Retornando para tela de login");
			return 11;
		case "12":
			System.out.println("Finish application");
			return 12;
		default:
			System.out.println("Incorrect Option");
			break;
		}
		return 1; // voltar para menu
	}
	
	
	public void registerClient() {
		Scanner sc = new Scanner(System.in);
		String login;
		String password;
		String nome;
		String telefone;
		String cpf;
		if(Banco.listaGerente.size() != 0) {		
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
				Random random = new Random();
				int indexGerenteRandom = random.nextInt(Banco.listaGerente.size());
				Banco.listaGerente.get(indexGerenteRandom).getListaClienteGerente().add(c);
				System.out.println("Cliente cadastrado no Gerente de ID: " + Banco.listaGerente.get(indexGerenteRandom).getId());
			}			
		}else {
			System.out.println("Não é possivel cadastrar");
		}
	}
	
	public void removeClient() {
		Scanner sc = new Scanner(System.in);
		int teste = 1;
		Gerente gerente = new Gerente();
		Client validarClient = validarContaClient();
		if(validarClient  == null) {
			System.out.println("Não há clientes");
		}else {			
			do {			
				System.out.println("Remover por CPF - 1");
				System.out.println("Remover por ID - 2");
				System.out.println("Voltar pro menu anterior- 0");
				System.out.println("Escolha a forma de remover:");
				String rmv = sc.nextLine();
				if (rmv.equals("1")) {
					String cpf;
					System.out.println("Digite o cpf do cliente:");
					cpf = sc.nextLine();
					gerente.removerClienteGerenteCpf(cpf);
					Banco.removerPorCpf(cpf);
				} else if (rmv.equals("2")) {
					int id;
					System.out.println("Digite o id do cliente:");
					id = sc.nextInt();
					gerente.removerClienteGerenteId(id);
					Banco.removerPorId(id);
				}else if(rmv.equals("0")) {
					System.out.println("Retornando para o menu anterior");
					teste = 0;
				}else if(!rmv.equals("0")){
					System.out.println("Opção Invalida");
				}
			}while(teste != 0);
			//sc.close();
		}
	}

	public void searchClient(){
		int teste = 1;
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
				String sch = sc.nextLine();
				Client c = null; // "instanciando c" com Client
				if (sch.equals("1")) {
					String cpf;
					System.out.println("Digite o cpf do cliente:");
					cpf = sc.nextLine();
					c = Banco.buscarPorCpf(cpf);
					if(c == null) {
						System.out.println("Cliente não encontrado");
					}else {
						c.mostrarInfo();
					}
				} else if (sch.equals("2")) {
					int id;
					System.out.println("Digite o id do cliente:");
					id = sc.nextInt();
					sc.nextLine();
					c = Banco.buscarPorId(id);
					if(c == null) {
						System.out.println("Cliente não encontrado");
					}else {
						c.mostrarInfo();
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
						c.mostrarInfo();
					}
				}else if (sch.equals("0")){ //função que retorna para a tela anterior
					System.out.println("Encaminhando para tela anterior");
					teste = 0;
					break;
				}else { // caso nao seja encontrado o cliente
					System.out.println("Não encontrado");
				}
			}while(teste != 0);	
		}
	}
	
	public void registerGerente() {
		Scanner sc = new Scanner(System.in);
		String login;
		String password;
		String nome;
		String telefone;
		String cpf;
		System.out.print("Digite o login do Gerente: ");
		login = (sc.nextLine());
		System.out.print("Digite a senha do Gerente: ");
		password = sc.nextLine();
		System.out.print("Digite o nome do Gerente: ");
		nome = sc.nextLine();
		System.out.print("Telefone para contato: ");
		telefone = sc.nextLine();
		System.out.print("CPF do Gerente: ");
		cpf = sc.nextLine();
		if (Banco.buscarGerentePorCpf(cpf) != null || Banco.buscarGerentePorNome(nome) != null) { //caso exista Gerente esntra nessa condicional
			System.out.println("Gerente ja cadastrado");
		}else if(Banco.validarLoginGerente(login) != null) {
			System.out.println("Login Invalido");
		}else {
			Banco.listaGerente.add(new Gerente(nome, telefone,cpf,login, password));
			System.out.println("Gerente cadastrado");				
		}
	}
	
	public void removeGerente() {
		Scanner sc = new Scanner(System.in);
		int id;
		System.out.println("Digite o ID do gerente que deseja remover");
		id = sc.nextInt();
		Usuario validarGerente = Banco.validarGerenteId(id);
		if(validarGerente  == null) {
			System.out.println("Gerente indisponivel");
		}else {	
			removerGerentePorId(id);
		}
	}
	
	public void searchGerente() {
		String sch;
		int idGerente;
		int teste = 1;
		System.out.println("1 - Vizulizar Gerente Especifico");
		System.out.println("2 - Vizulizar Todos Gerentes Disponiveis");
		Scanner sc = new Scanner(System.in);				
		sch = sc.nextLine();
		if(sch.equals("1")) {
			System.out.println("Digite o ID do Gerente:");
			idGerente = sc.nextInt();
			Gerente validarGerente = validarContaGerente(idGerente);
			if(validarContaGerente(idGerente) == null) {
				System.out.println("Gerente invalido");
			}else {
				System.out.println("As informações do Gerente:");
				mostrarGerenteInfo(idGerente); 
				System.out.println("As informações dos Clientes gerenciados:");
				mostrarInfoCliente(idGerente);
			}
		}else if(sch.equals("2")) {
			int i;
			for (i = 0; i < Banco.listaGerente.size(); i++) {
				System.out.println("As informações do Gerente: " + Banco.listaGerente.get(i).getId());
				mostrarGerenteInfo(Banco.listaGerente.get(i).getId());
				System.out.println("As informações dos Clientes gerenciados:");
				mostrarInfoCliente(Banco.listaGerente.get(i).getId());
			}
		}else {
			System.out.println("Opção indisponivel");
		}
	}

	public void registerDiretor() {
		Scanner sc = new Scanner(System.in);
		String login;
		String password;
		String nome;
		String telefone;
		String cpf;
		System.out.print("Digite o login do Diretor: ");
		login = (sc.nextLine());
		System.out.print("Digite a senha do Diretor: ");
		password = sc.nextLine();
		System.out.print("Digite o nome do Diretor: ");
		nome = sc.nextLine();
		System.out.print("Telefone para contato: ");
		telefone = sc.nextLine();
		System.out.print("CPF do Gerente: ");
		cpf = sc.nextLine();
		if (Banco.buscarDiretorPorCpf(cpf) != null || Banco.buscarDiretorPorNome(nome) != null) { //caso exista Gerente esntra nessa condicional
			System.out.println("Diretor ja cadastrado");
		}else if(Banco.validarLoginDiretor(login) != null) {
			System.out.println("Login Invalido");
		}else {
			Banco.listaDiretor.add(new Diretor(nome, telefone,cpf,login, password));
			System.out.println("Diretor cadastrado");				
		}
	}

	public void searchDiretor() {//PAREI AQUI
		int sch, idDiretores;
		if(Banco.listaDiretor.size() == 0) {
			System.out.println("Sem Gerentes Cadastrados");
		}else {			
			System.out.println("1 -Vizulizar Diretor Especifico");
			System.out.println("2 - Vizulizar Todos Diretores Disponiveis");
			Scanner sc = new Scanner(System.in);				
			sch = sc.nextInt();
			if(sch == 1) {
				System.out.println("Digite o ID do Diretores:");
				idDiretores = sc.nextInt();
				Diretor validarDiretores = validarContaDiretor(idDiretores);
				if(validarContaDiretor(idDiretores) == null) {
					System.out.println("Diretores invalido");
				}else {
					System.out.println("As informações do Diretores:");
					mostrarDiretorInfo(idDiretores);
				}
			}else if(sch == 2) {
				int i;
				for (i = 0; i < Banco.listaDiretor.size(); i++) {
					System.out.println("As informações do Diretor: " + i);
					mostrarDiretorInfo(Banco.listaDiretor.get(i).getId());
				}
			}else {
				System.out.println("Opção indisponivel");
			}
		}
	}


	public void removeDiretor() {
		Scanner sc = new Scanner(System.in);
		if(Banco.listaDiretor.size() == 0) {
			System.out.println("Não há Diretor Cadastrado");
		}else {
			int id;
			System.out.println("Digite o ID do Diretor que deseja remover");
			id = sc.nextInt();
			Usuario validarDiretor = Banco.validarDiretorId(id);
			if(validarDiretor  == null) {
				System.out.println("Diretor indisponivel");
			}else {	
				removerDiretorPorId(id);
			}
		}
		
	}
	
	public void visualizarClientes() {
		for(Client cliente: Banco.listaCliente) {
			cliente.mostrarInfoCliente();
		}
		if(Banco.listaCliente.size() == 0) {
			System.out.println("Sem Clientes Disponiveis");
		}
	}
}
