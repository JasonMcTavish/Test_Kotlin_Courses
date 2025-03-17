package ru.test.testkotlincourses.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.test.domain.models.CourseDomain
import ru.test.testkotlincourses.databinding.CourseItemListBinding
import ru.test.testkotlincourses.utils.formatDate

class CourseAdapter(
    private val onItemClick: (CourseDomain) -> Unit,
    private val onFavoriteClick: (CourseDomain) -> Unit
) : ListAdapter<CourseDomain, CourseAdapter.CourseViewHolder>(CourseDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = CourseItemListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class CourseViewHolder(
        private val binding: CourseItemListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.detailsButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick(getItem(position))
                }
            }

            binding.bookmarkIcon.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val course = getItem(position)
                    binding.bookmarkIcon.isSelected = !course.hasLike
                    onFavoriteClick(course)
                }
            }
        }

        fun bind(course: CourseDomain) {
            with(binding) {
                courseTitle.text = course.title
                courseDescription.text = course.text
                courseRating.text = course.rating.toString()
                courseDate.text = course.startDate.formatDate()
                coursePrice.text = "${course.price} ₽"
                bookmarkIcon.isChecked = course.hasLike
            }
        }
    }

    private class CourseDiffCallback : DiffUtil.ItemCallback<CourseDomain>() {
        override fun areItemsTheSame(oldItem: CourseDomain, newItem: CourseDomain): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CourseDomain, newItem: CourseDomain): Boolean {
            return oldItem == newItem
        }
    }
}