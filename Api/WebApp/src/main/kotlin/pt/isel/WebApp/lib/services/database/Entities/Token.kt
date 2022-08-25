package pt.isel.WebApp.lib.services.database.Entities


import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="tokens")
data class Token(@Id val code: UUID, val expiration: Date, val num_calls : Int, val status : String, val client_id: UUID) {

    constructor() : this(UUID(0,0),Date(),0,"Expired",UUID(0,0))
}