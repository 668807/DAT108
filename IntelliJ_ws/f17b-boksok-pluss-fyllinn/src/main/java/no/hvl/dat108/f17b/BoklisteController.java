package no.hvl.dat108.f17b;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoklisteController {

    @GetMapping("/alleboker")
    public String alleBoker(Model model) {
        model.addAttribute("boker", Boker.alleBoker);
        return "boklisteView";
    }

    @GetMapping("/noenboker")
	public String noenBoker(Model model /*TODO FYLLINN - Flere parametre .. */) {

        List<Bok> bokutvalg = null;

        // TODO FYLLINN - Søk på forfatter med valgfri sortering og utplukk på sjangre

		model.addAttribute("boker", bokutvalg);
		return"boklisteView";
	}

}
