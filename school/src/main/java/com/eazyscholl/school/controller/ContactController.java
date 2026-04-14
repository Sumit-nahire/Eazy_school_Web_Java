package com.eazyscholl.school.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eazyscholl.school.model.Contact;
import com.eazyscholl.school.service.ContactService;

import jakarta.validation.Valid;

@Controller
public class ContactController {

	private final ContactService contactService;

	public ContactController(ContactService contactService) {
		this.contactService = contactService;
	}

		@RequestMapping("/contact")
        public String displayContactPage(org.springframework.ui.Model model) {
			model.addAttribute("contact", new Contact());
        	return "contact";
        }
	 private static final Logger log = LoggerFactory.getLogger(ContactController.class);
//
//		    @RequestMapping(value = "/saveMsg", method = RequestMethod.POST)
//		    public ModelAndView saveMessage(@RequestParam String name,
//		                                    @RequestParam String mobileNum,
//		                                    @RequestParam String email,
//		                                    @RequestParam String subject,
//		                                    @RequestParam String message) {
//
//		        log.info("Name : " + name);
//		        log.info("Mobile Number : " + mobileNum);
//		        log.info("Email Address : " + email);
//		        log.info("Subject : " + subject);
//		        log.info("Message : " + message);
//
//		        return new ModelAndView("redirect:/contact");

	@RequestMapping(value="/saveMsg", method = RequestMethod.POST)
    public String saveMessage(@Valid @ModelAttribute("contact") Contact contact,Errors errors ) {

		if(errors.hasErrors()) {
		      log.error("Contact form Validation failed due to :"+ errors.toString());
		      return "contact";
		}
		    contactService.saveMessageDetails(contact);
		    contactService.setCounter(contactService.getCounter()+1);
		    log.info("Number of times Contact form is submitted:" +contactService.getCounter());
    	return "redirect:/contact";

         }

    }




