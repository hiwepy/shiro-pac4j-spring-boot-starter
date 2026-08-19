package org.apache.shiro.spring.boot.pac4j;

import io.buji.pac4j.filter.CallbackFilter;
import io.buji.pac4j.filter.LogoutFilter;
import io.buji.pac4j.filter.SecurityFilter;
import org.apache.shiro.biz.spring.ShiroFilterProxyFactoryBean;
import org.apache.shiro.spring.boot.utils.JakartaFilterAdapter;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.BeansException;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.ObjectUtils;

import javax.servlet.Filter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Auto-configuration for ShiroPac4jFilterFactoryBean.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */
@SuppressWarnings("rawtypes")
public class ShiroPac4jFilterFactoryBean extends ShiroFilterProxyFactoryBean implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	/**
	 * Returns the application context.
	 *
	 * @return the application context
	 */
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public ShiroPac4jFilterFactoryBean() {
	}

	protected boolean supports(Object filter) {
		// Unwrap JakartaFilterAdapter if present
		if (filter instanceof JakartaFilterAdapter) {
			filter = ((JakartaFilterAdapter) filter).getDelegate();
		}
		return filter instanceof AccessControlFilter || filter instanceof org.apache.shiro.web.filter.authc.LogoutFilter
				|| filter instanceof SecurityFilter || filter instanceof CallbackFilter || filter instanceof LogoutFilter;
	}
	
	// 过滤器链：实现对路径规则的拦截过滤
	@Override
	public Map<String, Filter> getFilters() {

		Map<String, Filter> filters = new LinkedHashMap<String, Filter>();

		Map<String, FilterRegistrationBean> beansOfType = getApplicationContext().getBeansOfType(FilterRegistrationBean.class);
		if (!ObjectUtils.isEmpty(beansOfType)) {
			Iterator<Entry<String, FilterRegistrationBean>> ite = beansOfType.entrySet().iterator();
			while (ite.hasNext()) {
				Entry<String, FilterRegistrationBean> entry = ite.next();
				Object filterObj = entry.getValue().getFilter();
				if (this.supports(filterObj)) {
					// Unwrap JakartaFilterAdapter to get the original javax.servlet.Filter
					if (filterObj instanceof JakartaFilterAdapter) {
						filterObj = ((JakartaFilterAdapter) filterObj).getDelegate();
					}
					if (filterObj instanceof Filter) {
						filters.put(entry.getKey(), (Filter) filterObj);
					}
				}
			}
		}

		filters.putAll(super.getFilters());

		return filters;

	}

	@Override
	/**
	 * Sets the application context.
	 *
	 * @param applicationContext the application context
	 * @throws BeansException if an error occurs
	 */
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
