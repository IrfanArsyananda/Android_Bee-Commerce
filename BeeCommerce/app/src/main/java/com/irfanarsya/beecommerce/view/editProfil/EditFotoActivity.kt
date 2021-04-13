package com.irfanarsya.beecommerce.view.editProfil

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.irfanarsya.beecommerce.R
import com.irfanarsya.beecommerce.databinding.ActivityEditFotoBinding
import com.irfanarsya.beecommerce.databinding.DialogChooseImageBinding
import com.irfanarsya.beecommerce.model.action.ResponseEditFoto
import com.irfanarsya.beecommerce.network.Constant
import com.irfanarsya.beecommerce.network.Constant.Companion.code.CAMERA_CODE
import com.irfanarsya.beecommerce.network.Constant.Companion.code.GALLERY_CODE
import com.irfanarsya.beecommerce.view.home.MainActivity
import com.irfanarsya.beecommerce.viewModel.ViewModelEditFoto
import com.udacoding.samplephotousingretrofit.utils.*
import kotlinx.android.synthetic.main.activity_edit_foto.*
import java.io.IOException
import kotlin.random.Random

class EditFotoActivity : AppCompatActivity() {

    private var image_path: String? = null
    private var mime_type: String? = null
    lateinit var binding: ActivityEditFotoBinding
    lateinit var dialog: BottomSheetDialog
    lateinit var dialogChooseImageBinding: DialogChooseImageBinding

    lateinit var viewModel: ViewModelEditFoto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_edit_foto)

        initPermission()

        binding = ActivityEditFotoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProviders.of(this).get(ViewModelEditFoto::class.java)

        val userId = intent.getStringExtra("userId")
        val url = intent.getStringExtra("urlFoto")

        val urlImage = Constant.BASE_IMG_USER+"$url"
        Glide.with(this).load(urlImage).into(imageLama)

        binding.chooseImg.setOnClickListener {
            showChooseImage()
        }

        binding.btnSimpan.setOnClickListener {
            viewModel.updateFoto(userId?:"", image_path?:"")
        }
        binding.btnBatal.setOnClickListener {
            finish()
        }

        attachObserve()

    }

    private fun attachObserve() {
        viewModel.onSuccess.observe(this, Observer { successUpdate(it) })
        viewModel.onError.observe(this, Observer { showError(it) })
        viewModel.isLoading.observe(this, Observer { showLoading(it) })
        viewModel.isKosong.observe(this, Observer { showEmpty(it) })

    }

    private fun showEmpty(it: Boolean?) {
        if (it == true){
            Toast.makeText(this, "Foto harus diisi !", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showLoading(it: Boolean?) {
        if (it == true){
            progressEditF.visibility = View.VISIBLE
        }else{
            progressEditF.visibility = View.GONE
        }
    }

    private fun showError(it: Throwable?) {
        Toast.makeText(this, it?.message.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun successUpdate(it: ResponseEditFoto?) {
        Toast.makeText(this, it?.message.toString(), Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun showChooseImage() {
        dialogChooseImageBinding =
            DialogChooseImageBinding.bind(View.inflate(this, R.layout.dialog_choose_image, null))
        dialog = BottomSheetDialog(this).apply {
            setContentView(dialogChooseImageBinding.root)
            show()
        }
        dialogChooseImageBinding.imageViewCamera.setOnClickListener {
            openCamera()
            dialog.dismiss()
        }
        dialogChooseImageBinding.imageViewGallery.setOnClickListener {
            openGallery()
            dialog.dismiss()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_CODE && resultCode == Activity.RESULT_OK) {
            initCamera(data)
        } else if (requestCode == GALLERY_CODE && resultCode == Activity.RESULT_OK) {
            initGallery(data)
        }
    }

    private fun initGallery(data: Intent?) {
        val image_bitmap = onSelectFromGalleryResult(data)
        binding.imageView.setImageBitmap(image_bitmap)
    }

    private fun initCamera(data: Intent?) {
        try {
            val image = data?.extras?.get("data")
            val random = Random.nextInt(0, 999999)
            var name_file ="PurchaseImage$random"

            image_path = persistImage(image as Bitmap, name_file)

            Log.d("TAG", "initCamera: MimeType : $mime_type")
            binding.imageView.setImageBitmap(BitmapFactory.decodeFile(image_path))
        } catch (e: Exception) {
            Log.d("Error", "initCameraException: $e")
        }
    }

    private fun onSelectFromGalleryResult(data: Intent?): Bitmap {
        var bm: Bitmap? = null
        Log.d("TAG", "onSelectFromGalleryResult: Masuk kesini")
        if (data != null) {
            Log.d("TAG", "onSelectFromGalleryResult: Sini juga Masuk")
            try {
                image_path = data.data?.let { FilePath.getPath(this, it) }
                Log.d("gallery_path", image_path ?: "")
                bm =
                    MediaStore.Images.Media.getBitmap(applicationContext.contentResolver, data.data)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return bm!!
    }

}