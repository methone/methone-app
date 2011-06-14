package com.methone

import org.grails.plugins.imagetools.*
import org.springframework.web.multipart.MultipartFile

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
	 * @throws IOException caso o diretorio nao exista
	 */
	public void saveImage(String diretorio, String nomeImagem, MultipartFile imagem){
		// TODO Verificar possibilidade de usar o imageTool para redimencionar  a imagem
		if(imagem != null && !imagem.empty){
			imagem.transferTo(new File(diretorio + nomeImagem))
		}
	}

	/**
	 * Exclui uma imagem
	 * @param diretorio diretorio onde a imagem sera salva
	 * @param nomeImagem nome do arquivo
	 * @throws IOException caso o diretorio nao exista
	 */
	public void deleteImage(String diretorio, String nomeImagem){
		def file = new File(diretorio + nomeImagem)
		file.delete();
	}

	/**
	 * Verifica se o tamanho da imagem eh valida
	 * @param imagem imagem a ser validada
	 * @param tamanhoMax tamanho maximo da imagem em bytes
	 * @return retorna true caso a imagem tenha um tamanho valido. Caso contrario false.
	 */
	public boolean tamanhoImagemValido(MultipartFile imagem, long tamanhoMax) {
		if (imagem == null || imagem.getSize() > tamanhoMax || imagem.getSize() == 0) {
			return false;
		}
		return true;
	}
}
