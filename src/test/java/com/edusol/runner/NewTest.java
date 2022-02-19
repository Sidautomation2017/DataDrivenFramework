package com.edusol.runner;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.edusol.listeners.RetryFailure;

public class NewTest {
	
	//fromTestNGRunner class
	//from testnG.XML
  @Test()
  public void test1() {
	  System.out.println("I am in test1");
	  Assert.assertTrue(false);
	  System.out.println("I am at test1 end");
  }
  
  
  @Test ()
  public void test2() {
	  System.out.println("I am in test2");
  }
  
}
