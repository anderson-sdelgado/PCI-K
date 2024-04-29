package br.com.usinasantafe.pcik.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.annotation.IdRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.usinasantafe.pcpk.R


fun Fragment.showGenericAlertDialog(message: String, context: Context): AlertDialog {
    val alert = AlertDialog.Builder(context).apply {
        setMessage(message)
        setPositiveButton(getString(R.string.texto_padrao_ok)) { dialog, _ ->
            dialog.dismiss()
        }
    }.show()
    return alert
}

fun Fragment.showGenericAlertDialog(message: String, context: Context, callback: () -> Unit) {
    AlertDialog.Builder(context).apply {
        setMessage(message)
        setPositiveButton(getString(R.string.texto_padrao_ok)) { _, _ ->
            callback.invoke()
        }
    }.show()
}

fun Activity.hideKeyboard() {
    val imm: InputMethodManager =
        getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view: View? = currentFocus
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun AppCompatActivity.replaceFragment(@IdRes id: Int, fragment: Fragment) {
    if (supportFragmentManager.findFragmentById(id) == null) {
        supportFragmentManager.beginTransaction().apply {
            add(id, fragment)
            commit()
        }
    } else {
        supportFragmentManager.beginTransaction().apply {
            replace(id, fragment)
            addToBackStack(null)
            commit()
        }
    }
    hideKeyboard()
}

fun Fragment.onBackPressed(callback: () -> Unit) {
    val funReturn: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            callback.invoke()
        }
    }
    requireActivity().onBackPressedDispatcher.addCallback(
        this, funReturn
    )
}
