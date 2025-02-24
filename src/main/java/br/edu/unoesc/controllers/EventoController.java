package br.edu.unoesc.controllers;

import br.edu.unoesc.entities.Evento;
import br.edu.unoesc.services.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping("/eventos/cadastrar")
    public String evento(Model model) {
        Evento evento = new Evento();
        model.addAttribute("evento", evento);
        return "eventos/index";
    }

    @GetMapping("/eventos")
    public String listarEventos(Model model) {
        List<Evento> eventos = eventoService.listarEventos();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        eventos.forEach(evento -> {
            boolean isParticipando = evento.getParticipantes().stream()
                    .anyMatch(user -> user.getUsername().equals(username));
            evento.setCurrentUserParticipating(isParticipando);
        });

        model.addAttribute("eventos", eventos);
        return "eventos/listar-eventos";
    }

    @PostMapping("/api/eventos/register")
    public ResponseEntity<Evento> cadastrarEvento(@ModelAttribute Evento evento) {
        try {
            evento = eventoService.cadastrarEvento(evento);
            return ResponseEntity.ok(evento);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PostMapping("/participar")
    public String participarEvento(@RequestParam("eventoId") Long eventoId, RedirectAttributes redirectAttributes) {
        Evento evento = eventoService.buscarEventoPorId(eventoId);
        if (evento != null) {
            eventoService.participarDoEvento(evento); // Método para registrar a participação
            redirectAttributes.addFlashAttribute("mensagem", "Você participou do evento com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("mensagem", "Evento não encontrado.");
        }
        return "redirect:/eventos";
    }

    @GetMapping("/eventos/{id}/participantes")
    public String listarParticipantes(@PathVariable Long id, Model model) {
        Evento evento = eventoService.buscarEventoPorId(id);
        if (evento != null) {
            model.addAttribute("evento", evento);
            model.addAttribute("participantes", evento.getParticipantes());
        } else {
            model.addAttribute("mensagem", "Evento não encontrado.");
        }
        return "listar-participantes";
    }

}
