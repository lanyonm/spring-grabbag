package org.lanyonm.grabbag.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Dynamic view resolver.
 * 
 * @author lanyonm
 *
 */
@Component
public class ViewResolver extends InternalResourceViewResolver {
	
	/**
	 * Use '/WEB-INF/views/' for the prefix and '.jsp' for the suffix.
	 */
	public ViewResolver() {
		super();
		setPrefix("/WEB-INF/views/");
		setSuffix(".jsp");
	}

	@Override
	protected AbstractUrlBasedView buildView(String viewName) throws Exception {
		if (viewName.isEmpty() || viewName.endsWith("/")) {
			viewName += "index";
		}
		return super.buildView(viewName);
	}

}
