package com.codberg.mvvm_type_A.sample.view.init

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.widget.HorizontalScrollView
import com.codberg.mvvm_type_A.R
import com.codberg.mvvm_type_A.sample.model.request.request_testApi
import com.codberg.mvvm_type_A.sample.viewmodel.ViewModel

open class init_data {

    val DATA_NONE   = -2
    val CUSTOM   = -1
    val TYPE_A =  0
    val TYPE_B =  1
    val TYPE_C =  2
    val TYPE_D =  3

    val HORIZONTAL =  4
    val VERTICAL =  5

    /** [common] **/
    var layout_config = 0 // 0, 1, 2, 3
    var layout_transition_type = TYPE_A

    /** [splash] ---------------------------------------------------------**/

        //타입
        var splash_view_type = TYPE_A // TYPE-A, customView
        var splash_anmation_type = TYPE_A // TYPE-A, customView

        //backround
        var splash_background = DATA_NONE //배경이미지
        var splash_backgroundColor = Color.parseColor("#444444") //배경이미지

        //이미지뷰
        var splash_img  = R.drawable.ic_launcher_foreground // R.drawble.00000
        var splash_img_scaleX = 0.5f// 0.3f
        var splash_img_scaleY = 0.5f// 0.3f
        var splash_img_positionX  = 0.25f// 0.3f
        var splash_img_positionY  = 0.25f// 0.3f

        //텍스트뷰
        var splash_text_color = Color.parseColor("#E2D9CD")
        var splash_text_scaleX = 0.6f// 0.3f
        var splash_text_scaleY = 0.1f// 0.3f
        var splash_text_positionX = 0.205f//0.3f
        var splash_text_positionY = 0.01f//0.8f
        var splash_text_size = 18.0f// 14
        var splash_text = "MAXIZER"// Codberg.co
        var splash_delay_time = 2500

    /**------------------------------------------------------------------**/


    /** [Login] ---------------------------------------------------------**/

        //타입
        var login_view_type = TYPE_A// TYPE-A, customView
        var login_anmation_type = TYPE_A // TYPE-A, customView

        //layout
        var login_parrent_background = DATA_NONE
        var login_parrent_backgroundColor = Color.parseColor("#222222")

        //이미지(external)
        var login_parrent_image_src = DATA_NONE// 이미지
        var login_parrent_image_scaleX = 0.0f// 0.3f
        var login_parrent_image_scaleY = 0.0f// 0.3f
        var login_parrent_image_positionX = 0.0f// 0.3f
        var login_parrent_image_positionY = 0.0f// 0.3f

        //텍스트(external)
        var login_parrent_text_background = ""// 이미지
        var login_parrent_text_scaleX = 0.0f// 0.3f
        var login_parrent_text_scaleY = 0.0f// 0.3f
        var login_parrent_text_positionX = 0.0f// 0.3f
        var login_parrent_text_positionY = 0.0f// 0.3f

        //서브 레이아웃
        var login_sub_parent_1_background = DATA_NONE//배경이미지
        var login_sub_parent_1_backgroundColor = Color.parseColor("#444444")//배경이미지
        var login_sub_parent_1_scaleX  = 0.85f// 0.3f
        var login_sub_parent_1_scaleY  = 0.65f// 0.3f
        var login_sub_parent_1_positionX = 0.075f// 0.3f
        var login_sub_parent_1_positionY = 0.06f// 0.3f

        //이미지(internal)
        var login_sub_parent_1_image_src = R.drawable.ic_launcher_foreground// 이미지
        var login_sub_parent_1_image_scaleX = 0.5f// 0.3f
        var login_sub_parent_1_image_scaleY = 0.5f// 0.3f
        var login_sub_parent_1_image_positionX = 0.33f// 0.3f
        var login_sub_parent_1_image_positionY = -0.035f// 0.3f

        //텍스트1(internal)
        var login_sub_parent_1_text1_str = "MAXIZER Serviece"
        var login_sub_parent_1_text1_typeface = Typeface.SANS_SERIF
        var login_sub_parent_1_text1_scaleX = 0.7f// 0.3f
        var login_sub_parent_1_text1_scaleY = 0.16f// 0.3f
        var login_sub_parent_1_text1_positionX = 0.055f// 0.3f
        var login_sub_parent_1_text1_positionY = 0.09f// 0.3f
        var login_sub_parent_1_text1_textSize  = 24f// 0.3f
        var login_sub_parent_1_text1_textColor = Color.parseColor("#E2D9CD")// 0.3f

        //텍스트2(internal)
        var login_sub_parent_1_text2_str = ""
        var login_sub_parent_1_text2_typeface = Typeface.SANS_SERIF
        var login_sub_parent_1_text2_scaleX = 0.0f// 0.3f
        var login_sub_parent_1_text2_scaleY = 0.0f// 0.3f
        var login_sub_parent_1_text2_positionX = 0.0f// 0.3f
        var login_sub_parent_1_text2_positionY = 0.0f// 0.3f
        var login_sub_parent_1_text2_textSIze = 32f// 0.3f
        var login_sub_parent_1_text2_textColor = Color.parseColor("#777777")// 0.3f

        //입력 레이아웃
        var login_sub_parent_2_background = DATA_NONE//배경이미지
        var login_sub_parent_2_backgroundColor = Color.parseColor("#ffffff")//배경이미지
        var login_sub_parent_2_scaleX  = 0.85f// 0.3f
        var login_sub_parent_2_scaleY  = 0.65f// 0.3f
        var login_sub_parent_2_positionX = 0.117f// 0.3f
        var login_sub_parent_2_positionY = 0.29f// 0.3f

        //텍스트(input)
        var login_sub_parent_2_text1_str = "LOGIN"
        var login_sub_parent_2_text1_typeface = Typeface.SANS_SERIF
        var login_sub_parent_2_text1_scaleX = 0.6f// 0.3f
        var login_sub_parent_2_text1_scaleY = 0.15f// 0.3f
        var login_sub_parent_2_text1_positionX = 0.11f// 0.3f
        var login_sub_parent_2_text1_positionY = 0.07f// 0.3f
        var login_sub_parent_2_text1_textSize = 32f// 0.3f
        var login_sub_parent_2_text1_textColor = Color.parseColor("#777777")// 0.3f

        //입력뷰(ID)
        var login_sub_parent_2_input_id_hint = "아이디 입력"
        var login_sub_parent_2_input_id_hintColor = Color.parseColor("#aaaaaa")
        var login_sub_parent_2_input_id_textSize = 16.0f
        var login_sub_parent_2_input_id_textColor = Color.parseColor("#222222")
        var login_sub_parent_2_input_id_scaleX  = 0.6f// 0.3f
        var login_sub_parent_2_input_id_scaleY  = 0.09f// 0.3f
        var login_sub_parent_2_input_id_positionX = 0.1f// 0.3f
        var login_sub_parent_2_input_id_positionY = -0.02f// 0.3f

        //입력뷰(PW)
        var login_sub_parent_2_input_pw_hint = "비밀번호 입력"
        var login_sub_parent_2_input_pw_hintColor = Color.parseColor("#aaaaaa")
        var login_sub_parent_2_input_pw_textSize = 16.0f
        var login_sub_parent_2_input_pw_textColor = Color.parseColor("#222222")
        var login_sub_parent_2_input_pw_scaleX  = 0.6f// 0.3f
        var login_sub_parent_2_input_pw_scaleY  = 0.09f// 0.3f
        var login_sub_parent_2_input_pw_positionX = 0.1f// 0.3f
        var login_sub_parent_2_input_pw_positionY = -0.02f// 0.3f

        //외부 기타 텍스트
        var login_sub_parent_2_text2_str = ""//
        var login_sub_parent_2_text2_scaleX = 0.6f
        var login_sub_parent_2_text2_scaleY = 0.15f
        var login_sub_parent_2_text2_positionX = 0.28f//
        var login_sub_parent_2_text2_positionY = 0.92f//
        var login_sub_parent_2_text2_textSize = 25f
        var login_sub_parent_2_text2_textColor = Color.parseColor("#ffffff")
        var login_sub_parent_2_text2_background = DATA_NONE
        var login_sub_parent_2_text2_backgroundColor = DATA_NONE

        //로그인 공통
        var login_sub_parent_2_signIn_group_orientation = VERTICAL // VERTICAL , HORIAONTAL
        var login_sub_parent_2_signIn_group_nomal_active = true
        var login_sub_parent_2_signIn_group_kakao_active = false
        var login_sub_parent_2_signIn_group_faceBook_active = false
        var login_sub_parent_2_signIn_group_naver_active = false
        var login_sub_parent_2_signIn_group_item_marginTop = 15 //15 , 10
        var login_sub_parent_2_signIn_group_item_marginLeft = 0 //0 , 0
        var login_sub_parent_2_signIn_group_item_marginRight = 0 //0 , 25
        var login_sub_parent_2_signIn_group_item_marginBottom = 0 //0 , 0
        var login_sub_parent_2_signIn_group_item_scaleX = 0.6f //0.6f , 0.18f
        var login_sub_parent_2_signIn_group_item_scaleY = 0.05f //0.05f , 0.1f
        var login_sub_parent_2_signIn_group_item_positionX = 0.105f// 0.105f , 0.115f
        var login_sub_parent_2_signIn_group_item_positionY = -0.02f// -0.02f , -0.02f
        var login_sub_parent_2_signIn_group_text_str = "Codberg. CO"//
        var login_sub_parent_2_signIn_group_text_scaleX = 0.2f
        var login_sub_parent_2_signIn_group_text_scaleY = 0.1f
        var login_sub_parent_2_signIn_group_text_positionX = 0.33f//
        var login_sub_parent_2_signIn_group_text_positionY = 0.18f//
        var login_sub_parent_2_signIn_group_text_textSize = 11f
        var login_sub_parent_2_signIn_group_text_textColor = Color.parseColor("#222222")
        var login_sub_parent_2_signIn_group_text_background = DATA_NONE
        var login_sub_parent_2_signIn_group_text_backgroundColor = DATA_NONE

        //일반 로그인 버튼
        var login_sub_parent_2_signIn_group_nomal_login_str = "로그인"
        var login_sub_parent_2_signIn_group_nomal_login_textSize = 15f
        var login_sub_parent_2_signIn_group_nomal_login_textColor = Color.parseColor("#222222")
        var login_sub_parent_2_signIn_group_nomal_login_background = R.drawable.round
        var login_sub_parent_2_signIn_group_nomal_login_backgroundColor = DATA_NONE

        //카카오 로그인 버튼
        var login_sub_parent_2_signIn_group_kakao_login_str = "카카오 로그인"
        var login_sub_parent_2_signIn_group_kakao_login_textSize = 15f
        var login_sub_parent_2_signIn_group_kakao_login_textColor = Color.parseColor("#222222")
        var login_sub_parent_2_signIn_group_kakao_login_background = R.drawable.round_yellow
        var login_sub_parent_2_signIn_group_kakao_login_backgroundColor = DATA_NONE

        //페이스북 로그인 버튼
        var login_sub_parent_2_signIn_group_faceBook_login_str = "페이스북 로그인"
        var login_sub_parent_2_signIn_group_faceBook_login_textSize = 15f
        var login_sub_parent_2_signIn_group_faceBook_login_textColor = Color.parseColor("#eeeeee")
        var login_sub_parent_2_signIn_group_faceBook_login_background = R.drawable.round_blue
        var login_sub_parent_2_signIn_group_faceBook_login_backgroundColor = DATA_NONE

        //네이버 로그인 버-튼
        var login_sub_parent_2_signIn_group_naver_login_str = "네이버 로그인"
        var login_sub_parent_2_signIn_group_naver_login_textSize = 15f
        var login_sub_parent_2_signIn_group_naver_login_textColor = Color.parseColor("#ffffff")
        var login_sub_parent_2_signIn_group_naver_login_background = R.drawable.round_green
        var login_sub_parent_2_signIn_group_naver_login_backgroundColor = DATA_NONE

        //회원가입 & 비번찾기 공통
        var login_sub_parent_2_info_group_orientation = HORIZONTAL
        var login_sub_parent_2_info_group_background = DATA_NONE
        var login_sub_parent_2_info_group_backgroundColor = Color.parseColor("#999999")
        var login_sub_parent_2_info_group_space_scaleX = 0.01f
        var login_sub_parent_2_info_group_space_scaleY = 0.02f
        var login_sub_parent_2_info_group_space_positionX = 0.14f//
        var login_sub_parent_2_info_group_space_positionY = 0.007f//

        //회원가입 버튼
        var login_sub_parent_2_info_group_signUP_text = "회원가입"
        var login_sub_parent_2_info_group_signUP_textSize = 15f
        var login_sub_parent_2_info_group_signUP_textColor = Color.parseColor("#222222")
        var login_sub_parent_2_info_group_signUP_scaleX = 0.2f
        var login_sub_parent_2_info_group_signUP_scaleY = 0.03f
        var login_sub_parent_2_info_group_signUP_positionX = 0.14f// 0.3f
        var login_sub_parent_2_info_group_signUP_positionY = 0.00f// 0.3f


        //비번찾기 버튼
        var login_sub_parent_2_info_group_findPW_text = "아이디/비번찾기"
        var login_sub_parent_2_info_group_findPW_textSize = 15f
        var login_sub_parent_2_info_group_findPW_textColor = Color.parseColor("#222222")
        var login_sub_parent_2_info_group_findPW_scaleX = 0.4f
        var login_sub_parent_2_info_group_findPW_scaleY = 0.03f
        var login_sub_parent_2_info_group_findPW_positionX = 0.1f// 0.3f
        var login_sub_parent_2_info_group_findPW_positionY = 0.00f// 0.3f

    /**------------------------------------------------------------------**/

    /** [SignUp_Start] ------------------------------------------------------**/

    // Sign Up Layout Type
    var signup_view_type = TYPE_A // TYPE-A, customView
    var signup_anmation_type = CUSTOM // TYPE-A, customView

    // Parent Layout
    var signup_background = DATA_NONE
    var signup_backgroundColor = Color.parseColor("#ffffff")

    // [Start] 회원가입 항목 틀 설정 ==========================================================================
    // Sub Parent
    var signup_sub_parent_input_scaleX    = 0.9f        // layout 가로 크기
    var signup_sub_parent_input_scaleY    = 0.08f       // layout 세로 크기

    // Sub Parent ImageView
    var signup_sub_parent_input_imageView_scaleX    = 0.08f             // layout 가로 크기
    var signup_sub_parent_input_imageView_scaleY    = 0.08f             // layout 세로 크기

    // Sub Parent EditTextView
    var signup_sub_parent_input_editTextView_scaleX    = signup_sub_parent_input_scaleX - signup_sub_parent_input_imageView_scaleX
    var signup_sub_parent_input_editTextView_scaleY    = 0.08f
    var signup_sub_parent_input_editTextView_marginLeft = 0.1f      // icon과 EditText 사이 간격
    // [End] 회원가입 항목 틀 설정 ==========================================================================

    // 회원가입 최상단 제목
    var signup_title_text_value        = "Codberg 회원가입"
    var signup_title_text_size         = 25.0f // ex) 14.0f
    var signup_title_text_color        = Color.parseColor("#2e2e2e")
    var signup_title_text_scaleX       = 1.0f  // ex) 0.3f
    var signup_title_text_scaleY       = 0.1f  // ex) 0.3f
    var signup_title_text_positionX    = 0.0f  // ex) 0.3f
    var signup_title_text_positionY    = 0.0f  // ex) 0.3f

    // ==================================================================================
    //                      [Start] 회원가입 항목 사용 유무
    // ==================================================================================
    var use_signup_id_email         = true     // 아이디 or 이메일
    var use_signup_password         = true     // 비밀번호 입력
    var use_signup_password_confirm = true     // 비밀번호 입력 확인
    var use_signup_name             = true     // 이름 입력
    var use_signup_phone            = true     // 휴대폰 번호 입력
    var use_signup_phone_auth       = true     // 휴대폰 번호 인증
    var use_signup_agreement        = true     // 약관
    // ==================================================================================
    //                      [End] 회원가입 항목 사용 유무
    // ==================================================================================

    // 회원가입 항목들의 EditText 밑 언더바 설정 (공통)
    var use_under_bar               = true      // 언더바 사용 유무
    var under_bar_height            = 0.002f    // 언더바 두께
    var signup_sub_parent_input_view_marginBottom = 0.008f
    var under_bar_color             = Color.parseColor("#2e2e2e")  // 언더바 색

    // 회원가입 항목들의 EditText Hint Color (공통)
    var editText_hint_color         = Color.parseColor("#902e2e2e")

    // 회원가입 항목들의 EditText Hint Text Value
    var editText_idEmail_hint_value             = "아이디(이메일)"
    var editText_password_hint_value            = "비밀번호"
    var editText_password_confirm_hint_value    = "비밀번호 확인"
    var editText_name_hint_value                = "이름"
    var editText_phone_hint_value               = "휴대폰 번호"
    var editText_phoneAuth_hint_value           = "인증번호 입력"


    // 회원가입 항목들의 EditText Text Color (공통)
    var editText_text_color         = Color.parseColor("#2e2e2e")

    // 회원가입 항목들의 EditText Max Length
    var editText_idEmail_max_length = 20
    var editText_password_max_length = 20
    var editText_name_max_length = 20
    var editText_phone_max_length = 20
    var editText_phoneAuth_max_length = 20

    // 회원가입 항목들의 Icon Image Resource
    var icon_idEmail_drawable_resource = R.drawable.icon_user
    var icon_password_drawable_resource = R.drawable.icon_password
    var icon_name_drawable_resource = R.mipmap.ic_launcher_round
    var icon_phone_drawable_resource = R.drawable.icon_phone
    var icon_phoneAuth_drawable_resource = R.drawable.icon_confirm

    // [휴대폰 인증 버튼]
    var passwordAuth_button_scaleX              = 0.2f // 0.3f
    var passwordAuth_button_scaleY              = 0.06f - 0.008f // 0.008f (언더바 마진 만큼 크기를 줄여서 center를 맞춘다.)
    var passwordAuth_button_background_resource = R.drawable.round // R.drawable.xxxx
    var passwordAuth_button_background_color    = Color.parseColor("#ffffff")
    var passwordAuth_button_text_value          = "인증번호 요청"
    var passwordAuth_button_text_size           = 12f // sp
    var passwordAuth_button_text_color          = Color.parseColor("#222222")

    // [휴대폰 인증 확인 버튼]
    var passwordAuth_confirm_button_scaleX              = 0.2f // 0.3f
    var passwordAuth_confirm_button_scaleY              = 0.06f - 0.008f // 0.008f (언더바 마진 만큼 크기를 줄여서 center를 맞춘다.)
    var passwordAuth_confirm_button_background_resource = R.drawable.round // R.drawable.xxxx
    var passwordAuth_confirm_button_background_color    = Color.parseColor("#ffffff")
    var passwordAuth_confirm_button_text_value          = "인증번호 확인"
    var passwordAuth_confirm_button_text_size           = 12f // sp
    var passwordAuth_confirm_button_text_color          = Color.parseColor("#222222")

    var auth_time = 3

    // [Start] 약관 ==================================================================================
    var agreement_checkBox_background_resource      = DATA_NONE
    var agreement_checkBox_scaleX                   = 0.1f
    var agreement_checkBox_scaleY                   = 0.1f

    // 약관 [전체동의]
    var agreement_checkBox_all_borderColor          = Color.parseColor("#2e2e2e")
    var agreement_checkBox_all_text_value           = "전체동의"
    var agreement_checkBox_all_text_color           = Color.parseColor("#2e2e2e")
    var agreement_checkBox_all_text_size            = 14f

    // 약관 [이용약관]
    var agreement_checkBox_item1_borderColor          = Color.parseColor("#2e2e2e")
    var agreement_checkBox_item1_text_value           = "이용약관"
    var agreement_checkBox_item1_text_color           = Color.parseColor("#2e2e2e")
    var agreement_checkBox_item1_text_size            = 14f
    var agreement_checkBox_item1_show_text_value      = "[보기]"
    var agreement_checkBox_item1_show_text_color      = Color.parseColor("#2e2e2e")
    var agreement_checkBox_item1_show_text_size       = 14f

    // 약관 [개인정보취급방침]
    var agreement_checkBox_item2_borderColor          = Color.parseColor("#2e2e2e")
    var agreement_checkBox_item2_text_value           = "개인정보취급방침"
    var agreement_checkBox_item2_text_color           = Color.parseColor("#2e2e2e")
    var agreement_checkBox_item2_text_size            = 14f
    var agreement_checkBox_item2_show_text_value      = "[보기]"
    var agreement_checkBox_item2_show_text_color      = Color.parseColor("#2e2e2e")
    var agreement_checkBox_item2_show_text_size       = 14f
    // [End] 약관 ==================================================================================

    // 회원가입 완료 버튼
    var signup_complete_button_background_resource  = R.drawable.round
    var signup_complete_button_background_color     = DATA_NONE
    var signup_complete_button_scaleX               = 0.9f
    var signup_complete_button_scaleY               = 0.07f
    var signup_complete_button_marginBottom         = 0.03f
    var signup_complete_button_text_value           = "회원가입 완료"
    var signup_complete_button_text_color           = Color.parseColor("#2e2e2e")
    var signup_complete_button_text_size            = 14f

    /** [SignUp_End]  ------------------------------------------------------------------**/

    /** [mainView] ------------------------------------------------------**/

        //타입
        var main_view_type = CUSTOM // main_type_A or CUSTOM

    /**------------------------------------------------------------------**/
    var test = request_testApi::javaClass
}