package no.hvl.dat108.f19b;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class NettbutikkController {

    @GetMapping("nettbutikk")
    public String visNettbutikken(Model model, HttpSession session, RedirectAttributes ra) {

        if (!LogginnUtil.erBrukerInnlogget(session)) {
            ra.addFlashAttribute("redirectMelding", "Du må være innlogget ...");
            return "redirect:logginn";
        }
        model.addAttribute("varer", Varer.vareliste.values());
        return "nettbutikkView";
    }

    @PostMapping("fjernfrakurv")
    public String fjernFraKurv(Integer varenr, HttpSession session, RedirectAttributes ra) {
        if (!LogginnUtil.erBrukerInnlogget(session)) {
            ra.addFlashAttribute("redirectMelding", "Du må være innlogget ...");
            return "redirect:logginn";
        }
        Handlekurv handlekurv = (Handlekurv) session.getAttribute("handlekurv");
        handlekurv.fjern(varenr);
        return "redirect:nettbutikk";
    }

    @PostMapping("leggikurv")
    public String leggikurv(Integer varenr, Integer antall, HttpSession session, RedirectAttributes ra) {
        if (!LogginnUtil.erBrukerInnlogget(session)) {
            ra.addFlashAttribute("redirectMelding", "Du må være innlogget ...");
            return "redirect:logginn";
        }
        Handlekurv handlekurv = (Handlekurv) session.getAttribute("handlekurv");
        handlekurv.leggTilEtAntall(varenr, antall);
        return "redirect:nettbutikk";
    }

}
