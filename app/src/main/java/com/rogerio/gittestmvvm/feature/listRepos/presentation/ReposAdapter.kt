package com.rogerio.xingtest.feature.listRepos.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.rogerio.xingtest.R
import com.rogerio.xingtest.databinding.ItemRepositorieBinding
import com.rogerio.xingtest.feature.listRepos.presentation.model.GitRepoViewEntity

class ReposAdapter: RecyclerView.Adapter<ReposAdapter.ViewHolder>() {

    private var reposList: List<GitRepoViewEntity> = emptyList()
    var loading: Int = 0
    var repo: Int = 1
    var onClickRepo: ((GitRepoViewEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (viewType == loading) {
            LoadingViewHolder(parent)
        } else {
            RepoViewHolder(parent)
        }
    }


    override fun getItemViewType(position: Int) =
        if (reposList.get(position).loading) loading else repo

    override fun getItemCount(): Int = reposList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is RepoViewHolder && reposList.size > position) {
            holder.bind(reposList[position])
            holder.itemView.setOnClickListener {
                onClickRepo?.invoke(reposList[position])
            }
        }
    }

    companion object {
        @JvmStatic
        @BindingAdapter("repos")
        fun RecyclerView.bindItems(items: List<GitRepoViewEntity>) {
            val adapter = adapter as ReposAdapter
            adapter.update(items)
        }
    }

    private fun update(items: List<GitRepoViewEntity>) {
        this.reposList = items
        notifyDataSetChanged()
    }


    abstract class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    class RepoViewHolder(
        private val parent: ViewGroup,
        private val binding: ItemRepositorieBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_repositorie,
            parent,
            false
        )
    ) : ViewHolder(binding.root) {
        fun bind(item: GitRepoViewEntity) {
            binding.viewData = item

        }
    }

    class LoadingViewHolder(
        private val parent: ViewGroup,
        view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.item_loading,
            parent,
            false
        )) : ViewHolder(view)

}

fun RecyclerView.onClickItem(onClick: (GitRepoViewEntity) -> Unit) {

}
