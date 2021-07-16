package com.example.nesdeneme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private IQuestionRepository questionRepository;

    @GetMapping("/sss")
    public String viewAddQuestions(Model model){
        List<Questions> listQuestions = questionRepository.findAll();
        model.addAttribute("listQuestions",listQuestions);
        return "sss";
    }
    @RequestMapping(value = "/addQuestion", method = RequestMethod.GET)
    public String questionList(Model model){
        List<Questions> listQuestions = questionRepository.findAll();
        model.addAttribute("listQuestions",listQuestions);
        return "addQuestion";
    }

    @ModelAttribute
    public Questions initModelQuestion(){
        return new Questions();
    }

    @RequestMapping(value = "/addQuestion", method = RequestMethod.POST)
    public String handlerQuestionSumbit(@ModelAttribute Questions questions){
        questionRepository.save(questions);
        return "addQuestion";
    }

    //delete question
    @RequestMapping(value = "/deleteQuestion/{id}")
    public String deleteQuestion(@PathVariable(name = "id") Long id) {
        questionRepository.deleteById(id);
        return "redirect:/doctorList";
    }
}
