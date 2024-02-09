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
        String encryptedPassword = encryptionStrategy.encrypt(password);

        DeveloperDAO updater = DeveloperDAO.getInstance();
        Developer dev = updater.getDeveloperByMail(email);
        encryptionStrategy.verify(password, dev.getPassword());
        if (dev == null) {
            throw new InvalidCredentialsException();
        }
    }

    @Override
    public void signupUser(User user) throws Exception {
        if (!(user instanceof Developer))
            throw new InvalidParameterException("DeveloperAuthService: Not a developer");

        DeveloperDAO developerDAO = DeveloperDAO.getInstance();
        try {
            developerDAO.addDeveloper((Developer) user);
        } catch (PersistenceException ex) {
            throw new Exception(ex.getMessage());
        }
    }
}
