package com.aliakberaakash.BostonHacks2020.ui.features.uploadImage

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.aliakberaakash.BostonHacks2020.R
import com.aliakberaakash.BostonHacks2020.data.model.Post
import com.google.android.material.button.MaterialButton
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.uploadimage_layout.*
import java.io.ByteArrayOutputStream
import java.io.FileNotFoundException
import java.io.IOException
import java.sql.Timestamp


class UploadImageFragment : Fragment() {
    companion object{
        const val GET_FROM_GALLERY = 3
    }
    var bitmap: Bitmap? = null
    lateinit var img: ImageView
    lateinit var selectedImage:Uri
    lateinit var storage: FirebaseStorage
    val db = Firebase.firestore

    private lateinit var viewModel: UploadImageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.uploadimage_layout, container, false)
        val uploadBtn: MaterialButton = view.findViewById(R.id.uploadBtn)
        img = view.findViewById(R.id.image_upload)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UploadImageViewModel::class.java)
        storage = Firebase.storage


        choose_image.setOnClickListener {
            startActivityForResult(
                Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.INTERNAL_CONTENT_URI
                ), GET_FROM_GALLERY
            )
        }

        uploadBtn.setOnClickListener {

            if(bitmap==null){
                Toast.makeText(requireContext(), "Choose an image", Toast.LENGTH_SHORT).show()
            }else{
                main_group.visibility = View.GONE
                progressBar.visibility = View.VISIBLE

                val baos = ByteArrayOutputStream()
                bitmap!!.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                val data = baos.toByteArray()

                val storageRef = storage.reference
                val currentTime = Timestamp(System.currentTimeMillis())
                val filename = "$currentTime.jpg"
                val imagesRef = storageRef.child("images/$filename")
                val uploadTask = imagesRef.putBytes(data)

                val tsLong = System.currentTimeMillis() / 1000
                val ts = tsLong.toString()
                val urlTask = uploadTask.continueWithTask{
                    imagesRef.downloadUrl
                }.addOnCompleteListener{ taskSnapshot ->

                    imagesRef.downloadUrl.onSuccessTask {
                        val post = Post(
                            id = ts,
                            user = viewModel.getCurrentUser(),
                            description = description_field.text.toString(),
                            image = taskSnapshot.result.toString()
                        )

                        // add the post to firestore
                        db.collection("posts").document(ts)
                            .set(post)
                            .addOnSuccessListener {
                                main_group.visibility = View.VISIBLE
                                progressBar.visibility = View.GONE
                                bitmap = null

                                Toast.makeText(
                                    requireContext(),
                                    "Successfully Uploaded",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            .addOnFailureListener { e ->

                            }
                    }
                }
            }


        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        //Detects request codes
        if (requestCode == GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                selectedImage = data.data!!
            }
            try {
                bitmap = MediaStore.Images.Media.getBitmap(activity?.contentResolver, selectedImage)
                image_upload.setImageBitmap(bitmap)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

    }
}