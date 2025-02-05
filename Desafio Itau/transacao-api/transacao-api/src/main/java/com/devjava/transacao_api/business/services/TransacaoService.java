package com.devjava.transacao_api.business.services;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.devjava.transacao_api.controller.dtos.TransacaorequestDTO;
import com.devjava.transacao_api.infrastructure.exceptions.UnprocessableEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoService {

    private final List<TransacaorequestDTO> listaTransacoes = new ArrayList<>();
    
    public void adicionarTransacoes(TransacaorequestDTO dto){

        log.info("Iniciando o processamento de gravar transações" + dto);

        if (dto.dataHora().isAfter(OffsetDateTime.now())) {
            log.error("Data e Hora maior que Data e Hora atual");
            throw new UnprocessableEntity("Data e Hora maior que Data e hora atual ");
        }
        if (dto.valor() < 0) {
            log.error("Valor não pode ser menor do que zero");
            throw new UnprocessableEntity("Valor não pode ser menor que zero");
        }
        listaTransacoes.add(dto);
        log.info("Transações adicionadas com sucesso");

    }

    public void limparTransacoes(){

        log.info("Iniciado  o processo pra deletar transações");
        listaTransacoes.clear();
        log.info("Transações deletadas com sucesso");

    }

    public List<TransacaorequestDTO> buscarTransacoes( Integer intervaloBusca ){
        log.info("Iniciadas as buscas de transações por tempo" + intervaloBusca);
        OffsetDateTime dataHoraInrervalo = OffsetDateTime.now().minusSeconds(intervaloBusca);

        log.info("Retorno de transações com sucesso");
        return listaTransacoes.stream()
            .filter(transacao -> transacao.dataHora()
                .isAfter(dataHoraInrervalo)).toList();
    }

}
