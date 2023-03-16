package com.felipeazsantos.msavaliadorcredito.application;

import com.felipeazsantos.msavaliadorcredito.application.ex.DadosClienteNotFoundException;
import com.felipeazsantos.msavaliadorcredito.application.ex.ErroComunicacaoMicroservicesException;
import com.felipeazsantos.msavaliadorcredito.domain.model.CartaoCliente;
import com.felipeazsantos.msavaliadorcredito.domain.model.DadosCliente;
import com.felipeazsantos.msavaliadorcredito.domain.model.SituacaoCliente;
import com.felipeazsantos.msavaliadorcredito.infra.clients.CartoesResourceClient;
import com.felipeazsantos.msavaliadorcredito.infra.clients.ClienteResourceClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {
    private final ClienteResourceClient clienteResourceClient;
    private final CartoesResourceClient cartoesResourceClient;

    public SituacaoCliente obterSituacaoCliente(String cpf)
            throws DadosClienteNotFoundException, ErroComunicacaoMicroservicesException {
        try {
            ResponseEntity<DadosCliente> dadosClienteResponse = clienteResourceClient.dadosCliente(cpf);
            ResponseEntity<List<CartaoCliente>> cartoesClienteResponse = cartoesResourceClient.getCartoesByCliente(cpf);

            return SituacaoCliente
                    .builder()
                    .cliente(dadosClienteResponse.getBody())
                    .cartoes(cartoesClienteResponse.getBody())
                    .build();
        } catch (FeignException.FeignClientException e) {
            int status = e.status();
            if (HttpStatus.NOT_FOUND.value() == status) {
                throw new DadosClienteNotFoundException();
            }
            throw new ErroComunicacaoMicroservicesException(e.getMessage(), status);
        }

    }
}

