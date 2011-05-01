package com.methone.enumeration


/**
 *
 * Enum com os tipos de interesse de um parceiro
 *
 */
enum Interesse {
	CONTRATAR("contratar"),
	SER_CONTRATADO("serContratado"),
	AMBOS("contratarSerContratado")

	def bundleKey

	Interesse(bundleKey){
		this.bundleKey = bundleKey;
	}


}
