package com.training.coroutinepackdemo.ui.newsList.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.training.coroutinepackdemo.R
import com.training.coroutinepackdemo.data.local.entity.NewsEntity
import kotlinx.android.synthetic.main.item_layout.view.*

class NewsAdapter(
    private val newsList: ArrayList<NewsEntity>
) : RecyclerView.Adapter<NewsAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(news: NewsEntity) {
            itemView.textViewTitle.text = news.title
            itemView.textViewDescription.text = news.description
            itemView.textViewSource.text = news.source
            Glide.with(itemView.imageViewBannner.context)
                .load(news.urlToImage)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .into(itemView.imageViewBannner)

            itemView.setOnClickListener {
                val builder = CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(it.context, Uri.parse(news.url))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(newsList[position])

    fun addData(list: List<NewsEntity>) {
        newsList.addAll(list)
    }

}
