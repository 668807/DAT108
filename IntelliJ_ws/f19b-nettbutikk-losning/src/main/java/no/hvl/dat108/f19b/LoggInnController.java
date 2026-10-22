package no.hvl.dat108.f19b;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoggInnController {

    @GetMapping("logginn")
    public String visLogginnSkjema() {
        return "logginnView";
    }

    @PostMapping("logginn")
    public String provAaLoggeInn(String brukernavn, HttpServletRequest request, RedirectAttributes ra) {

        if (!InputValidator.isValidUsername(brukernavn)) {
            ra.addFlashAttribute("redirectMelding", "Brukernavn er ikke gyldig");
            return "redirect:logginn";
        }

        LogginnUtil.loggInnBruker(request, brukernavn);
        return "redirect:nettbutikk";
    }

    @PostMapping("loggut")
    public String loggUt(HttpSession session, RedirectAttributes ra) {

        LogginnUtil.loggUtBruker(session);
        ra.addFlashAttribute("redirectMelding", "Du er nå logget ut");
        return "redirect:logginn";
    }
}
