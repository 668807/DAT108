package no.hvl.dat108.f17a;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistreringController {
	
	@PostMapping("/registrer")
	public String taImotRegistrering(Model model,
			String fornavn, String etternavn, int aar) {
		
		Person p = new Person(fornavn, etternavn, aar);
		model.addAttribute("personen", p);
		return "kvitteringView";
	}

    //Person p fungerer greit, men må da gjøre model.addAttr.. manuelt.
    //@ModelAttribute("personen") Person p gjør det for oss.
    //https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-methods/modelattrib-method-args.html
    @PostMapping("/registrer-ma")
    public String taImotRegistreringMA(@ModelAttribute("personen") Person p) {
        return "kvitteringView";
    }

    /*
     * For å unngå dobbeltposting ... PRG.
     * 302 Found + Location.
     */
    @PostMapping("/registrer-prg")
    public String taImotRegistreringPRG(HttpSession session,
                String fornavn, String etternavn, int aar) {

        Person p = new Person(fornavn, etternavn, aar);
        session.setAttribute("personen", p);
        return "redirect:/kvittering";
    }
    @GetMapping("/kvittering")
    public String visKvittering(HttpSession session, Model model) {

        Object p = session.getAttribute("personen");
        model.addAttribute("personen", p);
        return "kvitteringView";
    }

}
