package com.example.githubrepositories.ui.gitHubList

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.githubrepositories.model.Repository
import com.example.githubrepositories.data.GitHubRepository

class GitHubModelView @ViewModelInject constructor(private val repository: GitHubRepository) :
    ViewModel() {

    private var currentQueryValue: String? = null

    var currentRepositories: LiveData<PagingData<Repository>>? = null

    fun getRepositoriesList(queryString: String) {
        val lastResult = currentRepositories
        if (!(queryString == currentQueryValue && lastResult != null)) {
            currentQueryValue = queryString
            val newResult: LiveData<PagingData<Repository>> =
                repository.getRepositories(queryString).cachedIn(viewModelScope)
            currentRepositories = newResult
        }
    }
}