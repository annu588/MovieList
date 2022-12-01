package com.app.moviedemo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.moviedemo.R
import com.app.moviedemo.data.model.Search
import com.app.moviedemo.databinding.MovieItemLayoutBinding

class MovieAdapter(var list: List<Search>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    inner class MovieViewHolder(val binding: MovieItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)



    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MovieViewHolder {
        val mBinding: MovieItemLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(viewGroup.context),
            R.layout.movie_item_layout,
            viewGroup,
            false
        )
        return MovieViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        holder.binding.data=list[position]

    }

    fun update(it: List<Search>) {
        this.list=it
   notifyDataSetChanged()
    }
}