package com.methone


import org.grails.plugins.imagetools.*
import org.springframework.web.multipart.MultipartFile


/**
 *
 * Regras de negocio de parceiro
 * Esse bean deve ser declarado no resources.groovy porque existe configuracoes
 * especificas do ambiente
 *
 */
class ParceiroService {
	static transactional = true
	String diretorioImagem
	String diretorioImagemRelativo
	long tamanhoMaximoImagem = 512000 // (512000 bytes = 500 KB)
	def springSecurityService
	def imageService

	/**
	 * Cria novo parceiro e autentica o mesmo no sistema.
	 * Metodo usado durante o cadastro de parceiro
	 * @param parceiro parceiro a ser salvo
	 * @return Retorna true caso o parceiro seja salvo com sucesso. Caso contrario false.
	 */
	public boolean create(Parceiro parceiro) {
		// TODO criar um servico de email para notificar o usuario
		if (parceiro != null && parceiro.validate()) {
			String password = parceiro.password
			parceiro.password = springSecurityService.encodePassword(parceiro.password)
			parceiro.save(flush: true)
			springSecurityService.reauthenticate(parceiro.username, password)
			return true
		}
		return false
	}

	/**
	 *
	 * @return Retorna o usuario corrente da aplicacao
	 */
	public Parceiro getCurrentUser(){
		def currentUser = springSecurityService.getPrincipal()
		if(currentUser != null && currentUser.id != null){
			return Parceiro.get(currentUser.id)
		}
		return null
	}

	/**
	 * Atualiza detalhes do parceiro
	 * @param parceiro parceiro a ser atualizado
	 * @param nomeImagem nome da imagem
	 * @param imagem foto do parceiro
	 * @return Retorna true caso seja bem sucedida a atualizacao
	 */
	public boolean updateDetalheParceiro(Parceiro parceiro, String nomeImagem, MultipartFile imagem){
		if(parceiro == null || parceiro.id == null){
			return false
		}
		return uploadImagemParceiro(parceiro, nomeImagem, imagem) && !parceiro.hasErrors() && parceiro.save(flush: true)
	}

	/**
	 * Realiza upload da imagem do parceiro
	 * @param parceiro parceiro com a imagem
	 * @param nomeImagem nome da imagem
	 * @param imagem foto do parceiro
	 * @return Retorna true caso a imagem seja tranferida com sucesso. Caso contrario false.
	 */
	private boolean uploadImagemParceiro(Parceiro parceiro, String nomeImagem, MultipartFile imagem){
		if(imagem != null && !imagem.empty && nomeImagem != null){
			if(!imageService.extensaoValida(nomeImagem)){
				parceiro.errors.rejectValue "urlImagem", "extensaoArquivoInvalida"
				return false
			} else if(!imageService.tamanhoImagemValido(imagem,tamanhoMaximoImagem)){
			    parceiro.errors.rejectValue "urlImagem", "tamanhoArquivoInvalido"
			    return false
			}
			if(parceiro.urlImagem){
				// apaga a imagem antiga caso exista
				imageService.deleteImage(diretorioImagem, parceiro.urlImagem)
			}
			def nome = "imagem_" + parceiro.id + "_" + nomeImagem
			imageService.saveImage(diretorioImagem,nome,imagem)
			parceiro.urlImagem = nome
		}
		return true
	}


}
