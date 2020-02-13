package entities;

abstract class Administrador extends Usuario{
	//atributos que são apenas referidos ao usuárioAdministrador
	
	public Administrador() {
		
	}
	

	public Administrador(String nome, String telefone, String cpf, String login, String password) {
		setId(Banco.ID++);
		setNome(nome);
		setTelefone (telefone);
		setCpf(cpf);
		setLogin(login);
		setPassword(password);
	}

	public abstract int menu();
	
	//função que possibilita o usuarioAdministrador escolher uma opção
	public abstract int chooseOption();
	
	public abstract void registerClient();
	
	public abstract void removeClient();
	
	public abstract void searchClient();
	
	//função utilizada para retornar se existe ou não uma conta 
	public Client validarContaClient() {
		for (Client c : Banco.listaCliente) {
			return c;
		}
		return null;
	}
	
	public Gerente validarContaGerente(int id) {
		for (Gerente gerente: Banco.listaGerente) {
			if(gerente.getId() == id) {
				return gerente;
			}
		}
		return null;
	}
	
	public void mostrarInfo() {
		System.out.println("Id:" + getId());
		System.out.println("Nome:" + getNome());
		System.out.println("Cpf:" + getCpf());
		System.out.println("Telefone:" + getTelefone());
		System.out.println("Login:" + getLogin());
	}
	
	public static void mostrarGerenteInfo(int id) {
		for(Gerente gerente: Banco.listaGerente) {
			if(gerente.getId() == id) {
				gerente.mostrarInfo();
			}
		}
	}
	
	public static void mostrarClientInfo(int id) {
		for(Client cliente: Banco.listaCliente) {
			if(cliente.getId() == id) {
				cliente.mostrarInfoCliente();
			}
		}
	}
	
	public Gerente removerGerentePorId(int id) {
		int verifica = 0;
		for (Gerente gerente : Banco.listaGerente) {
			if (gerente.getId() == id) {
				Banco.listaGerente.remove(gerente);
				System.out.println("O gerente foi removido com sucesso");
				verifica = 1;
				return gerente;
			}
		}
		if(verifica == 0) {
			System.out.println("Cliente não encontrado");
		}
		return null;
	}
	
	public void mostrarInfoCliente(int id) {
		for(Gerente gerente: Banco.listaGerente) {
			if(gerente.getId() == id) {
				for(Client cliente: gerente.getListaClienteGerente()) {
					cliente.mostrarInfoCliente();
				}
			}
		}
	}

	public Diretor validarContaDiretor(int id) {
		for (Diretor diretor: Banco.listaDiretor) {
			return diretor;
		}
		return null;
	}
		
	public void mostrarDiretorInfo(int id) {
		for(Diretor diretor: Banco.listaDiretor) {
			if(diretor.getId() == id) {
				diretor.mostrarInfo();
			}
		}
	}
	
	public Diretor removerDiretorPorId(int id) {
		int verifica = 0;
		for (Diretor diretor : Banco.listaDiretor) {
			if (diretor.getId() == id) {
				Banco.listaGerente.remove(diretor);
				System.out.println("O gerente foi removido com sucesso");
				verifica = 1;
				return diretor;
			}
		}
		if(verifica == 0) {
			System.out.println("Cliente não encontrado");
		}
		return null;
	}
	

}
	


