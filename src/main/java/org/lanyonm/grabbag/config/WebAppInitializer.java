package org.lanyonm.grabbag.config;

import java.util.Set;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;

import com.opensymphony.sitemesh.webapp.SiteMeshFilter;

/**
 * Inspired by http://rockhoppertech.com/blog/spring-mvc-configuration-without-xml/
 * 
 * @author LanyonM
 */
public class WebAppInitializer implements WebApplicationInitializer {

	private static final Logger log = LoggerFactory.getLogger(WebAppInitializer.class);

	public void onStartup(ServletContext servletContext) throws ServletException {
		// Create the 'root' Spring application context
		AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();
		root.scan("org.lanyonm.grabbag");
		root.getEnvironment().setDefaultProfiles("embedded");
		
		// Manage the lifecycle of the root appcontext
		servletContext.addListener(new ContextLoaderListener(root));
		servletContext.setInitParameter("defaultHtmlEscape", "true");
		
		// now the config for the Dispatcher servlet
		AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();
		mvcContext.register(WebConfig.class);
		
		// Filters - http://static.springsource.org/spring/docs/3.2.x/javadoc-api/org/springframework/web/filter/package-frame.html
		// Hidden Http Methods
//		FilterRegistration.Dynamic fr = servletContext.addFilter("hiddenHttpMethodFilter", new HiddenHttpMethodFilter());
//		fr.addMappingForUrlPatterns(null, true, "/*");

		// UTF-8 Encoding
		FilterRegistration.Dynamic fr = servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
		fr.setInitParameter("encoding", "UTF-8");
		fr.setInitParameter("forceEncoding", "true");
		fr.addMappingForUrlPatterns(null, true, "/*");
		
		// SiteMesh
		fr = servletContext.addFilter("sitemesh", new SiteMeshFilter());
		fr.addMappingForUrlPatterns(null, true, "/*");
		
		// The main Spring MVC servlet
		ServletRegistration.Dynamic dispatcherServlet = servletContext.addServlet(
				"dispatcher", new DispatcherServlet(new GenericWebApplicationContext()));
		dispatcherServlet.setLoadOnStartup(1);
		Set<String> mappingConflicts = dispatcherServlet.addMapping("/");
		if (!mappingConflicts.isEmpty()) {
			for (String s : mappingConflicts) {
				log.error("Mapping conflict: " + s);
			}
			throw new IllegalStateException("'dispatcher' could not be mapped to '/' due " +
					"to an existing mapping. This is a known issue under Tomcat versions " +
					"<= 7.0.14; see https://issues.apache.org/bugzilla/show_bug.cgi?id=51278");
		}
	}

}
