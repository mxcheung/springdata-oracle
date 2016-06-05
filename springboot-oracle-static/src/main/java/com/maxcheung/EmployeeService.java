package com.maxcheung;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;


@Component
public class EmployeeService implements ApplicationListener ,InitializingBean, DisposableBean{
 
	private final CustomerReferenceGenerator customerReferenceGenerator;

	
	private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);
     
	@Autowired
    public EmployeeService(CustomerReferenceGenerator customerReferenceGenerator){
		this.customerReferenceGenerator = customerReferenceGenerator;
    	log.info("EmployeeService no-args constructor called");
    }
 
    @Override
    public void destroy() throws Exception {
    	log.info("EmployeeService Closing resources");
    }
 
    @Override
    public void afterPropertiesSet() throws Exception {
    	log.info("EmployeeService initializing to dummy value");
		Long nextReference = customerReferenceGenerator.generateCRN();
		log.info("Next Customer Reference : " + nextReference);

    }

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		   if (event instanceof ContextRefreshedEvent) {
	            ApplicationContext applicationContext = ((ContextRefreshedEvent) event).getApplicationContext();
	            // now you can do applicationContext.getBean(...)
	            // ...
	        	log.info("ContextRefreshedEvent ...");
	        }
		   
		   if (event instanceof ContextStartedEvent) {
	            ApplicationContext applicationContext = ((ContextRefreshedEvent) event).getApplicationContext();
	            // now you can do applicationContext.getBean(...)
	            // ...
	        	log.info("ContextStartedEvent ...");
	        }
		
	}
}