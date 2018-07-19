package com.sendcertifications.controllers;

import com.sendcertifications.domain.Member;
import com.sendcertifications.services.SendService;
import com.sendcertifications.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/")
public class IndexController {
    static final Logger LOG = Logger.getLogger(IndexController.class.getName());

    @Autowired
    private SendService sendService;
    @Autowired
    private TagService tagService;


    /**
     * Home Request mapping
     * @return
     */
    @GetMapping("")
    public ModelAndView index(){
      ModelAndView mv = new ModelAndView("index");

      return mv;
    }

    /**
     * this method maps the request to send the information
     * @param text
     * @param members
     * @param imageUrl
     * @param model
     * @return
     */
    @PostMapping("/send")
    public ModelAndView send(@RequestParam(value = "text") String text
            ,@RequestParam(value = "members") String members
            ,@RequestParam(value = "imageUrl") String imageUrl
    ,Model model) {

        List<Member> memberList = tagService.prepareCertificates(text, members);
        sendService.sendEmails(memberList);
        ModelAndView mv = new ModelAndView("index");

        return mv;
    }

}
