package com.sd.laborator.processor.registry

import com.sd.laborator.processor.model.PeerInfo

/**
 * Contractul clientului de registru (DIP + ISP). Ascunde detaliile HTTP
 * de restul aplicatiei. Doar trei operatii, strict cele necesare.
 */
interface IRegistryClient {
    /** Inregistreaza acest procesor in registru si returneaza instanta creata (cu id). */
    fun register(): PeerInfo

    /** Sterge din registru procesorul cu id-ul dat. */
    fun deregister(id: String)

    /** Descopera toate procesoarele de flux inregistrate. */
    fun fetchPeers(): List<PeerInfo>
}
