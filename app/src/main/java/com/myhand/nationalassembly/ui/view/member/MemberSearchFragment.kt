package com.myhand.nationalassembly.ui.view.member

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.myhand.nationalassembly.R
import com.myhand.nationalassembly.databinding.FragmentMemberSearchBinding
import com.myhand.nationalassembly.ui.view.member.adapter.MemberSearchPagingAdapter
import com.myhand.nationalassembly.ui.view.member.viewmodel.MemberViewModel
import com.myhand.nationalassembly.util.Const
import com.myhand.nationalassembly.util.LogUtil
import com.myhand.nationalassembly.util.collectLatestStateFlow
import dagger.hilt.android.AndroidEntryPoint
import java.io.File


@AndroidEntryPoint
class MemberSearchFragment : Fragment(), AdapterView.OnItemSelectedListener, View.OnClickListener {
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
        binding.viewmodel = memberVM

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setSearchBtn()
        setEditText()
        setSpinner()
        setUpRecyclerView()
        validatePhotoData()
        checkMemberPhotoDataExist()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setDefaultSearch() {
        LogUtil.d("setDefaultSearch")
        memberVM.fetchMemberPaging(
            numOfRows = null,
            pageNo = null,
            name = null,
            origName = null,
            partyName = null,
        )

        collectLatestStateFlow(memberVM.fetchMemberResult) {
            memberSearchAdapter.submitData(it)
        }

        searchMembers()
    }

    private fun checkMemberPhotoDataExist() {
        memberVM.memberPhotoDBData.observe(viewLifecycleOwner, Observer { photoData ->
            if (photoData.size > 0) {
                setDefaultSearch()
            }
        })
    }

    /**
     * 검색창으로 데이터 검색
     */
    private fun searchMembers() {
        var selectedLocation = ""
        var selectedParty = ""

        selectedLocation = binding.spinnerLocation.selectedItem.toString()
        selectedParty = binding.spinnerParty.selectedItem.toString()
        LogUtil.d("selectedLocation: $selectedLocation")

        binding.etMemberSearch.text?.let { text ->
            val name = if (text.toString().trim().isEmpty()) "" else text.toString().trim()
            memberVM.fetchMemberPaging(
                numOfRows = null,
                pageNo = null,
                name = name,
                origName = selectedLocation,
                partyName = selectedParty,
            )
        }
    }

    private fun setSearchBtn() {
        binding.ibSearchBtn.setOnClickListener(this)
    }

    private fun setEditText() {
        binding.etMemberSearch.setOnKeyListener { view, keyCode, keyevent ->
            //If the keyevent is a key-down event on the "enter" button
            if (keyevent.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                searchMembers()
                true
            } else false
        }
    }

    private fun setUpRecyclerView() {
        memberSearchAdapter = MemberSearchPagingAdapter()

        binding.rvMemberSearch.apply {
            setHasFixedSize(true)
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = memberSearchAdapter
        }

        memberSearchAdapter.setOnItemClickListener {
            val action =
                MemberSearchFragmentDirections.actionMemberSearchFragmentToMemberDetailFragment(it)
            findNavController().navigate(action)
        }
    }

    private fun setSpinner() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.location_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerLocation.adapter = adapter
        }
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.party_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerParty.adapter = adapter
        }

        binding.spinnerLocation.onItemSelectedListener = this
        binding.spinnerParty.onItemSelectedListener = this
    }

    private fun isPhotoDatabaseExist(dbName: String): Boolean {
        val dbFile: File = requireContext().getDatabasePath(dbName)
        return dbFile.exists()
    }

    private fun validatePhotoData() {
        LogUtil.d("isPhotoDataExist = ${isPhotoDatabaseExist(Const.DATABASE_MEMBER_PHOTO)}")

        // DB이름이 이미 존재하는 경우
        if (isPhotoDatabaseExist(Const.DATABASE_MEMBER_PHOTO))
            checkDBDataCount()
        else { //DB가 아직 존재하지 않는경우
            // api 통신 후 사진 저장하기
            fetchPhotoData()
        }
    }

    // TODO:: 정리
    private fun getPhotoFromDB() {
        LogUtil.d("getPhotoFromDB")
        memberVM.getDBMemberPhotoData()
    }

    private fun fetchPhotoData() {
        LogUtil.d("fetchPhotoData")
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

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        searchMembers()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.ib_search_btn -> {
                searchMembers()
            }
        }
    }
}