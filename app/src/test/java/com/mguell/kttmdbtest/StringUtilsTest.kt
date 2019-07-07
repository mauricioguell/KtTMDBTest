package com.mguell.kttmdbtest

import com.mguell.kttmdbtest.utils.StringUtils
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


/**
 * Test for StringUtils.
 */

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class StringUtilsTest {

    /**
     * Ensures that method processHtmlString works correctly if you pass a bold html text.
     * The method must return a normal String.
     */
    @Test
    fun processBoldHtmlText() {
        val htmlBoldText = "<b>Bold text.</b>"
        val expectedProcessingResult = "Bold text."
        assertEquals(expectedProcessingResult, StringUtils.processHtmlString(htmlBoldText))
    }

    /**
     * Ensures that method processHtmlString works correctly if you pass an italic html text.
     * The method must return a normal String.
     */
    @Test
    fun processItalicHtmlText() {
        val htmlItalicText = "<i>Italic text.</i>"
        val expectedProcessingResult = "Italic text."
        assertEquals(expectedProcessingResult, StringUtils.processHtmlString(htmlItalicText))
    }

    /**
     * Ensures that method processHtmlString works correctly if you pass a citation html text.
     * The method must return a normal String.
     */
    @Test
    fun processCitationHtmlText() {
        val htmlCitationText = "<cite>Citation text.</cite>"
        val expectedProcessingResult = "Citation text."
        assertEquals(expectedProcessingResult, StringUtils.processHtmlString(htmlCitationText))
    }

    /**
     * Ensures that method processHtmlString works correctly if you pass a emphasized html text.
     * The method must return a normal String.
     */
    @Test
    fun processEmphasizedHtmlText() {
        val htmlEmphasizedText = "<em>Emphasized text.</em>"
        val expectedProcessingResult = "Emphasized text."
        assertEquals(expectedProcessingResult, StringUtils.processHtmlString(htmlEmphasizedText))
    }

    /**
     * Ensures that method processHtmlString works correctly if you pass a code html text.
     * The method must return a normal String.
     */
    @Test
    fun processCodeHtmlText() {
        val htmlCodeText = "<code style=\"color:black\">Code text.</code>"
        val expectedProcessingResult = "Code text."
        assertEquals(expectedProcessingResult, StringUtils.processHtmlString(htmlCodeText))
    }

    /**
     * Ensures that method processHtmlString works correctly if you pass a big html text.
     * The method must return a normal String.
     *
     * @see [[StringUtils.processHtmlString
     */
    @Test
    fun processBigHtmlText() {
        val htmlBigText = "<big>Big text.</big>"
        val expectedProcessingResult = "Big text."
        assertEquals(expectedProcessingResult, StringUtils.processHtmlString(htmlBigText))
    }

    /**
     * Ensures that method processHtmlString works correctly if you pass an small html text.
     * The method must return a normal String.
     */
    @Test
    fun processSmallHtmlText() {
        val htmlSmallText = "<small>Small text.</small>"
        val expectedProcessingResult = "Small text."
        assertEquals(expectedProcessingResult, StringUtils.processHtmlString(htmlSmallText))
    }

    /**
     * Ensures that method processHtmlString works correctly if you pass a delete html text.
     * The method must return a normal String.
     */
    @Test
    fun processDeleteHtmlText() {
        val htmlDeleteText = "<del>Delete this text.</del>"
        val expectedProcessingResult = "Delete this text."
        assertEquals(expectedProcessingResult, StringUtils.processHtmlString(htmlDeleteText))
    }

    /**
     * Ensures that method processHtmlString works correctly if you pass an insert html text.
     * The method must return a normal String.
     */
    @Test
    fun processInsertHtmlText() {
        val htmlInsertText = "<ins>Insert this text.</ins>"
        val expectedProcessingResult = "Insert this text."
        assertEquals(expectedProcessingResult, StringUtils.processHtmlString(htmlInsertText))
    }

    /**
     * Ensures that method processHtmlString works correctly if you pass a keyboard html text.
     * The method must return a normal String.
     */
    @Test
    fun processKeyboardHtmlText() {
        val htmlKeyboardText = "<kbd>Keyboard text - text to be entered by the user.</kbd>"
        val expectedProcessingResult = "Keyboard text - text to be entered by the user."
        assertEquals(expectedProcessingResult, StringUtils.processHtmlString(htmlKeyboardText))
    }

    /**
     * Ensures that method processHtmlString works correctly if you pass a quotation html text.
     * The method must return a normal String.
     */
    @Test
    fun processQuotationHtmlText() {
        val htmlQuotationText = "<q>Quotation text.</q>"
        val expectedProcessingResult = "Quotation text."
        assertEquals(expectedProcessingResult, StringUtils.processHtmlString(htmlQuotationText))
    }

    /**
     * Ensures that method processHtmlString works correctly if you pass a sample html text.
     * The method must return a normal String.
     */
    @Test
    fun processSampleHtmlText() {
        val htmlSampleText = "<samp>Sample text (output from a computer program).</samp>"
        val expectedProcessingResult = "Sample text (output from a computer program)."
        assertEquals(expectedProcessingResult, StringUtils.processHtmlString(htmlSampleText))
    }

    /**
     * Ensures that method processHtmlString works correctly if you pass a variable html text.
     * The method must return a normal String.
     */
    @Test
    fun processVariableHtmlText() {
        val htmlVariableText = "<var>Variable text.</var>"
        val expectedProcessingResult = "Variable text."
        assertEquals(expectedProcessingResult, StringUtils.processHtmlString(htmlVariableText))
    }
}
