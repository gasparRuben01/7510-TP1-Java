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
		String[] tuple={"juan", "maria"};
		db.addFact("amigos", tuple);
		db.addFact("vecinos", tuple);
	}

	//start tests for RuleFact ----
	@Test
	public void ruleFact_shouldReturnTrue_When_isEvaluatedWhithAExistingFact_Test(){
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
	}
	//end tests for RuleFAct----
	
	//start tests for RuleAnd ----
	@Test
	public void ruleAnd_shouldReturnTrue_When_isEvaluatedWhitAllTrueRuleFacts_Test(){
		int[] order={0, 1};
		RuleFact rule1=new RuleFact("amigos", order);
		rule1.setDb(db);
		RuleFact rule2=new RuleFact("vecinos", order);
		rule2.setDb(db);
		RuleAnd ruleAnd=new RuleAnd("rule");
		ruleAnd.and(rule1);
		ruleAnd.and(rule2);
		String[] tuple={"juan", "maria"};
		Assert.assertTrue(rule1.evaluate(tuple));
	}

	//end tests for RuleAnd ---
}





