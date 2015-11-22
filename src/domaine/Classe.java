/**
 * 
 */
package domaine;

/**
 * 
 * fst = premiere classe, snd= 2eme classe
 * @author Tchioben
 *
 */
public enum Classe {

	fst("1ere Classe"),
	snd("2eme Classe");
	
	private String classe;
	
	
	Classe(String name){
		this.classe= name;
	}
	
	public String toString(){
		return this.name();
	}

	/**
	 * @return the classe
	 */
	public String getClasse() {
		return classe;
	}

	/**
	 * @param classe the classe to set
	 */
	public void setClasse(String classe) {
		this.classe = classe;
	}
}
