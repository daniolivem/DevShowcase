package com.example.DevShowcase.controller;

import com.example.DevShowcase.dto.ErrorResponseDTO;
import com.example.DevShowcase.dto.ProfileRequestDTO;
import com.example.DevShowcase.dto.ProfileResponseDTO;
import com.example.DevShowcase.dto.ValidationErrorResponseDTO;
import com.example.DevShowcase.service.ProfileService;
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
@RequestMapping("/api/profiles")
@Tag(
        name = "Perfis",
        description = "Endpoints para gerenciamento dos perfis de desenvolvedores"
)
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar perfil por ID",
            description = "Retorna um perfil cadastrado a partir do seu ID"
    )
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Perfil encontrado com sucesso",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(
                                implementation = ProfileResponseDTO.class
                        )
                )
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Perfil não encontrado",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(
                                implementation = ErrorResponseDTO.class
                        ),
                        examples = @ExampleObject(
                                value = """
                                {
                                  "timestamp": "2026-09-22T14:03:27.8478373",
                                  "status": 404,
                                  "error": "Not Found",
                                  "message": "Perfil não encontrado com id: 999"
                                }
                                """
                        )
                )
        )
    })
    public ResponseEntity<ProfileResponseDTO> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                profileService.buscarPorId(id)
        );
    }

    @PostMapping
    @Operation(
            summary = "Cadastrar perfil",
            description = "Cadastra um novo perfil de desenvolvedor"
    )
    @ApiResponses({
        @ApiResponse(
                responseCode = "201",
                description = "Perfil cadastrado com sucesso",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(
                                implementation = ProfileResponseDTO.class
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
                                    "name": "O nome não pode estar em branco",
                                    "bio": "A bio não pode estar em branco",
                                    "githubUrl": "A URL do GitHub deve ser válida"
                                  }
                                }
                                """
                        )
                )
        )
    })
    public ResponseEntity<ProfileResponseDTO> criar(
            @Valid @RequestBody ProfileRequestDTO requestDTO) {

        ProfileResponseDTO profile
                = profileService.criar(requestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(profile);
    }
}
