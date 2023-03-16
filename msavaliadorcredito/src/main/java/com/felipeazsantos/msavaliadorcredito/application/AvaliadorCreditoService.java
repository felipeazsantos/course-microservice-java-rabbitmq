package com.felipeazsantos.msavaliadorcredito.application;

import com.felipeazsantos.msavaliadorcredito.domain.model.SituacaoCliente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {
    public SituacaoCliente obterSituacaoCliente(String cpf) {
        //obterDadosCliente - MSCLIENTES
        //obterCartoesDoCliente - MSCARTOES
    }
}

