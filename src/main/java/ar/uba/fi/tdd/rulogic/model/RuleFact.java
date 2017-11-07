package ar.uba.fi.tdd.rulogic.model;

public class SingleRule extends Rule{
	private int[] orderOfVariables;

	//name es el nombre del fact a buscar en db y orderOfVariables es el orden que se va a evluar los parametros del fact
	public SingleRule(String name, int[] orderOfVariables){
		super(name);
		this.orderOfVariables=orderOfVariables.clone();
	}

	@Override
	public boolean evaluate(String[] tuple){
		if(db==null){
			return false;
		}
		String[] parameters=new String[tuple.length];
		int i=0;
		for(int var: orderOfVariables){
			parameters[i++]=tuple[var];
		}
		return db.containsFact(getName(), parameters);
	}
}

