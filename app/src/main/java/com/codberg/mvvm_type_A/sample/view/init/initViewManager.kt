package com.codberg.mvvm_type_A.sample.view.init

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.animation.ValueAnimator.REVERSE
import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.text.InputFilter
import android.view.Gravity
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.*
import androidx.core.animation.addListener
import androidx.core.content.ContextCompat
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import com.libs.cutil_kotlin.ViewUtil
import com.codberg.mvvm_type_A.sample.viewmodel.ViewModel
import com.libs.cutil_kotlin.BasicUtil
import com.libs.cutil_kotlin.ImageLoader
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onCheckedChange

abstract class initViewManager(rUtil : ViewUtil?, rViewModel : ViewModel, rCon : initActivity, rinit : init_data) : AnkoLogger {

    var con = rCon
    var util = rUtil
    var viewModel = rViewModel
    var init = rinit
    var cutomView_LOGIN : View? = null
    var customView_SPLASH : View? = null
    var customView_MAIN : View? = null
    var devWidth  = BasicUtil.getDeviceSize(con)[0]
    var devHeight = BasicUtil.getDeviceSize(con)[1]

    init  {
        if(init.splash_view_type == init.CUSTOM)    customView_SPLASH = onDraw_CUSTUM_VIEW_SPLASH(rUtil, rViewModel, rCon, rinit)
        if(init.login_view_type == init.CUSTOM)     cutomView_LOGIN   = onDraw_CUSTUM_VIEW_LOGIN(rUtil, rViewModel, rCon, rinit)
        if(init.main_view_type == init.CUSTOM)      customView_MAIN   = onDraw_CUSTUM_VIEW_MAIN(rUtil, rViewModel, rCon, rinit)
    }

    /** [공통_START]------------------**/

    fun checkData(data : Int) : Boolean = data != init.DATA_NONE
    fun checkCustomData(data : Int) : Boolean = data  == init.CUSTOM
    fun loadImage(wRate:Float, hRate:Float, path:Int,imgView:ImageView)  {
        ImageLoader().init(con, (devWidth.toFloat() * wRate).toInt(), (devHeight.toFloat() * hRate).toInt()).setPath(path).setImage(imgView)
    }
    fun loadImage(wRate:Float, hRate:Float, path:String,imgView:ImageView)  {
        ImageLoader().init(con, (devWidth.toFloat() * wRate).toInt(), (devHeight.toFloat() * hRate).toInt()).setPath(path).setImage(imgView)
    }

    /** [공통_END]------------------**/


    /** [시작화면_START]---------------------------------------------------------------------------------------------------------------------------------------------------**/

    fun setSplash()  {

        if(checkCustomData(init.splash_view_type))  return

        //part1
        setParrent_SPLASH()
        setText_SPLASH()
        setImage_SPLASH()
        setAnimation_SPLASH()

    }

    fun setParrent_SPLASH()  {
        init.splash_contentVIew_Type_A.apply {
            layoutParams = FrameLayout.LayoutParams(matchParent,matchParent)
            if(checkData(init.splash_background))         backgroundResource = init.splash_background
            if(checkData(init.splash_backgroundColor))    backgroundColor = init.splash_backgroundColor
        }
    }

    fun setText_SPLASH()  {
        init.splash_contentVIew_Type_A_text.apply {
            alpha = 0.0f
            text = init.splash_text
            textSize = init.splash_text_size
            layoutParams = LinearLayout.LayoutParams( (devWidth.toFloat() * init.splash_text_scaleX).toInt(),(devHeight.toFloat() * init.splash_text_scaleY).toInt() )
            translationX = (devWidth.toFloat() * init.splash_text_positionX)
            translationY = (devHeight.toFloat() * init.splash_text_positionY)
            textColor = init.splash_text_color
        }
    }

    fun setImage_SPLASH()  {
        loadImage(init.splash_img_scaleX, init.splash_img_scaleY, init.splash_img, init.splash_contentVIew_Type_A_img)
        init.splash_contentVIew_Type_A_img.apply {
            alpha = 0.0f
            layoutParams = LinearLayout.LayoutParams( (devWidth.toFloat() * init.splash_img_scaleX).toInt(),(devHeight.toFloat() * init.splash_img_scaleY).toInt() )
            translationX = (devWidth.toFloat() * init.splash_img_positionX)
            translationY = (devHeight.toFloat() * init.splash_img_positionY)
        }
    }

    fun setAnimation_SPLASH()  {
        when(init.splash_anmation_type)  {
            init.TYPE_A -> playAnimation_SPLASH(init.splash_contentVIew_Type_A_img , init.splash_contentVIew_Type_A_text)
            init.CUSTOM -> onPlayCustomAnimation_Splash_TypeA(init.splash_contentVIew_Type_A_img , init.splash_contentVIew_Type_A_text)
        }
    }

    fun playAnimation_SPLASH(img : ImageView, text : TextView)  {

        var set = AnimatorSet()

        var ani_A : ValueAnimator  = ObjectAnimator.ofFloat( img,  "ScaleX", 1.0f, 1.2f )
        ani_A.duration = 350
        ani_A.interpolator = LinearInterpolator()
        ani_A.repeatMode = REVERSE
        ani_A.startDelay = 300
        ani_A.repeatCount = 1

        var ani_B : ValueAnimator  = ObjectAnimator.ofFloat( img,  "ScaleY", 1.0f, 1.2f )
        ani_B.duration = 350
        ani_B.interpolator = LinearInterpolator()
        ani_B.repeatMode = REVERSE
        ani_B.startDelay = 400
        ani_B.repeatCount = 1

        var ani_C : ValueAnimator  = ObjectAnimator.ofFloat( img,  "alpha", 0.0f, 1.0f )
        ani_C.duration = 250
        ani_C.startDelay = 200
        ani_C.interpolator = FastOutLinearInInterpolator()

        var ani_D : ValueAnimator  = ObjectAnimator.ofFloat( text,  "alpha", 0.0f, 1.0f )
        ani_D.duration = 400
        ani_D.startDelay = 500
        ani_D.interpolator = FastOutLinearInInterpolator()

        set.play(ani_A).with(ani_B).with(ani_C).with(ani_D)
        set.start()

    }

    /** [시작화면_END]-----------------------------------------------------------------------------------------------------------------------------------------------------------**/




    @SuppressLint("ClickableViewAccessibility")

    /** [로그인화면_START]---------------------------------------------------------------------------------------------------------------------------------------------------**/

    fun setLogin(init : init_data)  {

        if(checkCustomData(init.login_view_type))  return

        //part1
        setParrent_LOGIN()
        setParrentImage_LOGIN()
        setParrentText_LOGIN()
        setParrentText2_LOGIN()

        //part2
        setSubParrent_LOGIN()
        setSubParrentImage_LOGIN()
        setSubParrentText_LOGIN()
        setSubParrentText1_LOGIN()

        //part3
        setSubParrent1_LOGIN()
        setSubParrent1_Input_Id_LOGIN()
        setSubParrent1_Input_pw_LOGIN()
        setSubParrent1_signIn_group_LOGIN()
        setSubParrent1_signIn_group_nomalLogin()
        setSubParrent1_signIn_group_kakaoLogin()
        setSubParrent1_signIn_group_naverLogin()
        setSubParrent1_signIn_group_facebookLogin()

        //part4
        setSubParrent2_text1_LOGIN()
        setSubParrent2_text2_LOGIN()
        setSubParrent2_infoGroup_LOGIN()
        setSubParrent2_infoGroup_contents_LOGIN()
        setSubParrent2_infoGroupSpace_LOGIN()
        setAnimation_LOGIN()

    }

    fun setParrent_LOGIN()  {
        init.login_Type_A_parrent.layoutParams = FrameLayout.LayoutParams(matchParent,matchParent)
        if(checkData(init.login_parrent_background))         init.login_Type_A_parrent.backgroundResource = init.login_parrent_background
        if(checkData(init.login_parrent_backgroundColor))    init.login_Type_A_parrent.backgroundColor = init.login_parrent_backgroundColor
    }

    fun setParrentImage_LOGIN()  {
        loadImage(init.login_parrent_image_scaleX, init.login_parrent_image_scaleY, init.login_parrent_image_src, init.login_Type_A_parrent_image)
        init.login_Type_A_parrent_image.apply {
            layoutParams = FrameLayout.LayoutParams((devWidth.toFloat() * init.login_parrent_image_scaleX).toInt(), (devHeight.toFloat() * init.login_parrent_image_scaleY).toInt() )
            translationX = (devWidth.toFloat() * init.login_parrent_image_positionX)
            translationY = (devHeight.toFloat() * init.login_parrent_image_positionY)
        }
    }

    fun setParrentText_LOGIN()  {
        init.login_Type_A_parrent_text.apply {
            text = init.login_parrent_text_background
            layoutParams = FrameLayout.LayoutParams((devWidth.toFloat() * init.login_parrent_text_scaleX).toInt(), (devHeight.toFloat() * init.login_parrent_text_scaleY).toInt() )
            translationX = (devWidth.toFloat() * init.login_parrent_text_positionX)
            translationY = (devHeight.toFloat() * init.login_parrent_text_positionY)
        }
    }

    fun setSubParrent_LOGIN()  {
        init.login_Type_A_sub_parent_1.apply {
            layoutParams =  FrameLayout.LayoutParams((devWidth.toFloat() * init.login_sub_parent_1_scaleX).toInt(), (devHeight.toFloat() * init.login_sub_parent_1_scaleY).toInt() )
            translationX = (devWidth.toFloat() * init.login_sub_parent_1_positionX)
            translationY = (devHeight.toFloat() * init.login_sub_parent_1_positionY)
            if(checkData(init.login_sub_parent_1_background))       backgroundResource   = init.login_sub_parent_1_background
            if(checkData(init.login_sub_parent_1_backgroundColor))  backgroundColor      = init.login_sub_parent_1_backgroundColor
        }
    }

    fun setSubParrentImage_LOGIN() {
        loadImage(init.login_sub_parent_1_image_scaleX, init.login_sub_parent_1_image_scaleY, init.login_sub_parent_1_image_src, init.login_Type_A_sub_parent_1_image)
        init.login_Type_A_sub_parent_1_image.apply {
            layoutParams = FrameLayout.LayoutParams((devWidth.toFloat() * init.login_sub_parent_1_image_scaleX).toInt(), (devHeight.toFloat() * init.login_sub_parent_1_image_scaleY).toInt() )
            translationX = (devWidth.toFloat() * init.login_sub_parent_1_image_positionX)
            translationY = (devHeight.toFloat() * init.login_sub_parent_1_image_positionY)
        }
    }

    fun setSubParrentText_LOGIN() {
        init.login_Type_A_sub_parent_1_text1.apply {
            text = init.login_sub_parent_1_text1_str
            typeface = init.login_sub_parent_1_text1_typeface
            textSize = init.login_sub_parent_1_text1_textSize
            textColor = init.login_sub_parent_1_text1_textColor
            layoutParams = FrameLayout.LayoutParams((devWidth.toFloat() * init.login_sub_parent_1_text1_scaleX).toInt(), (devHeight.toFloat() * init.login_sub_parent_1_text1_scaleY).toInt() )
            translationX = (devWidth.toFloat() * init.login_sub_parent_1_text1_positionX)
            translationY = (devHeight.toFloat() * init.login_sub_parent_1_text1_positionY)
        }
    }

    fun setSubParrentText1_LOGIN() {
        init.login_Type_A_sub_parent_1_text2.apply {
            text = init.login_sub_parent_1_text2_str
            typeface = init.login_sub_parent_1_text2_typeface
            textSize = init.login_sub_parent_1_text2_textSIze
            textColor = init.login_sub_parent_1_text2_textColor
            layoutParams = FrameLayout.LayoutParams((devWidth.toFloat() * init.login_sub_parent_1_text2_scaleX).toInt(), (devHeight.toFloat() * init.login_sub_parent_1_text2_scaleY).toInt() )
            translationX = (devWidth.toFloat() * init.login_sub_parent_1_text2_positionX)
            translationY = (devHeight.toFloat() * init.login_sub_parent_1_text2_positionY)
        }
    }

    fun setSubParrent1_LOGIN() {
        init.login_Type_A_sub_parent_2.apply {
            layoutParams =  FrameLayout.LayoutParams((devWidth.toFloat() * init.login_sub_parent_2_scaleX).toInt(), (devHeight.toFloat() * init.login_sub_parent_2_scaleY).toInt() )
            translationX = (devWidth.toFloat() * init.login_sub_parent_2_positionX)
            translationY = (devHeight.toFloat() * init.login_sub_parent_2_positionY)
            if(checkData(init.login_sub_parent_1_background))       backgroundResource   = init.login_sub_parent_2_background
            if(checkData(init.login_sub_parent_1_backgroundColor))  backgroundColor      = init.login_sub_parent_2_backgroundColor
        }
    }

    fun setSubParrent1_Input_Id_LOGIN() {
        init.login_Type_A_sub_parent_2_input_id.apply {
            hint = init.login_sub_parent_2_input_id_hint
            textSize = init.login_sub_parent_2_input_id_textSize
            hintTextColor = init.login_sub_parent_2_input_id_hintColor
            textColor = init.login_sub_parent_2_input_id_textColor
            layoutParams =  LinearLayout.LayoutParams((devWidth.toFloat() * init.login_sub_parent_2_input_id_scaleX).toInt(), (devHeight.toFloat() * init.login_sub_parent_2_input_id_scaleY).toInt() )
            translationX = (devWidth.toFloat() * init.login_sub_parent_2_input_id_positionX)
            translationY = (devHeight.toFloat() * init.login_sub_parent_2_input_id_positionY)
        }
    }

    fun setSubParrent1_Input_pw_LOGIN() {
        init.login_Type_A_sub_parent_2_input_pw.apply {
            hint = init.login_sub_parent_2_input_pw_hint
            textSize = init.login_sub_parent_2_input_pw_textSize
            hintTextColor = init.login_sub_parent_2_input_pw_hintColor
            textColor = init.login_sub_parent_2_input_pw_textColor
            layoutParams =  LinearLayout.LayoutParams((devWidth.toFloat() * init.login_sub_parent_2_input_pw_scaleX).toInt(), (devHeight.toFloat() * init.login_sub_parent_2_input_pw_scaleY).toInt() )
            translationX = (devWidth.toFloat() * init.login_sub_parent_2_input_pw_positionX)
            translationY = (devHeight.toFloat() * init.login_sub_parent_2_input_pw_positionY)
        }
    }

    fun setSubParrent1_signIn_group_LOGIN() {

        init.login_Type_A_sub_parent_2_signIn_group.translationX = (devWidth.toFloat() * init.login_sub_parent_2_signIn_group_item_positionX)
        init.login_Type_A_sub_parent_2_signIn_group.translationY = (devHeight.toFloat() * init.login_sub_parent_2_signIn_group_item_positionY)

        when(init.login_sub_parent_2_signIn_group_orientation)  {
            init.HORIZONTAL -> init.login_Type_A_sub_parent_2_signIn_group.orientation = LinearLayout.HORIZONTAL
            init.VERTICAL   -> init.login_Type_A_sub_parent_2_signIn_group.orientation = LinearLayout.VERTICAL
        }

        when(init.login_sub_parent_2_signIn_group_kakao_active)  {
            true  -> init.login_Type_A_sub_parent_2_signIn_group_login_kakao.visibility = View.VISIBLE
            false -> init.login_Type_A_sub_parent_2_signIn_group_login_kakao.visibility = View.GONE
        }

        when(init.login_sub_parent_2_signIn_group_faceBook_active)  {
            true  -> init.login_Type_A_sub_parent_2_signIn_group_login_faceBook.visibility = View.VISIBLE
            false -> init.login_Type_A_sub_parent_2_signIn_group_login_faceBook.visibility = View.GONE
        }

        when(init.login_sub_parent_2_signIn_group_naver_active)  {
            true  -> init.login_Type_A_sub_parent_2_signIn_group_login_naver.visibility = View.VISIBLE
            false -> init.login_Type_A_sub_parent_2_signIn_group_login_naver.visibility = View.GONE
        }

        when(init.login_sub_parent_2_signIn_group_nomal_active)  {
            true -> {
                init.login_Type_A_sub_parent_2_signIn_group_nomal_login.visibility = View.VISIBLE
                init.login_Type_A_sub_parent_2_input_id.visibility = View.VISIBLE
                init.login_Type_A_sub_parent_2_input_pw.visibility = View.VISIBLE
                init.login_Type_A_sub_parent_2_info_group_sign_up.visibility = View.VISIBLE
                init.login_Type_A_sub_parent_2_info_group_find_pw.visibility = View.VISIBLE
            }
            false -> {
                init.login_Type_A_sub_parent_2_signIn_group_nomal_login.visibility = View.GONE
                init.login_Type_A_sub_parent_2_input_id.visibility = View.GONE
                init.login_Type_A_sub_parent_2_input_pw.visibility = View.GONE
                init.login_Type_A_sub_parent_2_info_group_sign_up.visibility = View.GONE
                init.login_Type_A_sub_parent_2_info_group_find_pw.visibility = View.GONE
            }
        }

    }

    @SuppressLint("ClickableViewAccessibility")
    fun setSubParrent1_signIn_group_nomalLogin() {
        init.login_Type_A_sub_parent_2_signIn_group_nomal_login.apply {
            text = init.login_sub_parent_2_signIn_group_nomal_login_str
            textSize = init.login_sub_parent_2_signIn_group_nomal_login_textSize
            textColor = init.login_sub_parent_2_signIn_group_nomal_login_textColor
            if(checkData(init.login_sub_parent_2_signIn_group_nomal_login_background))          backgroundResource = init.login_sub_parent_2_signIn_group_nomal_login_background
            if(checkData(init.login_sub_parent_2_signIn_group_nomal_login_backgroundColor))     backgroundColor = init.login_sub_parent_2_signIn_group_nomal_login_backgroundColor

            var parma : LinearLayout.LayoutParams =  LinearLayout.LayoutParams((devWidth.toFloat() * init.login_sub_parent_2_signIn_group_item_scaleX).toInt(), (devHeight.toFloat() * init.login_sub_parent_2_signIn_group_item_scaleY).toInt() )
            parma.setMargins(init.login_sub_parent_2_signIn_group_item_marginLeft,init.login_sub_parent_2_signIn_group_item_marginTop,init.login_sub_parent_2_signIn_group_item_marginRight,init.login_sub_parent_2_signIn_group_item_marginBottom)
            layoutParams = parma

            setOnTouchListener(con.getViewUtil()!!.getAnimatingTouchlistener(ViewUtil.ANIMATION_SMALL_BUTTON__CLICK,
                ViewUtil.animatingTouchlistener {
                    var a = ""
                    var b = ""
                    if(init.login_Type_A_sub_parent_2_input_id.editableText != null) a = init.login_Type_A_sub_parent_2_input_id.editableText.toString()
                    if(init.login_Type_A_sub_parent_2_input_pw.editableText != null) b = init.login_Type_A_sub_parent_2_input_pw.editableText.toString()

                    var isOk = onLoginClick_LoginScene(a , b , util , viewModel , con)
                    if(isOk)    con.setMainScene()
                })
            )
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    fun setSubParrent1_signIn_group_kakaoLogin() {
        //카카오 로그인
        init.login_Type_A_sub_parent_2_signIn_group_login_kakao.apply {
            text = init.login_sub_parent_2_signIn_group_kakao_login_str
            textSize = init.login_sub_parent_2_signIn_group_kakao_login_textSize
            textColor = init.login_sub_parent_2_signIn_group_kakao_login_textColor
            if(checkData(init.login_sub_parent_2_signIn_group_kakao_login_background))          backgroundResource = init.login_sub_parent_2_signIn_group_kakao_login_background
            if(checkData(init.login_sub_parent_2_signIn_group_kakao_login_backgroundColor))     backgroundColor = init.login_sub_parent_2_signIn_group_kakao_login_backgroundColor

            var parma1 : LinearLayout.LayoutParams =  LinearLayout.LayoutParams((devWidth.toFloat() * init.login_sub_parent_2_signIn_group_item_scaleX).toInt(), (devHeight.toFloat() * init.login_sub_parent_2_signIn_group_item_scaleY).toInt() )
            parma1.setMargins(init.login_sub_parent_2_signIn_group_item_marginLeft,init.login_sub_parent_2_signIn_group_item_marginTop,init.login_sub_parent_2_signIn_group_item_marginRight,init.login_sub_parent_2_signIn_group_item_marginBottom)
            layoutParams = parma1

            setOnTouchListener(con.getViewUtil()!!.getAnimatingTouchlistener(ViewUtil.ANIMATION_SMALL_BUTTON__CLICK,
                ViewUtil.animatingTouchlistener {
                    var a = ""
                    var b = ""
                    if(init.login_Type_A_sub_parent_2_input_id.editableText != null) a = init.login_Type_A_sub_parent_2_input_id.editableText.toString()
                    if(init.login_Type_A_sub_parent_2_input_pw.editableText != null) b = init.login_Type_A_sub_parent_2_input_pw.editableText.toString()

                    var isOk = onLoginClick_LoginScene(a , b , util , viewModel , con)
                    if(isOk)    con.setMainScene()
                })
            )
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    fun setSubParrent1_signIn_group_naverLogin()  {
        //네이버 로그인
        init.login_Type_A_sub_parent_2_signIn_group_login_naver.apply {
            text = init.login_sub_parent_2_signIn_group_naver_login_str
            textSize = init.login_sub_parent_2_signIn_group_naver_login_textSize
            textColor = init.login_sub_parent_2_signIn_group_naver_login_textColor
            if(checkData(init.login_sub_parent_2_signIn_group_naver_login_background))          backgroundResource = init.login_sub_parent_2_signIn_group_naver_login_background
            if(checkData(init.login_sub_parent_2_signIn_group_naver_login_backgroundColor))     backgroundColor = init.login_sub_parent_2_signIn_group_naver_login_backgroundColor

            var parma2 : LinearLayout.LayoutParams =  LinearLayout.LayoutParams((devWidth.toFloat() * init.login_sub_parent_2_signIn_group_item_scaleX).toInt(), (devHeight.toFloat() * init.login_sub_parent_2_signIn_group_item_scaleY).toInt() )
            parma2.setMargins(init.login_sub_parent_2_signIn_group_item_marginLeft,init.login_sub_parent_2_signIn_group_item_marginTop,init.login_sub_parent_2_signIn_group_item_marginRight,init.login_sub_parent_2_signIn_group_item_marginBottom)
            layoutParams = parma2

            setOnTouchListener(con.getViewUtil()!!.getAnimatingTouchlistener(ViewUtil.ANIMATION_SMALL_BUTTON__CLICK,
                ViewUtil.animatingTouchlistener {
                    var a = ""
                    var b = ""
                    if(init.login_Type_A_sub_parent_2_input_id.editableText != null) a = init.login_Type_A_sub_parent_2_input_id.editableText.toString()
                    if(init.login_Type_A_sub_parent_2_input_pw.editableText != null) b = init.login_Type_A_sub_parent_2_input_pw.editableText.toString()

                    var isOk = onLoginClick_LoginScene(a , b , util , viewModel , con)
                    if(isOk)    con.setMainScene()
                })
            )
        }
    }
    @SuppressLint("ClickableViewAccessibility")
    fun setSubParrent1_signIn_group_facebookLogin() {
        //페북 로그인
        init.login_Type_A_sub_parent_2_signIn_group_login_faceBook.apply {
            text = init.login_sub_parent_2_signIn_group_faceBook_login_str
            textSize = init.login_sub_parent_2_signIn_group_faceBook_login_textSize
            textColor = init.login_sub_parent_2_signIn_group_faceBook_login_textColor
            if(checkData(init.login_sub_parent_2_signIn_group_faceBook_login_background))          backgroundResource = init.login_sub_parent_2_signIn_group_faceBook_login_background
            if(checkData(init.login_sub_parent_2_signIn_group_faceBook_login_backgroundColor))     backgroundColor = init.login_sub_parent_2_signIn_group_faceBook_login_backgroundColor
            var parma3 : LinearLayout.LayoutParams =  LinearLayout.LayoutParams((devWidth.toFloat() * init.login_sub_parent_2_signIn_group_item_scaleX).toInt(), (devHeight.toFloat() * init.login_sub_parent_2_signIn_group_item_scaleY).toInt() )
            parma3.setMargins(init.login_sub_parent_2_signIn_group_item_marginLeft,init.login_sub_parent_2_signIn_group_item_marginTop,init.login_sub_parent_2_signIn_group_item_marginRight,init.login_sub_parent_2_signIn_group_item_marginBottom)
            layoutParams = parma3
            setOnTouchListener(con.getViewUtil()!!.getAnimatingTouchlistener(ViewUtil.ANIMATION_SMALL_BUTTON__CLICK,
                ViewUtil.animatingTouchlistener {
                    var a = ""
                    var b = ""
                    if(init.login_Type_A_sub_parent_2_input_id.editableText != null) a = init.login_Type_A_sub_parent_2_input_id.editableText.toString()
                    if(init.login_Type_A_sub_parent_2_input_pw.editableText != null) b = init.login_Type_A_sub_parent_2_input_pw.editableText.toString()

                    var isOk = onLoginClick_LoginScene(a , b , util , viewModel , con)
                    if(isOk)    con.setMainScene()
                })
            )
        }
    }

    fun setParrentText2_LOGIN() {
        init.login_Type_A_parrent_text2.apply {
            text = init.login_sub_parent_2_text2_str
            textSize = init.login_sub_parent_2_text2_textSize
            textColor = init.login_sub_parent_2_text2_textColor
            layoutParams =  FrameLayout.LayoutParams((devWidth.toFloat() * init.login_sub_parent_2_text2_scaleX).toInt(), (devHeight.toFloat() * init.login_sub_parent_2_text2_scaleY).toInt() )
            translationX = (devWidth.toFloat() * init.login_sub_parent_2_text2_positionX)
            translationY = (devHeight.toFloat() * init.login_sub_parent_2_text2_positionY)
            if(checkData(init.login_sub_parent_2_text2_background))          backgroundResource = init.login_sub_parent_2_text2_background
            if(checkData(init.login_sub_parent_2_text2_backgroundColor))     backgroundColor = init.login_sub_parent_2_text2_backgroundColor
        }
    }

    fun setSubParrent2_text1_LOGIN() {
        init.login_Type_A_sub_parent_2_text1.apply {
            layoutParams =  LinearLayout.LayoutParams((devWidth.toFloat() * init.login_sub_parent_2_text1_scaleX).toInt(), (devHeight.toFloat() * init.login_sub_parent_2_text1_scaleY).toInt() )
            translationX = (devWidth.toFloat() * init.login_sub_parent_2_text1_positionX)
            translationY = (devHeight.toFloat() * init.login_sub_parent_2_text1_positionY)
            textSize = init.login_sub_parent_2_text1_textSize
            typeface = init.login_sub_parent_2_text1_typeface
            textColor = init.login_sub_parent_2_text1_textColor
            text = init.login_sub_parent_2_text1_str
        }
    }

    fun setSubParrent2_infoGroupSpace_LOGIN() {
        init.login_Type_A_sub_parent_2_info_group_space.apply {
            if(checkData(init.login_sub_parent_2_info_group_background))         backgroundResource = init.login_sub_parent_2_info_group_background
            if(checkData(init.login_sub_parent_2_info_group_backgroundColor))    backgroundColor = init.login_sub_parent_2_info_group_backgroundColor
            layoutParams =  LinearLayout.LayoutParams((devWidth.toFloat() * init.login_sub_parent_2_info_group_space_scaleX).toInt(), (devHeight.toFloat() * init.login_sub_parent_2_info_group_space_scaleY).toInt() )
            translationX = (devWidth.toFloat() * init.login_sub_parent_2_info_group_space_positionX)
            translationY = (devHeight.toFloat() * init.login_sub_parent_2_info_group_space_positionY)
        }
    }

    fun setSubParrent2_infoGroup_LOGIN() {
        when(init.login_sub_parent_2_info_group_orientation)  {
            init.HORIZONTAL -> { init.login_Type_A_sub_parent_2_info_group.orientation = LinearLayout.HORIZONTAL; init.login_Type_A_sub_parent_2_info_group_space.visibility = View.VISIBLE }
            init.VERTICAL   -> { init.login_Type_A_sub_parent_2_info_group.orientation = LinearLayout.VERTICAL;   init.login_Type_A_sub_parent_2_info_group_space.visibility = View.GONE }
        }
    }

    fun setSubParrent2_infoGroup_contents_LOGIN() {

        init.login_Type_A_sub_parent_2_info_group_sign_up.apply {
            textSize = init.login_sub_parent_2_info_group_signUP_textSize
            textColor = init.login_sub_parent_2_info_group_signUP_textColor
            layoutParams =  LinearLayout.LayoutParams((devWidth.toFloat() * init.login_sub_parent_2_info_group_signUP_scaleX).toInt(), (devHeight.toFloat() * init.login_sub_parent_2_info_group_signUP_scaleY).toInt() )
            translationX = (devWidth.toFloat() * init.login_sub_parent_2_info_group_signUP_positionX)
            translationY = (devHeight.toFloat() * init.login_sub_parent_2_info_group_signUP_positionY)
            text = init.login_sub_parent_2_info_group_signUP_text

            // [start] 회원가입 버튼
            setOnTouchListener(con.getViewUtil()?.getAnimatingTouchlistener(ViewUtil.ANIMATION_SMALL_BUTTON__CLICK, ViewUtil.animatingTouchlistener {
                when(init.signup_view_type) {
                    init.TYPE_A -> {
                        // todo -> draw view
                        con.getViewUtil()?.addView("signup", init.getSignUp(con), ViewUtil.ANIMATION_FADE_IN, ViewUtil.ANIMATION_FADE_OUT, object : ViewUtil.addViewInitListener {
                            override fun setView(p0: View?) {
                                // todo -> setting data

                                var parent: LinearLayout = init.signup_contentView_Type_A

                                var sub_parent: LinearLayout = init.signup_contentView_Type_A_sub_parent

                                // Parent Layout Settings
                                parent.apply {
                                    init.apply {
                                        layoutParams = FrameLayout.LayoutParams(matchParent, matchParent)
                                            .apply {
                                                gravity = Gravity.CENTER_HORIZONTAL
                                                orientation = LinearLayout.VERTICAL
                                                isClickable = true
                                        }

                                        if(signup_background != DATA_NONE)         backgroundResource = signup_background
                                        if(signup_backgroundColor != DATA_NONE)    backgroundColor = signup_backgroundColor
                                    }

                                    // Title TextView Settings
                                    setSignUpParentTitle()
                                }

                                sub_parent.apply {
                                    init.apply {
                                        layoutParams = LinearLayout.LayoutParams(wrapContent, wrapContent)
                                            .apply {
                                                orientation = LinearLayout.VERTICAL
                                                gravity = Gravity.CENTER_HORIZONTAL
                                            }
                                    }

                                    init.apply {
                                        // 회원가입 항목 세팅
                                        setSignUpIdEmailItem(use = use_signup_id_email)                              // 아이디 or 이메일
                                        setSignUpPasswordItem(use = use_signup_password)                             // 비밀번호 입력
                                        setSignUpPasswordConfirmItem(use = use_signup_password_confirm)              // 비밀번호 입력 확인
                                        setSignUpNameItem(use = use_signup_name)                                     // 이름 입력
                                        setSignUpPhoneItem(use = use_signup_phone, useAuth = use_signup_phone_auth)  // 휴대폰 번호 입력 and 휴대폰 번호 인증
                                        setAgreementLayout(use = init.use_signup_agreement)                          // 약관
                                        setAgreementButton()                                                         // 회원가입 완료 버튼
                                    }
                                }
                            }

                            override fun onRemove() {
                                // todo -> 뷰가 없어지기 직전에 호출
                            }
                        })
                    }

                    init.CUSTOM -> {
                        onSignUpClick_LoginScene(util , viewModel , con)
                    }
                }
            }))
            // [end] 회원가입 버튼

        }

        init.login_Type_A_sub_parent_2_info_group_find_pw.apply {
            textSize = init.login_sub_parent_2_info_group_findPW_textSize
            textColor = init.login_sub_parent_2_info_group_findPW_textColor
            layoutParams =  LinearLayout.LayoutParams((devWidth.toFloat() * init.login_sub_parent_2_info_group_findPW_scaleX).toInt(), (devHeight.toFloat() * init.login_sub_parent_2_info_group_findPW_scaleY).toInt() )
            translationX = (devWidth.toFloat() * init.login_sub_parent_2_info_group_findPW_positionX)
            translationY = (devHeight.toFloat() * init.login_sub_parent_2_info_group_findPW_positionY)
            text = init.login_sub_parent_2_info_group_findPW_text
            setOnClickListener {
                onFindInfoClick_LoginScene(util , viewModel , con)
            }
        }

    }

    fun setSubParrent2_text2_LOGIN() {
        init.login_Type_A_sub_parent_2_text2.apply {
            text = init.login_sub_parent_2_signIn_group_text_str
            textSize = init.login_sub_parent_2_signIn_group_text_textSize
            textColor = init.login_sub_parent_2_signIn_group_text_textColor
            layoutParams =  LinearLayout.LayoutParams((devWidth.toFloat() * init.login_sub_parent_2_signIn_group_text_scaleX).toInt(), (devHeight.toFloat() * init.login_sub_parent_2_signIn_group_text_scaleY).toInt() )
            translationX = (devWidth.toFloat() * init.login_sub_parent_2_signIn_group_text_positionX)
            translationY = (devHeight.toFloat() * init.login_sub_parent_2_signIn_group_text_positionY)
            if(checkData(init.login_sub_parent_2_signIn_group_text_background))          backgroundResource = init.login_sub_parent_2_signIn_group_text_background
            if(checkData(init.login_sub_parent_2_signIn_group_text_backgroundColor))     backgroundColor = init.login_sub_parent_2_signIn_group_text_backgroundColor
        }
    }

    fun setAnimation_LOGIN()  {
        when(init.login_anmation_type)  {
            init.TYPE_A ->  playAnimation_Login_Type_A(init.login_Type_A_sub_parent_1_image)
            init.CUSTOM ->  onPlayCustomAnimation_Login_TypeA(init.login_Type_A_parrent_text
                , init.login_Type_A_sub_parent_1_text1
                , init.login_Type_A_sub_parent_1_text2
                , init.login_Type_A_parrent_image
                , init.login_Type_A_sub_parent_1_image
                , init.login_Type_A_sub_parent_2_text1
                , init.login_Type_A_sub_parent_2_signIn_group_nomal_login)
        }
    }

    fun playAnimation_Login_Type_A(v : View)  {

        BasicUtil.DelayExecute(BasicUtil.DelayExecuteLitener {

            var ani_A : ValueAnimator  = ObjectAnimator.ofFloat( v,  "TranslationY", v.translationY, -v.translationY )
            ani_A.duration = 500
            ani_A.interpolator = AccelerateInterpolator()
            ani_A.addListener(onEnd = {

                v.rotationX = 0.0f
                BasicUtil.DelayExecute(BasicUtil.DelayExecuteLitener {

                    var width  = BasicUtil.getDeviceSize(con)[0]
                    var height = BasicUtil.getDeviceSize(con)[1]

                    val random = (-(width*0.05f).toInt()..(width*0.5f).toInt()).random()
                    v.translationX = random.toFloat()

                    val random1 = (7..14).random()
                    v.scaleX = random1.toFloat()*0.1f
                    v.scaleY = random1.toFloat()*0.1f

                    var ani_C : ValueAnimator  = ObjectAnimator.ofFloat( v,  "TranslationY", v.translationY, -v.translationY )
                    ani_C.duration = 1800
                    ani_C.interpolator = DecelerateInterpolator()
                    ani_C.addListener(onEnd = {

                        var ani_D : ValueAnimator  = ObjectAnimator.ofFloat( v,  "RotationX", 0.0f , -30.0f )
                        ani_D.duration = 300
                        ani_D.interpolator = DecelerateInterpolator()
                        ani_D.repeatCount = 1
                        ani_D.repeatMode = REVERSE
                        ani_D.start()

                        BasicUtil.DelayExecute(BasicUtil.DelayExecuteLitener {  playAnimation_Login_Type_A(v) }, 2000)
                    })
                    ani_C.start()

                    var set = AnimatorSet()
                    var ani_A : ValueAnimator  = ObjectAnimator.ofFloat( v,  "ScaleX", v.scaleX, v.scaleX-0.1f)
                    ani_A.duration = 300
                    ani_A.interpolator = LinearInterpolator()
                    ani_A.repeatMode = REVERSE
                    ani_A.startDelay = 450
                    ani_A.repeatCount= 1

                    var ani_B : ValueAnimator  = ObjectAnimator.ofFloat( v,  "ScaleY", v.scaleY, v.scaleY-0.2f )
                    ani_B.duration = 300
                    ani_B.interpolator = LinearInterpolator()
                    ani_B.repeatMode = REVERSE
                    ani_B.startDelay = 550
                    ani_B.repeatCount= 1

                    set.play(ani_A).with(ani_B)
                    set.start()

                },1000)

            })
            ani_A.start()

        }, 1000)

    }

    abstract  fun  onLoginClick_LoginScene (input_ID : String , input_PW : String , rUtil : ViewUtil?, rViewModel : ViewModel, rCon : initActivity) : Boolean

    abstract  fun  onSignUpClick_LoginScene(rUtil : ViewUtil?, rViewModel : ViewModel, rCon : initActivity)

    abstract  fun  onFindInfoClick_LoginScene(rUtil : ViewUtil?, rViewModel : ViewModel, rCon : initActivity)

    abstract  fun  onPlayCustomAnimation_Splash_TypeA(v : View, v1 : View )

    abstract  fun  onPlayCustomAnimation_Login_TypeA(v : View, v1 : View, v2 : View, v3 : View, v4 : View, v5 : View, v6 : View )

    abstract  fun  onDraw_CUSTUM_VIEW_SPLASH(rUtil : ViewUtil?, rViewModel : ViewModel, rCon : initActivity, rinit : init_data): View?

    abstract  fun  onDraw_CUSTUM_VIEW_LOGIN(rUtil : ViewUtil?, rViewModel : ViewModel, rCon : initActivity, rinit : init_data): View?

    abstract  fun  onDraw_CUSTUM_VIEW_MAIN(rUtil : ViewUtil?, rViewModel : ViewModel, rCon : initActivity, rinit : init_data): View?

    abstract  fun  onSignUpPhoneAuthButtonClick(rCon : initActivity)

    abstract  fun  onSignUpShowAgreementItem1ButtonClick(rCon : initActivity)

    abstract  fun  onSignUpShowAgreementItem2ButtonClick(rCon : initActivity)

    abstract  fun  onSignUpAgreementButtonClick(rCon : initActivity)

    /** [로그인화면_END]---------------------------------------------------------------------------------------------------------------------------------------------------**/


    /** [회원가입_START]---------------------------------------------------------------------------------------------------------------------------------------------------**/
    
    /**
     * 회원 가입 Main 최상단 제목
     */
    private fun setSignUpParentTitle() {
        var width  = BasicUtil.getDeviceSize(con)[0]
        var height = BasicUtil.getDeviceSize(con)[1]

        var titleView_ex: TextView = init.signup_contentView_Type_A_titleView_ex

        titleView_ex.apply {
            init.apply {
                layoutParams = LinearLayout.LayoutParams((width.toFloat() * signup_title_text_scaleX).toInt(), (height.toFloat() * signup_title_text_scaleY).toInt())

                text = signup_title_text_value
                textColor = signup_title_text_color
                textSize = signup_title_text_size
                gravity = Gravity.CENTER
                translationX = (width.toFloat() * signup_title_text_positionX)
                translationY = (height.toFloat() * signup_title_text_positionY)
            }
        }
    }

    /**
     * 아이디 및 이메일 입력
     * @param use 사용 true or false
     */
    private fun setSignUpIdEmailItem(use: Boolean) {
        var width  = BasicUtil.getDeviceSize(con)[0]
        var height = BasicUtil.getDeviceSize(con)[1]

        var sub_parent_input_id_email: RelativeLayout = init.signup_contentView_Type_A_sub_parent_input_id_email
        var sub_parent_input_imageView_id_email: ImageView = init.signup_contentView_Type_A_sub_parent_input_imageView_id_email
        var sub_parent_input_editTextView_id_email: EditText = init.signup_contentView_Type_A_sub_parent_input_editTextView_id_email
        var sub_parent_input_view_id_email: View = init.signup_contentView_Type_A_sub__parent_input_view_id_email

        if(!use) sub_parent_input_id_email.visibility = View.GONE

        // Sub Parent Input Layout Settings
        sub_parent_input_id_email.apply {
            init.apply {
                layoutParams = LinearLayout.LayoutParams((width.toFloat() * signup_sub_parent_input_scaleX).toInt(), (height.toFloat() * signup_sub_parent_input_scaleY).toInt())
                    .apply {
                        gravity = Gravity.CENTER_HORIZONTAL
                    }
            }

            sub_parent_input_imageView_id_email.apply {
                init.apply {
                    layoutParams = RelativeLayout.LayoutParams((width.toFloat() * signup_sub_parent_input_imageView_scaleX).toInt(), (height.toFloat() * signup_sub_parent_input_imageView_scaleY).toInt())
                        .apply {
                            addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)

                            setImageResource(icon_idEmail_max_length)
                        }
                }
            }

            sub_parent_input_editTextView_id_email.apply {
                init.apply {
                    layoutParams = RelativeLayout.LayoutParams((width.toFloat() * signup_sub_parent_input_editTextView_scaleX).toInt(), (height.toFloat() * signup_sub_parent_input_editTextView_scaleY).toInt())
                        .apply {
                            background = ContextCompat.getDrawable(con, android.R.color.transparent)

                            hint = editText_idEmail_hint_value
                            setHintTextColor(editText_hint_color)

                            setTextColor(editText_text_color)

                            maxLines = 1
                            singleLine = true
                            filters = arrayOf(InputFilter.LengthFilter(editText_idEmail_max_length))

                            addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)

                            addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE)
                            addRule(RelativeLayout.RIGHT_OF, sub_parent_input_imageView_id_email.id)

                            setMargins((width.toFloat() * signup_sub_parent_input_editTextView_marginLeft).toInt(),0,0, 0)
                        }
                }
            }

            if(!init.use_under_bar) sub_parent_input_view_id_email.visibility = View.GONE

            sub_parent_input_view_id_email.apply {
                init.apply {
                    layoutParams = RelativeLayout.LayoutParams((width.toFloat() * signup_sub_parent_input_scaleX).toInt(), (height.toFloat() * under_bar_height).toInt())
                        .apply {
                            backgroundColor = under_bar_color

                            addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)

                            setMargins(0,0,0,(height.toFloat() * signup_sub_parent_input_view_marginBottom).toInt())
                        }
                }
            }
        }
    }

    /**
     * "비밀번호" 입력
     * @param use 사용 true or false
     */
    private fun setSignUpPasswordItem(use: Boolean) {
        var width  = BasicUtil.getDeviceSize(con)[0]
        var height = BasicUtil.getDeviceSize(con)[1]

        var sub_parent_input_password: RelativeLayout = init.signup_contentView_Type_A_sub_parent_input_password
        var sub_parent_input_imageView_password: ImageView = init.signup_contentView_Type_A_sub_parent_input_imageView_password
        var sub_parent_input_editTextView_password: EditText = init.signup_contentView_Type_A_sub_parent_input_editTextView_password
        var sub_parent_input_view_password: View = init.signup_contentView_Type_A_sub__parent_input_view_password

        if(!use) sub_parent_input_password.visibility = View.GONE

        // Sub Parent Input Layout Settings
        sub_parent_input_password.apply {
            init.apply {
                layoutParams = LinearLayout.LayoutParams((width.toFloat() * signup_sub_parent_input_scaleX).toInt(), (height.toFloat() * signup_sub_parent_input_scaleY).toInt())
                    .apply {
                        gravity = Gravity.CENTER_HORIZONTAL
                    }
            }

            sub_parent_input_imageView_password.apply {
                init.apply {
                    layoutParams = RelativeLayout.LayoutParams((width.toFloat() * signup_sub_parent_input_imageView_scaleX).toInt(), (height.toFloat() * signup_sub_parent_input_imageView_scaleY).toInt())
                        .apply {
                            addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)

                            setImageResource(icon_idEmail_max_length)
                        }
                }
            }

            sub_parent_input_editTextView_password.apply {
                init.apply {
                    layoutParams = RelativeLayout.LayoutParams((width.toFloat() * signup_sub_parent_input_editTextView_scaleX).toInt(), (height.toFloat() * signup_sub_parent_input_editTextView_scaleY).toInt())
                        .apply {
                            background = ContextCompat.getDrawable(con, android.R.color.transparent)

                            hint = editText_password_hint_value
                            setHintTextColor(editText_hint_color)

                            setTextColor(editText_text_color)

                            maxLines = 1
                            singleLine = true
                            filters = arrayOf(InputFilter.LengthFilter(editText_password_max_length))

                            addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)

                            addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE)
                            addRule(RelativeLayout.RIGHT_OF, sub_parent_input_imageView_password.id)

                            setMargins((width.toFloat() * signup_sub_parent_input_editTextView_marginLeft).toInt(),0,0, 0)
                        }
                }
            }

            if(!init.use_under_bar) sub_parent_input_view_password.visibility = View.GONE

            sub_parent_input_view_password.apply {
                init.apply {
                    layoutParams = RelativeLayout.LayoutParams((width.toFloat() * signup_sub_parent_input_scaleX).toInt(), (height.toFloat() * under_bar_height).toInt())
                        .apply {
                            backgroundColor = under_bar_color

                            addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)

                            setMargins(0,0,0,(height.toFloat() * signup_sub_parent_input_view_marginBottom).toInt())
                        }
                }
            }
        }
    }

    /**
     * "비밀번호" 입력 확인
     * @param use true or false
     */
    private fun setSignUpPasswordConfirmItem(use: Boolean) {
        var width  = BasicUtil.getDeviceSize(con)[0]
        var height = BasicUtil.getDeviceSize(con)[1]

        var sub_parent_input_password_confirm: RelativeLayout = init.signup_contentView_Type_A_sub_parent_input_password_confirm
        var sub_parent_input_imageView_password_confirm: ImageView = init.signup_contentView_Type_A_sub_parent_input_imageView_password_confirm
        var sub_parent_input_editTextView_password_confirm: EditText = init.signup_contentView_Type_A_sub_parent_input_editTextView_password_confirm
        var sub_parent_input_view_password_confirm: View = init.signup_contentView_Type_A_sub__parent_input_view_password_confirm

        if(!use) sub_parent_input_password_confirm.visibility = View.GONE

        // Sub Parent Input Layout Settings
        sub_parent_input_password_confirm.apply {
            init.apply {
                layoutParams = LinearLayout.LayoutParams((width.toFloat() * signup_sub_parent_input_scaleX).toInt(), (height.toFloat() * signup_sub_parent_input_scaleY).toInt())
                    .apply {
                        gravity = Gravity.CENTER_HORIZONTAL
                    }
            }

            sub_parent_input_imageView_password_confirm.apply {
                init.apply {
                    layoutParams = RelativeLayout.LayoutParams((width.toFloat() * signup_sub_parent_input_imageView_scaleX).toInt(), (height.toFloat() * signup_sub_parent_input_imageView_scaleY).toInt())
                        .apply {
                            addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)

                            setImageResource(icon_password_max_length)
                        }
                }
            }

            sub_parent_input_editTextView_password_confirm.apply {
                init.apply {
                    layoutParams = RelativeLayout.LayoutParams((width.toFloat() * signup_sub_parent_input_editTextView_scaleX).toInt(), (height.toFloat() * signup_sub_parent_input_editTextView_scaleY).toInt())
                        .apply {
                            background = ContextCompat.getDrawable(con, android.R.color.transparent)

                            hint = editText_password_confirm_hint_value
                            setHintTextColor(editText_hint_color)

                            setTextColor(editText_text_color)

                            maxLines = 1
                            singleLine = true
                            filters = arrayOf(InputFilter.LengthFilter(editText_password_max_length))

                            addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)

                            addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE)
                            addRule(RelativeLayout.RIGHT_OF, sub_parent_input_imageView_password_confirm.id)

                            setMargins((width.toFloat() * signup_sub_parent_input_editTextView_marginLeft).toInt(),0,0, 0)
                        }
                }
            }

            if(!init.use_under_bar) sub_parent_input_view_password_confirm.visibility = View.GONE

            sub_parent_input_view_password_confirm.apply {
                init.apply {
                    layoutParams = RelativeLayout.LayoutParams((width.toFloat() * signup_sub_parent_input_scaleX).toInt(), (height.toFloat() * under_bar_height).toInt())
                        .apply {
                            backgroundColor = under_bar_color

                            addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)

                            setMargins(0,0,0,(height.toFloat() * signup_sub_parent_input_view_marginBottom).toInt())
                        }
                }
            }
        }
    }


    /**
     * 이름 입력
     * @param use 사용 true or false
     */
    private fun setSignUpNameItem(use: Boolean) {

        var width  = BasicUtil.getDeviceSize(con)[0]
        var height = BasicUtil.getDeviceSize(con)[1]

        var sub_parent_input_name: RelativeLayout = init.signup_contentView_Type_A_sub_parent_input_name
        var sub_parent_input_imageView_name: ImageView = init.signup_contentView_Type_A_sub_parent_input_imageView_name
        var sub_parent_input_editTextView_name: EditText = init.signup_contentView_Type_A_sub_parent_input_editTextView_name
        var sub_parent_input_view_name: View = init.signup_contentView_Type_A_sub__parent_input_view_name

        if(!use) sub_parent_input_name.visibility = View.GONE

        // Sub Parent Input Layout Settings
        sub_parent_input_name.apply {
            init.apply {
                layoutParams = LinearLayout.LayoutParams((width.toFloat() * signup_sub_parent_input_scaleX).toInt(), (height.toFloat() * signup_sub_parent_input_scaleY).toInt())
                    .apply {
                        gravity = Gravity.CENTER_HORIZONTAL
                    }
            }

            sub_parent_input_imageView_name.apply {
                init.apply {
                    layoutParams = RelativeLayout.LayoutParams((width.toFloat() * signup_sub_parent_input_imageView_scaleX).toInt(), (height.toFloat() * signup_sub_parent_input_imageView_scaleY).toInt())
                        .apply {
                            addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)

                            setImageResource(icon_name_max_length)
                        }
                }
            }

            sub_parent_input_editTextView_name.apply {
                init.apply {
                    layoutParams = RelativeLayout.LayoutParams((width.toFloat() * signup_sub_parent_input_editTextView_scaleX).toInt(), (height.toFloat() * signup_sub_parent_input_editTextView_scaleY).toInt())
                        .apply {
                            background = ContextCompat.getDrawable(con, android.R.color.transparent)

                            hint = editText_name_hint_value
                            setHintTextColor(editText_hint_color)

                            setTextColor(editText_text_color)

                            maxLines = 1
                            singleLine = true
                            filters = arrayOf(InputFilter.LengthFilter(editText_name_max_length))

                            addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)

                            addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE)
                            addRule(RelativeLayout.RIGHT_OF, sub_parent_input_imageView_name.id)

                            setMargins((width.toFloat() * signup_sub_parent_input_editTextView_marginLeft).toInt(),0,0, 0)
                        }
                }
            }

            if(!init.use_under_bar) sub_parent_input_view_name.visibility = View.GONE

            sub_parent_input_view_name.apply {
                init.apply {
                    layoutParams = RelativeLayout.LayoutParams((width.toFloat() * signup_sub_parent_input_scaleX).toInt(), (height.toFloat() * under_bar_height).toInt())
                        .apply {
                            backgroundColor = under_bar_color

                            addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)

                            setMargins(0,0,0,(height.toFloat() * signup_sub_parent_input_view_marginBottom).toInt())
                        }
                }
            }
        }
    }

    /**
     * "휴대폰" 번호 입력
     * @param use 사용 true or false
     * @param useAuth 휴대폰 인증 기능 사용 true or false
     */
    private fun setSignUpPhoneItem(use: Boolean, useAuth: Boolean) {
        var width  = BasicUtil.getDeviceSize(con)[0]
        var height = BasicUtil.getDeviceSize(con)[1]

        var sub_parent_input_phone: RelativeLayout = init.signup_contentView_Type_A_sub_parent_input_phone
        var sub_parent_input_imageView_phone: ImageView = init.signup_contentView_Type_A_sub_parent_input_imageView_phone
        var sub_parent_input_editTextView_phone: EditText = init.signup_contentView_Type_A_sub_parent_input_editTextView_phone
        var sub_parent_input_view_phone: View = init.signup_contentView_Type_A_sub__parent_input_view_phone
        var sub_parent_input_button_phone: Button = init.signup_contentView_Type_A_sub__parent_input_button_phone

        if(!use) sub_parent_input_phone.visibility = View.GONE

        // Sub Parent Input Layout Settings
        sub_parent_input_phone.apply {
            init.apply {
                layoutParams = LinearLayout.LayoutParams((width.toFloat() * signup_sub_parent_input_scaleX).toInt(), (height.toFloat() * signup_sub_parent_input_scaleY).toInt())
                    .apply {
                        gravity = Gravity.CENTER_HORIZONTAL
                    }
            }

            sub_parent_input_imageView_phone.apply {
                init.apply {
                    layoutParams = RelativeLayout.LayoutParams((width.toFloat() * signup_sub_parent_input_imageView_scaleX).toInt(), (height.toFloat() * signup_sub_parent_input_imageView_scaleY).toInt())
                        .apply {
                            addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)

                            setImageResource(icon_phone_max_length)
                        }
                }
            }

            sub_parent_input_editTextView_phone.apply {
                init.apply {
                    layoutParams = RelativeLayout.LayoutParams((width.toFloat() * signup_sub_parent_input_editTextView_scaleX).toInt(), (height.toFloat() * signup_sub_parent_input_editTextView_scaleY).toInt())
                        .apply {
                            background = ContextCompat.getDrawable(con, android.R.color.transparent)

                            hint = editText_phone_hint_value
                            setHintTextColor(editText_hint_color)

                            setTextColor(editText_text_color)

                            maxLines = 1
                            singleLine = true
                            filters = arrayOf(InputFilter.LengthFilter(editText_phone_max_length))

                            addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)

//                            addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE)
                            addRule(RelativeLayout.RIGHT_OF, sub_parent_input_imageView_phone.id)

                            setMargins((width.toFloat() * signup_sub_parent_input_editTextView_marginLeft).toInt(),0,0, 0)
                        }
                }
            }

            var view_scaleX = (width.toFloat() * init.signup_sub_parent_input_scaleX).toInt()

            if(useAuth) {
                view_scaleX -= (width.toFloat() * init.passwordAuth_button_scaleX).toInt()

                // show phone auth item
                setSignUpPhoneAuthItem()
            }
            else {
                sub_parent_input_button_phone.visibility = View.GONE
                init.signup_contentView_Type_A_sub_parent_input_phone_auth.visibility = View.GONE
            }

            sub_parent_input_button_phone.apply {
                init.apply {
                    layoutParams = RelativeLayout.LayoutParams((width.toFloat() * passwordAuth_button_scaleX).toInt(), (height.toFloat() * passwordAuth_button_scaleY).toInt())
                        .apply {
                            addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE)
                            addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)
                            setMargins(0,0,0,(height.toFloat() * signup_sub_parent_input_view_marginBottom).toInt())

                            if(passwordAuth_button_background_resource != DATA_NONE) backgroundResource = passwordAuth_button_background_resource
                            if(passwordAuth_button_background_color != DATA_NONE) backgroundColor = passwordAuth_button_background_color

                            text = passwordAuth_button_text_value
                            textSize = passwordAuth_button_text_size
                            textColor = passwordAuth_button_text_color
                        }

                    setOnClickListener {
                        onSignUpPhoneAuthButtonClick(con)
                    }
                }
            }

            if(!init.use_under_bar) sub_parent_input_view_phone.visibility = View.GONE

            sub_parent_input_view_phone.apply {
                init.apply {
                    layoutParams = RelativeLayout.LayoutParams(view_scaleX, (height.toFloat() * under_bar_height).toInt())
                        .apply {
                            backgroundColor = under_bar_color

                            addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)

                            setMargins(0,0,0,(height.toFloat() * signup_sub_parent_input_view_marginBottom).toInt())
                        }
                }
            }
        }
    }

    /**
     * "휴대폰" 인증 기능 이용시 사용될 [인증번호 입력]
     */
    private fun setSignUpPhoneAuthItem() {

        var width  = BasicUtil.getDeviceSize(con)[0]
        var height = BasicUtil.getDeviceSize(con)[1]

        var sub_parent_input_phone_auth: RelativeLayout = init.signup_contentView_Type_A_sub_parent_input_phone_auth
        var sub_parent_input_imageView_phone_auth: ImageView = init.signup_contentView_Type_A_sub_parent_input_imageView_phone_auth
        var sub_parent_input_editTextView_phone_auth: EditText = init.signup_contentView_Type_A_sub_parent_input_editTextView_phone_auth
        var sub_parent_input_view_phone_auth: View = init.signup_contentView_Type_A_sub__parent_input_view_phone_auth

        // Sub Parent Input Layout Settings
        sub_parent_input_phone_auth.apply {
            init.apply {
                //                                    backgroundColor = Color.RED    // 임시

                layoutParams = LinearLayout.LayoutParams((width.toFloat() * signup_sub_parent_input_scaleX).toInt(), (height.toFloat() * signup_sub_parent_input_scaleY).toInt())
                    .apply {
                        gravity = Gravity.CENTER_HORIZONTAL
                    }
            }

            sub_parent_input_imageView_phone_auth.apply {
                init.apply {
                    layoutParams = RelativeLayout.LayoutParams((width.toFloat() * signup_sub_parent_input_imageView_scaleX).toInt(), (height.toFloat() * signup_sub_parent_input_imageView_scaleY).toInt())
                        .apply {
                            addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)

                            setImageResource(icon_phoneAuth_max_length)
                        }
                }
            }

            sub_parent_input_editTextView_phone_auth.apply {
                init.apply {
                    layoutParams = RelativeLayout.LayoutParams((width.toFloat() * signup_sub_parent_input_editTextView_scaleX).toInt(), (height.toFloat() * signup_sub_parent_input_editTextView_scaleY).toInt())
                        .apply {
                            background = ContextCompat.getDrawable(con, android.R.color.transparent)

                            hint = editText_phoneAuth_hint_value
                            setHintTextColor(editText_hint_color)

                            setTextColor(editText_text_color)

                            maxLines = 1
                            singleLine = true
                            filters = arrayOf(InputFilter.LengthFilter(editText_phoneAuth_max_length))

                            addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)

                            addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE)
                            addRule(RelativeLayout.RIGHT_OF, sub_parent_input_imageView_phone_auth.id)

                            setMargins((width.toFloat() * signup_sub_parent_input_editTextView_marginLeft).toInt(),0,0, 0)
                        }
                }
            }

            if(!init.use_under_bar) sub_parent_input_view_phone_auth.visibility = View.GONE

            sub_parent_input_view_phone_auth.apply {
                init.apply {
                    layoutParams = RelativeLayout.LayoutParams((width.toFloat() * signup_sub_parent_input_scaleX).toInt(), (height.toFloat() * under_bar_height).toInt())
                        .apply {
                            backgroundColor = under_bar_color

                            addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)

                            setMargins(0,0,0,(height.toFloat() * signup_sub_parent_input_view_marginBottom).toInt())
                        }
                }
            }
        }
    }

    /**
     * "약관" 이용시 사용될 Main Layout
     * @param use 사용 true or false
     */
    private fun setAgreementLayout(use: Boolean) {
        var width  = BasicUtil.getDeviceSize(con)[0]
        var height = BasicUtil.getDeviceSize(con)[1]

        // main layout
        var sub_parent_agreement: LinearLayout = init.signup_contentView_Type_A_sub_parent_agreement

        if(!use) sub_parent_agreement.visibility = View.GONE

        sub_parent_agreement.apply {
            init.apply {
                layoutParams = LinearLayout.LayoutParams((width.toFloat() * 0.9f).toInt(), wrapContent)
                    .apply {
                        gravity = Gravity.CENTER_HORIZONTAL
                    }
//                backgroundColor = Color.RED // 임시
            }

            setAgreementAll(width, height)
            setAgreemnetItem1(width, height)
            setAgreemnetItem2(width, height)
        }
    }

    /**
     * "약관" 이용시 사용될 [전체 동의]
     */
    private fun setAgreementAll(width: Int, height: Int) {
        // agree all layout
        var sub_parent_agreement_all: LinearLayout = init.signup_contentView_Type_A_sub_parent_agreement_all
        var sub_parent_agreement_all_checkBox: CheckBox = init.signup_contentView_Type_A_sub_parent_agreement_all_checkBox
        var sub_parent_agreement_all_textView: TextView = init.signup_contentView_Type_A_sub_parent_agreement_all_textView

        var isCheckedAll: Boolean = false

        sub_parent_agreement_all.apply {
            init.apply {
                layoutParams = LinearLayout.LayoutParams(wrapContent, (height.toFloat() * 0.08f).toInt())
                    .apply {
                        gravity = Gravity.CENTER_VERTICAL
                    }
                orientation = LinearLayout.HORIZONTAL
            }

            sub_parent_agreement_all_checkBox.apply {
                init.apply {
                    if(agreement_checkBox_background_resource != DATA_NONE) {
                        backgroundResource = agreement_checkBox_background_resource
                        layoutParams = LinearLayout.LayoutParams((width.toFloat() * agreement_checkBox_scaleX).toInt(), (height.toFloat() * agreement_checkBox_scaleY).toInt())
                    }

                    layoutParams = LinearLayout.LayoutParams(wrapContent, wrapContent)
                    buttonTintList = ColorStateList.valueOf(agreement_checkBox_all_borderColor)
                }

                onCheckedChange { buttonView, isChecked ->
                    isCheckedAll = isChecked

//                    if(isChecked) {
//                        init.signup_contentView_Type_A_sub_parent_agreement_item1_sub_checkBox.isChecked = true
//                        init.signup_contentView_Type_A_sub_parent_agreement_item2_sub_checkBox.isChecked = true
//                    }
//                    else {
//                        init.signup_contentView_Type_A_sub_parent_agreement_item1_sub_checkBox.isChecked = false
//                        init.signup_contentView_Type_A_sub_parent_agreement_item2_sub_checkBox.isChecked = false
//                    }
                }

                isClickable = false
                isFocusable = false
            }

            sub_parent_agreement_all_textView.apply {
                init.apply {
                    layoutParams = LinearLayout.LayoutParams(matchParent, matchParent)
                    gravity = Gravity.CENTER_VERTICAL
                    text = agreement_checkBox_all_text_value
                    textColor = agreement_checkBox_all_text_color
                    textSize = agreement_checkBox_all_text_size
                }
            }

            setOnClickListener {
                sub_parent_agreement_all_checkBox.isChecked = !isCheckedAll

                if(isCheckedAll) {
                    init.signup_contentView_Type_A_sub_parent_agreement_item1_sub_checkBox.isChecked = false
                    init.signup_contentView_Type_A_sub_parent_agreement_item2_sub_checkBox.isChecked = false
                }
                else {
                    init.signup_contentView_Type_A_sub_parent_agreement_item1_sub_checkBox.isChecked = true
                    init.signup_contentView_Type_A_sub_parent_agreement_item2_sub_checkBox.isChecked = true
                }
            }
        }
    }

    /**
     * "약관" 이용시 사용될 첫 번째 항목
     */
    private fun setAgreemnetItem1(width: Int, height: Int) {
        var sub_parent_agreement_item1: LinearLayout = init.signup_contentView_Type_A_sub_parent_agreement_item1
        var sub_parent_agreement_item1_textView: TextView = init.signup_contentView_Type_A_sub_parent_agreement_item1_textView
        var sub_parent_agreement_item1_sub: LinearLayout = init.signup_contentView_Type_A_sub_parent_agreement_item1_sub
        var sub_parent_agreement_item1_sub_checkBox: CheckBox = init.signup_contentView_Type_A_sub_parent_agreement_item1_sub_checkBox
        var sub_parent_agreement_item1_sub_textView: TextView = init.signup_contentView_Type_A_sub_parent_agreement_item1_sub_textView

        var isCheckedItem1: Boolean = false

        sub_parent_agreement_item1.apply {
            init.apply {
                layoutParams = LinearLayout.LayoutParams(matchParent, (height.toFloat() * 0.08f).toInt())
                    .apply {
                        gravity = Gravity.CENTER_VERTICAL
                    }
                orientation = LinearLayout.HORIZONTAL
            }

            sub_parent_agreement_item1_sub.apply {
                init.apply {
                    layoutParams = LinearLayout.LayoutParams((width.toFloat() * 0.7f).toInt(), (height.toFloat() * 0.08f).toInt())
                        .apply {
                            gravity = Gravity.CENTER_VERTICAL
                        }
                    orientation = LinearLayout.HORIZONTAL

//                    backgroundColor = Color.BLUE // 임시
                }

                sub_parent_agreement_item1_sub_checkBox.apply {
                    init.apply {
                        if(agreement_checkBox_background_resource != DATA_NONE) {
                            backgroundResource = agreement_checkBox_background_resource
                            layoutParams = LinearLayout.LayoutParams((width.toFloat() * agreement_checkBox_scaleX).toInt(), (height.toFloat() * agreement_checkBox_scaleY).toInt())
                        }

                        layoutParams = LinearLayout.LayoutParams(wrapContent, wrapContent)
                        buttonTintList = ColorStateList.valueOf(agreement_checkBox_item1_borderColor)
                    }

                    onCheckedChange { buttonView, isChecked ->
                        isCheckedItem1 = isChecked
                    }

                    isClickable = false
                    isFocusable = false
                }

                sub_parent_agreement_item1_sub_textView.apply {
                    init.apply {
                        layoutParams = LinearLayout.LayoutParams(matchParent, matchParent)
                        gravity = Gravity.CENTER_VERTICAL
                        text = agreement_checkBox_item1_text_value
                        textColor = agreement_checkBox_item1_text_color
                        textSize = agreement_checkBox_item1_text_size
                    }
                }

                setOnClickListener {
                    sub_parent_agreement_item1_sub_checkBox.isChecked = !isCheckedItem1

                    init.signup_contentView_Type_A_sub_parent_agreement_all_checkBox.isChecked =
                        init.signup_contentView_Type_A_sub_parent_agreement_item1_sub_checkBox.isChecked &&
                            init.signup_contentView_Type_A_sub_parent_agreement_item2_sub_checkBox.isChecked
                }
            }

            sub_parent_agreement_item1_textView.apply {
                init.apply {
                    layoutParams = LinearLayout.LayoutParams(matchParent, matchParent)

//                    backgroundColor = Color.YELLOW //  임시

                    text = agreement_checkBox_item1_show_text_value
                    textColor = agreement_checkBox_item1_show_text_color
                    textSize = agreement_checkBox_item1_show_text_size

                    gravity = Gravity.CENTER
                }

                setOnClickListener {
                    onSignUpShowAgreementItem1ButtonClick(con)
                }
            }
        }
    }

    /**
     * "약관" 이용시 사용될 두 번째 항목
     */
    private fun setAgreemnetItem2(width: Int, height: Int) {
        var sub_parent_agreement_item2: LinearLayout = init.signup_contentView_Type_A_sub_parent_agreement_item2
        var sub_parent_agreement_item2_textView: TextView = init.signup_contentView_Type_A_sub_parent_agreement_item2_textView
        var sub_parent_agreement_item2_sub: LinearLayout = init.signup_contentView_Type_A_sub_parent_agreement_item2_sub
        var sub_parent_agreement_item2_sub_checkBox: CheckBox = init.signup_contentView_Type_A_sub_parent_agreement_item2_sub_checkBox
        var sub_parent_agreement_item2_sub_textView: TextView = init.signup_contentView_Type_A_sub_parent_agreement_item2_sub_textView

        var isCheckedItem2: Boolean = false

        sub_parent_agreement_item2.apply {
            init.apply {
                layoutParams = LinearLayout.LayoutParams(matchParent, (height.toFloat() * 0.08f).toInt())
                    .apply {
                        gravity = Gravity.CENTER_VERTICAL
                    }
                orientation = LinearLayout.HORIZONTAL
            }

            sub_parent_agreement_item2_sub.apply {
                init.apply {
                    layoutParams = LinearLayout.LayoutParams((width.toFloat() * 0.7f).toInt(), (height.toFloat() * 0.08f).toInt())
                        .apply {
                            gravity = Gravity.CENTER_VERTICAL
                        }
                    orientation = LinearLayout.HORIZONTAL

//                    backgroundColor = Color.GREEN // 임시
                }

                sub_parent_agreement_item2_sub_checkBox.apply {
                    init.apply {
                        if(agreement_checkBox_background_resource != DATA_NONE) {
                            backgroundResource = agreement_checkBox_background_resource
                            layoutParams = LinearLayout.LayoutParams((width.toFloat() * agreement_checkBox_scaleX).toInt(), (height.toFloat() * agreement_checkBox_scaleY).toInt())
                        }

                        layoutParams = LinearLayout.LayoutParams(wrapContent, wrapContent)
                        buttonTintList = ColorStateList.valueOf(agreement_checkBox_item2_borderColor)
                    }

                    onCheckedChange { buttonView, isChecked ->
                        isCheckedItem2 = isChecked
                    }

                    isClickable = false
                    isFocusable = false
                }

                sub_parent_agreement_item2_sub_textView.apply {
                    init.apply {
                        layoutParams = LinearLayout.LayoutParams(matchParent, matchParent)
                        gravity = Gravity.CENTER_VERTICAL
                        text = agreement_checkBox_item2_text_value
                        textColor = agreement_checkBox_item2_text_color
                        textSize = agreement_checkBox_item2_text_size
                    }
                }

                setOnClickListener {
                    sub_parent_agreement_item2_sub_checkBox.isChecked = !isCheckedItem2

                    init.signup_contentView_Type_A_sub_parent_agreement_all_checkBox.isChecked =
                        init.signup_contentView_Type_A_sub_parent_agreement_item1_sub_checkBox.isChecked &&
                                init.signup_contentView_Type_A_sub_parent_agreement_item2_sub_checkBox.isChecked
                }
            }

            sub_parent_agreement_item2_textView.apply {
                init.apply {
                    layoutParams = LinearLayout.LayoutParams(matchParent, matchParent)

//                    backgroundColor = Color.GRAY //  임시

                    text = agreement_checkBox_item2_show_text_value
                    textColor = agreement_checkBox_item2_show_text_color
                    textSize = agreement_checkBox_item2_show_text_size

                    gravity = Gravity.CENTER
                }

                setOnClickListener {
                    onSignUpShowAgreementItem2ButtonClick(con)
                }
            }
        }
    }

    /**
     * "회원가입 완료" 버튼
     */
    private fun setAgreementButton() {
        var width  = BasicUtil.getDeviceSize(con)[0]
        var height = BasicUtil.getDeviceSize(con)[1]

        var sub_parent_agreement_button_layout: LinearLayout = init.signup_contentView_Type_A_sub_parent_agreement_button_layout
        var sub_parent_agreement_button: Button = init.signup_contentView_Type_A_sub_parent_agreement_button

        sub_parent_agreement_button_layout.apply {
            init.apply {
                layoutParams = LinearLayout.LayoutParams((width.toFloat() * 0.9f).toInt(), matchParent)
                    .apply {
                        gravity = Gravity.CENTER_HORIZONTAL
                    }
//                backgroundColor = Color.RED // 임시

                gravity = Gravity.BOTTOM
            }

            sub_parent_agreement_button.apply {
                init.apply {
                    if(signup_complete_button_background_resource != DATA_NONE) layoutParams = LinearLayout.LayoutParams((width.toFloat() * signup_complete_button_scaleX).toInt(), (height.toFloat() * signup_complete_button_scaleY).toInt())
                    if(signup_complete_button_background_color != DATA_NONE) backgroundColor = signup_complete_button_background_color

                    layoutParams = LinearLayout.LayoutParams(matchParent, wrapContent)

                    text = signup_complete_button_text_value
                    textColor = signup_complete_button_text_color
                    textSize = signup_complete_button_text_size
                }

                setOnClickListener {
                    onSignUpAgreementButtonClick(con)
                }
            }
        }
    }

    /** [회원가입_END]---------------------------------------------------------------------------------------------------------------------------------------------------**/


    /** [메인화면_START]---------------------------------------------------------------------------------------------------------------------------------------------------**/

    fun setMain(init : init_data)  {
        if(init.main_view_type == init.CUSTOM)    return
    }

    /** [메인화면_END]---------------------------------------------------------------------------------------------------------------------------------------------------**/
}
