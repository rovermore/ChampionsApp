package com.example.championsapp.screen.bottom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.championsapp.ChampionsApp
import com.example.championsapp.databinding.FragmentBottomSheetBinding
import com.example.championsapp.model.Champion
import com.example.championsapp.screen.detail.BoardAdapter
import com.example.championsapp.screen.detail.ConstructorViewModel
import com.example.championsapp.utils.ScreenState
import com.example.championsapp.utils.gone
import com.example.championsapp.utils.visible
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import javax.inject.Inject

class BottomSheetFragment(private val constructorViewModel: ConstructorViewModel) :
    BottomSheetDialogFragment(), BoardAdapter.OnItemClicked {

    companion object {
        fun newInstance(constructorViewModel: ConstructorViewModel) =
            BottomSheetFragment(constructorViewModel)
    }

    private var _binding: FragmentBottomSheetBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private var adapter = BoardAdapter(mutableListOf(), this)
    private lateinit var layoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        constructorViewModel.initialize()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBottomSheetBinding.inflate(inflater, container, false)
        layoutManager = GridLayoutManager(context, 5)
        setupObservers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupReloadButton()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    init {
        ChampionsApp.daggerAppComponent().inject(this)
    }

    private fun setupReloadButton() {
        binding.errorView.reloadButton.setOnClickListener { constructorViewModel.loadData() }
    }

    private fun setupObservers() {
        observeData()
        observeState()
    }

    private fun observeState() {
        constructorViewModel.uiState.observe(viewLifecycleOwner, Observer {
            updateUI(it)
        })
    }

    private fun observeData() {
        constructorViewModel.uiModel.observe(viewLifecycleOwner, Observer {
            adapter.updateChampionList(it.toMutableList())
        })
    }

    private fun setupUI() {
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        recyclerView = binding.championsRecycler
        recyclerView.visibility = View.GONE
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }

    private fun updateUI(it: ScreenState?) {
        when (it) {
            ScreenState.Error -> {
                binding.apply {
                    progressBar.gone()
                    errorView.apply {
                        errorImage.visible()
                        errorText.visible()
                        reloadButton.visible()
                    }
                }
            }
            ScreenState.Loading -> binding.progressBar.visible()
            ScreenState.Success -> {
                binding.apply {
                    progressBar.gone()
                    championsRecycler.visible()
                }
            }
        }
    }

    override fun itemClicked(champion: Champion, position: Int) {
        constructorViewModel.addChampion(champion)
    }
}