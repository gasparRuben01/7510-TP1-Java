package ar.uba.fi.tdd.rulogic.model;

import java.util.regex.*;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;
import java.io.*;


public class Parser{
	private static String stringToParse;
	private static String namePattern="^([A-Za-z]*)";
	private static String startOfVarsPattern="^(\\()";
	private static String separatorPattern="^(,)";
	private static String endOfVarsPattern="^(\\))";
	private static String ruleFlagString="(:-)";
	private static String ruleFlagPattern="^(:-)";
	private static String endPattern="^(\\.)$";
	//borrar
	private static String msg="";

	static void  setStringToParse(String string){
		Pattern p=Pattern.compile("\\s");
		Matcher m=p.matcher(string);
		stringToParse=m.replaceAll("");
	}

	static String chop(String pattern) throws Exception{
		Pattern p=Pattern.compile(pattern);
		Matcher matcher=p.matcher(stringToParse);
		if (!matcher.lookingAt()){
			throw new Exception(msg);
		}
		stringToParse=matcher.replaceFirst("");
		if(matcher.groupCount()>0){
			return matcher.group(1);
		}
		return null;
	}

	static boolean lookingAt(String pattern){
		Pattern p=Pattern.compile(pattern);
		Matcher matcher=p.matcher(stringToParse);
		return matcher.lookingAt();
	}

	static boolean find(String pattern){
		Pattern p=Pattern.compile(pattern);
		Matcher m=p.matcher(stringToParse);
		return m.find();
	}


	static String chopName() throws Exception{
		try{
			return chop(namePattern);
		}catch(Exception e){throw e;}
	}


	static boolean lastVar(){
		return lookingAt(endOfVarsPattern); 
	}

	static String chopVarSeparator() throws Exception{
		return chop(separatorPattern);
	}

	static boolean lastRule(){
		return lookingAt(endPattern);
	}

	static String chopRuleSeparator() throws Exception{
		return chop(separatorPattern);
	}

	static String[] chopVars() throws Exception{
		try{
			chop(startOfVarsPattern);
			List<String> vars=new ArrayList<String>();
			do{
				vars.add(chopName());
			}while(!lastVar() && chopVarSeparator()!=null);
			chop(endOfVarsPattern);
			return vars.toArray(new String[vars.size()]);
		}catch(Exception e){throw e;}
	}

	static Rule[] chopRules(String[] vars) throws Exception{
		try{
			msg=stringToParse;
			chop(ruleFlagPattern);
			RuleFactBuilder builder=new RuleFactBuilder();
			builder.setVars(vars);
			List<Rule> rules=new ArrayList<Rule>();
			do{
				builder.setName(chopName());
				builder.setParamOrder(chopVars());
				rules.add(builder.build());
			}while(!lastRule() && chopRuleSeparator()!=null);
			chop(endPattern);
			return rules.toArray(new Rule[rules.size()]);
		}catch(Exception e){throw e;}
	}

	static public boolean isFact(){
		return !find(ruleFlagString);
	}

	public static RuleAnd parseRule(String string) throws Exception{
		try{	
			setStringToParse(string);
			if(isFact()){
				throw new Exception("bad input");
			}
			String name=chopName();
			Rule[] rules=chopRules(chopVars());
			RuleAnd rule=new RuleAnd(name);
			for(Rule r: rules){
				rule.and(r);
			}
			return rule;
		}catch(Exception e){throw e;}
	}

	public static TreeMap<String, String[]> parseFact(String string) throws Exception{
		try{
			setStringToParse(string);
			if(!isFact()){
				throw new Exception("bad input");
			}
			TreeMap<String, String[]> fact=new TreeMap<String, String[]>();
			fact.put(chopName(), chopVars());	
			chop(endPattern);
			return fact;
		}catch(Exception e){throw e;}
	}
}

