package com.turing_careers.logic.auth;

import com.turing_careers.data.dao.EmployerDAO;
import com.turing_careers.data.model.Employer;
import com.turing_careers.data.model.User;

import java.security.InvalidParameterException;

/**
 *
 * */
public class EmployerAuthenticator extends Authenticator {

    public EmployerAuthenticator() { super(); }

    @Override
    public void loginUser(String email, String password) throws InvalidCredentialsException {
        super.setEncryptionStrategy(new Argon2Encryption());
        EmployerDAO employerDAO = EmployerDAO.getInstance();

        try {
            Employer emp = employerDAO.getEmployerByMail(email);
            String psw = emp.getPassword();
            encryptionStrategy.verify(password, psw);
        } catch (InvalidCredentialsException invalidCredentials) {
            throw new InvalidCredentialsException(invalidCredentials.getMessage());
        }
    }

    @Override
    public void signupUser(User user) throws Exception {
        if (!(user instanceof Employer))
            throw new InvalidParameterException("EmployerAuthService: Not an Employer");
        Employer emp = (Employer) user;

        super.setEncryptionStrategy(new Argon2Encryption());
        String encryptedPassword = encryptionStrategy.encrypt(emp.getPassword());
        emp.setPassword(encryptedPassword);

        EmployerDAO employerDAO = EmployerDAO.getInstance();
        try {
            employerDAO.addEmployer((Employer) user);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
}
