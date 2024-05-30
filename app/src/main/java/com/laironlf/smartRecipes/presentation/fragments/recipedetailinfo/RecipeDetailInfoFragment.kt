package com.laironlf.smartRecipes.presentation.fragments.recipedetailinfo

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.laironlf.smartRecipes.databinding.FragmentRecipeDetailInfoBinding
import com.laironlf.smartRecipes.domain.models.Recipe
import com.laironlf.smartRecipes.presentation.adapters.recyclerAdapters.IngredientsAdapter
import com.laironlf.smartRecipes.presentation.adapters.lllists.RecipeStepAdapter
import com.laironlf.smartRecipes.presentation.fragments.recipedetailinfo.RecipeDetailInfoViewModel.RecipeStepState
import com.laironlf.smartRecipes.presentation.fragments.recipedetailinfo.RecipeDetailInfoViewModel.State
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailInfoFragment : Fragment() {

    private val viewModel: RecipeDetailInfoViewModel by viewModels()
    private var _binding: FragmentRecipeDetailInfoBinding? = null
    private val binding get() = _binding!!

    private val recipeId: Int get() = requireArguments().getInt(recipeIdKey)
    private val recipeTitle: String? get() = requireArguments().getString(recipeTitleKey)

    private val ingredientsAdapter = IngredientsAdapter()

    private lateinit var recipeStepAdapter: RecipeStepAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeDetailInfoBinding.inflate(layoutInflater)
        viewModel.recipeId = recipeId
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvRecipeTitle.text = recipeTitle
        recipeStepAdapter = RecipeStepAdapter(binding.llRecipeSteps)
        subscribeOnViewModel()
        setupRecyclerViews()
        viewModel.onRecipeDetailFragmentCreated()
    }

    private fun setupRecyclerViews() {
        binding.recviewRecipeProducts.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        binding.recviewRecipeProducts.adapter = ingredientsAdapter
        binding.recviewRecipeProducts.isNestedScrollingEnabled = false

    }

    @SuppressLint("SetTextI18n")
    private fun subscribeOnViewModel() {
        viewModel.state.observe(viewLifecycleOwner) { state ->
            if(state is State.Loaded) {
                val recipe = state.recipe
                binding.clViewHolder.visibility = View.VISIBLE
                binding.recviewRecipeProducts.visibility = View.VISIBLE
                ingredientsAdapter.ingredients = recipe.ingredients
                ingredientsAdapter.matches = recipe.matchesProducts
                binding.tvDishKkal.text = "${recipe.kcal} ккал"
                binding.tvDishCookTime.text = recipe.time
                binding.tvDishFridge.text = "${recipe.matchesProducts.size} / ${recipe.ingredients.size}"
                Picasso.get()
                    .load(recipe.imageUrl)
                    .into(binding.ivRecipeImage)
            } else {
                binding.clViewHolder.visibility = View.GONE
            }
        }
        viewModel.recipeStepState.observe(viewLifecycleOwner) { state ->
            binding.llRecipeSteps.visibility = if (state is RecipeStepState.Loaded) View.VISIBLE else View.GONE
            recipeStepAdapter.steps = if(state is RecipeStepState.Loaded) state.recipeStepList else emptyList()
        }

    }

    companion object {
        fun newInstance() = RecipeDetailInfoFragment()

        const val recipeIdKey : String = "RECIPE_ID"
        const val recipeTitleKey : String = "RECIPE_TITLE"

        fun createArguments(recipeId: Int, recipeTitle: String): Bundle {
            return bundleOf(recipeIdKey to recipeId, recipeTitleKey to recipeTitle)
        }
        fun createArguments(recipe: Recipe): Bundle {
            return bundleOf(
                recipeIdKey to recipe.id,
                recipeTitleKey to recipe.title
            )
        }
    }
}
