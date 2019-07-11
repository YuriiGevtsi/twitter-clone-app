package com.example.twitter.service;

import com.example.twitter.domain.Role;
import com.example.twitter.domain.User;
import com.example.twitter.repos.UserRepo;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepo userRepo;

    @MockBean
    private MailSender mailSender;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    public void addUser() {
        User user = new User();

        user.setUsername("some");
        user.setPassword("some");
        user.setEmail("some@some.some");

        boolean isUserCreated = userService.addUser(user);

        Assert.assertTrue(isUserCreated);
        Assert.assertNotNull(user.getActivationCode());
        Assert.assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.USER)));

        Mockito.verify(userRepo,Mockito.times(1)).save(user);
        Mockito.verify(mailSender,Mockito.times(1))
                .send(
                        ArgumentMatchers.eq(user.getEmail()),
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyString()
                );
    }

    @Test
    public void addUserFailTest(){
        User user = new User();

        user.setUsername("Twinky");

        doReturn(new User())
                .when(userRepo)
                .findByUsername("Twinky");

        boolean isUserCreated = userService.addUser(user);

        Mockito.verify(userRepo,Mockito.times(0)).save(any(User.class));
        Mockito.verify(mailSender,Mockito.times(0))
                .send(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyString()
                );
        Assert.assertFalse(isUserCreated);
    }

    @Test
    public void activateUser() {
        User user = new User();
        //user.setActivationCode("code");

        doReturn(new User())
                .when(userRepo)
                .findByActivationCode("activate");

        boolean isActivated = userService.activateUser("activate");

        Assert.assertTrue(isActivated);
        Assert.assertNull(user.getActivationCode());

        verify(userRepo,times(1)).save(user);
    }

    @Test
    public void activateUserFailTest(){
        boolean isActivated = userService.activateUser("activate me");

        Assert.assertFalse(isActivated);

        verify(userRepo,times(0)).save(any(User.class));
    }
}