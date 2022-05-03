package com.picpay.desafio.android.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import androidx.recyclerview.widget.LinearLayoutManager
import com.picpay.desafio.android.databinding.UserListFragmentBinding
import com.picpay.desafio.android.util.StatusResponse
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class UserListFragment : Fragment() {

    companion object {
        fun newInstance() = UserListFragment()
    }

    private lateinit var binding: UserListFragmentBinding;
    private val viewModel by viewModel<UserListViewModel>(){parametersOf(
        SavedStateHandle()
    )}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = UserListFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = UserListAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)

        viewModel.status.observe(viewLifecycleOwner, Observer { status ->
            when (status) {
                is StatusResponse.Success -> binding.userListProgressBar.visibility = View.GONE
                is StatusResponse.Failure -> {
                    binding.userListProgressBar.visibility = View.GONE
                    Toast.makeText(activity,"Houve um erro ao carregar as informações do servidor", Toast.LENGTH_LONG).show()
                }
                is StatusResponse.Load -> binding.userListProgressBar.visibility = View.VISIBLE
            }
        })
        if(savedInstanceState==null){
            viewModel.getUses()
            viewModel.refresh()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        viewModel.setSaveState();
        super.onSaveInstanceState(outState)
    }
}