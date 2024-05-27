package com.fiap.lanchoneteapi.application.adapters.controllers;

import com.fiap.lanchoneteapi.application.entities.dtos.PixPaymentDTO;
import com.fiap.lanchoneteapi.application.entities.dtos.PixPaymentResponseDTO;
import com.fiap.lanchoneteapi.infrastructure.service.PixPaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("pagamentos")
public class PixPaymentController {

    private final PixPaymentService pixPaymentService;

    public PixPaymentController(PixPaymentService pixPaymentService) {
        this.pixPaymentService = pixPaymentService;
    }

    @PostMapping("/processar")
    @Operation(summary = "Endpoint para processamento de pagamento via MercadoPago", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pagamento processado com sucesso")
    })
    public ResponseEntity<PixPaymentResponseDTO> processPayment(@RequestBody @Valid PixPaymentDTO pixPaymentDTO) {
        PixPaymentResponseDTO payment = pixPaymentService.processPayment(pixPaymentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(payment);
    }
}
