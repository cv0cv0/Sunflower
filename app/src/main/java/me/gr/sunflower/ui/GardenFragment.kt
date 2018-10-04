package me.gr.sunflower.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import me.gr.sunflower.adapter.PlantingAdapter
import me.gr.sunflower.databinding.FragmentGardenBinding
import me.gr.sunflower.util.InjectorUtils
import me.gr.sunflower.viewmodel.PlantingListViewModel

class GardenFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentGardenBinding.inflate(inflater, container, false)
        val adapter = PlantingAdapter()
        binding.gardenList.adapter = adapter
        subscribeUi(adapter, binding)
        return binding.root
    }

    private fun subscribeUi(adapter: PlantingAdapter, binding: FragmentGardenBinding) {
        val factory = InjectorUtils.providePlantingListViewModelFactory(requireContext())
        val viewModel = ViewModelProviders.of(this, factory).get(PlantingListViewModel::class.java)

        viewModel.plantings.observe(viewLifecycleOwner, Observer {
            binding.hasPlantings = it != null && it.isNotEmpty()
        })

        viewModel.plantAndPlantingList.observe(viewLifecycleOwner, Observer {
            if (it != null && it.isNotEmpty()) adapter.submitList(it)
        })
    }
}