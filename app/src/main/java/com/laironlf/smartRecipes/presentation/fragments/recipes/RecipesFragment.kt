package com.laironlf.smartRecipes.presentation.fragments.recipes

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.laironlf.smartRecipes.R
import com.laironlf.smartRecipes.databinding.FragmentRecipesBinding
import com.laironlf.smartRecipes.domain.models.Recipe
import com.laironlf.smartRecipes.presentation.adapters.RecipeListAdapter
import com.laironlf.smartRecipes.presentation.fragments.recipedetailinfo.RecipeDetailInfoFragment
import com.laironlf.smartRecipes.presentation.fragments.recipes.RecipesViewModel.State
import com.laironlf.smartRecipes.presentation.utils.bindTwoWay
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipesFragment : Fragment() {

    private val viewModel: RecipesViewModel by viewModels()

    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!
    private var adapter = RecipeListAdapter(::onItemRecipeClick)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tabLayout.bindTwoWay(viewModel.tabPos, viewLifecycleOwner
        ) { viewModel.onTabClicked() }
        setupRecyclerViewRecipes()
        subscribeToViewModel()
    }

    private fun setupRecyclerViewRecipes() {
        binding.recviewRecipes.layoutManager = LinearLayoutManager(context)
        binding.recviewRecipes.adapter = adapter
    }

    private fun subscribeToViewModel() = viewModel.state.observe(viewLifecycleOwner){ state ->
        adapter.items = if (state is State.Loaded) state.recipes else emptyList()
        binding.recviewRecipes.visibility = if(state is State.Loaded) View.VISIBLE else View.GONE
        binding.progressBar.visibility = if (state is State.Loading) View.VISIBLE else View.GONE
        if (state is State.Loaded) binding.recviewRecipes.startLayoutAnimation()
    }

    private fun onItemRecipeClick(recipe: Recipe){
        Log.d(TAG, "onItemRecipeClick: ${recipe.title} clicked")
        findNavController().navigate(
            R.id.action_recipesFragment_to_recipeDetailInfoFragment,
            RecipeDetailInfoFragment.createArguments(recipe.id, recipe.title)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = RecipesFragment()
    }
}

