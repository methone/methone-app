package com.methone

import grails.test.*
import groovy.lang.MetaClass

import com.methone.support.CrudControllerUnitTest


class EspecialidadeControllerTests extends CrudControllerUnitTest {

	EspecialidadeControllerTests(){
		super(EspecialidadeController.class);
	}

	@Override
	protected void buildDomainMocks() {
		mockDomain(Especialidade)
		mockDomain(Area)
	}

	@Override
	protected buildControllerParamsMock() {
		def paramsMock = ["nome": "nome", "descricao" : "descricao", "max" : "1",
			"id" : "1", "area.id": "1", "area":"['id':'1']"]
		return paramsMock;
	}

	@Override
	protected createPersistedEntity() {
		Area area = new Area(nome: "nome", descricao: "descricao")
		area.save()
		Especialidade especialidade = new Especialidade(nome: "nome", descricao: "descricao", area : area, version: 1)
		especialidade.save()
		return especialidade
	}

	@Override
	protected String entityInstanceName(){
		return "especialidade"
	}

	@Override
	protected Class entityClass(){
		return Especialidade.class
	}
}
