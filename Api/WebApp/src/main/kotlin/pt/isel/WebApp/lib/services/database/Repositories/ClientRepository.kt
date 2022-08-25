package pt.isel.WebApp.lib.services.database.Repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.jpa.repository.query.Procedure
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import pt.isel.WebApp.lib.services.database.Entities.Client
import java.util.*

@Repository
interface ClientRepository : JpaRepository<Client, UUID> {






}