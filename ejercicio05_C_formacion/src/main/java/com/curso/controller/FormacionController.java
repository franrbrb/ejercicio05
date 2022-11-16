package com.curso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.curso.model.Formacion;
import com.curso.service.FormacionService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;

@CrossOrigin("*")
@RestController
public class FormacionController {
	
	

	@Autowired
	FormacionService service;
	
	@ApiOperation(value = "Dar de alta una formacion")
	@PostMapping(value="formacion", consumes=MediaType.APPLICATION_JSON_VALUE)
	public void altaCurso (
			@ApiParam(value = "JSON con los datos de la formación") 
			@RequestBody Formacion formacion) {
		service.altaFormacion(formacion);
	}
	
	@ApiOperation(value = "Lista las formaciones existentes")
	@GetMapping(value="formaciones", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Formacion> buscarCursos () {
		return service.buscarFormaciones();
	}
}