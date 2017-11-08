package ar.uba.fi.tdd.rulogic.model;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.*;
import java.io.*;

public class KnowledgeBaseTest {

	private static KnowledgeBase knowledgeBase=new KnowledgeBase();

	@BeforeClass
	public static void setUp() throws Exception {
		FileReader file=new FileReader(new File(KnowledgeBaseTest.class.getResource("/rules.db").toURI()));
		knowledgeBase.loadDb(file);
	}

	@Test
	public void test() throws Exception{
		Assert.assertTrue(this.knowledgeBase.answer("varon (juan)."));
		Assert.assertTrue(this.knowledgeBase.answer("varon (roberto)."));
		Assert.assertFalse(this.knowledgeBase.answer("varon (maria)."));
		Assert.assertTrue(this.knowledgeBase.answer("padre(juan, pepe)."));
		Assert.assertTrue(this.knowledgeBase.answer("varon(nicolas)."));
		Assert.assertTrue(this.knowledgeBase.answer("tio(nicolas, alejandro, roberto)."));
		Assert.assertTrue(this.knowledgeBase.answer("hermano(cecilia, juan)."));
		Assert.assertTrue(this.knowledgeBase.answer("tia(cecilia, pepe, juan)."));
		
	}


}
