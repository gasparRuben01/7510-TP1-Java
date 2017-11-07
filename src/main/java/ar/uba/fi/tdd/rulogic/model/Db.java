package ar.uba.fi.tdd.rulogic.model;

public interface Db{
	//name es el nombre del fact y tuple los relacionados por el fact
	public boolean containsFact(String name, String[] tuple); 
	public void addFact(String name, String[] tuple);
}

