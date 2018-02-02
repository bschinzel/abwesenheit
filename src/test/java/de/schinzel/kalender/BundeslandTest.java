package de.schinzel.kalender;

import javax.naming.InitialContext;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.wildfly.swarm.arquillian.DefaultDeployment;

@RunWith(Arquillian.class)
@DefaultDeployment(type = DefaultDeployment.Type.JAR)
public class BundeslandTest {

	@ArquillianResource
	private InitialContext context;
	
	@Test
	public void test() {
		Assert.fail();
	}
}
