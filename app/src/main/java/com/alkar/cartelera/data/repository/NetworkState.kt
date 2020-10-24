package com.alkar.cartelera.data.repository

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}

class NetworkState (val status: Status, val msg: String) {
    companion object {
        val LOADED: NetworkState
        val LOADING: NetworkState
        val ERROR: NetworkState
        val ENDOFLIST: NetworkState

        init {
            LOADED = NetworkState(Status.SUCCESS,"Completado")
            LOADING = NetworkState(Status.RUNNING,"Ejecutando")
            ERROR = NetworkState(Status.FAILED,"Algo salio mal.")
            ENDOFLIST = NetworkState(Status.FAILED,"Has llegado al final.")
        }
    }
}