package com.codberg.mvvm_type_A.sample.etc

import android.text.InputFilter
import android.text.Spanned
import java.util.regex.Pattern

object Utils {

    enum class EnumInputType {
        ID,
        EMAIL
    }

    enum class EnumTermsType {
        INTERNAL,
        EXTERNAL
    }

    fun isValidateEmail(email: String): Boolean {
        val regex = "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"
        val pattern = Pattern.compile(regex)

        return pattern.matcher(email).matches()
    }

    fun isSamePassword(password: String, passwordConfirm: String): Boolean {
        return password == passwordConfirm
    }

    fun onlyInputEnglishAndEmailInputFilter() : InputFilter {
        return object : InputFilter {
            override fun filter(charSequence: CharSequence?, start: Int, end: Int, spanned: Spanned?, spannedStart: Int, spannedEnd: Int): CharSequence? {
                val pattern = Pattern.compile("^[a-z0-9@.]+$")

                if(!pattern.matcher(charSequence).matches()) return ""

                return null
            }
        }
    }
}