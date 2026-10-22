package no.hvl.dat108.f18b;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.validation.Valid;

@Controller
public class PersonController {

	@GetMapping("/personskjema")
	public String personskjema() {
		return "personskjema";
	}
	
	@GetMapping("/sjekkPerson")
	public String sjekkPerson(Model model, Person person) {

        //Data Binding, Form Object, @ModelAttribute, @Valid, BindingResult, ...

        //Guard
//		if(bindingResult.hasErrors()) {
//			List<String> feilmeldinger = bindingResult.getAllErrors().stream()
//					.map(e -> e.getDefaultMessage())
//                    .toList();
//
//			model.addAttribute("feilmeldinger", feilmeldinger);
//			return "personskjema";
//		}
		
		return "personkvittering";
	}

}



