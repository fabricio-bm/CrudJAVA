package br.com.CrudPOO.bean;

import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.CrudPOO.dao.EstadoDAO;
import br.com.CrudPOO.domain.Estado;


@ViewScoped
@ManagedBean
public class EstadoBean implements Serializable{
	
	private Estado estado;
	private ArrayList<Estado> estados;
	
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public ArrayList<Estado> getEstados() {
		return estados;
	}
	public void setEstados(ArrayList<Estado> estados) {
		this.estados = estados;
	}
	public void Novo() {
		estado=new Estado();
	}
	
	public void excluir(ActionEvent evento) {
		try {
			estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
			EstadoDAO estadoDAO = new EstadoDAO();
			estadoDAO.excluir(estado);
			
			estados = (ArrayList<Estado>) estadoDAO.listar();
			
			Messages.addGlobalInfo("Estado removido com sucesso");
		}catch(RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar remover o estado!");
			erro.printStackTrace();
		}
	}
	public void salvar() {
		try {
			EstadoDAO estadoDao = new EstadoDAO();
			estadoDao.Novo(estado);
			if (estado.getCodigo() == null) {
				Novo();

				Messages.addGlobalInfo("Estado cadastrado com sucesso.");
	
			} else {
				Novo();
				Messages.addGlobalInfo("Estado atualizado com sucesso.");
			}
			estados = estadoDao.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Erro ao tentar cadastrar o estado.");
			erro.printStackTrace();
		}
	}	
		public void editar(ActionEvent evento) {
			try {
				estado = (Estado) evento.getComponent().getAttributes().get("estadoSelecionado");
				
				
			}catch(RuntimeException erro) {
				Messages.addGlobalError("Ocorreu um erro ao tentar editar o estado");
				erro.printStackTrace();
			}
		}	
		@PostConstruct
		public void listar() {
				try {
					EstadoDAO estadoDAO = new EstadoDAO();
					estados = (ArrayList<Estado>) estadoDAO.listar();
				}catch(RuntimeException erro) {
					Messages.addGlobalError("Ocorreu um erro ao tentar listar os estados");
					erro.printStackTrace();
				}
			}
						
			}
