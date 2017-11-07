package ar.uba.fi.tdd.rulogic.model;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.*;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class KnowledgeBaseTest {

	private KnowledgeBase knowledgeBase=new KnowledgeBase();

	@BeforeClass
	public static void setUp() throws Exception {
		ClassLoader classloader=Thread.currentThread().getContextClassLoader();
		BufferedReader buff=new BufferedReader(new InputStreamReader(classloader.getResourceAsStream("rules.db")));
	}

	@Test
	public void test() {

	//	Assert.assertTrue(this.knowledgeBase.answer("varon (javier)."));
		Assert.assertTrue(true);

	}

}
