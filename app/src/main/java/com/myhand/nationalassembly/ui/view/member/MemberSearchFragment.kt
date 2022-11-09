package com.myhand.nationalassembly.ui.view.member

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.myhand.nationalassembly.R
import com.myhand.nationalassembly.databinding.FragmentMemberSearchBinding
import com.myhand.nationalassembly.ui.view.member.adapter.MemberSearchPagingAdapter
import com.myhand.nationalassembly.ui.viewmodel.MemberViewModel
import com.myhand.nationalassembly.util.Const
import com.myhand.nationalassembly.util.LogUtill
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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setSearchBtn()
        setSpinner()
        validatePhotoData()
        observeLiveData()
        setUpRecyclerView()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setDefaultSearch() {
        LogUtill.d("setDefaultSearch")
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

    private fun observeLiveData() {
        memberVM.memberPhotoDBData.observe(viewLifecycleOwner, Observer { photoData ->
            LogUtill.d("memberVM.memberPhotoDBData.observe photoData.size::${photoData.size}")

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
        LogUtill.d("selectedLocation: $selectedLocation")

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

    private fun setUpRecyclerView() {
        memberSearchAdapter = MemberSearchPagingAdapter()

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
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            binding.spinnerLocation.adapter = adapter
        }
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.party_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
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

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

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