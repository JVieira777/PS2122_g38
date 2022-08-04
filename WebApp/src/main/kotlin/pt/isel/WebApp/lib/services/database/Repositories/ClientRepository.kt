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

    //@Query("call newClient(':id',':name',':email',':password')")
    @Query(value = "call newClient(?1,?2,?3,?4)", nativeQuery = true)
    //fun newClient(@Param("_id") id: UUID,@Param("_name") name: String,@Param("_email") email: String,@Param("_password") password: String)
    fun newClient( id: UUID, name: String, email: String, password: String)



}