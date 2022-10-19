package com.ashu.permissionless

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.fragment.findNavController
import com.ashu.permissionless.databinding.FragmentFirstBinding
import com.google.modernstorage.photopicker.PhotoPicker

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val isPhotoPickerAvailable = ActivityResultContracts.PickVisualMedia.isPhotoPickerAvailable()

        if (!isPhotoPickerAvailable) {
            Toast.makeText(activity, "Limit won't be applied as all files would be treated as document only", Toast.LENGTH_LONG).show()
        }

        setOnClickListeners()

    }

    private fun setOnClickListeners() {
        val imagePicker = registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(5)) { uris ->
            println(uris)
        }
        val videoPicker = registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(5)) { uris ->
            println(uris)
        }
        val multimediaPicker = registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(15)) { uris ->
            println(uris)
        }
        val gifPicker = registerForActivityResult(ActivityResultContracts.PickMultipleVisualMedia(5)) { uris ->
            println(uris)
        }

        binding.apply {
            // For images upto max 5
            btnImage.setOnClickListener {
                imagePicker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }

            // For videos upto max 5
            btnVideo.setOnClickListener {
                videoPicker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.VideoOnly))
            }

            // For image and video, limit 15
            btnMultimedia.setOnClickListener {
                multimediaPicker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
            }

            // For GIFs only, limit 5
            btnGif.setOnClickListener {
                gifPicker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.SingleMimeType("image/gif")))
            }

            btnCrash.setOnClickListener {
                throw Error("Hello, I'm not Groot, I am a godamn Crash!")
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}