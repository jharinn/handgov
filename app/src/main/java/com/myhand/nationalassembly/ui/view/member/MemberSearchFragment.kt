package com.myhand.nationalassembly.ui.view.member

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.paging.map
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.myhand.nationalassembly.databinding.FragmentMemberSearchBinding
import com.myhand.nationalassembly.ui.view.member.adapter.MemberSearchPagingAdapter
import com.myhand.nationalassembly.ui.viewmodel.MemberViewModel
import com.myhand.nationalassembly.util.Const
import com.myhand.nationalassembly.util.LogUtill
import com.myhand.nationalassembly.util.collectLatestStateFlow
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class MemberSearchFragment : Fragment() {
    private var _binding: FragmentMemberSearchBinding? = null
    private val binding get() = _binding!!

    private val memberViewModel by viewModels<MemberViewModel>()
    private lateinit var memberSearchAdapter: MemberSearchPagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMemberSearchBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fetchMemberPhotoData()
        observeLiveData()
        searchMembers()
        setUpRecyclerView()
        collectLatestStateFlow(memberViewModel.fetchMemberResult) {
            memberSearchAdapter.submitData(it)
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun searchMembers() {
        memberViewModel.fetchMemberPaging("")

        var startTime = System.currentTimeMillis()
        var endTime: Long

        binding.etMemberSearch.addTextChangedListener { text ->
            endTime = System.currentTimeMillis()
            if (endTime - startTime >= Const.SEARCH_BOOKS_TIME_DELAY) {
                text?.let {
                    val query = text.toString().trim()
                    if (query.isNotEmpty()) {
                        memberViewModel.fetchMemberPaging(query)
                        //memberViewModel.query = query
                    }
                }
            }
            startTime = endTime
        }
    }

    private fun observeLiveData() {
        memberViewModel.memberPhotoResult.observe(viewLifecycleOwner, Observer {
            if (it.body.items.item.isNotEmpty())
                saveMemberPhotoData()
        })

        memberViewModel.memberPhotoDBData.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty())
                setMemberPhoto()
        })
    }

    private fun setUpRecyclerView() {
        memberSearchAdapter = MemberSearchPagingAdapter()

//        binding.etSearch.text =
//            Editable.Factory.getInstance().newEditable(searchViewModel.query)

        binding.rvMemberSearch.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = memberSearchAdapter
        }
//        bookSearchAdapter.setOnItemClickListener {
//            val action = SearchFragmentDirections.actionFragmentSearchToFragmentBook(it)
//            findNavController().navigate(action)
//        }
    }

    private fun isPhotoDataExist(): Boolean {
        if (isPhotoDatabaseExist(Const.DATABASE_MEMBER_PHOTO))
            if (memberViewModel.getMemberPhotoCount() > 0) {
                return true
            }
        return false
    }

    private fun isPhotoDatabaseExist(dbName: String): Boolean {
        val dbFile: File = requireContext().getDatabasePath(dbName)
        return dbFile.exists()
    }


    private fun fetchMemberPhotoData() {
        LogUtill.d("isFetchPhoto = ${isPhotoDataExist()}")
        if (!isPhotoDataExist())
            memberViewModel.fetchMemberPhotoData()
    }

    private fun setMemberPhoto() {
        val photoList = memberViewModel.memberPhotoDBData.value
        val data = memberViewModel.fetchMemberResult.value.map { infoItem ->
            photoList?.map { photoItem ->
                if (infoItem.name == photoItem.empNm && infoItem.electLocalName == photoItem.origNm) {
                    infoItem.photoLink = photoItem.jpgLink
                }
            }!!
        }
        LogUtill.d("data: ${data}")
    }

    private fun saveMemberPhotoData() {
        memberViewModel.saveAllMemberPhotoData()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}