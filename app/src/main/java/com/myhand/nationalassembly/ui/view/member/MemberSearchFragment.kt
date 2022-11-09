package com.myhand.nationalassembly.ui.view.member

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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

    private val memberVM by viewModels<MemberViewModel>()
    private lateinit var memberSearchAdapter: MemberSearchPagingAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMemberSearchBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        validatePhotoData()
        observeLiveData()
        setUpRecyclerView()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setDefaultSearch() {
        LogUtill.d("setDefaultSearch")
        memberVM.fetchMemberPaging("")

        collectLatestStateFlow(memberVM.fetchMemberResult) {
            memberSearchAdapter.submitData(it)
        }

        searchMembers()
    }

    private fun observeLiveData() {
        memberVM.memberPhotoDBData.observe(viewLifecycleOwner, Observer { photoData ->
            LogUtill.d("memberVM.memberPhotoDBData.observe")

            if (photoData.size > 0) {
                setDefaultSearch()
            }
        })
    }

    /**
     * 검색창으로 데이터 검색
     */
    private fun searchMembers() {
        var startTime = System.currentTimeMillis()
        var endTime: Long

        binding.etMemberSearch.addTextChangedListener { text ->
            endTime = System.currentTimeMillis()
            if (endTime - startTime >= Const.SEARCH_BOOKS_TIME_DELAY) {
                text?.let {
                    val query = text.toString().trim()
                    if (query.isNotEmpty()) {
                        memberVM.fetchMemberPaging(query)
                        //memberViewModel.query = query
                    }
                }
            }
            startTime = endTime
        }
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

    private fun isPhotoDatabaseExist(dbName: String): Boolean {
        val dbFile: File = requireContext().getDatabasePath(dbName)
        return dbFile.exists()
    }

    private fun validatePhotoData() {
        LogUtill.d("isPhotoDataExist = ${isPhotoDatabaseExist(Const.DATABASE_MEMBER_PHOTO)}")

        // DB이름이 이미 존재하는 경우
        if (isPhotoDatabaseExist(Const.DATABASE_MEMBER_PHOTO))
            checkDBDataCount()
        else { //DB가 아직 존재하지 않는경우
            // api 통신 후 사진 저장하기
            fetchPhotoData()
        }
    }

    private fun getPhotoFromDB() {
        LogUtill.d("getPhotoFromDB")
        memberVM.getDBMemberPhotoData()
    }

    private fun fetchPhotoData() {
        LogUtill.d("fetchPhotoData")
        memberVM.fetchMemberPhotoData()
    }

    /**
     * DB에 저장된 사진 수 확인
     */
    private fun checkDBDataCount() {
        memberVM.getDBMemberPhotoCount()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}