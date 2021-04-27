package com.example.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.project.classes.PageCounter;
import com.example.project.models.ContactUsModel;
import com.example.project.models.FormDataModel;
import com.example.project.repository.ContactUsRepository;
import com.example.project.repository.EmployeeDBRepository;

@Controller
public class MVCController {

	@Autowired
	EmployeeDBRepository employeeDBRepository;
	@Autowired
	ContactUsRepository conatctUsRepository;
	@Autowired
	PageCounter pageCounter;

	public int pageHits = 0;

	@RequestMapping("/")
	public String indexPage(@ModelAttribute("formValidation") FormDataModel formValidation, Model model) {
		pageCounter.incrementPageCounter();
		model.addAttribute("pageCount", pageCounter.getCurrentPageCount());
		return "index";
	}
	
	@RequestMapping("/contact")
	public String contactPage(@ModelAttribute("contactUs") ContactUsModel contactUs, Model model) {
		pageCounter.incrementPageCounter();
		model.addAttribute("pageCount", pageCounter.getCurrentPageCount());
		return "contact";
	}
	
	@RequestMapping("/employeeBenefits")
	public String benefitPage(Model model) {
		pageCounter.incrementPageCounter();
		model.addAttribute("pageCount", pageCounter.getCurrentPageCount());
		return "employeeBenefits";
	}
	
	@RequestMapping("/careers")
	public String careerPage(Model model) {
		pageCounter.incrementPageCounter();
		model.addAttribute("pageCount", pageCounter.getCurrentPageCount());
		return "careers";
	}
	
	@PostMapping("/form_validation")
	public String formValidation(@Valid @ModelAttribute("formValidation") FormDataModel formValidation,
			BindingResult bindingResult) {

		System.out.println(formValidation.toString());
		if (bindingResult.hasErrors()) {
			System.out.println("Errors found");
			return "index";
		}

		FormDataModel fm = employeeDBRepository.save(formValidation);
		System.out.println("success db " + fm.getEmployeeID());
		return "redirect:/employeeList";
	}

	@RequestMapping("/employeeList")
	public String employeeList(Model model) {

		pageCounter.incrementPageCounter();
		model.addAttribute("pageCount", pageCounter.getCurrentPageCount());
		List<FormDataModel> frmList = (List<FormDataModel>) employeeDBRepository.findAll();
		model.addAttribute("employeeList", frmList);
		System.out.println("Size is " + frmList.size());
		return "employeeList";
	}

	@PostMapping("/contact_us")
	public String contactUs(@Valid @ModelAttribute("contactUs") ContactUsModel contactUs, BindingResult bindingResult) {
		System.out.println(contactUs.toString());
		if (bindingResult.hasErrors()) {
			System.out.println("Errors found");
			return "contact";
		}

		ContactUsModel cu = conatctUsRepository.save(contactUs);
		System.out.println("success db " + cu.getId());
		return "redirect:/";
	}

}
