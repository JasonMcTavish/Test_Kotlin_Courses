package ru.test.testkotlincourses.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.test.testkotlincourses.R
import ru.test.testkotlincourses.databinding.FragmentHomeBinding
import ru.test.testkotlincourses.ui.adapter.CourseAdapter
import ru.test.testkotlincourses.ui.state.StateLoading


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupSortButton()
    }

    private fun setupRecyclerView() {
        val adapterCourse = CourseAdapter(
            onItemClick = { setUpBundleAndNavigate(it.id) },
            onFavoriteClick = { viewModel.changeFavoriteStateById(it.id) }
        )

        binding.coursesRecyclerView.apply {
            adapter = adapterCourse
            layoutManager = LinearLayoutManager(requireContext())
        }

        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is StateLoading.Success -> {
                        binding.coursesRecyclerView.isVisible = true
                        viewModel.courses.observe(viewLifecycleOwner) { courses ->
                            Log.d("listCourses", "onCreateView: $courses")
                            adapterCourse.submitList(courses)
                            updateSortButtonText()
                        }
                    }

                    is StateLoading.Loading -> {
                        binding.coursesRecyclerView.isVisible = false
                    }

                    else -> {
                        binding.coursesRecyclerView.isVisible = false
                    }

                }
            }
        }
    }

    private fun setupSortButton() {
        binding.sortText.setOnClickListener {
            viewModel.toggleSortOrder()
            updateSortButtonText()
        }
    }

    private fun updateSortButtonText() {
        binding.sortText.text = viewModel.getSortText()
    }

    private fun setUpBundleAndNavigate(id: Int) {
        val bundle = bundleOf("id" to id)
        findNavController().navigate(R.id.action_home_to_course, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
