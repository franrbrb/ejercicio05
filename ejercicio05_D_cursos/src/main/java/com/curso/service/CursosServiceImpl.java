package com.curso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.dao.CursosDao;
import com.curso.model.Curso;
@Service
public class CursosServiceImpl implements CursosService{
	
	@Autowired
	CursosDao dao;

	@Override
	public List<Curso> cursos() {
		
		return dao.findAll();
	}

	@Override
	public List<Curso> altaCurso(Curso curso) {

       dao.save(curso);
       return dao.findAll();
		
	}

	@Override
	public List<Curso> eliminarCurso(int codCurso) {
		dao.deleteById(codCurso);
		return dao.findAll();
	}

	@Override
	public void actualizarDuracion(int codCurso, int numeroHoras) {

        Curso curso = dao.findById(codCurso).orElse(null);
        if (curso != null ) {
        	curso.setDuracion(numeroHoras);
        	dao.save(curso);
        }
		
	}

	@Override
	public Curso buscarCurso(int codCurso) {
		
		return dao.findById(codCurso).orElse(null);
	}

	@Override
	public List<Curso> buscarCursosPorPrecio(double minimo, double maximo) {
		
		return dao.cursosPorPrecio(minimo, maximo);
	}

}
