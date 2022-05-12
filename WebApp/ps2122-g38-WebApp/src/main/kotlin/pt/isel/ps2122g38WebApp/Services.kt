package pt.isel.ps2122g38WebApp

import org.springframework.stereotype.Component
import pt.isel.ps2122g38WebApp.services.blockchainServices.blockchainServices
import pt.isel.ps2122g38WebApp.services.dbServices.dbServices

@Component
class Services(dbServices: dbServices, blockchainServices: blockchainServices) {
}

