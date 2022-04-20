package com.hhoss.test;

import org.junit.runners.model.InitializationError;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hhoss.boot.App;

public class SpringJUnitConfigRunner extends SpringJUnit4ClassRunner {
	static {App.initial();}
	public SpringJUnitConfigRunner(Class<?> clazz) throws InitializationError {
		super(clazz);
	}

}
