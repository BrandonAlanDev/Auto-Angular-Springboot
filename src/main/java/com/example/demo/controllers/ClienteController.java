package com.example.demo.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.models.entity.cliente;
import com.example.demo.models.service.ClienteServiceImp;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;

@Controller
public class ClienteController {
	@Autowired
	ClienteServiceImp clienteService;
	
	@Autowired
	private MessageSource messageSource;
	
	@GetMapping("/listar")
	//-----ResponseEntity<?> en algular
	public String listar(Model model,Locale locale) {
		model.addAttribute("titulo",messageSource.getMessage("texto.controller.titulo",null,locale));
		List<cliente> lista=clienteService.findAll();
		model.addAttribute("lista",lista);
		model.addAttribute("titulo","Lista de clientes");
		return "listar";
	}
	
	@GetMapping("/form")
	//-----ResponseEntity<?> en algular
	public String form(Model model) {
		List<cliente> lista=clienteService.findAll();
		model.addAttribute("lista",lista);
		model.addAttribute("titulo","Cargar cliente");
		cliente cl = new cliente();
		cl.setId(0);
		model.addAttribute("cliente",cl);
		return "form";
	}
	
	@GetMapping("/ver/{id}")
	public String ver(@PathVariable(value="id")long id,Map<String,Object> model)
	{
		cliente cliente = clienteService.findById(id);
		model.put("cliente", cliente);
		model.put("titulo", "Vista de cliente");
		return "ver";
	}
	
	@PostMapping("/form")
	public String guardar(@Valid cliente client, BindingResult resultado, Model modelo, @RequestParam ("file")MultipartFile foto, RedirectAttributes flash) {
		if(!foto.isEmpty()) {
			String uniqueFileName = UUID.randomUUID().toString()+"_"+foto.getOriginalFilename();
			Path rootPath=Paths.get("uploads").resolve(uniqueFileName);
			Path rootAbsolutePath = rootPath.toAbsolutePath();
			try {
				Files.copy(foto.getInputStream(), rootAbsolutePath);
				flash.addAttribute("info","foto subida con exito");
				client.setFoto(uniqueFileName);
			}catch(IOException ex){
				ex.printStackTrace();
			}
			clienteService.save(client);
			flash.addFlashAttribute("Success","Cliente creado con exito");
		}
		return "redirect:/listar";			
	}
	@GetMapping(value = "/listar", params = "format=xlsx")
	public ModelAndView listarExcel() {
	    List<cliente> lista = clienteService.findAll();
	    ModelAndView mav = new ModelAndView("clientesXlsView");
	    mav.addObject("clientes", lista);
	    return mav;
	}
}
