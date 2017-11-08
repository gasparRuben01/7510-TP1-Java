package ar.uba.fi.tdd.rulogic.model;

public class RuleFactBuilder{
	protected String name=null;
	protected String[] vars=null;
	private int[] paramsOrder=null;

	public void setName(String name){
		this.name=name;
	}

	public void setVars(String[] vars){
		this.vars=vars.clone();
	}

	public void setParamOrder(String[] parameters) throws Exception{
		paramsOrder=new int[parameters.length];

		if(parameters.length>vars.length){
			throw new Exception();
		}

		boolean paramNoFind=true;
		int var_index=0;
		int param_index=0;

		for(String param: parameters){
			var_index=0;
			for(String var: vars){
				if(param.equals(var)){
					paramsOrder[param_index]=var_index;
					paramNoFind=false;
					break;
				}
				++var_index;
			}
			++param_index;
		}

		if (paramNoFind){
			throw new Exception();
		}
	}

	public RuleFact build() throws Exception{
		if(name==null || vars==null || paramsOrder==null){
			throw new Exception();
		}
		return new RuleFact(name, paramsOrder);
	}	
}
