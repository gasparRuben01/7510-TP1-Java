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

		for(int param_index=0; param_index<parameters.length; param_index++){
			for(int var_index=0; var_index<vars.length; var_index++){
				if(parameters[param_index].equals(vars[var_index])){
					paramsOrder[param_index]=var_index;
					paramNoFind=false;
					break;
				}
			}
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
