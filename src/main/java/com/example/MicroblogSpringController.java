package com.example;//Created by KevinBozic on 3/7/16.

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class MicroblogSpringController {

    ArrayList<Message> messages = new ArrayList<Message>();

    @RequestMapping(path = "/", method = RequestMethod.GET) // same as a get/post route in spark
    public String user(Model model, HttpSession session) {
        model.addAttribute("name", session.getAttribute("userName"));
        model.addAttribute("messages", messages);
        return "home";
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST) //login is linked to the form action in html
    public String home(HttpSession session, String userName) { // Has 2 arguments. 1 is the establishing a session, and 2 is defining a username
        session.setAttribute("userName", userName); // this is where we assign the username that gets entered into the text field to the session
        return "redirect:/"; // this redirects to the home page. Since our session saved our user name, we jump to that users homepage after logging in
    }

    @RequestMapping(path = "/addMessage", method = RequestMethod.POST) //
    public String addMessage(String text) {
//        Message message = new Message(messages.size(), text);
//        int index = messages.size() - 1;
//        messages.add(index, "addMessage");
//        return "redirect:/";
        Message m = new Message(messages.size() + 1 , text);
        messages.add(m);
        return "redirect:/";
    }

    @RequestMapping(path = "/deleteMessage", method = RequestMethod.POST) //
    public String deleteMessage(Integer id) {
        messages.remove(id - 1);
        return "redirect:/";
    }

    @RequestMapping(path = "logout", method = RequestMethod.POST)
    public String logout(HttpSession session) { // This contains Session as an argument
        session.invalidate(); // This invalidates the current session. Used for logging out
        return "redirect:/"; // Redirects back to the home
    }
}
