package com.dataart.store.auxiliary;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.GenericFilterBean;

public class HeaderFilter extends GenericFilterBean
{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		((HttpServletResponse)response).setHeader("Pragma", "no-cache");
		((HttpServletResponse)response).setHeader("Cache-control", "no-cache, no-store, must-revalidate");
		((HttpServletResponse)response).setHeader("Expires", "0");
		chain.doFilter(request, response);
	}
}
