package ar.uba.fi.tdd.rulogic.model;

import java.util.Map;
import java.util.HashMap;
  
public class ConcreteDb implements Db{
	private HashMap<String, HashMap<String, String[]> > db;

	//este metodo retorna la key con la que se guarda la tupla
	private String tupleToString(String[] tuple){
		String concanated=new String(); 
		for(String s: tuple){
			concanated+=s;
		}
		return concanated;
	}

	public ConcreteDb(){
		db=new HashMap<String, HashMap<String, String[]> >();
	}

	@Override
	public  boolean containsFact(String name, String[] tuple){
		HashMap<String, String[]> fact=db.get(name);
		if (fact==null){
			return false;
		}
		return fact.containsKey(tupleToString(tuple));
	}

	@Override
	public void addFact(String name, String[] tuple){
		HashMap<String, String[]> fact=db.get(name);
		if (fact==null){
			fact=new HashMap<String, String[]>();
			db.put(name, fact);
		}
		fact.put(tupleToString(tuple), tuple.clone());
	}
}
