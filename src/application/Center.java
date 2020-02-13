package application;

import entities.Login;

public class Center {

	public static void main(String[] args) {
		
		int verifica;
		do{
			Login loginSystem = new Login();
			verifica = loginSystem.logarUsuario();
		}while(verifica != 0);
	}

}
