package br.edu.unoesc.repositories;

import br.edu.unoesc.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository  extends JpaRepository<Evento, Long> {
}
