package com.dani.spring.ecommerce_backend_api.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dani.spring.ecommerce_backend_api.dto.requests.PaymentMethodRequestDto;
import com.dani.spring.ecommerce_backend_api.dto.responses.PaymentMethodResponseDto;
import com.dani.spring.ecommerce_backend_api.entities.payment_method.PaymentMethod;
import com.dani.spring.ecommerce_backend_api.exceptions.DataAlreadyExistsException;
import com.dani.spring.ecommerce_backend_api.services.PaymentMethodService;
import com.dani.spring.ecommerce_backend_api.utils.PaymentMethodUtility;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "7. Metodos de pago", description = "API para gestión de los metodos de pago del usuario")
@RestController
@RequestMapping("/payment-method")
public class PaymentMethodController {

    @Autowired
    PaymentMethodService service;
    
    //GET_MY_ALL
    @Operation(summary = "Obtener todos los metodos de pago de mi usuario")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Metodos de pago obtenidos correctamente",
            content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PaymentMethodResponseDto.class))))
    })
    @GetMapping
    public ResponseEntity<List<PaymentMethodResponseDto>> getAllMyPaymentsMethod(Principal principal){
        //En caso de no tener ningun metodo de pago devolvera un 404
        List<PaymentMethod> payments = service.getAllUserPaymentMethods(principal.getName());
        
        return ResponseEntity.ok(PaymentMethodUtility.getPaymentMethodResponeList(payments));
    }

    //GET_ONE
    @Operation(summary = "Obtener un metodo de pago de mi usuario")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Metodos de pago obtenido correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentMethodResponseDto.class))),
        @ApiResponse(responseCode = "404", description = "No se ha encontrado el metodo de pago indicado", content = @Content)
    })
    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentMethodResponseDto> getPaymentMethod(@PathVariable Long paymentId, Principal principal){
        //En caso de no tener ningun metodo de pago devolvera un 404
        PaymentMethod payment = service.getPaymentMethodById(paymentId, principal.getName());
        
        return ResponseEntity.ok(PaymentMethodUtility.getPaymentMethodRespone(payment));
    }


    //CREATE
    @Operation(summary = "Agregar un nuevo metodo de pago")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Metodo de pago agregado correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentMethodResponseDto.class))),
        @ApiResponse(responseCode = "400", description = "Datos inválidos o error de validación", content = @Content)
    })
    @PostMapping
    public ResponseEntity<PaymentMethodResponseDto> createPaymentMethod(@Valid @RequestBody PaymentMethodRequestDto request, Principal principal){
        PaymentMethod response = null;
        //Buscamos si el metodo ya existe y lo tiene deshabilitado
        PaymentMethod paymentDb = service.getByUserAndLast4(request.getLast4(), principal.getName());
        if (paymentDb != null){
            //En caso de existir y estar habilitado informamos de que ya existe el metodo de pago
            if (paymentDb.isEnabled()){
                throw new DataAlreadyExistsException(String.format("El metodo de pago termiando en ****%s ya se encuentra asociado a tu usuario", paymentDb.getLast4()));
            } else{
                //En caso de estar deshabilitado lo habilitamos y guardamos
                paymentDb.setEnabled(true);
                response = service.savePaymentMethod(paymentDb);
            }
        } else{
            //Si no existe creamos uno nuevo
            response = service.createPaymentMethod(request, principal.getName());
        }
        
        return ResponseEntity.ok(PaymentMethodUtility.getPaymentMethodRespone(response));
    }

    //BAJA
    @Operation(summary = "Dar de baja un metodo de pago")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "El metodo de pago ha sido retirado correctamente",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentMethodResponseDto.class))),
        @ApiResponse(responseCode = "404", description = "No se ha encontrado el metodo de pago indicado", content = @Content)
    })
    @PatchMapping("/disable/{paymentId}")
    public ResponseEntity<Map<String, String>> disablePaymentMethod(@PathVariable Long paymentId, Principal principal){
        service.disablePaymentMethod(paymentId, principal.getName());
        return ResponseEntity.ok(Map.of("message", "El metodo de pago ha sido retirado correctamente"));
    }

    //CHANGE_DEFAULT
    @Operation(summary = "Poner un metodo de pago como default")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "El metodo de pago termiando en **** ha activado como principal",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PaymentMethodResponseDto.class))),
        @ApiResponse(responseCode = "404", description = "No se ha encontrado el metodo de pago indicado", content = @Content)
    })
    @PatchMapping("/default/{paymentId}")
    public ResponseEntity<Map<String, String>> setPaymentAsDefault(@PathVariable Long paymentId, Principal principal){
        PaymentMethod payment = service.setPaymentAsDefault(paymentId, principal.getName());
        return ResponseEntity.ok(Map.of("message", String.format("El metodo de pago termiando en ****%s ha activado como principal", payment.getLast4())));
    }


}