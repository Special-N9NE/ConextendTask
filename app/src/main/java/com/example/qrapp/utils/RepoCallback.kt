package com.example.qrapp.utils

interface RepoCallback<T> {
    fun onSuccessful(response: T)
    fun onError(error: String)
}