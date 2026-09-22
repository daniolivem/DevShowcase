package com.example.DevShowcase.controller;

import com.example.DevShowcase.dto.ErrorResponseDTO;
import com.example.DevShowcase.dto.FeedbackRequestDTO;
import com.example.DevShowcase.dto.FeedbackResponseDTO;
import com.example.DevShowcase.dto.ValidationErrorResponseDTO;
import com.example.DevShowcase.service.FeedbackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
@Tag(
        name = "Feedbacks",
        description = "Endpoints para avaliações dos projetos"
)
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/{id}/feedbacks")
    @Operation(
            summary = "Cadastrar feedback",
            description = "Cadastra um comentário e uma nota de 1 a 5 para o projeto e atualiza sua nota média"
    )
    @ApiResponses({
        @ApiResponse(
                responseCode = "201",
                description = "Feedback cadastrado com sucesso",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(
                                implementation = FeedbackResponseDTO.class
                        )
                )
        ),
        @ApiResponse(
                responseCode = "400",
                description = "Dados inválidos",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(
                                implementation = ValidationErrorResponseDTO.class
                        ),
                        examples = @ExampleObject(
                                value = """
                                {
                                  "timestamp": "2026-09-22T13:51:35.6867805",
                                  "status": 400,
                                  "error": "Bad Request",
                                  "message": "Erro de validação",
                                  "fields": {
                                    "rating": "A nota máxima é 5"
                                  }
                                }
                                """
                        )
                )
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Projeto não encontrado",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(
                                implementation = ErrorResponseDTO.class
                        ),
                        examples = @ExampleObject(
                                value = """
                                {
                                  "timestamp": "2026-09-22T13:52:57.2115338",
                                  "status": 404,
                                  "error": "Not Found",
                                  "message": "Projeto não encontrado com id: 999"
                                }
                                """
                        )
                )
        )
    })
    public ResponseEntity<FeedbackResponseDTO> criar(
            @PathVariable Long id,
            @Valid @RequestBody FeedbackRequestDTO requestDTO) {

        FeedbackResponseDTO feedback
                = feedbackService.criar(id, requestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(feedback);
    }
}
