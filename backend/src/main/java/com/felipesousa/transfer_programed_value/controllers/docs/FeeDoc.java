package com.felipesousa.transfer_programed_value.controllers.docs;


import com.felipesousa.transfer_programed_value.dto.bodies.FeeBodyDTO;
import com.felipesousa.transfer_programed_value.dto.response.FeeDTO;
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
@Tag(name = "Transfer Fee")
@RequestMapping(value = "/fees")
public interface FeeDoc {

    @Operation(summary = "Get all fees (paginated)", description = "Retrieve a paginated list of all registered fees.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of fees retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FeeDTO.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardError.class)))
    })
    @GetMapping
    ResponseEntity<Page<FeeDTO>> getAllFeePaginated(Pageable pageable);


    @Operation(summary = "Get fee by ID", description = "Retrieve the details of a specific fee using its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "fee retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FeeDTO.class))),
            @ApiResponse(responseCode = "404", description = "fee not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardError.class)))
    })
    @GetMapping(value = "/{id}")
    ResponseEntity<FeeDTO> getFeePerId(
            @Parameter(description = "ID of the fee", required = true) Long id
    );


    @Operation(summary = "Create a new fee", description = "Create and persist a new fee record.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Fee created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FeeDTO.class))),
            @ApiResponse(responseCode = "404", description = "fee not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardError.class)))
    })
    @PostMapping
    ResponseEntity<FeeDTO> postNewFee(
            @RequestBody @Parameter(description = "Body containing fee data", required = true) FeeBodyDTO dto);


    @Operation(summary = "Update fee by ID", description = "Update the details of an existing fee using its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "fee updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FeeDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request body or parameters",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "404", description = "fee not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardError.class))),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StandardError.class)))
    })
    @PutMapping(value = "/{id}")
    ResponseEntity<FeeDTO> putFeePerId(
            @Parameter(description = "ID of the fee", required = true) Long id,
            @RequestBody @Parameter(description = "Body containing updated fee data", required = true) FeeBodyDTO dto
    );


    @Operation(
            summary = "Delete fee by ID",
            description = "Delete a specific fee record using its ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "fee deleted successfully"),
            @ApiResponse(responseCode = "404", description = "fee not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping(value = "/{id}")
    ResponseEntity<Void> deleteFeePerId(
            @Parameter(description = "ID of the fee", required = true) Long id
    );
}
