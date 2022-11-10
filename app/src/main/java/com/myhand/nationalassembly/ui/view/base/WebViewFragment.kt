package com.myhand.nationalassembly.ui.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.myhand.nationalassembly.databinding.FragmentWebviewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewFragment : Fragment() {
    private var _binding: FragmentWebviewBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<WebViewFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWebviewBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setWebView()
        initWebview()

        super.onViewCreated(view, savedInstanceState)
    }

    fun setWebView() {
        val webview = binding.webview

        webview.settings.apply { //세부 세팅 등록
            setSupportMultipleWindows(false) // 새창 띄우기 허용 여부
            setSupportZoom(false) // 화면 줌 허용 여부
            javaScriptEnabled // 웹페이지 자바스클비트 허용 여부
            databaseEnabled = true // 데이터베이스 접근 허용 여부
            domStorageEnabled = true // 로컬저장소 허용 여부
            defaultTextEncodingName = "UTF-8" // encoding 설정
            displayZoomControls = true // 돋보기 없애기
            javaScriptCanOpenWindowsAutomatically = false // 자바스크립트 새창 띄우기(멀티뷰) 허용 여부
            loadWithOverviewMode = true // 메타태그 허용 여부
            loadWithOverviewMode = true // 컨텐츠가 웹뷰보다 클 경우 스크린 크기에 맞게 조정
            //setAllowFileAccessFromFileURLs(true) // 파일 URL로부터 파일 접근 허용
            allowContentAccess = true // 컨텐츠 접근 허용
            builtInZoomControls = true // 화면 확대 축소 허용 여부
            allowFileAccess = true // 파일 접근 허용 여부
            cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK // 브라우저 캐시 허용 여부
        }
        webview.webViewClient = WebViewClient() // 클릭시 새창 안뜨게 (알림 및 요청 관련 설정)
    }

    fun initWebview() {
        binding.webview.loadUrl(args.url)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}