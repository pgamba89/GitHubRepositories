package com.example.githubrepositories.ui.gitHubList

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubrepositories.R
import com.example.githubrepositories.databinding.FragmentGitHubListBinding
import com.example.githubrepositories.utils.Constants.Companion.DEFAULT_QUERY
import com.example.githubrepositories.utils.Constants.Companion.LAST_SEARCH_QUERY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GitHubRepositoriesList : Fragment(), SearchView.OnQueryTextListener {

    private val viewModel: GitHubModelView by viewModels()

    private lateinit var binding: FragmentGitHubListBinding
    private lateinit var adapter: GitHubListAdapter

    private var searchView: SearchView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu, menu)

        val item = menu.findItem(R.id.action_search)
        searchView = item.actionView as SearchView
        searchView!!.setOnQueryTextListener(this)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_git_hub_list, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        adapter = GitHubListAdapter(ListItemListener {
            view?.findNavController()
                ?.navigate(
                    GitHubRepositoriesListDirections.actionGitHubRepositoriesListToGitHubDetailFragment(it)
                )
        })

        binding.recyclerviewlist.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.recyclerviewlist.adapter = adapter

        val query = savedInstanceState?.getString(LAST_SEARCH_QUERY) ?: DEFAULT_QUERY
        viewModel.getRepositoriesList(query)

        subscribeObservers()
        return binding.root
    }

    private fun subscribeObservers() {
        viewModel.currentRepositories?.observe(viewLifecycleOwner, {
            it?.let {
                adapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            viewModel.getRepositoriesList(query)
            binding.recyclerviewlist.scrollToPosition(0)
            subscribeObservers()
            searchView?.clearFocus()
        }
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }
}