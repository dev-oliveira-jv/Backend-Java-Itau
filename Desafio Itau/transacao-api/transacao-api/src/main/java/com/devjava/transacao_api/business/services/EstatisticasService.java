package com.devjava.transacao_api.business.services;

import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.springframework.stereotype.Service;

import com.devjava.transacao_api.controller.dtos.EstatisticasResponseDTO;
import com.devjava.transacao_api.controller.dtos.TransacaorequestDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstatisticasService {

    public final TransacaoService transacaoService;

    public EstatisticasResponseDTO calcularEstatisticasTransacoes(Integer intervaloBusca){
        
        log.info("Iniciada busca de estatisticas de transações pelo periodo de tempo" + intervaloBusca);
        List<TransacaorequestDTO> transacoes = transacaoService.buscarTransacoes(intervaloBusca);

        if (transacoes.isEmpty()) {
            return new  EstatisticasResponseDTO(0L, 0.0, 0.0, 0.0, 0.0);
        }

        DoubleSummaryStatistics estatisticasTransacoes = transacoes.stream().mapToDouble(TransacaorequestDTO::valor).summaryStatistics();

        log.info("Estatisticas retornadas com sucesso");

        return new EstatisticasResponseDTO(estatisticasTransacoes.getCount()
                                            ,estatisticasTransacoes.getSum()
                                            ,estatisticasTransacoes.getAverage()
                                            ,estatisticasTransacoes.getMin()
                                            ,estatisticasTransacoes.getMax());
    }
}
