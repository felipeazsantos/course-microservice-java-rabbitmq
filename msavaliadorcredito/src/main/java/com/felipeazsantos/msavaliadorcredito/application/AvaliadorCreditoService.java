package com.felipeazsantos.msavaliadorcredito.application;

import com.felipeazsantos.msavaliadorcredito.domain.model.DadosCliente;
import com.felipeazsantos.msavaliadorcredito.domain.model.SituacaoCliente;
import com.felipeazsantos.msavaliadorcredito.infra.clients.ClienteResourceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {
    private final ClienteResourceClient clienteResourceClient;

    public SituacaoCliente obterSituacaoCliente(String cpf) {
        //obterDadosCliente - MSCLIENTES
        //obterCartoesDoCliente - MSCARTOES

        ResponseEntity<DadosCliente> dadosClienteResponse = clienteResourceClient.dadosCliente(cpf);

        return SituacaoCliente
                .builder()
                .cliente(dadosClienteResponse.getBody())
                .build();

    }
}

