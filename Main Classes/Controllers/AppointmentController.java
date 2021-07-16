package com.example.nesdeneme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class AppointmentController {

    @Autowired
    private IAppointmentRepository appointmentRepository;

    @Autowired
    private IDoctorRepository doctorRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ICallDoctorRepository callDoctorRepository;

    ///RANDEVU ALMAK
    @GetMapping("/appoints")
    public String viewAppoints() {
        return "appointment";
    }

    ///RANDEVU LİSTESİNİN GÖRÜNTÜLENMESİ
    @GetMapping("/appointmentList")
    public String viewAppointmentList(Model model){
        List<Appointment> listAppointment = appointmentRepository.findAll();
        model.addAttribute("listAppointment",listAppointment);
        return "showAppointment";
    }


    @RequestMapping(value = "/newAppointment", method = RequestMethod.GET)
    public String newAppointment(Model model) {
        List<Doctors> listDoctors = doctorRepository.findAll();
        model.addAttribute("listDoctors",listDoctors);
        return "newAppointment";
    }

    @ModelAttribute
    public Appointment initModelAppointment() {
        return new Appointment();
    }

    @RequestMapping(value = "/newAppointment", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String handlerAppointmentSumbit(@ModelAttribute Appointment appointment) {
        appointmentRepository.save(appointment);
        return "redirect:/appoints";
    }

    //////HEKİM ÇAĞIR
    @GetMapping("/callDoctor")
    public String viewCalls(Model model){
        List<Doctors> listDoctors = doctorRepository.findAll();
        model.addAttribute("listDoctors",listDoctors);
        return "callDoctor";
    }
    //hekim çağır listesini göster
    @GetMapping("/showCallDoctorList")
    public String viewCallDoctorList(Model model){
        List<CallDoctor> listCallDoctor = callDoctorRepository.findAll();
        model.addAttribute("listCallDoctor",listCallDoctor);
        return "showCallDoctor";
    }

    @ModelAttribute
    public CallDoctor initModelCallDoctor(){ return new CallDoctor();}

    @RequestMapping(value = "/newCall", method = RequestMethod.POST)
    public String handlerCallDoctorSumbit(@ModelAttribute CallDoctor callDoctor){
        callDoctorRepository.save(callDoctor);
        return "redirect:/callDoctor";
    }
    @GetMapping("/newCall")
    public String callingDr(){
        return "callDoctor";
    }

    ///call doctor delete
    @RequestMapping(value = "/deleteCallDoctor/{id}")
    public String deleteCallDoctor(@PathVariable(name = "id") Long id){
        callDoctorRepository.deleteById(id);
        return "redirect:/showCallDoctorList";
    }

    /*APPOINTMENT EDİTLEMEK*/

    @RequestMapping(value = "/updateAppointment/{id}", method = RequestMethod.GET)
    public String loadAppointment(@PathVariable Long id, ModelMap modelMap, Model model){
        List<Doctors> listDoctors = doctorRepository.findAll();
        model.addAttribute("listDoctors", listDoctors);
        Optional<Appointment> appointment = appointmentRepository.findById(id);
        modelMap.put("appointment", appointment);
        return "editAppointment";
    }

    @RequestMapping(value = "/updateAppointment/{id}", method = RequestMethod.POST)
    public ModelAndView handleAppointmentUpdate(@PathVariable(name = "id") Long id){
        ModelAndView modelAndView = new ModelAndView("editAppointment");
        Appointment appointment = appointmentRepository.getOne(id);
        modelAndView.addObject("appointment", appointment);
        return modelAndView;
    }

    /*APPOINTMENT DELETE*/
    @RequestMapping(value = "/deleteAppointment/{id}")
    public String deleteAppointment(@PathVariable(name = "id") Long id){
        appointmentRepository.deleteById(id);
        return "redirect:/appointmentList";
    }
}
