package com.example.championsapp.screen.detail

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.championsapp.ChampionsApp
import com.example.championsapp.R
import com.example.championsapp.databinding.AlertDialogEditTextBinding
import com.example.championsapp.databinding.FragmentConstructorBinding
import com.example.championsapp.model.Champion
import com.example.championsapp.screen.bottom.BottomSheetFragment
import com.example.championsapp.utils.ScreenState
import com.example.championsapp.utils.gone
import com.example.championsapp.utils.visible
import javax.inject.Inject


class ConstructorFragment : Fragment(), BoardAdapter.OnItemClicked {

    @Inject
    lateinit var constructorViewModel: ConstructorViewModel

    private var _binding: FragmentConstructorBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private var adapter = BoardAdapter(ConstructorViewModel.inflateInnerPlaceHolderInnerBoardList(), this)
    private lateinit var layoutManager: GridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConstructorBinding.inflate(inflater, container, false)
        layoutManager = GridLayoutManager(context,5)
        invokeBottomFragment()
        setupObservers()
        return binding.root
    }

    private fun invokeBottomFragment() {
        BottomSheetFragment.newInstance(constructorViewModel).show(requireActivity().supportFragmentManager,"bottom sheet")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupReloadButton()
        setupSaveButton()
        setUpCloseButton()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    init {
        ChampionsApp.daggerAppComponent().inject(this)
    }

    private fun setUpCloseButton() {
        binding.closeImageView.setOnClickListener {
            returnToMainFragment()
        }
    }

    private fun setupSaveButton() {
        binding.saveButton.setOnClickListener {
            showSaveDialog()
        }
    }

    private fun showSaveDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.alert_dialog_edit_text, null)
        val alertBinding = AlertDialogEditTextBinding.bind(view)
        builder.setView(view)
        builder.setMessage(R.string.naming_message)
        builder.setPositiveButton(R.string.save_button,
            DialogInterface.OnClickListener { dialog, id ->
                constructorViewModel.saveChampionTeam(alertBinding.alertEditText.text.toString())
                dialog?.dismiss()
                returnToMainFragment()
            })
        builder.setNegativeButton(R.string.cancel_button,
            DialogInterface.OnClickListener { dialog, id ->
                dialog?.dismiss()
            })
        val alertDialog = builder.create()
        alertDialog.show()
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
        constructorViewModel.boardList.observe(viewLifecycleOwner, Observer {
            adapter.updateChampionList(it)
        })
    }

    private fun setupUI() {
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        recyclerView = binding.boardRecycler
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
            ScreenState.Success -> {
                binding.apply {
                    progressBar.gone()
                    boardRecycler.visible()
                }
            }
        }
    }

    override fun itemClicked(champion: Champion, position: Int) {
        if (champion.image != "a")
            constructorViewModel.deleteChampion(position)
        else
            invokeBottomFragment()
    }

    private fun returnToMainFragment() {
        constructorViewModel.clearInnerList()
        NavHostFragment.findNavController(this).navigate(R.id.action_ConstructorFragment_to_MainFragment)

    }
}