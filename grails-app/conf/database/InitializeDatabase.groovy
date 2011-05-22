package database



import com.methone.Area
import com.methone.Especialidade;
import com.methone.Parceiro
import com.methone.enumeration.Interesse

/**
 *  Entidade responsavel pela carga inicial do banco de dados
 */
class InitializeDatabase {
	def parceiroService

	public void init(){
		createUser()
		def list = createArea()
		createEspecialidade(list)
	}

	/**
	 * Cria um usuario default para a aplicacao
	 */
	private createUser(){
		Parceiro parceiro = new Parceiro(username : "usuario", email : "teste@teste.com", password: "senha",
			nome:"nome", telefone: "(11) 1111-1111", endereco : "Rua A 22 Centro",
			cep : "11111-000", estado: "RJ", cidade : "Rio de Janeiro", interesse: Interesse.AMBOS)
		parceiroService.create(parceiro)
	}

	/**
	 * Cria areas default para a aplicacao
	 */
	private createArea(){
		def areaList = ["Websites, TI e Software","Celulares e informatica","Escrita e conteudo", "Design, Media & Arquitetura",
			"Entrada de dados e Administracao", "Engenharia e Ciencia", "Procura de Produtos e Produtor", "Marketing e Vendas",
			"Empresas, Contabilidade, Recursos Humanos e Juridico", "Outros"]
		Area area = null
		List<Area> list = new ArrayList<Area>()
		for (nomeArea in areaList) {
		   area = new Area(nome: nomeArea, descricao: nomeArea)
		   area.save()
		   list.add(area)
		}
	   return list
	}

	/**
	 *  Cria especialidades dafault para a aplicacao
	 */
	private createEspecialidade(List<Area> areaList){
		Especialidade especialidade = null
		def numeroEspecialidade = 15
		def nomeEspecialidade = null
		for (area in areaList) {
			while (numeroEspecialidade-- > 0) {
				nomeEspecialidade = "especialidade " + numeroEspecialidade
				especialidade = new Especialidade(nome:  nomeEspecialidade, descricao: nomeEspecialidade)
				especialidade.area = area
				especialidade.save()
			}
			numeroEspecialidade = 15
		}
	}

}
