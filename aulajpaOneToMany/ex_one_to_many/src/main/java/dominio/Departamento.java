package dominio;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Departamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	private String nome;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "departamento")
	private List<Funcionario> funcionarios;
	
	public Departamento() {
		this("");
	}
	public Departamento(String nome) {
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
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	@Override
	public String toString() {
		return "Departamento [Id=" + Id + ", nome=" + nome + "]";
	}
}
