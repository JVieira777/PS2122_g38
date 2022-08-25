package pt.isel.WebApp.lib.services.database.Repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import pt.isel.WebApp.lib.services.database.Entities.Credential

@Repository
interface CredentialRepository  : JpaRepository<Credential, String> {
}