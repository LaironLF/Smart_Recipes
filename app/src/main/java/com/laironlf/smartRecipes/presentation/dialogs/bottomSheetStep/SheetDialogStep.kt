package com.laironlf.smartRecipes.presentation.dialogs.bottomSheetStep

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.MutableLiveData
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.laironlf.smartRecipes.databinding.SheetDialogStepBinding
import com.laironlf.smartRecipes.domain.models.post.StepPost
import com.laironlf.smartRecipes.domain.usecases.technical.UploadImageUseCase
import com.laironlf.smartRecipes.presentation.utils.PermissionUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SheetDialogStep(
    val addStepAction: (StepPost) -> Unit
): BottomSheetDialogFragment(){
    
    @Inject lateinit var uploadImageUseCase: UploadImageUseCase

    private var _binding: SheetDialogStepBinding? = null
    private val binding get() = _binding!!
    private val image: MutableLiveData<Uri> = MutableLiveData()
    private val startForImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            if (resultCode == Activity.RESULT_OK) {
                //Image Uri will not be null for RESULT_OK
                val fileUri = data?.data!!
                image.postValue(fileUri)
            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                Toast.makeText(context, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SheetDialogStepBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAddIngredient.setOnClickListener { onAddStepAction() }
        binding.cvAddPicture.setOnClickListener {
            if(!PermissionUtils.checkStoragePermissions(requireActivity())) return@setOnClickListener
            ImagePicker.with(this)
                .crop(16f, 9f)	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1024, 1024)
                .createIntent {
                    startForImageResult.launch(it)
                }
        }
        image.observe(viewLifecycleOwner) { binding.ivPicture.setImageURI(it)}
    }

    private fun onAddStepAction() = try {
        val imageUri = image.value
        val text = binding.tietStepDescription.text.toString()
        if (text == "") throw IllegalArgumentException("Текст шага не может быть пустым")
        val step = StepPost(
            imageUrl = imageUri?.path ?: "",
            text = binding.tietStepDescription.text.toString()
        )
        addStepAction(step)
        dismiss()
    } catch (_: Exception){
    } catch (e: IllegalArgumentException){
      Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val TAG = "SheetDialogStep"
    }


}