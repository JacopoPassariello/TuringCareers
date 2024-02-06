package com.turing_careers.controller;

import com.turing_careers.data.DAO;
import com.turing_careers.data.model.Developer;
import com.turing_careers.data.model.Employer;
import jakarta.persistence.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@WebServlet(name = "AuthenticationServlet", value = "/AuthenticationServlet")
public class AuthenticationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authType = request.getParameter("authType");
        String userType = request.getParameter("userType");
        Boolean authOutcome = false;
        
        if (authType.equals("login")){
            authOutcome = this.loginUser(request, userType);
        } else if (authType.equals("register")) {
            authOutcome = this.registerUser(request, userType);
        }

        if (authOutcome){
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("authOutcome", "negative");
            if(authType.equals("login")) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            } else if (authType.equals("register")) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("subscription.jsp");
                dispatcher.forward(request, response);
            }
        }
    }

    private boolean loginUser(HttpServletRequest request, String userType) {
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");
        if (userType.equals("developer")) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("turing_careersPU");
            EntityManager em = emf.createEntityManager();
            List<Developer> d = null;
            try{
                d = em.createNamedQuery("findDevsByMailAndPassword", Developer.class).setParameter("mail", mail).setParameter("password", password).getResultList();
            } catch(NoResultException exception){
                System.out.println("No dev founded!!!");
                exception.printStackTrace();
                return false;
            }
            if (d == null || d.size() != 1)
                return false;
            Developer dev = d.get(0);
            HttpSession session = request.getSession();
            session.setAttribute("loggedIn", "true");
            session.setAttribute("user", dev);
            return true;
        } else if (userType.equals("employer")) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("turing_careersPU");
            EntityManager em = emf.createEntityManager();
            List<Employer> e = null;
            try {
                e = em.createNamedQuery("findEmplsByMailAndPassword", Employer.class).setParameter("mail", mail).setParameter("password", password).getResultList();
            } catch (NoResultException exception){
                System.out.println("No dev founded!!!");
                exception.printStackTrace();
                return false;
            }
            if (e == null || e.size() != 1)
                return false;
            Employer emp = e.get(0);
            HttpSession session = request.getSession();
            session.setAttribute("loggedIn", "true");
            session.setAttribute("user", emp);
            return true;
        }
        return false;
    }

    private boolean registerUser(HttpServletRequest request, String userType) {
        final String firstname = request.getParameter("firstname");
        final String lastname = request.getParameter("lastname");
        final String mail = request.getParameter("email");
        final String password = request.getParameter("password");

        //controlliamo mail e password passati dall'utente per verificare
        //che rispettino il giusto formato
        if (!validate(mail, password)) {
            return false;
        }

        if (userType.equals("developer")) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("turing_careersPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            Developer dev = new Developer();
            tx.begin();
            //strano che la persist non generi eccezioni???? come faccio a sapere che ha
            //avuto effettivamente successo???? devo controllare tramite query???
            em.persist(dev);
            tx.commit();
            //si può effettuare un controllo aggiuntivo cercando il dev per mail e password
            //se la query non genera un eccezzione allora ha trovato una corrispondenza e
            //il commit ha avuto successo
            HttpSession session = request.getSession();
            session.setAttribute("isLoggedIn", "true");
            session.setAttribute("utente", dev);
            return true;
        } else if(userType.equals("employer")) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("turing_careersPU");
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            Employer emp = new Employer();
            tx.begin();
            //strano che la persist non generi eccezzioni???? come faccio a sapere che ha
            //avuto effettivamente successo???? devo controllare tramite query???
            em.persist(emp);
            tx.commit();
            //si può effettuare un controllo aggiuntivo cercando il dev per mail e password
            //se la query non genera un eccezzione allora ha trovato una corrispondenza e
            //il commit ha avuto successo
            HttpSession session = request.getSession();
            session.setAttribute("isLoggedIn", "true");
            session.setAttribute("utente", emp);
            return true;
        }
        return false;
    }

    private boolean validate(String mail, String password) {
        //validare mail e password
        return true;
    }
}