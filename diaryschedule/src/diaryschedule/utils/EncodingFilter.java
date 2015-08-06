package diaryschedule.utils;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import diaryschedule.dataBean.UserLoginInfo;

/**
 * 
 * @author caiyao 
 *
 * @function 设置请求参数的编码格式
 */
@WebFilter(filterName="encodingFilter",urlPatterns="/*",
dispatcherTypes={DispatcherType.REQUEST,DispatcherType.FORWARD,DispatcherType.ERROR,DispatcherType.INCLUDE})
public class EncodingFilter implements Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)arg0 ;
		request.setCharacterEncoding("utf-8");
		arg2.doFilter(arg0, arg1) ;
		arg1.setCharacterEncoding("utf-8");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
