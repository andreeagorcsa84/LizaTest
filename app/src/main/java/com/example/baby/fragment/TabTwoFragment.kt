package com.example.baby.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.baby.adapter.ParentAdapter
import com.example.baby.datafactory.ParentDataFactory
import com.example.baby.databinding.FragmentTabTwoBinding

class TabTwoFragment : Fragment() {
    private lateinit var binding: FragmentTabTwoBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTabTwoBinding.inflate(inflater)
        initRecycler()
        return binding.root
    }

    private fun initRecycler(){
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            adapter = ParentAdapter(ParentDataFactory.getParents(5))
        }
    }
}