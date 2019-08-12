package com.example.ridecellpracticaldemo.utils.genericadapter



import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class Holder<B : ViewDataBinding>(val binding: B) : RecyclerView.ViewHolder(binding.root) {
    internal var created = false
}