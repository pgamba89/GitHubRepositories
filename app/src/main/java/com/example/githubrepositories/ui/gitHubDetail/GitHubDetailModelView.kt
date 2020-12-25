package com.example.githubrepositories.ui.gitHubDetail

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.githubrepositories.model.Repository

class GitHubDetailModelView @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _repoSelected = MutableLiveData<Repository>()

    val repoSelected: LiveData<Repository>
        get() = _repoSelected

    init {
        val repo = savedStateHandle.get<Repository>("repoSelected")!!
        _repoSelected.value = repo
    }
}