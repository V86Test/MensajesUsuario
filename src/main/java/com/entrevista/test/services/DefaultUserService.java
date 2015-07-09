package com.entrevista.test.services;

import com.entrevista.test.entities.User;
import com.entrevista.test.repository.UserRepository;
import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 *
 * @author Victor Manuel Sanchez (vmso86@gmail.com) vimso
 */
@Named
public class DefaultUserService implements UserService{

    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());
    
    @Inject
    private UserRepository userRepository;
    
    @Inject
    private EmailService emailService;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void registerUser(final String name, final String mail, final String identificator) {
        if (validateUser(name,mail,identificator)) {
            if (createUser(name,mail,identificator)) {
                sendConfirmationMail(name,mail,identificator);
            }
        }else{
            LOGGER.info("Error validating user");
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean existsUserByIdentificator(final String identificator) {
        final List<User> users = userRepository.getUserByIdentificator(identificator);
        return CollectionUtils.isEmpty(users);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<User> getAllUser() {
        return Lists.newArrayList(userRepository.findAll());
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public User getUserByIdentificator(String userIdentificator) {
        List<User> users = userRepository.getUserByIdentificator(userIdentificator);
        return (!CollectionUtils.isEmpty(users) && users.size() == 1) ? users.get(0) : null;
        
    }
    
    private boolean existsUserByMail(final String mail) {
        final List<User> users = userRepository.getUserByMail(mail);
        return CollectionUtils.isEmpty(users);
    }    
    
    private boolean validateUser(final String name,final String mail,final String identificator) {
        return (!StringUtils.isEmpty(name) && !StringUtils.isEmpty(mail) && !StringUtils.isEmpty(identificator)
                && existsUserByIdentificator(identificator) && existsUserByMail(mail));
    }

    private boolean createUser(final String name,final String mail,final String identificator) {
        try {
            final User user = new User(name,mail,identificator);
            userRepository.save(user);
            return true;

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,
                    String.format("An error has occurred trying to save the user, with the data: identificator %s, mail %s, name %s",
                            identificator, mail, name), e);
            return false;
        }
    }

    private void sendConfirmationMail(final String name,final String mail, final String identificator) {        
        emailService.sendMail(mail,"Welcome to messaging services X","Welcome to messaging services X");
    }
}
