package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;

import constantes.Constantes;
import model.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageFilter implements Filter {

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpSession sessao = ((HttpServletRequest) request).getSession();
		Usuario user = (Usuario) sessao.getAttribute(Constantes.KEY_SESSION_USUARIO_LOGADO);
		String newCurrentPage = ((HttpServletRequest) request).getRequestURI();
		  
		if(Constantes.PAGINA_LOGIN_APLICACAO.equals(newCurrentPage)){
			chain.doFilter(request, response);
		} else if(user != null && user.getLogin() != null) {
			chain.doFilter(request, response);
		} else {
			sessao.setAttribute("message", "É obrigatório realizar o login.");
			String contextPath = ((HttpServletRequest) request).getContextPath();
			((HttpServletResponse) response).sendRedirect(contextPath + Constantes.PAGINA_REDIRECIONAMENTO_LOGIN);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
