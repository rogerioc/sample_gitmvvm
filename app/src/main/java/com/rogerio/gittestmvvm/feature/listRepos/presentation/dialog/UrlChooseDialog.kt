package com.rogerio.xingtest.feature.listRepos.presentation.dialog


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rogerio.xingtest.R
import com.rogerio.xingtest.core.BaseViewModelFactory
import com.rogerio.xingtest.databinding.FragmentUrlChooseDialogBinding
import com.rogerio.xingtest.feature.listRepos.presentation.model.GitRepoViewEntity


/**
 * A simple [Fragment] subclass.
 * Use the [UrlChooseDialog.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class UrlChooseDialog : AppCompatDialogFragment() {
    // TODO: Rename and change types of parameters
    private var gitRepoViewEntity: GitRepoViewEntity? = null
    private lateinit var binding:  FragmentUrlChooseDialogBinding

    private val viewModel: UrlChooseDialogViewModel by lazy {
        ViewModelProviders.of(
            this,
            BaseViewModelFactory { UrlChooseDialogViewModel(this!!.gitRepoViewEntity) }
        ).get(UrlChooseDialogViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            gitRepoViewEntity = it.getParcelable(GITREPO_PARAM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_url_choose_dialog, container, false)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
        viewModel.event.observe(this, Observer {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse(it)
            startActivity(openURL)
            dismiss()
        })
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }


    companion object {
        const val DIALOG_TAG = "UrlChooseDialogFragment"
        private const val GITREPO_PARAM = "param1"

        @JvmStatic
        fun newInstance(param1: GitRepoViewEntity) =
            UrlChooseDialog().also {
                    it.arguments = bundleOf(GITREPO_PARAM to param1)
                }
        }
}

