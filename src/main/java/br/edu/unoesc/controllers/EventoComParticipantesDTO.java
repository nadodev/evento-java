package br.edu.unoesc.controllers;

import br.edu.unoesc.entities.Evento;
import lombok.Data;

@Data
public class EventoComParticipantesDTO {
    private Evento evento;
    private Long quantidadeParticipantes;

    public EventoComParticipantesDTO(Evento evento, Long quantidadeParticipantes) {
        this.evento = evento;
        this.quantidadeParticipantes = quantidadeParticipantes;
    }


}
