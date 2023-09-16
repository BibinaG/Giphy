package com.example.assignment.androidcommon.handler

import com.example.assignment.androidcommon.base.BaseErrorEntity
import com.example.assignment.androidcommon.extensions.logException
import com.example.assignment.androidcommon.utils.UiState
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response

fun getMessage(error: BaseErrorEntity): String? {
    val errors = error.errors
    return if (errors != null) {
        errors.email?.firstOrNull() ?: errors.password?.firstOrNull()
    } else {
        null
    }
}

suspend fun <T> Response<T>.handleResponse(doActionOnSuccess: suspend (body: T) -> Unit = {}): UiState<T> {
    val genericErrorMessage = "Error encountered. Please try again later."
    return if (isSuccessful || code() in listOf(201)) {
        if (body() != null) {
            doActionOnSuccess.invoke(body()!!)
            UiState.Success(body()!!)
        } else {
            UiState.Error(message())
        }
    } else if (code() in listOf(401)) {
        /// App.instance.forceLogout()
        UiState.Error(message(), code())
    }
    /*else if (code() == 400) {
        loggerE("This is Error ${errorBody().toJson().orEmpty()}")
        UiState.Error(message(), code())
    } */ else {
        val errorBody = errorBody()?.string()
        loggerE(errorBody?.trim())
        return try {
            val errorModel = Gson().fromJson(errorBody, BaseErrorEntity::class.java)
            UiState.Error(
                errorModel.error ?: /*getMessage(errorModel)*/ errorModel.message
                ?: errorModel.detail
                ?: errorModel.errors?.email?.get(0)
                ?: errorModel.errors?.password?.get(0)
                ?: errorModel.nonFieldErrors?.first()
                ?: errorModel.fail
                ?: throw Exception("Thrown to try another approach!")
            )
        } catch (e: Exception) {
            loggerE("On First Exception")
            e.logException()
            //Parse: {"password":["This field may not be blank."]}
            val type = object : TypeToken<Map<String, List<String>>>() {}.type
            val data: Map<String, List<String>> = Gson().fromJson(errorBody, type)
            return if (!data.isNullOrEmpty()) {
                val firstEntry = data.entries.iterator().next()
                val errorMessage = firstEntry.value
                if (errorMessage.isNullOrEmpty()) {
                    UiState.Error(message())
                } else {
                    UiState.Error(errorMessage.first())
                }
            } else {
                UiState.Error(message())
            }
        } catch (e: Exception) {
            loggerE("On Second Exception")
            e.logException()
            val stringArrayType = object : TypeToken<List<String>>() {}.type
            val arrayMessages: List<String> = Gson().fromJson(errorBody, stringArrayType)
            return if (!arrayMessages.isNullOrEmpty()) {
                UiState.Error(arrayMessages[0])
            } else {
                UiState.Error(message())
            }
        } catch (e: Exception) {
            e.logException()
            UiState.Error(genericErrorMessage)
        }
    }
}
