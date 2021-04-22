package org.ciberfarma.app;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.ciberfarma.modelo.Usuario;

public class JPA_Test01 {

	public static void main(String[] args) {
		Usuario u = new Usuario();
		//u.setCodigo(10);
		u.setNombre("Rimuru");
		u.setApellido("Tempest");
		u.setUsuario("nadie2@ciber.com");
		u.setClave("ninguna");
		u.setFnacim("2000/10/15");
		u.setTipo(1);
		u.setEstado(1);
		
		//llama al manage de la entidad
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA_Clase01");
		EntityManager em = factory.createEntityManager();
		/*
		//Listado
		List<Usuario> lstUsuarios = em.createQuery("select u from Usuario u", Usuario.class).getResultList();
		if (lstUsuarios == null) {
			System.out.println("No hay datos");
		}else {
			for (Usuario u2 : lstUsuarios) {
				System.out.println(u2.getNombre()+", "+u2.getApellido());
			}
		}
		*/	
		/*
		//consulta x codigo
		Usuario u2 = em.find(Usuario.class, 30);
		if (u2 == null) {
			System.out.println("Usuario no existe");
		}else {
			System.out.println(u2.getNombre()+", "+u2.getApellido());
		}*/
		
		//para el registro
		try {
		em.getTransaction().begin();
		//em.merge(u); //si existe lo actualiza sino lo crea
		em.persist(u);
		em.getTransaction().commit();
		System.out.println("Registro Exitoso");
		em.close();
		}catch (Exception e){
			System.out.println("Error al registrar " + e);
		}

	}

}
