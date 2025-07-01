package com.example.musicytdl

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.core.graphics.toColorInt

class SceneAdapter(
    private val scenes: List<String>,
    private val onSceneSelected: (Int) -> Unit
) : RecyclerView.Adapter<SceneAdapter.SceneViewHolder>() {
    private var selectedPosition = -1

    inner class SceneViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sceneName: TextView = itemView.findViewById(R.id.scene_name)
        init {
            itemView.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val previous = selectedPosition
                    selectedPosition = position
                    notifyItemChanged(previous)
                    notifyItemChanged(selectedPosition)
                    onSceneSelected(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SceneViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_scene, parent, false)
        return SceneViewHolder(view)
    }

    override fun onBindViewHolder(holder: SceneViewHolder, position: Int) {
        holder.sceneName.text = scenes[position]
        holder.itemView.isSelected = (position == selectedPosition)
        holder.itemView.setBackgroundColor(
            if (position == selectedPosition) "#FFDDFF".toColorInt() else Color.TRANSPARENT
        )
    }

    override fun getItemCount(): Int = scenes.size
} 