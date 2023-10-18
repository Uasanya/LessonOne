package com.example.lessonone.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lessonone.databinding.ItemElementBinding
import com.example.lessonone.element.Element

class ListAdapter(private val elementListener: ElementListener) :
    RecyclerView.Adapter<ListAdapter.ElementViewHolder>() {

    private var elements: List<Element> = emptyList()

    fun setElements(elements: List<Element>) {
        this.elements = elements
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElementViewHolder {
        val binding = ItemElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ElementViewHolder(binding)
    }

    override fun getItemCount(): Int = elements.size

    override fun onBindViewHolder(holder: ElementViewHolder, position: Int) {
        holder.bind(elements[position])
    }

    inner class ElementViewHolder(private val binding: ItemElementBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(element: Element) {
            binding.tvItem.text = element.name
            binding.viewHolder.setOnClickListener {
                elementListener.navigateToElement(element)
            }
        }
    }
}