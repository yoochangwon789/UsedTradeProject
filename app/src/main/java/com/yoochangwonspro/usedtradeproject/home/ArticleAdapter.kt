package com.yoochangwonspro.usedtradeproject.home

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.yoochangwonspro.usedtradeproject.databinding.ItemArticleBinding

class ArticleAdapter : ListAdapter<ArticleModel, ArticleAdapter.ArticleItemViewHolder>(diffUtil) {

    inner class ArticleItemViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleItemViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ArticleItemViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}