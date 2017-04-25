package br.edu.pazin.controle;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import org.hibernate.SessionFactory;
import br.edu.pazin.dao.HibernateUtil;

/**
 *
 * @author apazi
 */
@WebFilter(filterName = "FiltroTransacaoBD", urlPatterns = {"/*"})
public class FiltroTransacaoBD implements Filter{

    private SessionFactory sf;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.sf= HibernateUtil.getSessionFactory();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try{
            this.sf.getCurrentSession().beginTransaction();
            chain.doFilter(request, response);
            this.sf.getCurrentSession().getTransaction().commit();
        } catch(Exception e){
            if(this.sf.getCurrentSession().getTransaction()!=null
                && this.sf.getCurrentSession().getTransaction().isActive()){
                this.sf.getCurrentSession().getTransaction().rollback();
            }
        }
        
    }

    @Override
    public void destroy() {
        this.sf.getCurrentSession().close();
    }

}