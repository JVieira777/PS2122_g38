package pt.isel.WebApp.lib.services.database.Entity

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "refundform")
data class RefundForm(
    @Id
    val id: UUID = UUID.randomUUID(),
    val client_id: UUID,
    val exchange_id: Long,
    var description: String
) {
    constructor() : this(UUID(0L, 0L), UUID(0L, 0L), 0, "")


}