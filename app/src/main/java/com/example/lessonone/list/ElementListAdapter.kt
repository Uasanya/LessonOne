package com.example.lessonone.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lessonone.databinding.ItemElementBinding
import com.example.lessonone.element.Element

class ElementListAdapter(private val onItemCLicked: (element: Element) -> Unit) :
    ListAdapter<Element, ElementViewHolder>(MyItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementViewHolder {
        val binding = ItemElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ElementViewHolder(binding, onItemCLicked)
    }

    override fun onBindViewHolder(holder: ElementViewHolder, position: Int) {

        holder.bind(getItem(position))
    }

    class MyItemDiffCallback : DiffUtil.ItemCallback<Element>() {
        override fun areItemsTheSame(oldItem: Element, newItem: Element): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Element, newItem: Element): Boolean {
            TODO("Not yet implemented")
        }
    }
}

class ElementViewHolder(
    private val binding: ItemElementBinding,
    private val onItemCLicked: (element: Element) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(element: Element) {
        binding.tvItem.text = element.name
        binding.viewHolder.setOnClickListener {
            onItemCLicked.invoke(element)
        }
    }
}