package com.algaworks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ContatosControle {
	
	@Autowired
	private Contatos contatos;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/contatos")
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("listar");
		
		modelAndView.addObject("contatos", contatos.findAll());
		
		return modelAndView;
	}
	
	@GetMapping("/contatos/novo")
	public ModelAndView novo() {
		ModelAndView modelAndView = new ModelAndView("formulario");
		
		modelAndView.addObject("contato", new Contato());
		
		return modelAndView;
	}	
	
	@PostMapping("/contatos")
	public String cadastrar(Contato contato, RedirectAttributes attributes) {
		contatos.save(contato);

        attributes.addFlashAttribute("mensagem", "Cadastrado com sucesso!");
		
		return "redirect:/contatos";
	}
	
	@GetMapping("/contatos/{id}/editar")
	public ModelAndView editar(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("formulario");
		
		Contato contato = contatos.findOne(id);
		
		modelAndView.addObject("contato", contato);
		
		return modelAndView;
	}
	
	@PutMapping("/contatos/{id}")
	public String atualizar(Contato contato, RedirectAttributes attributes) {
		contatos.save(contato);

        attributes.addFlashAttribute("mensagem", "Alterado com sucesso!");
		
		return "redirect:/contatos";
	}
	
	@DeleteMapping("/contatos/{id}")
	public String remover(@PathVariable Long id) {
		contatos.delete(contatos.getOne(id));
		
		return "redirect:/contatos";
	}
}
