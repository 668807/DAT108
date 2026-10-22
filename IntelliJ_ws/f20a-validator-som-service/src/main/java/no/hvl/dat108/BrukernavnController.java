package no.hvl.dat108;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BrukernavnController {

    /*
     * Aldri opprett denne med new InputValidator().
     */
	private final InputValidator inputValidator;

    /*
     * BrukernavnController blir opprettet med denne konstruktøren,
     * og da vil Spring opprette et objekt av typen InputValidator og
     * putte (injisere) dette inn i controlleren.
     *
     * NB! Vi kan kun gjøre dette hvis BrukernavnController selv er
     * en Spring komponent, f.eks. en @Controller eller en @Service.
     */
    public BrukernavnController(InputValidator inputValidator) {
        this.inputValidator = inputValidator;
    }

    @GetMapping("/skjema")
	public String brukenavnSkjema() {
		return "skjema";
	}

	@GetMapping("/sjekkBrukernavn")
	public String sjekkBrukernavn(Model model, String brukernavn) {

		boolean gyldig = inputValidator.erGyldigBrukernavn(brukernavn);
		
		if (!gyldig) {
			model.addAttribute("feilmelding", 
					"Brukernavnet må være 3 eller flere tegn, og ikke allerede i bruk.");
			return "skjema"; 
		}
		//Kommer vi hit, er alt i orden
		return "tilbakemelding";
	}
	
}
