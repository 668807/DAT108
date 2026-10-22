package no.hvl.dat108.f19b;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class LogginnUtil {
	
	public static void loggUtBruker(HttpSession session) {
		if (session != null) {
			session.invalidate();		
		}
	}

	public static void loggInnBruker(HttpServletRequest request, String username) {

		//NB! 
		loggUtBruker(request.getSession());
		
		HttpSession sesjon = request.getSession();
		sesjon.setAttribute("brukernavn", username);
		sesjon.setAttribute("handlekurv", new Handlekurv());
		sesjon.setMaxInactiveInterval(20); //sekunder		
	}
	
	public static boolean erBrukerInnlogget(HttpSession session) {
		return session != null && session.getAttribute("brukernavn") != null;
	}

}
