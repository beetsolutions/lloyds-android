package com.beettechnologies.lloyds.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beettechnologies.lloyds.common.data.model.Status
import com.beettechnologies.lloyds.common.presentation.model.UiStateModel
import com.beettechnologies.lloyds.home.domain.interactor.GetMealsInACategoryUseCase
import com.beettechnologies.lloyds.home.domain.model.MealModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealsViewModel @Inject constructor(private val mealsUseCase: GetMealsInACategoryUseCase) : ViewModel() {

    private val _uiState: MutableStateFlow<UiStateModel> = MutableStateFlow(UiStateModel.Loading)
    val uiState: StateFlow<UiStateModel> = _uiState

    fun getMealsByCategory(category: String) {
        viewModelScope.launch {
            mealsUseCase(GetMealsInACategoryUseCase.Params(category)).collectLatest {
                when (it.status) {

                    Status.SUCCESS -> {
                        _uiState.value = UiStateModel.Success(it.data as List<MealModel>)
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
