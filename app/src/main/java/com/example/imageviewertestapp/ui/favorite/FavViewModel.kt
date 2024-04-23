package com.example.imageviewertestapp.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.imageviewertestapp.data.mapers.toCat
import com.example.imageviewertestapp.data.mapers.toCatEntity
import com.example.imageviewertestapp.domain.Cat
import com.example.imageviewertestapp.domain.repo.CatLocalRepo
import com.example.imageviewertestapp.domain.repo.CatsPagingRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FaveViewModel @Inject constructor(
    private val catLocalRepo: CatLocalRepo
) : ViewModel() {

    private var _state = MutableStateFlow(State(emptyList()))
    val state = _state.asStateFlow()


    fun getCatsFromLocal() {
        viewModelScope.launch {
            catLocalRepo.getCats().collectLatest {
                _state.value = _state.value.copy(cats = it.map { it.toCat() })
            }
        }
    }

}

data class State(
    val cats: List<Cat>
)