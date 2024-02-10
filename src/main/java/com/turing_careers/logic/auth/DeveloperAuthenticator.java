package com.turing_careers.logic.auth;

import com.turing_careers.data.dao.DeveloperDAO;
import com.turing_careers.data.dao.PersistenceException;
import com.turing_careers.data.model.Developer;
import com.turing_careers.data.model.User;

import java.security.InvalidParameterException;


/**
 * @author Antonino Lorenzo
 * */
public class DeveloperAuthenticator extends Authenticator {

    public DeveloperAuthenticator() {
        super();
    }

    @Override
    public void loginUser(String email, String password) throws InvalidCredentialsException {
        super.setEncryptionStrategy(new Argon2Encryption());
        DeveloperDAO developerDAO = DeveloperDAO.getInstance();

        try {
            Developer dev = developerDAO.getDeveloperByMail(email);
            encryptionStrategy.verify(password, dev.getPassword());
        } catch (InvalidCredentialsException invalidCredentials) {
            throw new InvalidCredentialsException(invalidCredentials.getMessage());
        }
    }

    @Override
    public void signupUser(User user) throws PersistenceException, InvalidParameterException {
        if (!(user instanceof Developer))
            throw new InvalidParameterException("DeveloperAuthService: Not a developer");
        Developer dev = (Developer) user;

        super.setEncryptionStrategy(new Argon2Encryption());
        String encryptedPassword = encryptionStrategy.encrypt(dev.getPassword());
        dev.setPassword(encryptedPassword);

        DeveloperDAO developerDAO = DeveloperDAO.getInstance();
        developerDAO.addDeveloper((Developer) user);
    }
}
