package ru.demetra.callrec.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.demetra.callrec.model.User;
import ru.demetra.callrec.service.CallService;
import ru.demetra.callrec.service.UserService;
import ru.demetra.callrec.util.CallFile;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Class for main logic
 *
 * @author Vyacheslav Y.
 * @version 2.4
 */

@Controller
@SessionAttributes("userJSP")
public class UserController {

    private UserService userService;
    private CallService callService;
    private CallFile callFile;


    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired(required = true)
    @Qualifier(value = "callFile")
    public void setCallFile(CallFile callFile) {
        this.callFile = callFile;
    }

    @Autowired(required = true)
    @Qualifier(value = "callService")
    public void setCallService(CallService callService) {
        this.callService = callService;
    }

    /**
     * @param user       {@link User} auth attribute
     * @param pageNumber For paging
     * @param beginDate
     * @param endDate
     * @param criteria   Type of filter condition
     * @param critValue  Value of filter condition
     * @param request
     * @return
     * @see List defaultFilter() Default filter value
     */
    @RequestMapping(value = "calls", method = RequestMethod.GET)
    public String callSearch(@ModelAttribute("userJSP") User user,
                             Model model,
                             @RequestParam("page") Integer pageNumber,
                             @RequestParam("beginDate") String beginDate,
                             @RequestParam("endDate") String endDate,
                             @RequestParam("criteria") String criteria,
                             @RequestParam("critValue") String critValue,
                             HttpServletRequest request) {
        int firstIndex = pageNumber * 20;
        int pageNumberNext = pageNumber + 1;
        int pageNumberPrev = pageNumber - 1;
        String ipAddress = request.getRemoteAddr();

        /**
         * String2Date and format
         */
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDateDt = null;
        Date endDateDt = null;

        try {
            beginDateDt = df.parse(beginDate);
            endDateDt = df.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        /**
         * Create List and complete for show into View calls.jsp
         */
        List tmpList = null;

        if (critValue.equals("")) {
            tmpList = defaultFilter(beginDateDt, endDateDt, criteria, critValue, firstIndex, user);
        } else {
            if (criteria.equals("")) {
                tmpList = defaultFilter(beginDateDt, endDateDt, criteria, critValue, firstIndex, user);
            } else {
                tmpList = this.callService.listFilter(beginDateDt, endDateDt, criteria, critValue, firstIndex, user);
            }
        }


        /**
         * Create link for navigation in Calls table and change password
         */
        String nextLink = String.format("<a href=\"calls?beginDate=%s&endDate=%s&criteria=%s&critValue=%s&page=%d\">Следующая страница ></a>", beginDate, endDate, criteria, critValue, pageNumberNext);
        String prevLink = String.format("<a href=\"calls?beginDate=%s&endDate=%s&criteria=%s&critValue=%s&page=%d\">< Предыдущая страница</a>", beginDate, endDate, criteria, critValue, pageNumberPrev);
        String changePass = "<a href=\"/pwd.jsp\" onclick=\"window.open(this.href, 'mywin','left=40,top=40,width=296,height=180,toolbar=0,resizable=0'); return false;\">Сменить пароль</a>";
        String changePassAdmin = "<a href=\"/pwdAdmin.jsp\" onclick=\"window.open(this.href, 'mywin','left=40,top=40,width=296,height=180,toolbar=0,resizable=0'); return false;\">Сменить пароль пользователям</a>";

        if ((pageNumber < 1) && (tmpList.size() == 20)) {
            model.addAttribute("pageNumberNext", nextLink);

        } else if ((pageNumber < 1) && (tmpList.size() < 20)) {
//            model.addAttribute("pageNumberPrev", prevLink);
//            model.addAttribute("pageNumberNext", nextLink);
        } else if (tmpList.size() < 20) {
            model.addAttribute("pageNumberPrev", prevLink);
//            model.addAttribute("pageNumberNext", nextLink);
        } else {
            model.addAttribute("pageNumberPrev", prevLink);
            model.addAttribute("pageNumberNext", nextLink);
        }

        if (user.getLogin().equals("admin")) {
            model.addAttribute("changePass", changePassAdmin);
        } else {
            model.addAttribute("changePass", changePass);
        }

        /**
         * Add different attribute into model
         */
        model.addAttribute("callList", tmpList);
        model.addAttribute("ipAddress", ipAddress);
        model.addAttribute("beginDate", beginDate);
        model.addAttribute("endDate", endDate);

        return "calls";
    }

    List defaultFilter(Date beginDateDt, Date endDateDt, String criteria, String critValue, int firstIndex, User user) {
        List tmpList;

        if (user.getLogin().equals("Demetra")) {
            return tmpList = this.callService.listCallsDemetra(firstIndex, beginDateDt, endDateDt);
        } else if (user.getLogin().equals("Trapeza")) {
            return tmpList = this.callService.listCallsTrapeza(firstIndex, beginDateDt, endDateDt);
        } else {
            return tmpList = this.callService.listFilter(beginDateDt, endDateDt, criteria, critValue, firstIndex, user);
        }
    }

    /**
     * For play callrec file
     *
     * @param path File path on PBX
     * @return View file.jsp, open in new window
     */
    @RequestMapping(value = "play", method = RequestMethod.GET)
    public String playFile(@RequestParam("path") String path, Model model) {

        try {
            String file = callFile.pathSelector(path);
            model.addAttribute("path", file);
        } catch (NullPointerException e) {
            model.addAttribute("path", path);
            return "error";
        }
        return "file";
    }

    /**
     * For print information msg about change pass
     *
     * @return if user Admin return View pwdAdmin.jsp, else pwd.jsp
     */
    @RequestMapping(value = "pwdInfo", method = RequestMethod.GET)
    public String pwdError(@ModelAttribute("userJSP") User user, @RequestParam("passError") Integer
            passError, Model model) {
        String error = null;
        switch (passError) {
            case 0:
                error = "Пароль изменен";
                break;
            case 1:
                error = "Пароли не совпадают";
                break;
            case 2:
                error = "Длинна пароля должна быть от 4 до 10 символов";
                break;
        }

        model.addAttribute("passError", error);

        if (user.getLogin().equals("admin")) {
            return "pwdAdmin";
        }
        return "pwd";
    }

    /**
     * Change password procedure for users
     */
    @RequestMapping(value = "pwd", method = RequestMethod.POST)
    public String pwd(@ModelAttribute("userJSP") User user,
                      @RequestParam("password") String password,
                      @RequestParam("repassword") String repassword,
                      Model model) {

        if (!password.equals(repassword)) {

            model.addAttribute("passError", 1); //Пароли не совпадают
            return "redirect: pwdInfo";
        }
        if (password.length() < 4 || password.length() > 10) {

            model.addAttribute("passError", 2);   //Длинна пароля должна быть 10< >4
            return "redirect: pwdInfo";
        }

        user.setPassword(password);
        this.userService.changeUserPass(user);

        model.addAttribute("passError", 0);   //Пароль изменен
        return "redirect: pwdInfo";
    }

    /**
     * Change password procedure for Admin
     *
     * @param login Which user change pass
     */
    @RequestMapping(value = "pwdAdmin", method = RequestMethod.POST)
    public String pwdAdmin(@RequestParam("login") String login,
                           @RequestParam("password") String password,
                           @RequestParam("repassword") String repassword,
                           Model model) {

        if (!password.equals(repassword)) {
            model.addAttribute("passError", 1); //Пароли не совпадают
            return "redirect: pwdInfo";
        }

        User usr = this.userService.getUserByLogin(login);

        usr.setPassword(password);
        this.userService.changeUserPass(usr);

        model.addAttribute("passError", 0);   //Пароль изменен

        return "redirect: pwdInfo";
    }

}






