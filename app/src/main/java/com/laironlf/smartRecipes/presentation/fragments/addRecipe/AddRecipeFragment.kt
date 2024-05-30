package com.laironlf.smartRecipes.presentation.fragments.addRecipe

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.github.dhaval2404.imagepicker.ImagePicker
import com.laironlf.smartRecipes.databinding.FragmentAddRecipeBinding
import com.laironlf.smartRecipes.domain.models.post.IngredientPost
import com.laironlf.smartRecipes.domain.models.post.StepPost
import com.laironlf.smartRecipes.presentation.adapters.lllists.IngredientsPostAdapter
import com.laironlf.smartRecipes.presentation.adapters.lllists.StepPostAdapter
import com.laironlf.smartRecipes.presentation.dialogs.bottomSheetIngredients.SheetDialogIngredients
import com.laironlf.smartRecipes.presentation.dialogs.bottomSheetStep.SheetDialogStep
import com.laironlf.smartRecipes.presentation.fragments.addRecipe.AddRecipeViewModel.Action
import com.laironlf.smartRecipes.presentation.fragments.addRecipe.AddRecipeViewModel.State
import com.laironlf.smartRecipes.presentation.utils.PermissionUtils
import com.laironlf.smartRecipes.presentation.utils.bindTwoWay
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddRecipeFragment : Fragment() {

    private val viewModel: AddRecipeViewModel by viewModels()

    private var _binding: FragmentAddRecipeBinding? = null
    private val binding get() = _binding!!
    private lateinit var llIngredientsList: IngredientsPostAdapter
    private lateinit var llStepList: StepPostAdapter
    private val startForImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK) {
                //Image Uri will not be null for RESULT_OK
                val fileUri = data?.data!!
                viewModel.imageUri.postValue(fileUri)
            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(context, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddRecipeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cvAddIngredient.setOnClickListener {
            SheetDialogIngredients(::addIngredient).show(parentFragmentManager, SheetDialogIngredients.TAG)
        }
        binding.cvAddStep.setOnClickListener {
            SheetDialogStep(::addStep).show(parentFragmentManager, SheetDialogStep.TAG)
        }
        binding.cvAddPicture.setOnClickListener {
            if (!PermissionUtils.checkStoragePermissions(requireActivity())) return@setOnClickListener
            ImagePicker.with(this)
                .crop(16f, 9f)
                .compress(1024)
                .maxResultSize(1024, 1024)
                .createIntent {
                    startForImageResult.launch(it)
                }
        }
        binding.btnAddRecipe.setOnClickListener {
            viewModel.onAddRecipeAction()
        }
        binding.tietRecipeTitle.bindTwoWay(viewModel.recipeTitle, viewLifecycleOwner)
        binding.tietRecipeKcal.bindTwoWay(viewModel.kCal, viewLifecycleOwner)
        binding.npHours.bindTwoWay(viewModel.hours, viewLifecycleOwner)
        binding.npMinutes.bindTwoWay(viewModel.minutes, viewLifecycleOwner)
        setupLists()
        subscibeToViewModel()
    }


    private fun addStep(stepPost: StepPost) {
        viewModel.onAddStepAction(stepPost)
    }

    private fun setupLists() {
        llIngredientsList = IngredientsPostAdapter(binding.llIngredientsList, ::onDeleteAction)
        llStepList = StepPostAdapter(binding.llStepList)
    }

    private fun onDeleteAction(ingredientPost: IngredientPost) {

    }

    private fun subscibeToViewModel() {
        viewModel.ingredients.observe(viewLifecycleOwner) { llIngredientsList.ingredients = it }
        viewModel.steps.observe(viewLifecycleOwner){ llStepList.steps = it }
        viewModel.imageUri.observe(viewLifecycleOwner) {
            binding.ivPicture.setImageURI(it)
        }
        lifecycleScope.launch {
            viewModel.actions.collect{ handleAction(it) }
        }
        viewModel.state.observe(viewLifecycleOwner) { state ->
            binding.addScreen.visibility = if (state is State.Idle) View.VISIBLE else View.GONE
            if (state is State.Loading){
                binding.loadScreen.visibility = View.VISIBLE
                binding.tvLoadTitle.text = state.message
            } else {
                binding.loadScreen.visibility = View.GONE
            }
            if (state is State.Loaded){
                showToast("Рецепт загружен!")
                findNavController().popBackStack()
            }
        }
    }

    private fun handleAction(action: Action){
        when (action){
            is Action.ShowToastAction -> showToast(action.message)
            is Action.Close -> findNavController().popBackStack()
            else -> {}
        }
    }

    private fun showToast(message: String){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    private fun addIngredient(ingredient: IngredientPost) {
        viewModel.onAddIngredientAction(ingredient)
    }


    companion object {

    }
}
