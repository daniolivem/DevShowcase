package com.example.DevShowcase.controller;

import com.example.DevShowcase.dto.TechnologyRequestDTO;
import com.example.DevShowcase.dto.TechnologyResponseDTO;
import com.example.DevShowcase.dto.ValidationErrorResponseDTO;
import com.example.DevShowcase.service.TechnologyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/technologies")
@Tag(
        name = "Tecnologias",
        description = "Endpoints para gerenciamento das tecnologias"
)
public class TechnologyController {

    @Autowired
    private TechnologyService technologyService;

    @GetMapping
    @Operation(
            summary = "Listar tecnologias",
            description = "Retorna todas as tecnologias cadastradas"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Tecnologias listadas com sucesso",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(
                            schema = @Schema(
                                    implementation = TechnologyResponseDTO.class
                            )
                    )
            )
    )
    public ResponseEntity<List<TechnologyResponseDTO>> listar() {

        return ResponseEntity.ok(
                technologyService.listar()
        );
    }

    @PostMapping
    @Operation(
            summary = "Cadastrar tecnologia",
            description = "Cadastra uma nova tecnologia"
    )
    @ApiResponses({
        @ApiResponse(
                responseCode = "201",
                description = "Tecnologia cadastrada com sucesso",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(
                                implementation = TechnologyResponseDTO.class
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
                                  "timestamp": "2026-09-22T14:00:00",
                                  "status": 400,
                                  "error": "Bad Request",
                                  "message": "Erro de validação",
                                  "fields": {
                                    "name": "O nome da tecnologia não pode estar em branco"
                                  }
                                }
                                """
                        )
                )
        )
    })
    public ResponseEntity<TechnologyResponseDTO> criar(
            @Valid @RequestBody TechnologyRequestDTO requestDTO) {

        TechnologyResponseDTO technology
                = technologyService.criar(requestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(technology);
    }
}
