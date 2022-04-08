package com.hhoss.test;

import junit.framework.TestCase;

import org.junit.Test;

import com.hhoss.jour.Logger;

public abstract class JUnitTest extends TestCase {
	protected static final Logger logger = Logger.get();
	
	public static void main(String[] args){
		new JUnitTest(){
			public void test(){
				logger.info("test of "+getClass().getName());
			}
		}.test();
	}
	/*
	 * subclass should implement for test, subMethod needn't added the annotation again.
	 */
	@Test
	public abstract void test();
}
