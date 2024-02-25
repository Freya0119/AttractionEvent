package com.example.attractions.adapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import coil.load
import com.example.attractions.R
import com.example.attractions.data.AttractionUiState
import com.example.attractions.data.EventUiState
import com.example.attractions.databinding.AttractionCardBinding
import com.example.attractions.databinding.EventDetailBinding

const val RECYCLER_LOG = "RECYCLER_LOG"

// TODO: for argument
const val KEY = "KEY_STRING"

const val TYPE_ATTRACTION = 1
const val TYPE_EVENT = 2

//  TODO: component
fun setListener(name: String, view: View) {
    Log.d(RECYCLER_LOG, "On click: $name!")

    val bundle = Bundle()
    bundle.putString(KEY, name)
//    Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_attractionDetailFragment, bundle)
}

class ShowListAdapter(private val uiState: List<Any>) : RecyclerView.Adapter<ShowListAdapter.ShowListViewHolder>() {
    class ShowListViewHolder(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                Log.d(RECYCLER_LOG, "Init holder clickable.")
            }
        }

        fun bind(name: String) {
            setListener(name, binding.root)
        }
    }

    override fun getItemViewType(position: Int): Int {
        Log.d(RECYCLER_LOG, "Item type: ${uiState[position] is AttractionUiState}")
        return if (uiState[position] is AttractionUiState)
            TYPE_ATTRACTION else TYPE_EVENT
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowListViewHolder {
        Log.d(RECYCLER_LOG, "Create holder with Binding.")

        val binding =
            when (viewType) {
                TYPE_ATTRACTION -> {
                    AttractionCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                }

                else -> {
                    EventDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                }
            }

        return ShowListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShowListViewHolder, position: Int) {
        if (uiState[position] is AttractionUiState) {
            Log.d(RECYCLER_LOG, "Type is att!")

            val item = uiState[position] as AttractionUiState
            val binding = holder.binding as AttractionCardBinding

            binding.attractionCardImage.load(item.image)
            binding.attractionCardName.text = item.name
            binding.attractionCardIntroduction.text = item.introduction
            holder.bind(item.name)
        } else {
            Log.d(RECYCLER_LOG, "Type is eve!")

            val item = uiState[position] as EventUiState
            val binding = holder.binding as EventDetailBinding

            binding.eventTitle.text = item.title
            binding.eventUrl.text = item.url
            binding.eventDescription.text = item.description
            holder.bind(item.title)
        }
    }

    override fun getItemCount(): Int {
        return uiState.size
    }
}