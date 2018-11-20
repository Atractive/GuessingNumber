package com.mycompany.guessingnumber;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


public class CountingUsers implements HttpSessionListener {

    private int numberOfUsers = 0;
    
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        numberOfUsers++;
        se.getSession().getServletContext().setAttribute("numberConnected",numberOfUsers);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        numberOfUsers--;
        se.getSession().getServletContext().setAttribute("numberConnected",numberOfUsers);
    }
    
}
