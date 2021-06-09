package com.goodylabs.android.interview.ui.characterlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.goodylabs.android.interview.databinding.FragmentCharacterListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    private val viewModel: CharacterListViewModel by viewModels()

    private lateinit var binding: FragmentCharacterListBinding
    private val characterListAdapter = CharacterListAdapter(CharacterListener {
        this.findNavController().navigate(
            CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailsFragment(
                it
            )
        )
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterListBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        val linearLayoutManager = LinearLayoutManager(context)
        binding.listCharacterRecycler.apply {
            adapter = characterListAdapter
            layoutManager = linearLayoutManager
        }

        binding.listCharacterRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = linearLayoutManager.itemCount
                if (totalItemCount == linearLayoutManager.findLastVisibleItemPosition() + 1) {
                    viewModel.loadNextList()
                }
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.listCharacters.observe(viewLifecycleOwner, {
            characterListAdapter.submitList(it)
        })
    }
}
