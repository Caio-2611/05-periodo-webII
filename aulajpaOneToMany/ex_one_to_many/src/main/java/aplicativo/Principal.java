package aplicativo;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dominio.Departamento;
import dominio.Funcionario;

public class Principal {

	public static void main(String[] args) {
			
		EntityManagerFactory emf = null;
		EntityManager em = null;
		
		try {
			emf = Persistence.createEntityManagerFactory("aula-jpa");
			em = emf.createEntityManager();
			
			Funcionario objFuncionario1 = new Funcionario("Rafael");
			Departamento objDepartamento1 = new Departamento("Professores");
			
			Funcionario objFuncionario2 = new Funcionario("Guilherme");
			Departamento objDepartamento2 = new Departamento("Redes");
			
			Funcionario objFuncionario3 = new Funcionario("Gabriel");
			Funcionario objFuncionario4 = new Funcionario("Miguel");
			
			objFuncionario1.setDepartamento(objDepartamento1);
			objFuncionario2.setDepartamento(objDepartamento2);
			objFuncionario3.setDepartamento(objDepartamento2);
			objFuncionario4.setDepartamento(objDepartamento1);
			
			em.getTransaction().begin();
			
			em.persist(objFuncionario1);
			em.persist(objFuncionario2);
			em.persist(objFuncionario3);
			em.persist(objFuncionario4);
			
			em.persist(objDepartamento1);
			em.persist(objDepartamento2);
			
			
			Query consultaFuncionarios = em.createQuery("select funcionario from Funcionario funcionario");
			ArrayList<Funcionario> listaFuncionario = (ArrayList<Funcionario>)consultaFuncionarios.getResultList();
			
			Query consultaDepartamento = em.createQuery("select departamento from Departamento departamento");
			ArrayList<Departamento> listaDepartamento = (ArrayList<Departamento>)consultaDepartamento.getResultList();
			
			em.getTransaction().commit();
			
			for(Funcionario objFuncionario: listaFuncionario) {
				System.out.println(objFuncionario);
			}
		
			for(Departamento objDepartamento: listaDepartamento) {
				System.out.println(objDepartamento);
			}
		}catch (Exception e){
			if(em.getTransaction() != null) {
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		}finally {
			if(em != null) {
				em.close();
			}
			if(emf != null) {
				emf.close();
			}
		}
	}
}
