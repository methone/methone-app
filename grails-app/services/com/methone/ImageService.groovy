package com.methone

import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.grails.plugins.imagetools.*

/**
 *
 * Servicos para manipulacao de imagens
 *
 */
class ImageService {
	def extensaoPermitidasList = ["gif", "jpg", "jpeg", "png"]
	static transactional = false

	/**
	 * Realiza a validacao da extensao do arquivo (imagem)
	 * @param nomeImagem nome da imagem
	 * @return Retorna true caso a extensao do arquivo seja valida. Caso contrario false.
	 */
	public boolean extensaoValida(String nomeImagem){
		String extensao = getExtensao(nomeImagem)
		return extensaoPermitidasList.contains(extensao)
	}

	/**
	 *
	 * @param nomeImagem nome da imagem
	 * @return Retorna extensao da imagem.
	 */
	public String getExtensao(String nomeImagem){
		if(nomeImagem == null || !nomeImagem.contains(".")){
			return ""
		}
		return nomeImagem.substring(nomeImagem.lastIndexOf("."), nomeImagem.length()).toLowerCase().replace(".","")
	}

	/**
	 * Grava uma imagem
	 * @param diretorio diretorio onde a imagem sera salva
	 * @param nomeImagem nome do arquivo
	 * @param imagem que sera salva
	 *
	 */
	public void saveImage(String diretorio, String nomeImagem, CommonsMultipartFile imagem){
		// TODO Verificar possibilidade de usar o imageTool para redimencionar  a imagem
		if(!imagem.empty){
			imagem.transferTo(new File(diretorio + nomeImagem))
		}
	}

	/**
	 * Exclui uma imagem
	 * @param diretorio diretorio onde a imagem sera salva
	 * @param nomeImagem nome do arquivo
	 */
	public void deleteImage(String diretorio, String nomeImagem){
		def file = new File(diretorio + nomeImagem)
		file.delete();
	}
}
