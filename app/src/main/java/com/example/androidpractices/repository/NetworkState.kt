package com.example.androidpractices.repository

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}

class NetworkState(val status: Status, val msg: String) {

    companion object {
        val LOADED: NetworkState
        val LOADING: NetworkState
        val ERROR: NetworkState
        val ENDOFLIST: NetworkState
        var errorMessage = ""

        init {
            LOADED =
                NetworkState(
                    Status.SUCCESS,
                    "Success"
                )

            LOADING =
                NetworkState(
                    Status.RUNNING,
                    "Running"
                )

            ERROR =
                NetworkState(
                    Status.FAILED,
                    "Something went wrong"
                )

            ENDOFLIST =
                NetworkState(
                    Status.FAILED,
                    "You have reached he last page"
                )

        }
    }

}