package ar.uba.fi.tdd.rulogic.model;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.TreeMap;
import java.util.regex.*;

public class ParserTest{
	@Test
	public void parseFact_Should_returnAFact() throws Exception{
		String fact="varon(juan).";
		TreeMap<String, String[]> parsed=Parser.parseFact(fact);
		String name=parsed.firstKey();
		String[] vars=parsed.get(name);
		Assert.assertTrue(name.equals("varon"));
		Assert.assertTrue(vars.length==1 && vars[0].equals("juan"));
	}
	 
	@Test
	public void parseFact_Should_returnRule() throws Exception{
		String in="rule(X,Y):-varon(X), varon(Y).";
		Rule r=Parser.parseRule(in);
		Assert.assertTrue(r.getName().equals("rule"));
	}
}
			

	
