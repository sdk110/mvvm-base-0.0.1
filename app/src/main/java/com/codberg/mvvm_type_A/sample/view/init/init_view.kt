package com.codberg.mvvm_type_A.sample.view.init

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewManager
import android.widget.*
import org.jetbrains.anko.*
import org.jetbrains.anko.custom.ankoView

open class init_view(mCon : Context) {

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
                        login_Type_A_sub_parent_2_info_group_sign_up = textView { gravity = Gravity.CENTER }
                        login_Type_A_sub_parent_2_info_group_space   = imageView{ }
                        login_Type_A_sub_parent_2_info_group_find_pw = textView { gravity = Gravity.CENTER }
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

    /** [main] ----------------------------------------------**/
    fun getMain_TYPE_A(mCon:Context) : View {
        return mCon.UI {
            verticalLayout {
                backgroundColor = Color.parseColor("#ffffff")
            }
        }.view
    }

    /**----------------------------------------------------------------**/

}