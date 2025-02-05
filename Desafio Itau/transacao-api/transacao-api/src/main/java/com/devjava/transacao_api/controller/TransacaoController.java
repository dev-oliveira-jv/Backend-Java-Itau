package com.devjava.transacao_api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devjava.transacao_api.business.services.TransacaoService;
import com.devjava.transacao_api.controller.dtos.TransacaorequestDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
// import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequiredArgsConstructor
@RestController
@RequestMapping("/transação")
public class TransacaoController {

    private final TransacaoService transacaoService; 

    @PostMapping
    @Operation(description = "Endpoint responsável por adicionar transações")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Trasação gravada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Erro de requisição"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> adicionarTransacao(@RequestBody TransacaorequestDTO dto ) {
        //TODO: process POST request
        transacaoService.adicionarTransacoes(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping 
    @Operation(description = "Endpoint responsável por deletar transações")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Transação deletada com sucesso"),
        @ApiResponse(responseCode = "422", description = "Campos não atendem os requisitos da transação"),
        @ApiResponse(responseCode = "400", description = "Erro de requisição"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> deletarTransacoes(){

        transacaoService.limparTransacoes();
        return ResponseEntity.ok().build();
    }
}
