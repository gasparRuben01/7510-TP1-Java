package ar.uba.fi.tdd.rulogic.model;

import java.io.*;
import java.util.TreeMap;
import java.util.HashMap;


public class KnowledgeBase {
	Db db=null;
	HashMap<String, Rule> rules=null;

	//carga la base de datos
	public void loadDb(FileReader file) throws Exception{
		db=new ConcreteDb();
		rules=new HashMap<String, Rule>();
		BufferedReader br=new BufferedReader(file);
		for(String line; (line=br.readLine())!=null;){
			if(Parser.isFact(line)){
				TreeMap<String, String[]> fact=Parser.parseFact(line);
				String name=fact.firstKey();
				String[] tuple=fact.get(name);
				db.addFact(name, tuple);
			}else{
				Rule r=Parser.parseRule(line);
				r.setDb(db);
				rules.put(r.getName(), r);
			}
		}
	}


	//carga la base de datos
	public void loadDb(String dbpath) throws Exception{
		loadDb(new FileReader(dbpath));
	}

	public boolean answer(String query) throws Exception{
		//query tiene la misma estrcutura que fact
		TreeMap<String, String[]> fact=Parser.parseFact(query);
		String name=fact.firstKey();
		String[] tuple=fact.get(name);
		if(db.containsFact(name, tuple)){
			return true;
		}
		Rule r=rules.get(name);
		if(r!=null){
			return r.evaluate(tuple);
		}
		return false;
	}

}
