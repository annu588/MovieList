package com.app.moviedemo.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.app.moviedemo.data.model.Search
import com.app.moviedemo.databinding.ActivityMainBinding
import com.app.moviedemo.utils.Status

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MyViewModel
    lateinit var binding: ActivityMainBinding
    val mAdapter = MovieAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        setAdapter()
        setObserver()
        textChangeListener()


    }

    private fun setAdapter() {
        binding.movieRv.adapter = mAdapter
    }

    private fun textChangeListener() {

        binding.inputEv.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(text: Editable?) {
                val list = viewModel.data.value?.data?.Search
                if (!list.isNullOrEmpty()) {


                    if (text!!.isNotEmpty()) {
                        val tempList = ArrayList<Search>()
                        list.forEach {

                            if (it.Title.uppercase().contains(text.toString().uppercase())) {
                                tempList.add(it)
                            }
                        }
                        mAdapter.update(tempList)
                    } else {
                        viewModel.data?.value?.data?.Search?.let { mAdapter.update(it) }
                    }

                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

    }

    private fun setObserver() {
        viewModel.data.observe(this) {
            when (it.status) {
                Status.ERROR -> {
                    Log.e("TAG", "setObserver: ")
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                Status.SUCCESS -> {
                    Log.e("TAG", "setObserver: ")


                    it.data?.Search?.let { it1 -> mAdapter.update(it1) }
                }
                Status.LOADING -> {

                }
            }
        }
    }


}
