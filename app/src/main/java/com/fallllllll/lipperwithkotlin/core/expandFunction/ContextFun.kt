package com.fallllllll.lipperwithkotlin.core.expandFunction

/**
 * Created by fallllllll on 2017/6/9/009.
 * GitHub :  https://github.com/348476129/Lipper
 */
fun android.view.View.showIme() {
    val imm = context.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as android.view.inputmethod.InputMethodManager
    try {
        val showSoftInputUnchecked = android.view.inputmethod.InputMethodManager::class.java.getMethod("showSoftInputUnchecked", Int::class.java, android.os.ResultReceiver::class.java)
        showSoftInputUnchecked.isAccessible = true
        showSoftInputUnchecked.invoke(imm, 0, null)
    } catch (e: Exception) {
        //方法不见了
    }
}

fun android.view.View.hideIme() {
    val imm = context.getSystemService(android.content.Context.INPUT_METHOD_SERVICE) as android.view.inputmethod.InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}