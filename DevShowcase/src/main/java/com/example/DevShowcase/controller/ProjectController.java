package com.example.DevShowcase.controller;

import com.example.DevShowcase.dto.ErrorResponseDTO;
import com.example.DevShowcase.dto.ProjectRequestDTO;
import com.example.DevShowcase.dto.ProjectResponseDTO;
import com.example.DevShowcase.dto.ValidationErrorResponseDTO;
import com.example.DevShowcase.service.ProjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
@Tag(
        name = "Projetos",
        description = "Endpoints para gerenciamento dos projetos"
)
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    @Operation(
            summary = "Listar projetos",
            description = "Retorna os projetos cadastrados com paginação e filtro opcional por tecnologia"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Projetos listados com sucesso",
            content = @Content(
                    mediaType = "application/json",
                    examples = @ExampleObject(
                            value = """
                        {
                          "content": [
                            {
                              "id": 1,
                              "title": "DevShowcase",
                              "description": "API para exibição de portfólios de desenvolvedores",
                              "projectUrl": "https://github.com/exemplo/devshowcase",
                              "profileId": 1,
                              "technologies": [
                                {
                                  "id": 1,
                                  "name": "Java"
                                }
                              ],
                              "averageRating": 4.0,
                              "upvotes": 0
                            }
                          ],
                          "pageable": {
                            "pageNumber": 0,
                            "pageSize": 10,
                            "sort": {
                              "empty": true,
                              "unsorted": true,
                              "sorted": false
                            },
                            "offset": 0,
                            "paged": true,
                            "unpaged": false
                          },
                          "last": true,
                          "totalPages": 1,
                          "totalElements": 1,
                          "first": true,
                          "size": 10,
                          "number": 0,
                          "sort": {
                            "empty": true,
                            "unsorted": true,
                            "sorted": false
                          },
                          "numberOfElements": 1,
                          "empty": false
                        }
                        """
                    )
            )
    )
    public ResponseEntity<Page<ProjectResponseDTO>> listar(
            @Parameter(
                    description = "ID da tecnologia usada como filtro",
                    example = "1"
            )
            @RequestParam(required = false) Long technologyId,
            @ParameterObject Pageable pageable) {

        return ResponseEntity.ok(
                projectService.listar(technologyId, pageable)
        );
    }

    @PostMapping
    @Operation(
            summary = "Cadastrar projeto",
            description = "Cadastra um novo projeto associado a um perfil e às tecnologias informadas"
    )
    @ApiResponses({
        @ApiResponse(
                responseCode = "201",
                description = "Projeto cadastrado com sucesso",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(
                                implementation = ProjectResponseDTO.class
                        ),
                        examples = @ExampleObject(
                                value = """
                        {
                          "id": 1,
                          "title": "DevShowcase",
                          "description": "API para exibição de portfólios de desenvolvedores",
                          "projectUrl": "https://github.com/exemplo/devshowcase",
                          "profileId": 1,
                          "technologies": [
                            {
                              "id": 1,
                              "name": "Java"
                            }
                          ],
                          "averageRating": 0.0,
                          "upvotes": 0
                        }
                        """
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
                          "timestamp": "2026-09-22T13:39:48.4136467",
                          "status": 400,
                          "error": "Bad Request",
                          "message": "Erro de validação",
                          "fields": {
                            "description": "A descrição não pode estar em branco",
                            "title": "O título não pode estar em branco",
                            "profileId": "O ID do perfil é obrigatório",
                            "projectUrl": "A URL do projeto não pode estar em branco"
                          }
                        }
                        """
                        )
                )
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Perfil ou tecnologia não encontrada",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(
                                implementation = ErrorResponseDTO.class
                        ),
                        examples = {
                            @ExampleObject(
                                    name = "Perfil inexistente",
                                    value = """
                                    {
                                      "timestamp": "2026-09-22T14:00:00",
                                      "status": 404,
                                      "error": "Not Found",
                                      "message": "Perfil não encontrado com id: 999"
                                    }
                                    """
                            ),
                            @ExampleObject(
                                    name = "Tecnologia inexistente",
                                    value = """
                                    {
                                      "timestamp": "2026-09-22T14:00:00",
                                      "status": 404,
                                      "error": "Not Found",
                                      "message": "Uma ou mais tecnologias informadas não foram encontradas"
                                    }
                                    """
                            )
                        }
                )
        )
    })
    public ResponseEntity<ProjectResponseDTO> criar(
            @Valid @RequestBody ProjectRequestDTO requestDTO) {

        ProjectResponseDTO project
                = projectService.criar(requestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(project);
    }

    @PutMapping("/{id}/upvote")
    @Operation(
            summary = "Adicionar upvote",
            description = "Incrementa em uma unidade a quantidade de upvotes do projeto"
    )
    @ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Upvote adicionado com sucesso",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(
                                implementation = ProjectResponseDTO.class
                        ),
                        examples = @ExampleObject(
                                value = """
                                {
                                  "id": 1,
                                  "title": "DevShowcase",
                                  "description": "API para exibição de portfólios de desenvolvedores",
                                  "projectUrl": "https://github.com/exemplo/devshowcase",
                                  "profileId": 1,
                                  "technologies": [
                                    {
                                      "id": 1,
                                      "name": "Java"
                                    }
                                  ],
                                  "averageRating": 4.0,
                                  "upvotes": 1
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
                                  "timestamp": "2026-09-22T14:03:27.8478373",
                                  "status": 404,
                                  "error": "Not Found",
                                  "message": "Projeto não encontrado com id: 999"
                                }
                                """
                        )
                )
        )
    })
    public ResponseEntity<ProjectResponseDTO> incrementarUpvote(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                projectService.incrementarUpvote(id)
        );
    }
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Excluir projeto",
            description = "Remove um projeto cadastrado pelo ID correspondente"
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Projeto excluído com sucesso"),
        @ApiResponse(responseCode = "404", description = "Projeto não encontrado", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))
    })
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        projectService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
