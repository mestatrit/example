package util.hibernate;



import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class HibernateSessionFilter implements Filter {

   public void destroy() {
       // TODO Auto-generated method stub

    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HibernateSessionFactory.getSession();
       HibernateSessionFactory.openTransaction();
       try {
            chain.doFilter(request, response);
            
            HibernateSessionFactory.commitTransaction();
        } catch (Exception e) {
            HibernateSessionFactory.RollbackTransaction();
            e.printStackTrace();
       } finally {
            HibernateSessionFactory.closeSession();
        }
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

}