package br.edu.unoesc.services;

import br.edu.unoesc.entities.Evento;
import br.edu.unoesc.entities.User;
import br.edu.unoesc.repositories.EventoRepository;
import br.edu.unoesc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UserRepository userRepository;

    public Evento cadastrarEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    public Optional<Evento> obterEventoPorId(Long eventoId) {
        return eventoRepository.findById(eventoId);
    }

    public Evento salvarEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    public List<Evento> listarEventos() {
        return eventoRepository.findAll();
    }

    public Evento buscarEventoPorId(Long id) {
        return eventoRepository.findById(id).orElse(null);
    }

    public void participarDoEvento(Evento evento) {
        Evento eventoExistente = eventoRepository.findById(evento.getId()).orElse(null);

        if (eventoExistente != null) {
            String nomeUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
            User usuario = userRepository.findByUsername(nomeUsuario);

            if (usuario == null) {
                throw new RuntimeException("Usuário não encontrado!");
            }

            if (eventoExistente.getVagasDisponiveis() <= 0) {
                throw new RuntimeException("Não há vagas disponíveis!");
            }

            if (eventoExistente.getParticipantes().contains(usuario)) {
                throw new RuntimeException("Você já está participando deste evento!");
            }

            eventoExistente.getParticipantes().add(usuario);
            eventoExistente.setVagasDisponiveis(eventoExistente.getVagasDisponiveis() - 1);

            eventoRepository.save(eventoExistente);
        }
    }
}
