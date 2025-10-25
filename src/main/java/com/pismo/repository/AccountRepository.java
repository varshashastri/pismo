package com.pismo.repository;

import com.pismo.entity.Account;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repository for managing Account entities.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * Finds an account by its document number.
     *
     * @param documentNumber the document number to search for
     * @return an Optional containing the account if found
     */
    @Schema(description = "Find an account by its document number")
    Optional<Account> findByDocumentNumber(
            @Parameter(description = "Document number of the account to search") String documentNumber);
}
