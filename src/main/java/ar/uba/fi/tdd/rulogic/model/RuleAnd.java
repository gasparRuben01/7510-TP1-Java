package ar.uba.fi.tdd.rulogic.model;
import java.util.ArrayList;

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
		boolean result=true;
		for(Rule rule: rules){
			if(db!=null){
				result&=rule.evaluate(tuple,db);
			}else{
				result&=rule.evaluate(tuple);
			}
		}
		return result;
	}
}

