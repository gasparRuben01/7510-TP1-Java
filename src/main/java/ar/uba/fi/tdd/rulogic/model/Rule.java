package ar.uba.fi.tdd.rulogic.model;

public abstract class Rule{
	protected Db db=null;
	private String name;

	//nombre de la regla
	public Rule(String name){
		this.name=name;
	}

	public abstract boolean evaluate(String[] tuple) throws Exception;
	
	//se evalua consultando en la db pasada por parametro y se ignora la seteada como atributo
	public boolean evaluate(String[] tuple, Db otherdb) throws Exception{
		Db mineDb=this.db;
		this.db=otherdb;
		boolean result=evaluate(tuple);
		this.db=mineDb;
		return result;
	}

	public String getName(){
		return name;
	}

	//una rule se evalua en contexto de una db
	public void setDb(Db db){
		this.db=db;
	}

	public Db getDb(){
		return db;
	}
}
