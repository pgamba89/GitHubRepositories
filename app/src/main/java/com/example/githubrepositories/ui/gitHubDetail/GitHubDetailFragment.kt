package com.example.githubrepositories.ui.gitHubDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.githubrepositories.R
import com.example.githubrepositories.databinding.FragmentGitHubDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GitHubDetailFragment : Fragment() {

    private val viewModel: GitHubDetailModelView by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentGitHubDetailBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_git_hub_detail, container, false
        )

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }
}