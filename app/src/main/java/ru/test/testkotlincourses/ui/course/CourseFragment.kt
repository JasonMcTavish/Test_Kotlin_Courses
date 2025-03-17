package ru.test.testkotlincourses.ui.course

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.test.testkotlincourses.databinding.FragmentCourseBinding

@AndroidEntryPoint
class CourseFragment : Fragment() {

    private val viewModel: CourseViewModel by viewModels()
    private var _binding: FragmentCourseBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCourseBinding.inflate(layoutInflater)
        arguments?.getInt("id")?.let {
            viewModel.getCourseById(it)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.course.collectLatest { course ->
                if (course != null) binding.apply {
                    ratingText.text = course.rating
                    dateText.text = course.startDate
                    titleText.text = course.title
                    aboutText.text = course.text
                    bookmarkButton.isChecked = course.hasLike
                    backButton.setOnClickListener {
                        findNavController().navigateUp()
                    }
                    bookmarkButton.setOnClickListener {
                        viewModel.changeFavoriteStateById(course.id)
                        viewModel.getCourseById(course.id)
                    }
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}