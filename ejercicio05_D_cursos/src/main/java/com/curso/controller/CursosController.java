package com.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.curso.model.Curso;
import com.curso.service.CursosService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin("*")
@RestController
public class CursosController {

	@Value
	("${eureka.instance.instance-id}")
	String instancia;
	
	@Autowired
	CursosService service;
	
	@ApiOperation(value = "Listar cursos existentes")
	@GetMapping(value="curso",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> cursos() {
		return service.cursos();
	}
	
	@ApiOperation(value = "Dar de alta curso")
	@PostMapping(value="curso", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> altaCurso (
			@ApiParam(value = "JSON con datos del curso")
			@RequestBody Curso curso) {
		System.out.println("instancia " + instancia);
		return service.altaCurso(curso);
	}
	
	
	@ApiOperation(value = "Borrar un curso a través de su codCurso")
	@DeleteMapping(value="curso/{codCurso}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> eliminacionCurso (
			@ApiParam(value = "Código del curso para eliminar")
			@PathVariable int codCurso) {
		System.out.println("instancia " + instancia);
		return service.eliminarCurso(codCurso);
	}
	
	@ApiOperation(value = "Actualizar duración de un curso")
	@GetMapping(value="curso/{codCurso}/{duracion}")
	public void actualizacionDuracion (
			@ApiParam(value = "Código del curso para actalizar")
			@PathVariable int codCurso, 
			
			@ApiParam(value = "Nueva duración del curso") 
			@PathVariable int duracion) {
		
		System.out.println("instancia " + instancia);
		service.actualizarDuracion(codCurso, duracion);
	}
	
	@ApiOperation(value = "Buscar un curso, a través de su codCurso")
	@GetMapping(value="curso/{codCurso}", produces=MediaType.APPLICATION_JSON_VALUE)
	public Curso buscarCurso (
			@ApiParam(value = "Código del curso a buscar") 
			@PathVariable int codCurso) {
		
		System.out.println("instancia " + instancia);
		return service.buscarCurso(codCurso);
	}

	@ApiOperation(value = "Buscar cursos a través de un intervalo de precios")
	@GetMapping(value="buscarporprecio/{precioMinimo}/{precioMaximo}", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Curso> buscarCursosPorPrecio (
			@ApiParam(value = "Límite inferior del precio")
			@PathVariable double precioMinimo, 
			
			@ApiParam(value = "Límite superior del precio")
			@PathVariable double precioMaximo) {
		
		System.out.println("instancia " + instancia);
		return service.buscarCursosPorPrecio(precioMinimo, precioMaximo);
	}	
}