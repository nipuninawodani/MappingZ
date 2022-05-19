package com.example.mapapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mapapplication.database.AppDatabase
import com.example.mapapplication.databinding.FragmentAddDataBinding
import com.example.mapapplication.model.Location


class AddDataFragment : Fragment() {

    private var _binding: FragmentAddDataBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        _binding = FragmentAddDataBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View,savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        binding.btnSave.setOnClickListener {
            findNavController().navigate(R.id.action_addDataFragment_to_viewFragment)
            var location = Location(binding.editTextName.text.toString(),binding.editTextLatitude.text.toString(),binding.editTextLongitude.text.toString())
            val db = AppDatabase.getDatabase(view.context)
            db.locationDao().insert(location)
        }

    }

    override fun onDestroyView(){
        super.onDestroyView()
        _binding = null
    }

}