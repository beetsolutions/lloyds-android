package com.beettechnologies.lloyds.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beettechnologies.lloyds.common.data.model.Status
import com.beettechnologies.lloyds.common.presentation.model.UiStateModel
import com.beettechnologies.lloyds.home.domain.interactor.GetCategoriesUseCase
import com.beettechnologies.lloyds.home.domain.model.CategoryModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val categoriesUseCase: GetCategoriesUseCase) : ViewModel() {

    private val _uiState: MutableStateFlow<UiStateModel> = MutableStateFlow(UiStateModel.Loading)
    val uiState: StateFlow<UiStateModel> = _uiState

    fun getCategories() {
        viewModelScope.launch {
            categoriesUseCase(Unit).collectLatest {
                when (it.status) {
                    Status.SUCCESS -> {
                        _uiState.value = UiStateModel.Success(it.data as List<CategoryModel>)
                    }

                    Status.ERROR -> {
                        _uiState.value = UiStateModel.Error(it.message ?: "Oop! Something went wrong!")
                    }

                    Status.LOADING -> {
                        _uiState.value = UiStateModel.Loading
                    }
                }
            }
        }
    }
}
