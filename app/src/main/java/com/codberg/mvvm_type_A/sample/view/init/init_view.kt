package com.codberg.mvvm_type_A.sample.view.init

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewManager
import android.webkit.WebView
import android.widget.*
import androidx.viewpager2.widget.ViewPager2
import com.codberg.mvvm_type_A.R
import com.codberg.mvvm_type_A.sample.viewmodel.ViewModel
import com.google.android.material.tabs.TabLayout
import com.libs.cutil_kotlin.ScalingImageView
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView

class init_view(val viewModel: ViewModel): init_data() {

    /** [splash_TYPE_A] ----------------------------------------------**/
    lateinit var splash_contentVIew_Type_A : LinearLayout
    lateinit var splash_contentVIew_Type_A_img : ImageView
    lateinit var splash_contentVIew_Type_A_text : TextView

    fun getSplash_TYPE_A(mCon : Context) : View {
        return mCon.UI {
            splash_contentVIew_Type_A = verticalLayout  {
                splash_contentVIew_Type_A_img = imageView()  {
                }
                splash_contentVIew_Type_A_text = textView()  {
                    gravity = Gravity.CENTER
                }
            }
        }.view
    }

    /**----------------------------------------------------------------**/



    /** [login_TYPE_A] ----------------------------------------------**/

    lateinit var login_Type_A_parrent : FrameLayout
    lateinit var login_Type_A_parrent_image : ImageView
    lateinit var login_Type_A_parrent_text : TextView
    lateinit var login_Type_A_sub_parent_1 : FrameLayout
    lateinit var login_Type_A_sub_parent_2 : LinearLayout
    lateinit var login_Type_A_sub_parent_2_info_group : LinearLayout

    lateinit var login_Type_A_sub_parent_2_signIn_group : LinearLayout
    lateinit var login_Type_A_sub_parent_2_signIn_group_nomal_login : TextView
    lateinit var login_Type_A_sub_parent_2_signIn_group_login_kakao : TextView
    lateinit var login_Type_A_sub_parent_2_signIn_group_login_faceBook : TextView
    lateinit var login_Type_A_sub_parent_2_signIn_group_login_naver : TextView

    lateinit var login_Type_A_sub_parent_1_image : ImageView
    lateinit var login_Type_A_sub_parent_1_text1 : TextView
    lateinit var login_Type_A_sub_parent_1_text2 : TextView
    lateinit var login_Type_A_sub_parent_2_text1 : TextView
    lateinit var login_Type_A_sub_parent_2_input_id : EditText
    lateinit var login_Type_A_sub_parent_2_input_pw : EditText
    lateinit var login_Type_A_sub_parent_2_info_group_sign_up : TextView
    lateinit var login_Type_A_sub_parent_2_info_group_space : ImageView
    lateinit var login_Type_A_sub_parent_2_info_group_find_pw : TextView
    lateinit var login_Type_A_sub_parent_2_text2 : TextView
    lateinit var login_Type_A_parrent_text2 : TextView

    fun getLogin_TYPE_A(mCon : Context) : View  {

        return mCon.UI {
            login_Type_A_parrent = frameLayout {
                login_Type_A_parrent_image = imageView { }
                login_Type_A_parrent_text  = textView  { }
                login_Type_A_sub_parent_1 = frameLayout {
                    login_Type_A_sub_parent_1_image = imageView { }
                    login_Type_A_sub_parent_1_text1 = textView { }
                    login_Type_A_sub_parent_1_text2 = textView { }
                }
                login_Type_A_sub_parent_2 = verticalLayout {
                    login_Type_A_sub_parent_2_text1 = textView { }
                    login_Type_A_sub_parent_2_input_id = editText {  }
                    login_Type_A_sub_parent_2_input_pw = editText {  }
                    login_Type_A_sub_parent_2_signIn_group = linearLayout {
                        gravity = Gravity.LEFT
                        login_Type_A_sub_parent_2_signIn_group_nomal_login      = textView  { gravity = Gravity.CENTER }
                        login_Type_A_sub_parent_2_signIn_group_login_kakao      = textView  { gravity = Gravity.CENTER }
                        login_Type_A_sub_parent_2_signIn_group_login_faceBook   = textView  { gravity = Gravity.CENTER }
                        login_Type_A_sub_parent_2_signIn_group_login_naver      = textView  { gravity = Gravity.CENTER }
                    }.lparams(wrapContent, wrapContent)
                    login_Type_A_sub_parent_2_info_group = linearLayout {
                        login_Type_A_sub_parent_2_info_group_sign_up = textView {
                            viewModel.setSignUpSettings()
                        }
                        login_Type_A_sub_parent_2_info_group_space   = imageView{ }
                        login_Type_A_sub_parent_2_info_group_find_pw = textView {
                            viewModel.setFindIdPwSettings()
                        }
                    }.lparams(matchParent, wrapContent)
                    login_Type_A_sub_parent_2_text2 = textView { }
                }
                login_Type_A_parrent_text2 = textView { }
            }
        }.view

    }

    /**----------------------------------------------------------------**/

    /** [signUp_TYPE_A] ----------------------------------------------**/
    lateinit var signup_contentView_Type_A: LinearLayout

    lateinit var signup_contentView_Type_A_titleView_ex: TextView

    lateinit var signup_contentView_Type_A_sub_parent: LinearLayout

    // id or email
    lateinit var signup_contentView_Type_A_sub_parent_input_id_email: RelativeLayout
    lateinit var signup_contentView_Type_A_sub_parent_input_imageView_id_email: ImageView
    lateinit var signup_contentView_Type_A_sub_parent_input_editTextView_id_email: EditText
    lateinit var signup_contentView_Type_A_sub__parent_input_view_id_email: View

    // password
    lateinit var signup_contentView_Type_A_sub_parent_input_password: RelativeLayout
    lateinit var signup_contentView_Type_A_sub_parent_input_imageView_password: ImageView
    lateinit var signup_contentView_Type_A_sub_parent_input_editTextView_password: EditText
    lateinit var signup_contentView_Type_A_sub__parent_input_view_password: View

    // password confirm
    lateinit var signup_contentView_Type_A_sub_parent_input_password_confirm: RelativeLayout
    lateinit var signup_contentView_Type_A_sub_parent_input_imageView_password_confirm: ImageView
    lateinit var signup_contentView_Type_A_sub_parent_input_editTextView_password_confirm: EditText
    lateinit var signup_contentView_Type_A_sub__parent_input_view_password_confirm: View

    // name
    lateinit var signup_contentView_Type_A_sub_parent_input_name: RelativeLayout
    lateinit var signup_contentView_Type_A_sub_parent_input_imageView_name: ImageView
    lateinit var signup_contentView_Type_A_sub_parent_input_editTextView_name: EditText
    lateinit var signup_contentView_Type_A_sub__parent_input_view_name: View

    // phone
    lateinit var signup_contentView_Type_A_sub_parent_input_phone: RelativeLayout
    lateinit var signup_contentView_Type_A_sub_parent_input_imageView_phone: ImageView
    lateinit var signup_contentView_Type_A_sub_parent_input_editTextView_phone: EditText
    lateinit var signup_contentView_Type_A_sub__parent_input_view_phone: View
    lateinit var signup_contentView_Type_A_sub__parent_input_button_phone: Button

    // phone auth
    lateinit var signup_contentView_Type_A_sub_parent_input_phone_auth: RelativeLayout
    lateinit var signup_contentView_Type_A_sub_parent_input_imageView_phone_auth: ImageView
    lateinit var signup_contentView_Type_A_sub_parent_input_editTextView_phone_auth: EditText
    lateinit var signup_contentView_Type_A_sub__parent_input_view_phone_auth: View
    lateinit var signup_contentView_Type_A_sub__parent_input_button_phone_auth: Button
    lateinit var signup_contentView_Type_A_sub_parent_input_phone_auth_timer_layout: RelativeLayout
    lateinit var signup_contentView_Type_A_sub_parent_input_phone_auth_timer_textView: TextView

    // sub parent agree
    lateinit var signup_contentView_Type_A_sub_parent_agreement: LinearLayout

    lateinit var signup_contentView_Type_A_sub_parent_agreement_all: LinearLayout
    lateinit var signup_contentView_Type_A_sub_parent_agreement_all_checkBox: CheckBox
    lateinit var signup_contentView_Type_A_sub_parent_agreement_all_textView: TextView

    lateinit var signup_contentView_Type_A_sub_parent_agreement_item1: LinearLayout
    lateinit var signup_contentView_Type_A_sub_parent_agreement_item1_textView: TextView
    lateinit var signup_contentView_Type_A_sub_parent_agreement_item1_sub: LinearLayout
    lateinit var signup_contentView_Type_A_sub_parent_agreement_item1_sub_checkBox: CheckBox
    lateinit var signup_contentView_Type_A_sub_parent_agreement_item1_sub_textView: TextView

    lateinit var signup_contentView_Type_A_sub_parent_agreement_item2: LinearLayout
    lateinit var signup_contentView_Type_A_sub_parent_agreement_item2_textView: TextView
    lateinit var signup_contentView_Type_A_sub_parent_agreement_item2_sub: LinearLayout
    lateinit var signup_contentView_Type_A_sub_parent_agreement_item2_sub_checkBox: CheckBox
    lateinit var signup_contentView_Type_A_sub_parent_agreement_item2_sub_textView: TextView

    lateinit var signup_contentView_Type_A_sub_parent_agreement_button_layout: LinearLayout
    lateinit var signup_contentView_Type_A_sub_parent_agreement_button: Button

    fun getSignUp(mCon: Context): View {

        return mCon.UI {
            signup_contentView_Type_A = verticalLayout {
                signup_contentView_Type_A_titleView_ex = textView { }

                signup_contentView_Type_A_sub_parent = verticalLayout {

                    // id or email
                    signup_contentView_Type_A_sub_parent_input_id_email = relativeLayout {
                        signup_contentView_Type_A_sub_parent_input_imageView_id_email = imageView { }
                        signup_contentView_Type_A_sub_parent_input_editTextView_id_email = editText { }
                        signup_contentView_Type_A_sub__parent_input_view_id_email = view { }
                    }

                    // password
                    signup_contentView_Type_A_sub_parent_input_password = relativeLayout {
                        signup_contentView_Type_A_sub_parent_input_imageView_password = imageView { }
                        signup_contentView_Type_A_sub_parent_input_editTextView_password = editText { }
                        signup_contentView_Type_A_sub__parent_input_view_password = view { }
                    }

                    // password confirm
                    signup_contentView_Type_A_sub_parent_input_password_confirm = relativeLayout {
                        signup_contentView_Type_A_sub_parent_input_imageView_password_confirm = imageView { }
                        signup_contentView_Type_A_sub_parent_input_editTextView_password_confirm = editText { }
                        signup_contentView_Type_A_sub__parent_input_view_password_confirm = view { }
                    }

                    // name
                    signup_contentView_Type_A_sub_parent_input_name = relativeLayout {
                        signup_contentView_Type_A_sub_parent_input_imageView_name = imageView { }
                        signup_contentView_Type_A_sub_parent_input_editTextView_name = editText { }
                        signup_contentView_Type_A_sub__parent_input_view_name = view { }
                    }

                    // phone
                    signup_contentView_Type_A_sub_parent_input_phone = relativeLayout {
                        signup_contentView_Type_A_sub_parent_input_imageView_phone = imageView { }
                        signup_contentView_Type_A_sub_parent_input_editTextView_phone = editText { }
                        signup_contentView_Type_A_sub__parent_input_view_phone = view { }
                        signup_contentView_Type_A_sub__parent_input_button_phone = button { }
                    }

                    // phone auth
                    signup_contentView_Type_A_sub_parent_input_phone_auth = relativeLayout {
                        signup_contentView_Type_A_sub_parent_input_imageView_phone_auth = imageView { }
                        signup_contentView_Type_A_sub_parent_input_editTextView_phone_auth = editText { }
                        signup_contentView_Type_A_sub__parent_input_view_phone_auth = view { }
                        signup_contentView_Type_A_sub__parent_input_button_phone_auth = button { }
                    }

                    signup_contentView_Type_A_sub_parent_input_phone_auth_timer_layout = relativeLayout {
                        signup_contentView_Type_A_sub_parent_input_phone_auth_timer_textView = textView {  }
                    }
                }

                // agree layout
                signup_contentView_Type_A_sub_parent_agreement = verticalLayout {
                    // agree all
                    signup_contentView_Type_A_sub_parent_agreement_all = verticalLayout {
                        signup_contentView_Type_A_sub_parent_agreement_all_checkBox = checkBox {  }
                        signup_contentView_Type_A_sub_parent_agreement_all_textView = textView {  }
                    }

                    // 1
                    signup_contentView_Type_A_sub_parent_agreement_item1 = verticalLayout {
                        signup_contentView_Type_A_sub_parent_agreement_item1_sub = verticalLayout {
                            signup_contentView_Type_A_sub_parent_agreement_item1_sub_checkBox = checkBox {  }
                            signup_contentView_Type_A_sub_parent_agreement_item1_sub_textView = textView {  }
                        }

                        signup_contentView_Type_A_sub_parent_agreement_item1_textView = textView{  }
                    }

                    // 2
                    signup_contentView_Type_A_sub_parent_agreement_item2 = verticalLayout {
                        signup_contentView_Type_A_sub_parent_agreement_item2_sub = verticalLayout {
                            signup_contentView_Type_A_sub_parent_agreement_item2_sub_checkBox = checkBox {  }
                            signup_contentView_Type_A_sub_parent_agreement_item2_sub_textView = textView {  }
                        }

                        signup_contentView_Type_A_sub_parent_agreement_item2_textView = textView{  }
                    }
                }

                signup_contentView_Type_A_sub_parent_agreement_button_layout = verticalLayout {
                    signup_contentView_Type_A_sub_parent_agreement_button = button {  }
                }
            }
        }.view

    }

    /**----------------------------------------------------------------**/
    lateinit var signup_contentView_type_A_main_linearLayout: LinearLayout
    lateinit var signup_contentView_type_A_linearLayout: LinearLayout
    lateinit var signup_contentView_Type_A_webView: WebView

    fun createTermsWebView(mContext: Context): View {
        return mContext.UI {
            signup_contentView_type_A_main_linearLayout = linearLayout {
                isClickable = true
                gravity = Gravity.CENTER
                backgroundColor = Color.parseColor("#CC000000")
                signup_contentView_type_A_linearLayout = linearLayout {
                    signup_contentView_Type_A_webView = webView {  }
                }
            }
        }.view
    }

    /** [Find Id and Password] --------------------------------------------- **/

    lateinit var find_IdPw_contentView_Type_A_main_parent: LinearLayout
    lateinit var find_IdPw_contentView_Type_A_main_parent_textView_title: TextView

    lateinit var find_IdPw_contentView_Type_A_sub_parent: LinearLayout
    lateinit var find_IdPw_contentView_Type_A_sub_parent_viewpager2: ViewPager2
    lateinit var find_IdPw_contentView_Type_A_sub_parent_tablayout: TabLayout

    fun getFindIdPw(mCon: Context): View {
        return mCon.UI {
            find_IdPw_contentView_Type_A_main_parent = linearLayout {
                find_IdPw_contentView_Type_A_main_parent_textView_title = textView {  }

                find_IdPw_contentView_Type_A_sub_parent = verticalLayout {
                    find_IdPw_contentView_Type_A_sub_parent_tablayout = tabLayout {
                        layoutParams = LinearLayout.LayoutParams(matchParent, wrapContent)
                    }
                    find_IdPw_contentView_Type_A_sub_parent_viewpager2 = viewPager2 {
                        layoutParams = LinearLayout.LayoutParams(matchParent, matchParent)
                    }
                }
            }
        }.view
    }

    lateinit var find_id_view_type_a_adapter_main_parent: LinearLayout

    lateinit var find_id_view_type_a_sub_parent_input_phone: RelativeLayout
    lateinit var find_id_view_type_a_sub_parent_input_imageView_phone: ImageView
    lateinit var find_id_view_type_a_sub_parent_input_editTextView_phone: EditText
    lateinit var find_id_view_type_a_sub_parent_input_view_phone: View
    lateinit var find_id_view_type_a_sub_parent_input_button_phone: Button

    lateinit var find_id_view_type_a_sub_parent_input_phone_auth: RelativeLayout
    lateinit var find_id_view_type_a_sub_parent_input_imageView_phone_auth: ImageView
    lateinit var find_id_view_type_a_sub_parent_input_editTextView_phone_auth: EditText
    lateinit var find_id_view_type_a_sub_parent_input_view_phone_auth: View
    lateinit var find_id_view_type_a_sub_parent_input_button_phone_auth: Button

    lateinit var find_id_view_type_a_sub_parent_input_phone_auth_timer_layout: RelativeLayout
    lateinit var find_id_view_type_a_sub_parent_input_phone_auth_timer_textView: TextView

    lateinit var find_id_view_type_a_sub_parent_input_find_button_layout: LinearLayout
    lateinit var find_id_view_type_a_sub_parent_input_find_button: Button

    fun getFindIdAdapterItem(mCon: Context): View {
        return mCon.UI {
            find_id_view_type_a_adapter_main_parent = linearLayout {

                // Find ID phone
                find_id_view_type_a_sub_parent_input_phone = relativeLayout {
                    find_id_view_type_a_sub_parent_input_imageView_phone = imageView { }
                    find_id_view_type_a_sub_parent_input_editTextView_phone = editText { }
                    find_id_view_type_a_sub_parent_input_view_phone = view { }
                    find_id_view_type_a_sub_parent_input_button_phone = button { }
                }

                // Find ID phone auth
                find_id_view_type_a_sub_parent_input_phone_auth = relativeLayout {
                    find_id_view_type_a_sub_parent_input_imageView_phone_auth = imageView { }
                    find_id_view_type_a_sub_parent_input_editTextView_phone_auth = editText { }
                    find_id_view_type_a_sub_parent_input_view_phone_auth = view { }
                    find_id_view_type_a_sub_parent_input_button_phone_auth = button { }
                }

                // Find ID phone auth timer
                find_id_view_type_a_sub_parent_input_phone_auth_timer_layout = relativeLayout {
                    find_id_view_type_a_sub_parent_input_phone_auth_timer_textView = textView {  }
                }

                // Find ID Find Button
                find_id_view_type_a_sub_parent_input_find_button_layout = verticalLayout {
                    find_id_view_type_a_sub_parent_input_find_button = button {  }
                }
            }
        }.view
    }

    lateinit var find_id_view_type_b_adapater_main_parent: LinearLayout
    lateinit var find_id_view_type_b_sub_parent_input_phone: RelativeLayout
    lateinit var find_id_view_type_b_sub_parent_input_imageView_phone: ImageView
    lateinit var find_id_view_type_b_sub_parent_input_editTextView_phone: EditText
    lateinit var find_id_view_type_b_sub_parent_input_view_phone: View

    lateinit var find_id_view_type_b_sub_parent_input_find_button_layout: LinearLayout
    lateinit var find_id_view_type_b_sub_parent_input_find_button: Button

    fun getFindIdAdpaterItem_b(mContext: Context): View {
        return mContext.UI {
            find_id_view_type_b_adapater_main_parent = linearLayout {
                // Find ID phone
                find_id_view_type_b_sub_parent_input_phone = relativeLayout {
                    find_id_view_type_b_sub_parent_input_imageView_phone = imageView { }
                    find_id_view_type_b_sub_parent_input_editTextView_phone = editText { }
                    find_id_view_type_b_sub_parent_input_view_phone = view { }
                }

                // Find ID Button
                find_id_view_type_b_sub_parent_input_find_button_layout = verticalLayout {
                    find_id_view_type_b_sub_parent_input_find_button = button {  }
                }
            }
        }.view
    }


    lateinit var find_pw_view_type_a_adapter_main_parent: LinearLayout

    lateinit var find_pw_view_type_a_sub_parent_input_id_email: RelativeLayout
    lateinit var find_pw_view_type_a_sub_parent_input_imageView_id_email: ImageView
    lateinit var find_pw_view_type_a_sub_parent_input_editTextView_id_email: EditText
    lateinit var find_pw_view_type_a_sub_parent_input_view_id_email: View

    lateinit var find_pw_view_type_a_sub_parent_input_phone: RelativeLayout
    lateinit var find_pw_view_type_a_sub_parent_input_imageView_phone: ImageView
    lateinit var find_pw_view_type_a_sub_parent_input_editTextView_phone: EditText
    lateinit var find_pw_view_type_a_sub_parent_input_view_phone: View
    lateinit var find_pw_view_type_a_sub_parent_input_button_phone: Button

    lateinit var find_pw_view_type_a_sub_parent_input_phone_auth: RelativeLayout
    lateinit var find_pw_view_type_a_sub_parent_input_imageView_phone_auth: ImageView
    lateinit var find_pw_view_type_a_sub_parent_input_editTextView_phone_auth: EditText
    lateinit var find_pw_view_type_a_sub_parent_input_view_phone_auth: View
    lateinit var find_pw_view_type_a_sub_parent_input_button_phone_auth: Button

    lateinit var find_pw_view_type_a_sub_parent_input_phone_auth_timer_layout: RelativeLayout
    lateinit var find_pw_view_type_a_sub_parent_input_phone_auth_timer_textView: TextView

    lateinit var find_pw_view_type_a_sub_parent_input_find_button_layout: LinearLayout
    lateinit var find_pw_view_type_a_sub_parent_input_find_button: Button

    fun getFindPwAdapterItem(mCon: Context): View {
        return mCon.UI {
            find_pw_view_type_a_adapter_main_parent = linearLayout {

                // Find Id or Email phone
                find_pw_view_type_a_sub_parent_input_id_email = relativeLayout {
                    find_pw_view_type_a_sub_parent_input_imageView_id_email = imageView { }
                    find_pw_view_type_a_sub_parent_input_editTextView_id_email = editText { }
                    find_pw_view_type_a_sub_parent_input_view_id_email = view { }
                }

                // Find PW phone
                find_pw_view_type_a_sub_parent_input_phone = relativeLayout {
                    find_pw_view_type_a_sub_parent_input_imageView_phone = imageView { }
                    find_pw_view_type_a_sub_parent_input_editTextView_phone = editText { }
                    find_pw_view_type_a_sub_parent_input_view_phone = view { }
                    find_pw_view_type_a_sub_parent_input_button_phone = button { }
                }

                // Find PW phone auth
                find_pw_view_type_a_sub_parent_input_phone_auth = relativeLayout {
                    find_pw_view_type_a_sub_parent_input_imageView_phone_auth = imageView { }
                    find_pw_view_type_a_sub_parent_input_editTextView_phone_auth = editText { }
                    find_pw_view_type_a_sub_parent_input_view_phone_auth = view { }
                    find_pw_view_type_a_sub_parent_input_button_phone_auth = button { }
                }

                // Find PW phone auth timer
                find_pw_view_type_a_sub_parent_input_phone_auth_timer_layout = relativeLayout {
                    find_pw_view_type_a_sub_parent_input_phone_auth_timer_textView = textView {  }
                }

                // Find PW Button
                find_pw_view_type_a_sub_parent_input_find_button_layout = verticalLayout {
                    find_pw_view_type_a_sub_parent_input_find_button = button {  }
                }
            }
        }.view
    }

    lateinit var find_pw_view_type_b_adapter_main_parent: LinearLayout

    lateinit var find_pw_view_type_b_sub_parent_input_id_email: RelativeLayout
    lateinit var find_pw_view_type_b_sub_parent_input_imageView_id_email: ImageView
    lateinit var find_pw_view_type_b_sub_parent_input_editTextView_id_email: EditText
    lateinit var find_pw_view_type_b_sub_parent_input_view_id_email: View

    lateinit var find_pw_view_type_b_sub_parent_input_phone: RelativeLayout
    lateinit var find_pw_view_type_b_sub_parent_input_imageView_phone: ImageView
    lateinit var find_pw_view_type_b_sub_parent_input_editTextView_phone: EditText
    lateinit var find_pw_view_type_b_sub_parent_input_view_phone: View

    lateinit var find_pw_view_type_b_sub_parent_input_find_button_layout: LinearLayout
    lateinit var find_pw_view_type_b_sub_parent_input_find_button: Button

    fun getFindPwAdapterItem_b(mCon: Context): View {
        return mCon.UI {
            find_pw_view_type_b_adapter_main_parent = linearLayout {

                // Find Id or Email phone
                find_pw_view_type_b_sub_parent_input_id_email = relativeLayout {
                    find_pw_view_type_b_sub_parent_input_imageView_id_email = imageView { }
                    find_pw_view_type_b_sub_parent_input_editTextView_id_email = editText { }
                    find_pw_view_type_b_sub_parent_input_view_id_email = view { }
                }

                // Find PW phone
                find_pw_view_type_b_sub_parent_input_phone = relativeLayout {
                    find_pw_view_type_b_sub_parent_input_imageView_phone = imageView { }
                    find_pw_view_type_b_sub_parent_input_editTextView_phone = editText { }
                    find_pw_view_type_b_sub_parent_input_view_phone = view { }
                }

                // Find PW Button
                find_pw_view_type_b_sub_parent_input_find_button_layout = verticalLayout {
                    find_pw_view_type_b_sub_parent_input_find_button = button {  }
                }
            }
        }.view
    }

    inline fun ViewManager.viewPager2(theme: Int = 0, init: ViewPager2.() -> Unit) = ankoView({
        ViewPager2(it)
    }, theme, init)
    inline fun ViewManager.tabLayout(theme: Int = R.style.AppTheme, init: TabLayout.() -> Unit) = ankoView({ TabLayout(it) }, theme, init)

    // [Auth Code]
    lateinit var find_view_type_b_auth_code_main_parent: LinearLayout

    fun getAuthCodeView(mContext: Context): View {
        return mContext.UI {
            find_view_type_b_auth_code_main_parent = linearLayout {

            }
        }.view
    }

    /** -------------------------------------------------------------------- **/

    /** [main] ----------------------------------------------**/
    lateinit var main_parrent : FrameLayout
    lateinit var main_viewPager : ViewPager2
    lateinit var main_tabLayout : TabLayout
    inline fun ViewManager.ScalingImageView(theme: Int = 0, init: ScalingImageView.() -> Unit) = ankoView({ ScalingImageView(it) }, theme, init)
    inline fun ViewManager.ViewPager2(theme: Int = 0, init: ViewPager2.() -> Unit) = ankoView({ ViewPager2(it) }, theme, init)
    inline fun ViewManager.TabLayout(theme: Int = R.style.AppTheme, init: TabLayout.() -> Unit) = ankoView({ TabLayout(it) }, theme, init)

    fun getMain_TYPE_A(mCon:Context) : View {
        return mCon.UI {
            main_parrent = frameLayout {
                main_viewPager = ViewPager2 {
                    orientation = ViewPager2.ORIENTATION_VERTICAL
                }
                main_tabLayout = TabLayout {

                }.lparams(matchParent, 100)
            }
        }.view
    }


    fun getViewpager_Item_1_MAIN(mCon:Context) : View {
        return mCon.UI {
            frameLayout {
                lparams(matchParent,matchParent)
                textView {
                    id = R.id.viewPager_item_text
                }.lparams(matchParent, matchParent)

            }
        }.view
    }

    /**----------------------------------------------------------------**/

}