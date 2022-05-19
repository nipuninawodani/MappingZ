package com.example.mapapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mapapplication.adapters.LocationAdapter
import com.example.mapapplication.database.AppDatabase
import com.example.mapapplication.databinding.FragmentViewBinding

class ViewFragment : Fragment() {

    private var _binding: FragmentViewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View,savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.recyclerview
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        val db = AppDatabase.getDatabase(view.context)
        val data = db.locationDao().getAll()
        recyclerView.adapter = LocationAdapter(data)

        binding.btnAddNew.setOnClickListener {
            findNavController().navigate(R.id.action_viewFragment_to_addDataFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}