package ru.demetra.callrec.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import ru.demetra.callrec.model.User;
import ru.demetra.callrec.service.UserService;

import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Class for login procedure
 *
 * @author Vyacheslav Y.
 * @version 2.2
 */

@Controller
@SessionAttributes("userJSP")   //Http session attribute for authentication

public class LoginController {

    private UserService userService;

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    /**
     * Add string UserJSP into http session
     * Add attribute default number page
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(HttpSession httpSession) {

        httpSession.setAttribute("userJSP", "anonymous");
        httpSession.setAttribute("page", 0);

        return "redirect: login";
    }


    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ModelAndView main(@ModelAttribute("userJSP") String user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userJSP", user);
        modelAndView.setViewName("login");
        return modelAndView;
    }


    /**
     * MAV for auth error print
     */
    @RequestMapping(value = "error", method = RequestMethod.GET)
    public ModelAndView error(String alert) {
        ModelAndView modelAndView = new ModelAndView();
        if (alert.equals("1")) {

            modelAndView.setViewName("login");
        } else {
            modelAndView.setViewName("loginPretoriants");
        }
        modelAndView.addObject("error", "Не правильный пароль!");

        return modelAndView;
    }


    /**
     * MAV from form in login.jsp
     * <p>
     * Check login/pass and if success add default attribute in http session
     */
    @RequestMapping(value = "check-user", method = RequestMethod.POST)
    public ModelAndView checkUser(@ModelAttribute("userJSP") String user,
                                  @RequestParam("login1") String login,
                                  @RequestParam("password") String password) {

        User userAuth = new User();

        userAuth.setLogin(login);
        userAuth.setPassword(password);

        ModelAndView modelAndView = new ModelAndView();

        if (userAuth.getPassword().equals(this.userService.getUserByLogin(userAuth.getLogin()).getPassword())) {
            modelAndView.addObject("init", 0);
            modelAndView.addObject("page", 0);

            Instant instant = Instant.ofEpochMilli(new Date().getTime());

            modelAndView.addObject("beginDate", LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate().toString());
            modelAndView.addObject("endDate", LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate().plusDays(1).toString());
            modelAndView.addObject("criteria", "");
            modelAndView.addObject("critValue", "");

            User usr = this.userService.getUserByLogin(userAuth.getLogin());

            modelAndView.addObject("userJSP", usr);
            modelAndView.setViewName("redirect: calls");

        } else {
            modelAndView.addObject("alert", "1");
            modelAndView.setViewName("redirect: error");
        }
        return modelAndView;
    }


    /**
     * Special order
     */
    @RequestMapping(value = "pretoriants", method = RequestMethod.POST)
    public ModelAndView checkUser(@ModelAttribute("userJSP") String user,
                                  @RequestParam("password") String password) {

        ModelAndView modelAndView = new ModelAndView();

        if (password.equals(this.userService.getUserByLogin("admin").getPassword())) {

            modelAndView.addObject("userList", this.userService.getUsers());
            modelAndView.setViewName("users");
        } else {
            modelAndView.addObject("alert", "2");
            modelAndView.setViewName("redirect: error");
        }
        return modelAndView;
    }

    /**
     * Logout procedure
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(SessionStatus status, HttpSession session) {
        status.setComplete();
        session.invalidate();
        return "redirect: /";
    }
}
