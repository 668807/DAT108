package no.hvl.dat108.prggreier;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
public class TheController {

    @PostMapping("/skjema")
    public String skjema(String navn, Model model, RedirectAttributes ra) {
        model.addAttribute("navn", navn);   //Blir lagt til model i denne requesten
        ra.addFlashAttribute("navn", navn); //Blir lagt til model i neste requesten
        return "redirect:/kvittering";

//      Kan også legge på greier for bokmerking
//        return "redirect:/kvittering?id=12345678";
    }

    @GetMapping("kvittering")
    public String kvittering(Model model) {
        IO.println("model: " + model.getAttribute("navn"));
        return "kvittering";
    }
}
