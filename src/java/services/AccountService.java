package services;

import dataaccess.UserDB;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import models.User;

public class AccountService {
    
    public User login(String email, String password, String path) {
        UserDB userDB = new UserDB();
        
        try {
            User user = userDB.get(email);
            if (password.equals(user.getPassword())) {
                Logger.getLogger(AccountService.class.getName()).log(Level.INFO, "Successful login by {0}", email);
                GmailService.sendMail(user.getEmail(), "Notes App Login", "Hi " + user.getFirstName() + ", you logged in on " + (new Date()).toString(), false);
                
                String to = user.getEmail();
                String subject = "Notes App Login";
                String template = path + "/emailtemplates/login.html";
                
                HashMap<String, String> tags = new HashMap<>();
                tags.put("firstname", user.getFirstName());
                tags.put("lastname", user.getLastName());
                tags.put("date", (new java.util.Date()).toString());
                
                //GmailService.sendMail(to, subject, template, tags);
                return user;
            }
        } catch (Exception e) {
        }
        
        return null;
    }
    
    public void resetPassword (String email, String path, String url) {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        
        String uuid = UUID.randomUUID().toString();
        String link = url + "?uuid=" + uuid;
        
        try
        {
            GmailService.sendMail(email, "Notes App Password Reset", "Hi " + user.getFirstName() + ", please click on the following link to reset your password: " + link, false);
        }
        catch (Exception e)
        {
            Logger.getLogger(AccountService.class.getName()).log(Level.INFO, "Error sending email", email);
        }
    }
    
    public boolean changePassword(String uuid, String password) {
        UserDB userDB = new UserDB();
        
        try
        {
            User user = userDB.getByUUID(uuid);
            user.setPassword(password);
            user.setResetPasswordUuid("null");
            userDB.update(user);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }
    
}
