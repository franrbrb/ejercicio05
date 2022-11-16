package com.curso.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.curso.model.Curso;
import com.curso.model.Formacion;

@Service
public class FormacionServiceImpl implements FormacionService {
	
	@Autowired
	RestTemplate template;
	
	private String url = "http://servicio-cursos/";

	@Override
	public List<Formacion> buscarFormaciones() {
		List<Curso> result = Arrays.asList(template.getForObject(url + "cursos/0/9999999", Curso[].class));
		return result.stream().map((c) -> new Formacion(c.getNombre(), c.getDuracion() >= 50 ? 10 : 5, c.getPrecio())).collect(Collectors.toList());
	}
	
	@Override
	public void altaFormacion(Formacion formacion) {
		List<Curso> cursos = Arrays.asList(template.getForObject(url + "cursos/0/9999999", Curso[].class));
		List<Formacion> formaciones = cursos.stream().map((c) -> new Formacion(c.getNombre(), c.getDuracion() >= 50 ? 10 : 5, c.getPrecio())).collect(Collectors.toList());
		
		if (formaciones.stream().filter((f) -> f.getCurso() != null && f.getCurso().equals(formacion.getCurso())).count() == 0 ) {
			int duracion = formacion.getAsignaturas() > 0 ? formacion.getAsignaturas() * 10 : 0;
			Curso curso = new Curso (getNuevoCodCurso(cursos), formacion.getCurso(), duracion, formacion.getPrecio());
			template.postForLocation(url + "curso/", curso);
		}		
	}


	private int getNuevoCodCurso (List<Curso> cursos) {
		int codCurso = 0;
		for (Curso curso : cursos) {
			if (codCurso < curso.getCodCurso()) {
				codCurso = curso.getCodCurso();
			}
		}
		return codCurso + 1;
	}






}