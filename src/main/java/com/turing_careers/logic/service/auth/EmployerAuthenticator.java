package com.turing_careers.logic.service.auth;

import com.turing_careers.data.dao.EmployerDAO;
import com.turing_careers.data.dao.PersistenceException;
import com.turing_careers.data.model.Employer;
import com.turing_careers.data.model.User;
import com.turing_careers.logic.service.user.UserValidator;
import com.turing_careers.logic.service.utils.ValidationException;

/**
 * @author Antonino Lorenzo
 * */
public class EmployerAuthenticator extends Authenticator {

    public EmployerAuthenticator() { super(); }

    @Override
    public Employer loginUser(String email, String password) throws InvalidCredentialsException {
        super.setEncryptionStrategy(new Argon2Encryption());
        EmployerDAO employerDAO = EmployerDAO.getInstance();

        Employer emp;
        try {
            emp = employerDAO.getEmployerByMail(email);
            encryptionStrategy.verify(password, emp.getPassword());
        } catch (InvalidCredentialsException invalidCredentials) {
            throw new InvalidCredentialsException(invalidCredentials.getMessage());
        }

        return emp;
    }

    @Override
    public Employer signupUser(User user) throws ValidationException, PersistenceException {
        if (!(user instanceof Employer))
            throw new ValidationException("EmployerAuthService: Not an Employer");
        Employer emp = (Employer) user;
        UserValidator.checkValidity(emp);

        super.setEncryptionStrategy(new Argon2Encryption());
        String encryptedPassword = encryptionStrategy.encrypt(emp.getPassword());
        emp.setPassword(encryptedPassword);

        EmployerDAO employerDAO = EmployerDAO.getInstance();
        employerDAO.addEmployer(emp);

        return emp;
    }
}
