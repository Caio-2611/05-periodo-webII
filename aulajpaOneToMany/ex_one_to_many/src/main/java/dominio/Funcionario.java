package dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Funcionario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "departamento_id")
	private Departamento departamento;
	
	public Funcionario() {
		this("");
	}
	
	public Funcionario(String nome) {
		setNome(nome);
	}
	
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	@Override
	public String toString() {
		return "Funcionario [Id=" + Id + ", nome=" + nome + ", departamento=" + departamento.getNome() + "]";
	}
}
