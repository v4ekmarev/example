package com.vladlen.dotaautochessguide.screens.guides.guidcontent

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import com.vladlen.dotaautochessguide.application.di.AppComponentHolder
import com.vladlen.dotaautochessguide.screens.BaseFragment
import com.vladlen.dotaautochessguide.screens.guides.di.GuidesComponentHolder
import com.vladlen.dotaautochessguide.screens.guides.guidcontent.di.DaggerGuideDetailComponent
import com.vladlen.dotaautochessguide.screens.guides.guidcontent.di.GuideContentFactory
import com.vladlen.dotaautochessguide.screens.guides.guidcontent.di.GuideDetailComponentHolder
import com.vladlen.dotaautochessguide.viewobjects.ViewObject
import kotlinx.android.synthetic.main.fragment_guide_content.*
import javax.inject.Inject





class GuideContentFragment : BaseFragment() {

    companion object {
        private const val GUIDE_ID = "GUIDE_ID"
        fun newInstance(guideId: String): GuideContentFragment {
            val args = Bundle()
            args.putString(GUIDE_ID, guideId)
            val fragment = GuideContentFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var guideContentFactory: GuideContentFactory
    lateinit var guideContentViewModel: GuideContentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GuideDetailComponentHolder.instance.bindComponent(
            DaggerGuideDetailComponent.builder()
                .appComponent(AppComponentHolder.instance.component)
                .build()
        )
        GuideDetailComponentHolder.instance.component?.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(com.vladlen.dotaautochessguide.R.layout.fragment_guide_content, container, false)
    }

    @SuppressLint("ObsoleteSdkInt", "JavascriptInterface")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val guidId = arguments?.getString(GUIDE_ID)
        guideContentViewModel = ViewModelProviders.of(this, guideContentFactory).get(GuideContentViewModel::class.java)

        guideContentViewModel.getGuideContent.observe(this, Observer { t ->
            when (t?.status) {
                ViewObject.Status.LOADING -> {

                }
                ViewObject.Status.ERROR -> {

                }
                ViewObject.Status.SUCCESS -> {
                    wv_guides.loadDataWithBaseURL("", t.data?.content, "text/html", "UTF-8", "")
                }
            }
        })
        wv_guides.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
//        wv_guides.getSettings().setLoadWithOverviewMode(true);
//        wv_guides.getSettings().setUseWideViewPort(true);
//        wv_guides.addJavascriptInterface(this, "MyApp");
//
//        wv_guides.webViewClient = object : WebViewClient() {
//            override fun onPageFinished(view: WebView, url: String) {
//                wv_guides.loadUrl("javascript:MyApp.resize(document.body.getBoundingClientRect().height)")
//                super.onPageFinished(view, url)
//            }
//        }
        guidId?.let { guideContentViewModel.runGetGuideContent(it) }
    }

//    @JavascriptInterface
//    fun resize(height: Float) {
//        activity?.runOnUiThread(Runnable { wv_guides.setLayoutParams(LinearLayout.LayoutParams(resources.displayMetrics.widthPixels, (height * resources.displayMetrics.density).toInt())) })
//    }

    override fun onDestroy() {
        super.onDestroy()
        GuidesComponentHolder.instance.unbindComponent()
    }
}