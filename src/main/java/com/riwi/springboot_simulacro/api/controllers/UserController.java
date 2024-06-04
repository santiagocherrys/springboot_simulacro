package com.riwi.springboot_simulacro.api.controllers;

import com.riwi.springboot_simulacro.api.dto.request.UserReq;
import com.riwi.springboot_simulacro.api.dto.response.UserResp;
import com.riwi.springboot_simulacro.api.error_handler.ErrorResponse;
import com.riwi.springboot_simulacro.domain.entities.User;
import com.riwi.springboot_simulacro.infrastructure.abstract_services.IUserService;
import com.riwi.springboot_simulacro.infrastructure.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor
//Con tag podemos cambiar el nombre en swagger
@Tag(name = "User")
public class UserController {

    @Autowired
    private final IUserService userService;

    @Operation(
            summary = "Lista todos los usuarios con paginación",
            description = "Debes enviar la página y el tamaño de la página para recibir todas las variables correspondientes"
    )
    @GetMapping
    public ResponseEntity<Page<UserResp>> getAll(@RequestParam(defaultValue = "1") int page,
                                                @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(this.userService.getAll(page -1,size));
    }

    //ApiResponse nos ayuda a crear un nuevo esquema de respuesta
    @ApiResponse(
            responseCode = "400",
            description = "Cuando el id no es válido",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema( implementation =  ErrorResponse.class)
                    )
            }
    )
    @Operation(
            summary = "Obtiene un usuario por id",
            description = "Obtiene un usuario por id"
    )
    @GetMapping(path = "/{id}")
    public ResponseEntity<UserResp> getById(@PathVariable Integer id){
        return ResponseEntity.ok(this.userService.getById(id));
    }
    @Operation(
            summary = " Crea un usuario",
            description = "Crea un usuario"
    )
    @PostMapping
    public ResponseEntity<UserResp> insert(
            @Validated
            @RequestBody UserReq request
    ){
        return ResponseEntity.ok(this.userService.create(request));
    }

    @Operation(
            summary = "Elimina usuario por ID",
            description = "Elimina usuario por ID"
    )
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Integer id
    ){
        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Actualiza un usuario por ID",
            description = "Actualiza un usuario por ID"
    )
    @PutMapping(path = "/{id}")
    public ResponseEntity<UserResp> update(@PathVariable Integer id,
                                           @Validated
                                           @RequestBody UserReq request){
       return ResponseEntity.ok(this.userService.update(id, request));
    }
}
