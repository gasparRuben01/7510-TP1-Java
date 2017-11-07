package ar.uba.fi.tdd.rulogic.model;
import java.util.ArrayList;
import java.util.Iterator;

public class RuleAnd extends Rule{
	private ArrayList<Rule> rules=new ArrayList<Rule>();

	public RuleAnd(String name){
		super(name);
	}

	//agrega una rule para evaluar en conjuncion con las otras
	public void and(Rule rule){
		rules.add(rule);
	}

	@Override
	public boolean evaluate(String[] tuple){
		boolean result=false;
		for(Rule rule: rules){
			result= db!=null ? rule.evaluate(tuple, db) : rule.evaluate(tuple);
			if(!result){
				return false;
			}
		}
		return result;
	}
}

