package com.turing_careers.logic.user;

import com.turing_careers.data.dao.DeveloperDAO;
import com.turing_careers.data.dao.EmployerDAO;
import com.turing_careers.data.model.Developer;
import com.turing_careers.data.model.Employer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserManager {
    public static void editProfile(Developer newProfile) throws UpdateProfileException, UserNotValidException {
        checkValidity(newProfile);
        DeveloperDAO updater = DeveloperDAO.getInstance();
        try {
            updater.updateDeveloper(newProfile);
        } catch (Exception e) {
            throw new UpdateProfileException(e.getMessage());
        }
    }

    public static void editProfile(Employer newProfile) throws UpdateProfileException, UserNotValidException {
        checkValidity(newProfile);
        EmployerDAO updater = EmployerDAO.getInstance();
        try {
            updater.updateEmployer(newProfile);
        } catch (Exception e) {
            throw new UpdateProfileException(e.getMessage());
        }
    }

    public static void deleteAccount() {
        //TODO
    }
    public static void saveProfile() {
        //TODO
    }

    private static void checkValidity(Developer user) throws UserNotValidException {
        Pattern mailPattern = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$");

        if(user.getFirstName().length() == 0
                || user.getFirstName().length() > 32
                || user.getLastName().length() == 0
                || user.getLastName().length() > 64
                || user.getBio().length() > 2048
                || user.getMail().length() == 0
                || !mailPattern.matcher(user.getMail()).matches()
                || user.getSkills().isEmpty()
                //TODO: controllare che le skills dello user siano presenti nel database
                || user.getLanguages().isEmpty()
                //TODO: controllare che le lingue dello user siano presenti nel database
        ) throw new UserNotValidException();
    }

    private static void checkValidity(Employer user) throws UserNotValidException {
        Pattern mailPattern = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$");

        if(user.getFirstName().length() == 0
                || user.getFirstName().length() > 32
                || user.getLastName().length() == 0
                || user.getLastName().length() > 64
                || user.getCompanyName().length() == 0
                || user.getCompanyName().length() > 64
                || !mailPattern.matcher(user.getMail()).matches()
        ) throw new UserNotValidException();
    }

}
