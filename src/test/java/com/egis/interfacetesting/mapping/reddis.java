package com.egis.interfacetesting.mapping;

import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.consol.citrus.dsl.testng.TestNGCitrusTestDesigner;

import redis.embedded.RedisServer;




public class reddis extends TestNGCitrusTestDesigner{
	public RedisServer server;
	
	@BeforeSuite
	public void setupReddis() throws IOException {
		server = new RedisServer(6379);
		server.start();			
	}
	
	@AfterSuite
	public void teardown() {
		server.stop();		
	}

}
