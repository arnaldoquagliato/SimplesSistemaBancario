package entities;

import java.util.Scanner;

public class Login {
	String login;
	String password;

	public int logarUsuario() {

		Scanner sc = new Scanner(System.in);
		//Passando o Usuario numero 1
		System.out.println("Digite o login: ");
		login = sc.nextLine();
		System.out.println("Digite a senha: ");
		password = sc.nextLine();
		if (login.equals("superDiretor") && password.equals("superDiretor")) {
			String nome = "superUsuario";
			String telefone = "123";
			String cpf = "123";
			Diretor diretor = new Diretor (nome, telefone, cpf, login, password); //Instaciando showMenu da classe Administrador e passando pro construtor login e password 
			return diretor.menu();
		} else{
			Usuario usuario = Banco.validarLoginUsuario(login, password);
			if(usuario != null) {
				return usuario.menu();
			}else{
				return 1;
			}
		}
	}
}


