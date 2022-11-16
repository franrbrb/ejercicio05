package com.curso.service;

import java.util.List;

import com.curso.model.Curso;

public interface CursosService {
	
	List<Curso> cursos();
	
	List<Curso> altaCurso(Curso libro);
	
	List<Curso> eliminarCurso(int codCurso);
	
	void actualizarDuracion(int codCurso,int numeroHoras);	
	
	Curso buscarCurso(int codCurso);	
	
	List<Curso> buscarCursosPorPrecio(double minimo , double maximo);
	

}
