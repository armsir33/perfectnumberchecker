package com.digitalriver.integrationtest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import com.digitalriver.client.ClientMain;
import com.digitalriver.configs.AppConfig;
import com.digitalriver.models.PerfectNumberReply;

import junit.framework.Assert;

public class IntegrationTest {

	Server server;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		server = new Server(8080);
		// Register and map the dispatcher servlet
		final ServletHolder servletHolder = new ServletHolder(new CXFServlet());
		final ServletContextHandler context = new ServletContextHandler();
		context.setContextPath("/");
		context.addServlet(servletHolder, "/rest/*");
		context.addEventListener(new ContextLoaderListener());
		context.setInitParameter("contextClass", AnnotationConfigWebApplicationContext.class.getName());
		context.setInitParameter("contextConfigLocation", AppConfig.class.getName());
		server.setHandler(context);
		server.start();
//		server.join();
	}

	@After
	public void tearDown() throws Exception {
		server.stop();
	}

	@Test
	public void testRest() {
		List<Object> providers = new ArrayList<Object>();
		providers.add(new JacksonJaxbJsonProvider());

		WebClient client = WebClient.create("http://localhost:8080/rest/api", providers);
		
		PerfectNumberReply reply = ClientMain.invokePerfectNumberChecking(client, 28);
		Assert.assertEquals("28", reply.getRequestNo());
		Assert.assertEquals(true, reply.isResult());
		
		client.reset();
		
		Collection<Long> collection = ClientMain.invokeRangeNumberPerfectNumberChecking(client, 2, 10000);
		Assert.assertFalse(collection.isEmpty());
		Assert.assertEquals(4, collection.size());
	}

}
