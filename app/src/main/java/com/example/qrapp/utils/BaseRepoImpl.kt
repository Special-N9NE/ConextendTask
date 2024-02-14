package com.example.qrapp.utils

abstract class BaseRepoImpl {
    fun <T> runWithTryCatch(repoCallback: RepoCallback<T>, block: () -> T) {
        try {
            val result = block()
            repoCallback.onSuccessful(result)
        } catch (e: Exception) {
            e.printStackTrace()
            repoCallback.onError(e.message.toString())
        }
    }
}