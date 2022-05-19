package com.example.mapapplication.adapters

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mapapplication.R
import com.example.mapapplication.database.AppDatabase
import com.example.mapapplication.model.Location

class LocationAdapter (private val mList: List<Location>) : RecyclerView.Adapter<LocationAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocationAdapter.ViewHolder, position: Int) {
        val location = mList[position]
        holder.textView.text = location.name
        val bundel = Bundle()
        bundel.putString("latitude",location.latitude)
        bundel.putString("longitude",location.longitude)
        bundel.putString("name",location.name)
        holder.textView.setOnClickListener{
            it.findNavController().navigate(R.id.action_viewFragment_to_mapFragment,bundel)
        }
        holder.btnDelete.setOnClickListener{
            val builder = AlertDialog.Builder(it.context)
            builder.setMessage("Are you sure you want to Delete?")
                .setCancelable(false)
                .setPositiveButton("Yes"){ dialog, id ->
                    val db = AppDatabase.getDatabase(it.context)
                    db.locationDao().delete(location)
                }
                .setNegativeButton("No"){ dialog, id ->
                    dialog.dismiss()
                }
            val alert = builder.create()
            alert.show()
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
        val btnDelete: TextView = itemView.findViewById(R.id.btnDelete)
    }

}
