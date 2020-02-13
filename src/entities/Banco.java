package entities;

import java.util.ArrayList;

public class Banco {
	static ArrayList<Client> listaCliente = new ArrayList<Client>();
	static ArrayList<Gerente> listaGerente = new ArrayList<Gerente>();
	static ArrayList<Diretor> listaDiretor = new ArrayList<Diretor>();
	
	
	
	public static int ID=0;
	
	
	public static Client buscarPorCpf(String cpf) {
		for (Client c : Banco.listaCliente) {
			if (c.getCpf().equals(cpf)) {
				return c;
			}

		}
		return null;
	}

	public static Client buscarPorLogin(String login) {
		for (Client c : Banco.listaCliente) {
			if (c.getLogin().equals(login)) {
				return c;
			}
		}
		return null;
	}
	
	//validar login será uma lista com todos os usuarios
	public static Client validarLogin(String login, String password) {
		for (Client c : Banco.listaCliente) {
			if (c.getLogin().equals(login) && c.getPassword().equals(password)) {
				return c;
			}
		}
		return null;
	}

	public static Client buscarPorId(int id) {
		for (Client c : Banco.listaCliente) {
			if (c.getId() == id) {
				return c;
			}
		}
		return null;
	}

	public static Client buscarPorNome(String nome) {
		for (Client c : Banco.listaCliente) {
			if (c.getNome().equals(nome)) {
				return c;
			}
		}
		return null;
	}

	public static Usuario validarLoginClient(String login) {
		for (Client cliente : Banco.listaCliente) {
			if (cliente.getLogin().equals(login)) {
				return cliente;
			}
		}
		for (Diretor diretor : Banco.listaDiretor) {
			if (diretor.getLogin().equals(login)) {
				return diretor;
			}
		}for (Gerente gerente : Banco.listaGerente) {
			if (gerente.getLogin().equals(login)) {
				return gerente;
			}
		}
		
		return null;
	}

	public static Client removerPorCpf(String cpf) {
		for (Client c : Banco.listaCliente) {
			if (c.getCpf().equals(cpf)) {
				Banco.listaCliente.remove(c);
				System.out.println("O cliente foi removido com sucesso");
				return c;
			}
		}
		System.out.println("Cliente não encontrado");
		return null;
	}
			
	public static Client removerPorId(int id) {
		for (Client c : Banco.listaCliente) {
			if (c.getId() == id) {
				Banco.listaCliente.remove(c);
				System.out.println("O cliente foi removido com sucesso");
				return c;
			}
		}
		System.out.println("Cliente não encontrado");
		return null;
	}
	
	public static Gerente validarLoginGerente(String login) {
		for (Gerente gerente : Banco.listaGerente) {
			if (gerente.getLogin().equals(login)) {
				return gerente;
			}
		}
		return null;
	}

	public static Gerente buscarGerentePorCpf(String cpf) {
		for (Gerente gerente : listaGerente) {
			if (gerente.getCpf().equals(cpf)) {
				return gerente;
			}
		}
		return null;
	}
		
	public static Gerente buscarGerentePorId(int id) {
		for (Gerente gerente : listaGerente) {
			if (gerente.getId() == id) {
				return gerente;
			}
		}
		return null;
	}
	
	public static Gerente buscarGerentePorNome(String nome) {
		for (Gerente gerente : listaGerente) {
			if (gerente.equals(nome)) {
				return gerente;
			}
		}
		return null;
	}
	
	public static Usuario validarLoginUsuario(String login,String senha) {
		for (Gerente gerente : Banco.listaGerente) {
			if (gerente.getLogin().equals(login) && gerente.getLogin().equals(senha)) {
				return gerente;
			}
			System.out.println();
		}
		for (Diretor diretor : Banco.listaDiretor) {
			if (diretor.getLogin().equals(login) && diretor.getLogin().equals(senha)) {
				return diretor;
			}
		}
		for (Client cliente : Banco.listaCliente) {
			if (cliente.getLogin().equals(login) && cliente.getLogin().equals(senha)) {
				return cliente;
			}
		}
		System.out.println("Usuário não encontrado");
		return null;
	}
	
	public static Gerente validarGerenteId(int id) {
		for(Gerente gerente: listaGerente) {
			if(gerente.getId() == id) {
				return gerente;
			}
		}
		return null;
	}	

	public static Diretor validarLoginDiretor(String login) {
		for (Diretor diretor : listaDiretor) {
			if (diretor.getLogin().equals(login)) {
				return diretor;
			}
		}
		return null;
	}
	
	public static Diretor buscarDiretorPorCpf(String cpf) {
		for (Diretor diretor : listaDiretor) {
			if (diretor.getCpf().equals(cpf)) {
				return diretor;
			}
		}
		return null;
	}
	
	public static Diretor buscarDiretorPorId(int id) {
		for (Diretor diretor : listaDiretor) {
			if (diretor.getId() == id) {
				return diretor;
			}
		}
		return null;
	}
	
	public static Diretor buscarDiretorPorNome(String nome) {
		for (Diretor diretor : listaDiretor) {
			if (diretor.equals(nome)) {
				return diretor;
			}
		}
		return null;
	}
	
	public static Diretor validarDiretorId(int id) {
		for(Diretor diretor : listaDiretor) {
			if(diretor.getId() == id) {
				return diretor;
			}
		}
		return null;
	}
}
