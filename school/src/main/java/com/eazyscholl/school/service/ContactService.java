package com.eazyscholl.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import com.eazyscholl.school.model.Contact;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestScope
@Service
public class ContactService {

	 private int counter = 0;
	 
	 public ContactService() {
		 System.out.println("Contact service Bean intialized");
	 }
	 
    private static final Logger log = LoggerFactory.getLogger(ContactService.class);
    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = true;
        log.info(contact.toString());
        return isSaved;
        
        
    }
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
}