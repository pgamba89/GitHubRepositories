package com.example.githubrepositories.gitHubList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepositories.databinding.FragmentGitHubItemBinding
import com.example.githubrepositories.model.Repository

class GitHubListAdapter(private val clickListener: ListItemListener) :
    PagingDataAdapter<Repository, GitHubListAdapter.ViewHolder>(ItemsDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: FragmentGitHubItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Repository, clickListener: ListItemListener) {
            binding.item = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FragmentGitHubItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class ItemsDiffCallback : DiffUtil.ItemCallback<Repository>() {

    override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return oldItem == newItem
    }
}

class ListItemListener(val clickListener: (repository: Repository) -> Unit) {
    fun onClick(repository: Repository) = clickListener(repository)
}