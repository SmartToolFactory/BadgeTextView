package com.smarttoolfactory.badgetextview.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.smarttoolfactory.badgetextview.BadgeTextView
import com.smarttoolfactory.badgetextview.databinding.FragmentMainBinding
import kotlinx.coroutines.*

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val badgeTextViewList = ArrayList<BadgeTextView>()

        binding.llBadgeContainer.children.forEach {
            if (it is BadgeTextView) badgeTextViewList.add(it)
        }

        var jobTimer: Job? = null

        binding.buttonTimer.setOnClickListener {

            jobTimer?.cancel()

            jobTimer = GlobalScope.launch {

                repeat(100) { time ->
                    withContext(Dispatchers.Main) {
                        badgeTextViewList.forEach {
                            it.setBadgeCount(time + 1, false)
                        }
                    }

                    delay(200)
                }
            }
        }

        binding.buttonSetCount.setOnClickListener {
            val text = binding.etText.text.toString()

            badgeTextViewList.forEach {
                it.setBadgeCount(text)
            }

        }
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}