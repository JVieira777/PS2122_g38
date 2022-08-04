package pt.isel.WebApp.lib.services.database.Entities

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name="credentials")
data class Credential( @Id val email: String, val password: String, val client_id : UUID) {

    constructor():this("","",UUID(0,0))
}