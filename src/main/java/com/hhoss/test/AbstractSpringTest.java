package com.hhoss.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@RunWith(SpringJUnitConfigRunner.class)
@ContextConfiguration({"classpath*:res/**/spring/*.xml","classpath*:res/**/spring-*.xml"})
@Deprecated 
/**@Deprecated using SprintTest*/

public abstract class AbstractSpringTest extends JUnitTest {
	public static void main(String[] args){
		logger.info("test of AbstractSpringTest");
	}

}
