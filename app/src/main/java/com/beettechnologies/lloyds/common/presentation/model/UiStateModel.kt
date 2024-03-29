package com.beettechnologies.lloyds.common.presentation.model

sealed class UiStateModel {
    data object Loading : UiStateModel()
    data class Success(val value: Any) : UiStateModel()
    data class Error(val errorMessage: String) : UiStateModel()
}
