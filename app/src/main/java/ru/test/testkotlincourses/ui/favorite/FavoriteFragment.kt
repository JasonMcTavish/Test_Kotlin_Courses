package ru.test.testkotlincourses.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.test.testkotlincourses.R
import ru.test.testkotlincourses.databinding.FragmentFavoriteBinding
import ru.test.testkotlincourses.ui.adapter.CourseAdapter
import ru.test.testkotlincourses.ui.home.HomeViewModel

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapterCourse = CourseAdapter(onItemClick = { setUpBundleAndNavigate(it.id) },
            onFavoriteClick = { viewModel.changeFavoriteStateById(it.id) })
        binding.favoritesRecyclerView.apply {
            adapter = adapterCourse
            layoutManager = LinearLayoutManager(requireContext())
        }
        viewModel.favorites.observe(viewLifecycleOwner) {
            adapterCourse.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpBundleAndNavigate(id: Int) {
        val bundle = bundleOf("id" to id)
        findNavController().navigate(R.id.action_favorite_to_course, bundle)
    }
}