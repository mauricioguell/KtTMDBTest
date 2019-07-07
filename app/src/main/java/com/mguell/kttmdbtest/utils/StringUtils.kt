package com.mguell.kttmdbtest.utils

import android.os.Build
import android.text.Html
import androidx.annotation.NonNull

/**
 * Object created for group methods that will be commonly used to work with String values.
 */
object StringUtils {

    /**
     * Processes an html text to have the standards of an Android text.
     *
     * @param htmlString html text that is going to be processed.
     * @return String with the text processed as Android text.
     */
    @Suppress("DEPRECATION")
    fun processHtmlString(@NonNull htmlString: String): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(htmlString, Html.FROM_HTML_MODE_COMPACT).toString()
        } else {
            Html.fromHtml(htmlString).toString()
        }
    }
}