package ar.uba.fi.tdd.rulogic.model;

import java.io.*;
import java.util.TreeMap;
import java.util.ArrayList;


public class KnowledgeBase {
	Db db=null;
	ArrayList rules=null;

	public void loadDb(String dbpath) throws Exception{
		db=new ConcreteDb();
		rules=new ArrayList();
		BufferedReader br=new BufferedReader(new FileReader(dbpath));
		for(String line; (line=br.readLine())!=null;){
			if(Parser.isFact(line)){
				TreeMap fact=Parser.parseFact(line);
				String name=fact.firstKey();
				String[] tuple=fact.get(name);
				db.addFact(name, tuple);
			}else{
				rules.add(Parser.parseRule(line));
			}
		}
	}





	public boolean answer(String query) {
		return true;
	}

}
