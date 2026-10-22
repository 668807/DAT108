package no.hvl.dat108.prggreier;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TheController {

    @PostMapping("/skjema")
    public String skjema(String navn, RedirectAttributes ra) {
        ra.addFlashAttribute("navn", navn);   //Blir lagt til model i denne requesten
        return "redirect:kvittering";
    }

    @GetMapping("kvittering")
    public String visKvittering(Model model) {
        return "kvitteringView";
    }

}
