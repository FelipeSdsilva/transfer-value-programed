package com.felipesousa.transfer_programed_value.controllers.docs;

import com.felipesousa.transfer_programed_value.dto.bodies.TransferBodyDTO;
import com.felipesousa.transfer_programed_value.dto.response.BankTransferDTO;
import com.felipesousa.transfer_programed_value.exceptions.StandardError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping(value = "/bank-transfers")
@Tag(name = "Bank Transfer", description = "Endpoints for managing bank transfers")
public interface BankTransferDoc {

    @Operation(
            summary = "Get all bank transfers (paginated)",
            description = "Retrieve a paginated list of all registered bank transfers."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of bank transfers retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardError.class)))
    })
    @GetMapping
    ResponseEntity<Page<BankTransferDTO>> getAllBankTransferPaginated(Pageable pageable);


    @Operation(
            summary = "Get bank transfer by ID",
            description = "Retrieve the details of a specific bank transfer using its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bank transfer retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Bank transfer not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardError.class)))
    })
    @GetMapping(value = "/{id}")
    ResponseEntity<BankTransferDTO> getBankTransferPerId(
            @Parameter(description = "ID of the bank transfer", required = true) Long id
    );


    @Operation(
            summary = "Create a new bank transfer",
            description = "Create and persist a new bank transfer record."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Bank transfer created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardError.class)))
    })
    @PostMapping
    ResponseEntity<BankTransferDTO> postNewBankTrans(
            @RequestBody @Parameter(description = "Body containing bank transfer data", required = true) TransferBodyDTO dto
    );


    @Operation(
            summary = "Update bank transfer by ID",
            description = "Update the details of an existing bank transfer using its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bank transfer updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body or parameters",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "404", description = "Bank transfer not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardError.class)))
    })
    @PutMapping(value = "/{id}")
    ResponseEntity<BankTransferDTO> putBankTransferPerId(
            @Parameter(description = "ID of the bank transfer", required = true) Long id,
            @RequestBody @Parameter(description = "Body containing updated bank transfer data", required = true) TransferBodyDTO dto
    );


    @Operation(
            summary = "Delete bank transfer by ID",
            description = "Delete a specific bank transfer record using its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Bank transfer deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Bank transfer not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardError.class)))
    })
    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> deleteBankTransferPerId(
            @Parameter(description = "ID of the bank transfer", required = true) Long id
    );
}
