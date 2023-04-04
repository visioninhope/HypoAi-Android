package dev.atick.hypoai.ui.home

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import dagger.hilt.android.AndroidEntryPoint
import dev.atick.core.ui.base.BaseFragment


@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    @Composable
    override fun ComposeUi() {
        HomeScreen(::openHypoAiWebsite)
    }

    private fun openHypoAiWebsite() {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://hypospadias-ai.netlify.app/"))
        startActivity(browserIntent)
    }
}