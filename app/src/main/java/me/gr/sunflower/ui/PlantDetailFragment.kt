package me.gr.sunflower.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import me.gr.sunflower.R
import me.gr.sunflower.databinding.FragmentPlantDetailBinding
import me.gr.sunflower.util.InjectorUtils
import me.gr.sunflower.viewmodel.PlantDetailViewModel

class PlantDetailFragment : Fragment() {
    private lateinit var shareText: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val plantId = PlantDetailFragmentArgs.fromBundle(arguments).plantId
        val factory = InjectorUtils.providePlantDetailViewModelFactory(requireContext(), plantId)
        val plantDetailViewModel = ViewModelProviders.of(this, factory).get(PlantDetailViewModel::class.java)

        val binding = FragmentPlantDetailBinding.inflate(inflater, container, false).apply {
            viewModel = plantDetailViewModel
            setLifecycleOwner(this@PlantDetailFragment)
            floatButton.setOnClickListener {
                plantDetailViewModel.addPlantToGarden()
                Snackbar.make(it, R.string.added_plant_to_garden, Snackbar.LENGTH_LONG).show()
            }
        }

        plantDetailViewModel.plant.observe(this, Observer {
            shareText = if (it == null) {
                ""
            } else {
                getString(R.string.share_text_plant, it.name)
            }
        })

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_plant_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_share -> {
                val shareIntent = ShareCompat.IntentBuilder.from(activity)
                        .setText(shareText)
                        .setType("text/plain")
                        .createChooserIntent()
                        .apply {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
                            } else {
                                addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)
                            }
                        }
                startActivity(shareIntent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}