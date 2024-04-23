package com.example.imageviewertestapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.imageviewertestapp.data.mapers.toCatEntity
import com.example.imageviewertestapp.domain.Cat
import com.example.imageviewertestapp.domain.repo.CatLocalRepo
import com.example.imageviewertestapp.domain.repo.CatsPagingRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    catsPagingRepo: CatsPagingRepo,
    private val catLocalRepo: CatLocalRepo
) : ViewModel() {

    val catsPagingFlow = catsPagingRepo.getCats().cachedIn(viewModelScope)
    private var _dialogState = MutableStateFlow(DialogState())
    val dialogState = _dialogState.asStateFlow()

    fun fave(cat: Cat) {
        viewModelScope.launch {
            catLocalRepo.addCat(cat.toCatEntity())
            _dialogState.value = _dialogState.value.copy(isSaved = true)
        }
    }

    fun showImage(cat: Cat) {
        viewModelScope.launch {
            val savedCat = catLocalRepo.getCat(cat.id)
            _dialogState.value = DialogState(
                cat,
                savedCat != null,
                true
            )
        }
    }

    fun hideDialog(){
        _dialogState.value = DialogState()
    }
}

data class DialogState(
    val cat: Cat? = null,
    val isSaved: Boolean? = null,
    val showDialog: Boolean = false
)