package com.laironlf.smartRecipes.presentation.fragments.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.laironlf.smartRecipes.R
import com.laironlf.smartRecipes.databinding.FragmentHomeBinding
import com.laironlf.smartRecipes.domain.models.Recipe
import com.laironlf.smartRecipes.presentation.adapters.recyclerAdapters.RecipeMiniListAdapter
import com.laironlf.smartRecipes.presentation.fragments.home.HomeViewModel.State
import com.laironlf.smartRecipes.presentation.fragments.recipedetailinfo.RecipeDetailInfoFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val newRecipeListAdapter = RecipeMiniListAdapter(onRecipeClick = ::onRecipeClick)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.shflNewRecipes.startShimmer()
        binding.shflTopRating.startShimmer()

        binding.baseCategories.btnBaking.setOnClickListener{ Log.d("TAG", "onViewCreated: BAKERY")}
        subscribeToViewModel()
        setupRecyclerViews()
        setupServiceFunctions()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupServiceFunctions() {
        binding.cvAddRecipe.setOnClickListener {
            findNavController().navigate(
                R.id.action_homeFragment_to_addRecipeFragment
            )
        }
    }

    private fun setupRecyclerViews() {
        binding.recviewNewRecipes.adapter = newRecipeListAdapter
        binding.recviewTopRating.adapter = newRecipeListAdapter
    }

    private fun subscribeToViewModel() {
        viewModel.newRecipesState.observe(viewLifecycleOwner){ state ->
            newRecipeListAdapter.items = if (state is State.Loaded) state.recipes else emptyList()
            binding.shflNewRecipes.visibility = if (state is State.Loading) View.VISIBLE else View.GONE
            binding.shflTopRating.visibility = if (state is State.Loading) View.VISIBLE else View.GONE
            binding.recviewNewRecipes.visibility = if (state is State.Loaded) View.VISIBLE else View.GONE
            binding.recviewTopRating.visibility = if (state is State.Loaded) View.VISIBLE else View.GONE
        }
    }

    private fun onRecipeClick(recipe: Recipe){
        findNavController().navigate(
            R.id.recipeDetailInfoFragment,
            RecipeDetailInfoFragment.createArguments(recipe)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}