package entities;

import java.util.ArrayList;

public class Notificacao {
	private int id = 0;
	private int AgenciaQuemEnvia;
	
	private String resposta;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getResposta() {
		return resposta;
	}
	public void setResposta(String resposta) {
		this.resposta = resposta;
	}
	
	public Notificacao(String resposta, int AgenciaQuemEnvia) {
		this.id = id++;
		this.resposta = resposta;
		this.AgenciaQuemEnvia = AgenciaQuemEnvia;
	}
	public int getAgenciaQuemEnvia() {
		return AgenciaQuemEnvia;
	}
	public void setAgenciaQuemEnvia(int agenciaQuemEnvia) {
		AgenciaQuemEnvia = agenciaQuemEnvia;
	}
	
}
