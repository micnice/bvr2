package zw.co.micnice.logic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import zw.co.micnice.logic.configuration.EmploymentConfiguration;


/**
 *
 *@author Clive Gurure 
 */
public class App_1 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-application-context.xml");
        context.getBean(TestClass.class).printBasicPay();
        
        
    }
}
