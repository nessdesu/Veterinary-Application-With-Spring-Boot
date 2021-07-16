package com.example.nesdeneme;

import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;

@Controller
public class ApplicationController {
    @Autowired
    private IUserRepository repo;

    @Autowired
    private IAppointmentRepository appointmentRepository;

    @Autowired
    private IDoctorRepository doctorRepository;

    //ANA SAYFA

    @GetMapping("")
    public String viewHomePage() {
        return "login";
    }

    @GetMapping("/index")
    public String showLogin() {
        return "index";
    }

    @GetMapping("/register")
    public String signUp(Model model) {
        model.addAttribute("user", new User());
        return "signUp_page";
    }
    @GetMapping("/login")
    public String afterRegister(){
        return "login";
    }

    //KULLANICI KAYIT İŞLEMLERİ

    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        repo.save(user);

        return "register_success";
    }

    //KULLANICI LİSTESİ

    @GetMapping("/list_users")
    public String viewUserList(Model model) {
        List<User> listUsers = repo.findAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }

    ///KUllanıcı Edit
    @RequestMapping(value = "/newUser", method = RequestMethod.GET)
    public String newUser() {
        return "newUser";
    }

    @ModelAttribute
    public User initModelUser() {
        return new User();
    }

    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    public String handlerUserSumbit(@ModelAttribute User users) {
        repo.save(users);
        return "redirect:/list_users";
    }

    @RequestMapping(value = "/updateUser/{id}", method = RequestMethod.GET)
    public String loadUser(@PathVariable Long id, ModelMap modelMap) {
        Optional<User> users = repo.findById(id);
        modelMap.put("users", users);
        return "editUser";
    }

    @RequestMapping(value = "/updateUser/{id}", method = RequestMethod.POST)
    public ModelAndView handleUserUpdate(@PathVariable(name = "id") Long id) {
        ModelAndView modelAndView = new ModelAndView("editUser");
        User users = repo.getOne(id);
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    ///KULLANICI SİL
    @RequestMapping(value = "/deleteUser/{id}")
    public String deleteUser(@PathVariable(name = "id") Long id) {
        repo.deleteById(id);
        return "redirect:/list_users";
    }

    //DOKTOR LİSTESİ

    @GetMapping("/doctorList")
    public String viewDoctorList(Model model) {
        List<Doctors> listDoctors = doctorRepository.findAll();
        model.addAttribute("listDoctors", listDoctors);
        return "doctors";
    }

    //YENİ DOKTOR KAYIDI

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newDoctor() {
        return "newDoctor";
    }

    @ModelAttribute
    public Doctors initModel() {
        return new Doctors();
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String handlerDoctorSumbit(@ModelAttribute Doctors doctors) {
        doctorRepository.save(doctors);
        return "redirect:/doctorList";
    }

    ///HEKİM BİLGİLERİ EDİTLEMEK
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String loadOwner(@PathVariable Long id, ModelMap modelMap) {
        Optional<Doctors> doctors = doctorRepository.findById(id);
        modelMap.put("doctors", doctors);
        return "editDoctor";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public ModelAndView handleFormSubmit(@PathVariable(name = "id") Long id) {
        ModelAndView modelAndView = new ModelAndView("editDoctor");
        Doctors doctors = doctorRepository.getOne(id);
        modelAndView.addObject("doctors", doctors);
        return modelAndView;
    }

    ///HEKİM SİLMEK
    @RequestMapping(value = "/delete/{id}")
    public String deleteDoctor(@PathVariable(name = "id") Long id) {
        doctorRepository.deleteById(id);
        return "redirect:/doctorList";
    }


    @GetMapping("/questions")
    public String viewQuestions() {
        return "sss";
    }


}


