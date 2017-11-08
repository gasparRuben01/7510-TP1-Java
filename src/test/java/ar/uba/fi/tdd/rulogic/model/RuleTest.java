package ar.uba.fi.tdd.rulogic.model;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class RuleTest{
	public static Db db=new ConcreteDb();
	@BeforeClass
	public static void InitializeDb(){
		String[] tuple1={"juan", "maria"};
		String[] tuple2={"maria", "roberto"};
		db.addFact("amigos", tuple1);
		db.addFact("amigos", tuple2);
		db.addFact("vecinos", tuple1);
	}

	//start tests for RuleFact ----
	@Test
	public void ruleFact_shouldReturnTrue_When_isEvaluatedWhithAExistingFact_Test() throws Exception{
		int[] order={1, 0};
		RuleFact rule=new RuleFact("amigos", order);
		rule.setDb(db);
		String[] tuple={"maria", "juan"};
		Assert.assertTrue(rule.evaluate(tuple));
		order[0]=0;
		order[1]=1;
		tuple[0]="juan";
		tuple[1]="maria";
		rule=new RuleFact("vecinos", order);
		rule.setDb(db);
		Assert.assertTrue(rule.evaluate(tuple));
		Assert.assertTrue(rule.evaluate(tuple, db));
		//Evaluo a ruleFact con una tupla mas grande, pero como ruleFact solo toma las indicas por order={1,0}
		//solo usa las dos elementos en la evaluación ignorando el tercero
		String[] tuple2={"juan", "maria", "Valor ignorado por Rule"};
		Assert.assertTrue(rule.evaluate(tuple2));
	}

	@Test
	public void ruleFactBuilder_shouldReturnACorrectRuleTest() throws Exception{
		RuleFactBuilder b=new RuleFactBuilder();
		b.setName("amigos");
		String[] vars={"X", "Y"};
		b.setVars(vars);
		vars[0]="Y";
		vars[1]="X";
		b.setParamOrder(vars);
		RuleFact r=b.build();
		r.setDb(db);
		String[] p={"maria", "juan"};
		Assert.assertTrue(r.evaluate(p));
	}

	//end tests for RuleFAct----
	
	//start tests for RuleAnd ----
	@Test
	public void ruleAnd_shouldReturnTrue_When_isEvaluatedWhitAllTrueRuleFacts_Test() throws Exception{
		int[] order={0, 1};
		RuleFact rule1=new RuleFact("amigos", order);
		RuleFact rule2=new RuleFact("vecinos", order);
		RuleAnd ruleAnd=new RuleAnd("rule");
		ruleAnd.and(rule1);
		ruleAnd.and(rule2);
		ruleAnd.setDb(db);
		String[] tuple={"juan", "maria"};
		Assert.assertTrue(ruleAnd.evaluate(tuple));
	}

	@Test
	public void parserRule_shouldReturnARuleWhichEvaluateTrue_Test() throws Exception{
		RuleAnd r=Parser.parseRule("comprades(X,Y):- amigos(Y,X), vecinos(Y, X).");
		r.setDb(db);
		String[] tuple1={"maria", "juan"};
		Assert.assertTrue(r.evaluate(tuple1));
		r=Parser.parseRule("amigoDeamigo(X, Y, Z) :- amigos(X,Y), amigos(Y,Z).");
		String[] tuple2={"juan", "maria", "roberto"};
		r.setDb(db);
		Assert.assertTrue(r.evaluate(tuple2));
	}

	//end tests for RuleAnd ---
}





