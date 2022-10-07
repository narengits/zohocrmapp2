package com.zohocrm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zohocrm.entities.Contact;
import com.zohocrm.entities.Lead;
import com.zohocrm.services.ContactService;
import com.zohocrm.services.LeadService;

@Controller
public class LeadController {
	
	@Autowired
	private LeadService leadService;
	
	@Autowired
	private ContactService contactService;
	
	@RequestMapping("/viewLeadPage")
	public String viewLeadPage() {
	return "create_lead";	
	}
	
	@RequestMapping("/saveLead")
	public String saveLead(@ModelAttribute("lead") Lead lead,ModelMap model) {
		leadService.saveOneLead(lead);
		model.addAttribute("lead", lead);
		return "lead_info";
	}
	@RequestMapping("/composeEmail")
	public String composeEmail(@RequestParam("email") String email,ModelMap model)
	{
		model.addAttribute("email",email);
		return "compose_email";
}
	@RequestMapping("/convertLead")
	public String ConvertLead(@RequestParam("id") long id,ModelMap model)
	{
		Lead lead = leadService.getOneLead(id);
		
		//below code will take/get data from lead object & put that into contact object.
		//but create a save method in service layer,ContactService.
		Contact contact = new Contact();
		contact.setFirstName(lead.getFirstName());
		contact.setLastName(lead.getLastName());
		contact.setEmail(lead.getEmail());
		contact.setMobile(lead.getMobile());
		contact.setSource(lead.getSource());
		
		contactService.saveOneContact(contact);
	
		leadService.deleteOneLead(lead.getId());
		
		List<Contact> contacts = contactService.getAllContacts();
		model.addAttribute("contacts",contacts);
		return "list_contacts";
	}
	
	@RequestMapping("/listall")
	public String getAllLeads(ModelMap model) {
		List<Lead> leads = leadService.listLeads();
		model.addAttribute("leads",leads);
		return "list_leads";
	}
	
	@RequestMapping("/findLeadById")
	public String findOneLead(@RequestParam("id") long id,ModelMap model)
	{
		Lead lead = leadService.getOneLead(id);
		model.addAttribute("lead",lead);
		return "lead_info";
	}
}
