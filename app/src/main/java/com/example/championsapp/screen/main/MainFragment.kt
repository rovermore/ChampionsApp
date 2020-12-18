package com.example.championsapp.screen.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.championsapp.ChampionsApp
import com.example.championsapp.R
import com.example.championsapp.databinding.FragmentMainBinding
import com.example.championsapp.utils.ScreenState
import com.example.championsapp.utils.gone
import com.example.championsapp.utils.visible
import javax.inject.Inject


class MainFragment : Fragment(){

    @Inject
    lateinit var mainViewModel: MainViewModel

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private var adapter = MainAdapter(null)
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.initialize()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        layoutManager = LinearLayoutManager(context)
        setupObservers()
        return binding.root
    }

    init { ChampionsApp.daggerAppComponent().inject(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupReloadButton()
        setUpNavigationToFloatingButton()
    }

    private fun setUpNavigationToFloatingButton() {
        binding.fab.apply {
            visible()
            setOnClickListener { navigateToDetailFragment() }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupReloadButton() {
        binding.errorView.reloadButton.setOnClickListener {
            adapter.clearMainAdapter()
            mainViewModel.initialize()
        }
    }

    private fun setupObservers() {
        observeData()
        observeState()
    }

    private fun observeState() {
        mainViewModel.uiState.observe(viewLifecycleOwner, Observer {
            updateUI(it)
        })
    }

    private fun observeData() {
        mainViewModel.uiModel.observe(viewLifecycleOwner, Observer {championTeamList ->
            if (championTeamList == null) updateUI(ScreenState.Loading)
            if (championTeamList.isEmpty()) updateUI(ScreenState.Error)
            if (!championTeamList.isNullOrEmpty()) {
                adapter.updateTransactionList(championTeamList.toMutableList())
                updateUI(ScreenState.Success)
            }
        })
    }

    private fun setupUI() {
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        recyclerView = binding.mainRecycler
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
    }

    private fun updateUI(screenState: ScreenState) {
        when (screenState) {
            ScreenState.Error -> {
                binding.apply {
                    mainRecycler.gone()
                    progressBar.gone()
                    errorView.apply {
                        errorImage.visible()
                        errorImage.bringToFront()
                        errorText.visible()
                        errorText.text = resources.getString(R.string.error_database)
                        errorText.bringToFront()
                    }
                }
                recyclerView.gone()

            }
            ScreenState.Loading -> {
                binding.apply {
                    progressBar.apply {
                        visible()
                        bringToFront()
                    }
                }
            }
            ScreenState.Success -> {
                binding.fab.visible()
                binding.progressBar.gone()
                recyclerView.visible()
            }
        }
    }

    private fun navigateToDetailFragment() {
        NavHostFragment.findNavController(this).navigate(R.id.action_MaintFragment_to_ConstructorFragment)
    }
}