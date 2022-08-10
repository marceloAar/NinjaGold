package cl.ninjagold.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControlGold {

	@GetMapping("/Gold")
	public String gold(HttpSession sesion) {
		
		if(sesion.getAttribute("suma") ==null) {
			sesion.setAttribute("suma",0);
		}
		if(sesion.getAttribute("lista") ==null) {
			ArrayList<String> listSesion= new ArrayList<String>();
			sesion.setAttribute("lista", listSesion);
		}
		return "index";
	}

	@PostMapping("/Gold")
	public String findGold(HttpSession sesion,@RequestParam int oro) {
		LocalDateTime fecha = LocalDateTime.now();
		DateTimeFormatter formFecha = DateTimeFormatter.ofPattern("EEEE, d-MM,YYYY hh:mm a");
		String fechaFormateada =fecha.format(formFecha);
		int numRandom=0;		
		int suma = (int) sesion.getAttribute("suma");
		ArrayList<String> reg = (ArrayList<String>) sesion.getAttribute("lista");
		
		switch (oro) {
		case 1:
			numRandom = (int)(Math.random()*(20-10+1)+10);				
			suma+= numRandom;
			sesion.setAttribute("suma", suma);
			reg.add("<p class='text-success'>You entered a farm and earned "+numRandom+" gold. ("+fechaFormateada+") <p/>");			
			sesion.setAttribute("lista", reg);			
			break;
		case 2:
			numRandom = (int)(Math.random()*(10-5+1)+5);				
			suma+= numRandom;
			sesion.setAttribute("suma", suma);
			reg.add("<p class='text-success'>You entered a cave and earned "+numRandom+" gold. ("+fechaFormateada+") <p/>");
			sesion.setAttribute("lista", reg);	
			break;
		case 3:
			numRandom = (int)(Math.random()*(5-2+1)+2);				
			suma+= numRandom;
			sesion.setAttribute("suma", suma);
			reg.add("<p class='text-success'>You entered a house and earned "+numRandom+" gold. ("+fechaFormateada+") <p/>");
			sesion.setAttribute("lista", reg);	
			break;
		case 4:
			numRandom = (int)(Math.random()*(50-0+1)+0);	
			int opcion = (int) (Math.random()* 2 + 0);
			
			if(opcion == 0) {			
				suma+= numRandom;
				sesion.setAttribute("suma", suma);
				reg.add("<p class='text-success'>You entered a casino and earned "+numRandom+" gold. ("+fechaFormateada+") <p/>");
				sesion.setAttribute("lista", reg);	
				break;
			}else if(opcion == 1) {
				suma-= numRandom;
				sesion.setAttribute("suma", suma);
				reg.add("<p class='text-danger'>You entered a casino and lost "+numRandom+" gold. ("+fechaFormateada+") <p/>");
				sesion.setAttribute("lista", reg);	
				break;
			}

		default:
			break;
		}
		return "index";
	}	
	
}
