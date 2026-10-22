package no.hvl.dat108.f18a;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SjekkAarController {

	@GetMapping("/skjema")
	public String aarstallSkjema() {
		return "skjema";
	}

	@GetMapping("/sjekkAar")
	public String sjekkFodselsaar(Model model, int fodselsaar) {
        //Skal fodselsaar være
        //int, Integer, @RequestParam, required=false, String, ... ?

		//"Guard" - Vi sjekker og håndterer feilsituasjoner først.
		//          Her har vi laget vår egen validator.
		//			Programmatisk validering.
//		String valideringsmelding = InputValidator.erGyldigAarstall(fodselsaar);
//        boolean gyldig = valideringsmelding.isBlank();
        String valideringsmelding = "";
        boolean gyldig = true;

		if (!gyldig) {
			model.addAttribute("feilmelding", valideringsmelding);
			return "skjema"; //Vi viser skjemaet igjen med feilmelding.
		}

		//Kommer vi hit, er alt i orden
		model.addAttribute("gyldig", gyldig);
//        model.addAttribute("aar", aar);
        return "tilbakemelding";

//  Alternativt: redirect:
//        ra.addFlashAttribute("fodselsaar", fodselsaar);
//        ra.addFlashAttribute("gyldig", gyldig);
//		  return "redirect:tilbakemelding";
	}

    /*
     * Hva hvis innsending av skjema var en POST, eller generelt om vi ønsket
     * en redirect til en tilbakemelding-URL i stedet for å bruke viewet direkte.
     *
     * Jo, da lager vi en GET-mapping for tilbakemelding og redirecter dit.
     * Da vil nettleseren utføre en ny request.
     * Evt. model-attributter vil ikke lenger være gyldige (siden det er en ny
     * request), så vi må bruke såkalte flash-attributter i stedet !
     */
    @GetMapping("/tilbakemelding")
    public String visTilbakemelding() {
        return "tilbakemelding";
    }
}
