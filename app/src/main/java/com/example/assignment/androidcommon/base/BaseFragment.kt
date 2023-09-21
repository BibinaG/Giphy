package com.example.assignment.androidcommon.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding


abstract class BaseFragment<VB : ViewBinding> : Fragment() {
    private var _binding: VB? = null
    val binding: VB get() = _binding!!

  /*  private val progressDialog: Dialog by lazy {//added for adding showprogressbar
        ProgressDialogHelper.getProgressDialog(context = requireContext())
    }*/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding(inflater, container)
        return binding.root
    }

    abstract fun getViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB

//    open fun showProgress(message: String = "Loading…") {
//        progressDialog.showProgress(message)
//    }
//
//    open fun hideProgress() {
//        progressDialog.dismissProgress()
//    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}