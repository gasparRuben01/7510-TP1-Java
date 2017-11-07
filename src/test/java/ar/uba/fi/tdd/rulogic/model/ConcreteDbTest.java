package ar.uba.fi.tdd.rulogic.model;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import java.util.Set;
import java.util.HashSet;

public class ConcreteDbTest{
	@Test
	public void should_returnTrue_When_AskedForAFactThatContains(){
		ConcreteDb db=new ConcreteDb();
		String[] tuple={"juan", "julio"};
		db.addFact("amigos", tuple);
		Assert.assertTrue(db.containsFact("amigos", tuple));
	}

	@Test
	public void should_returnFalse_When_AskedForAFactThatNoContains(){
		ConcreteDb db=new ConcreteDb();
		String[] tuple={"juan", "julio"};
		db.addFact("amigos", tuple);
		tuple[0]="otra";
		tuple[1]="cosa";
		Assert.assertFalse(db.containsFact("amigos", tuple));
	}

}
