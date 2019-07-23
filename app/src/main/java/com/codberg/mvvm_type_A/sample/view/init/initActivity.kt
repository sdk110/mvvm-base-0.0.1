package com.codberg.mvvm_type_A.sample.view.init

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import com.codberg.mvvm_type_A.sample.view.MainViewManager

import android.os.Bundle
import android.text.InputFilter
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.animation.addListener
import androidx.core.content.ContextCompat
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.codberg.mvvm_type_A.R
import com.libs.cutil_kotlin.BaseKotlinActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.codberg.mvvm_type_A.sample.viewmodel.ViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.libs.cutil_kotlin.BasicUtil
import com.libs.cutil_kotlin.GeneralPerposeRecyclerViewAdapter
import com.libs.cutil_kotlin.ViewUtil
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onCheckedChange

open class initActivity : BaseKotlinActivity<ViewModel>() {
    lateinit var initViewManager : MainViewManager
    lateinit var init : init_view
    var isDraw : Boolean = false

    var devWidth: Int = 0
    var devHeight: Int = 0

    val mContext: Context = this
    lateinit var mMainPagerAdapter : GeneralPerposeRecyclerViewAdapter

    override val viewModel: ViewModel by viewModel()

    override fun onCustomBackPressed(): Int = 3
    override fun onBackground(): Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 초기 회원가입에 대한 observer settings (create ui)
        setSignupObserver()
    }

    override fun onRemoveVIew(key: String) {
        when(key) {
            "signup" -> {
                viewModel.compositeDisposable.clear()

                // 회원가입에 대한 observer settings
                setSignupObserver()
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        process()
    }

    fun setView() {

        when(init.layout_config)  {

            in (0..1) -> {
                if(init.splash_view_type == init.TYPE_A)    setContentView(init.getSplash_TYPE_A(baseContext))
                else                                        setContentView(initViewManager.customView_SPLASH)
            }

            2 -> {
                if( init.login_view_type       == init.TYPE_A )  setContentView(init.getLogin_TYPE_A(baseContext))
                else                                             setContentView(initViewManager.cutomView_LOGIN)
            }

            3 -> {
                if( init.main_view_type       == init.TYPE_A )   setContentView(init.getMain_TYPE_A(baseContext))
                else                                             setContentView(initViewManager.customView_MAIN)
            }

        }

    }

    fun process()  {

        if(isDraw) return
        isDraw = true

        init = init_view(viewModel)
        initViewManager = MainViewManager(getViewUtil(),viewModel,this, init)

        setView()

        when(init.layout_config)  {
            in (0..1) -> nextScene()
            2 -> initViewManager.setLogin(init)
            3 -> initViewManager.setMain(init)
        }

        devWidth  = BasicUtil.getDeviceSize(mContext)[0]
        devHeight = BasicUtil.getDeviceSize(mContext)[1]
    }

    fun nextScene()  {

        initViewManager.setSplash()
        BasicUtil.DelayExecute(BasicUtil.DelayExecuteLitener {
            setLoginScene()
        },init.splash_delay_time)

    }

    fun setLoginScene()  {

        playTransition {

            when(init.layout_config)  {

                0 ->  {
                    if( init.login_view_type       == init.TYPE_A )  setContentView(init.getLogin_TYPE_A(baseContext))
                    else                                             setContentView(initViewManager.cutomView_LOGIN)
                }

                in (1..2) ->  {
                    if( init.main_view_type == init.TYPE_A )   setContentView(init.getMain_TYPE_A(baseContext))
                    else                                      setContentView(initViewManager.customView_MAIN)
                }

            }

            when(init.layout_config)  {
                0 -> initViewManager.setLogin(init)
                in (1..2) -> initViewManager.setMain(init)
            }

        }

    }

    fun setMainScene()  {

        playTransition {

            if( init.main_view_type       == init.TYPE_A )   setContentView(init.getMain_TYPE_A(baseContext))
            else                                             setContentView(initViewManager.customView_MAIN)

            initViewManager.setMain(init)

        }

    }

    fun playTransition(rFunction : () -> Unit)  {

        when(init.layout_transition_type)  {

            init.TYPE_A ->  {

                var a : ValueAnimator = ObjectAnimator.ofFloat( contentView,  "alpha", 1.0f, 0.0f )
                a.setDuration(450)
                a.setInterpolator(FastOutLinearInInterpolator())
                a.addListener(onEnd = {

                    rFunction()
                    contentView!!.alpha =0.0f
                    var b : ValueAnimator = ObjectAnimator.ofFloat( contentView,  "alpha", 0.0f, 1.0f )
                    b.setDuration(450)
                    b.setInterpolator(FastOutLinearInInterpolator())
                    BasicUtil.DelayExecute(BasicUtil.DelayExecuteLitener { b.start() }, 0)

                })
                a.start()

            }

            init.DATA_NONE ->  {

                rFunction()

            }

        }

    }

    /**
     * 회원가입 화면에 대한 Observer settings
     */
    private fun setSignupObserver() {

        viewModel.observerSignUpSettings = object : Observer<Void> {
            var dispose: Disposable? = null

            override fun onComplete() {
                init.apply {
                    login_Type_A_sub_parent_2_info_group_sign_up.apply {
                        gravity = Gravity.CENTER
                        textSize = login_sub_parent_2_info_group_signUP_textSize
                        textColor = login_sub_parent_2_info_group_signUP_textColor
                        layoutParams = LinearLayout.LayoutParams(
                            (devWidth.toFloat() * login_sub_parent_2_info_group_signUP_scaleX).toInt(),
                            (devHeight.toFloat() * login_sub_parent_2_info_group_signUP_scaleY).toInt()
                        )
                        translationX = (devWidth.toFloat() * login_sub_parent_2_info_group_signUP_positionX)
                        translationY = (devHeight.toFloat() * login_sub_parent_2_info_group_signUP_positionY)
                        text = login_sub_parent_2_info_group_signUP_text

                        setOnTouchListener(getViewUtil()?.getAnimatingTouchlistener(ViewUtil.ANIMATION_SMALL_BUTTON__CLICK) {
                            when(init.signup_view_type) {
                                init.TYPE_A -> {
                                    getViewUtil()?.addView("signup", init.getSignUp(mContext), ViewUtil.ANIMATION_FADE_IN, ViewUtil.ANIMATION_FADE_OUT, object : ViewUtil.addViewInitListener {
                                        override fun onCreateView(p0: View?) {
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

                                        /** 회원 가입 Main 최상단 제목 */
                                        private fun setSignUpParentTitle() {
                                            init.signup_contentView_Type_A_titleView_ex.apply {
                                                init.apply {
                                                    layoutParams = LinearLayout.LayoutParams((devWidth.toFloat() * signup_title_text_scaleX).toInt(), (devHeight.toFloat() * signup_title_text_scaleY).toInt())

                                                    text = signup_title_text_value
                                                    textColor = signup_title_text_color
                                                    textSize = signup_title_text_size
                                                    gravity = Gravity.CENTER
                                                    translationX = (devWidth.toFloat() * signup_title_text_positionX)
                                                    translationY = (devHeight.toFloat() * signup_title_text_positionY)
                                                }
                                            }
                                        }

                                        /**
                                         * 아이디 및 이메일 입력
                                         * @param use 사용 true or false
                                         */
                                        private fun setSignUpIdEmailItem(use: Boolean) {
                                            var sub_parent_input_id_email: RelativeLayout = init.signup_contentView_Type_A_sub_parent_input_id_email
                                            var sub_parent_input_imageView_id_email: ImageView = init.signup_contentView_Type_A_sub_parent_input_imageView_id_email
                                            var sub_parent_input_editTextView_id_email: EditText = init.signup_contentView_Type_A_sub_parent_input_editTextView_id_email
                                            var sub_parent_input_view_id_email: View = init.signup_contentView_Type_A_sub__parent_input_view_id_email

                                            if(!use) sub_parent_input_id_email.visibility = View.GONE

                                            // Sub Parent Input Layout Settings
                                            sub_parent_input_id_email.apply {
                                                init.apply {
                                                    layoutParams = LinearLayout.LayoutParams((devWidth.toFloat() * signup_sub_parent_input_scaleX).toInt(), (devHeight.toFloat() * signup_sub_parent_input_scaleY).toInt())
                                                        .apply {
                                                            gravity = Gravity.CENTER_HORIZONTAL
                                                        }
                                                }

                                                sub_parent_input_imageView_id_email.apply {
                                                    init.apply {
                                                        layoutParams = RelativeLayout.LayoutParams((devWidth.toFloat() * signup_sub_parent_input_imageView_scaleX).toInt(), (devHeight.toFloat() * signup_sub_parent_input_imageView_scaleY).toInt())
                                                            .apply {
                                                                addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)

                                                                setImageResource(icon_idEmail_drawable_resource)
                                                            }
                                                    }
                                                }

                                                sub_parent_input_editTextView_id_email.apply {
                                                    init.apply {
                                                        layoutParams = RelativeLayout.LayoutParams((devWidth.toFloat() * signup_sub_parent_input_editTextView_scaleX).toInt(), (devHeight.toFloat() * signup_sub_parent_input_editTextView_scaleY).toInt())
                                                            .apply {
                                                                background = ContextCompat.getDrawable(mContext, android.R.color.transparent)

                                                                hint = editText_idEmail_hint_value
                                                                setHintTextColor(editText_hint_color)

                                                                setTextColor(editText_text_color)

                                                                maxLines = 1
                                                                singleLine = true
                                                                filters = arrayOf(InputFilter.LengthFilter(editText_idEmail_max_length))

                                                                addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)

                                                                addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE)
                                                                addRule(RelativeLayout.RIGHT_OF, sub_parent_input_imageView_id_email.id)

                                                                setMargins((devWidth.toFloat() * signup_sub_parent_input_editTextView_marginLeft).toInt(),0,0, 0)
                                                            }
                                                    }
                                                }

                                                if(!init.use_under_bar) sub_parent_input_view_id_email.visibility = View.GONE

                                                sub_parent_input_view_id_email.apply {
                                                    init.apply {
                                                        layoutParams = RelativeLayout.LayoutParams((devWidth.toFloat() * signup_sub_parent_input_scaleX).toInt(), (devHeight.toFloat() * under_bar_height).toInt())
                                                            .apply {
                                                                backgroundColor = under_bar_color

                                                                addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)

                                                                setMargins(0,0,0,(devHeight.toFloat() * signup_sub_parent_input_view_marginBottom).toInt())
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
                                            var sub_parent_input_password: RelativeLayout = init.signup_contentView_Type_A_sub_parent_input_password
                                            var sub_parent_input_imageView_password: ImageView = init.signup_contentView_Type_A_sub_parent_input_imageView_password
                                            var sub_parent_input_editTextView_password: EditText = init.signup_contentView_Type_A_sub_parent_input_editTextView_password
                                            var sub_parent_input_view_password: View = init.signup_contentView_Type_A_sub__parent_input_view_password

                                            if(!use) sub_parent_input_password.visibility = View.GONE

                                            // Sub Parent Input Layout Settings
                                            sub_parent_input_password.apply {
                                                init.apply {
                                                    layoutParams = LinearLayout.LayoutParams((devWidth.toFloat() * signup_sub_parent_input_scaleX).toInt(), (devHeight.toFloat() * signup_sub_parent_input_scaleY).toInt())
                                                        .apply {
                                                            gravity = Gravity.CENTER_HORIZONTAL
                                                        }
                                                }

                                                sub_parent_input_imageView_password.apply {
                                                    init.apply {
                                                        layoutParams = RelativeLayout.LayoutParams((devWidth.toFloat() * signup_sub_parent_input_imageView_scaleX).toInt(), (devHeight.toFloat() * signup_sub_parent_input_imageView_scaleY).toInt())
                                                            .apply {
                                                                addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)

                                                                setImageResource(icon_password_drawable_resource)
                                                            }
                                                    }
                                                }

                                                sub_parent_input_editTextView_password.apply {
                                                    init.apply {
                                                        layoutParams = RelativeLayout.LayoutParams((devWidth.toFloat() * signup_sub_parent_input_editTextView_scaleX).toInt(), (devHeight.toFloat() * signup_sub_parent_input_editTextView_scaleY).toInt())
                                                            .apply {
                                                                background = ContextCompat.getDrawable(mContext, android.R.color.transparent)

                                                                hint = editText_password_hint_value
                                                                setHintTextColor(editText_hint_color)

                                                                setTextColor(editText_text_color)

                                                                maxLines = 1
                                                                singleLine = true
                                                                filters = arrayOf(InputFilter.LengthFilter(editText_password_max_length))

                                                                addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)

                                                                addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE)
                                                                addRule(RelativeLayout.RIGHT_OF, sub_parent_input_imageView_password.id)

                                                                setMargins((devWidth.toFloat() * signup_sub_parent_input_editTextView_marginLeft).toInt(),0,0, 0)
                                                            }
                                                    }
                                                }

                                                if(!init.use_under_bar) sub_parent_input_view_password.visibility = View.GONE

                                                sub_parent_input_view_password.apply {
                                                    init.apply {
                                                        layoutParams = RelativeLayout.LayoutParams((devWidth.toFloat() * signup_sub_parent_input_scaleX).toInt(), (devHeight.toFloat() * under_bar_height).toInt())
                                                            .apply {
                                                                backgroundColor = under_bar_color

                                                                addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)

                                                                setMargins(0,0,0,(devHeight.toFloat() * signup_sub_parent_input_view_marginBottom).toInt())
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
                                            var sub_parent_input_password_confirm: RelativeLayout = init.signup_contentView_Type_A_sub_parent_input_password_confirm
                                            var sub_parent_input_imageView_password_confirm: ImageView = init.signup_contentView_Type_A_sub_parent_input_imageView_password_confirm
                                            var sub_parent_input_editTextView_password_confirm: EditText = init.signup_contentView_Type_A_sub_parent_input_editTextView_password_confirm
                                            var sub_parent_input_view_password_confirm: View = init.signup_contentView_Type_A_sub__parent_input_view_password_confirm

                                            if(!use) sub_parent_input_password_confirm.visibility = View.GONE

                                            // Sub Parent Input Layout Settings
                                            sub_parent_input_password_confirm.apply {
                                                init.apply {
                                                    layoutParams = LinearLayout.LayoutParams((devWidth.toFloat() * signup_sub_parent_input_scaleX).toInt(), (devHeight.toFloat() * signup_sub_parent_input_scaleY).toInt())
                                                        .apply {
                                                            gravity = Gravity.CENTER_HORIZONTAL
                                                        }
                                                }

                                                sub_parent_input_imageView_password_confirm.apply {
                                                    init.apply {
                                                        layoutParams = RelativeLayout.LayoutParams((devWidth.toFloat() * signup_sub_parent_input_imageView_scaleX).toInt(), (devHeight.toFloat() * signup_sub_parent_input_imageView_scaleY).toInt())
                                                            .apply {
                                                                addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)

                                                                setImageResource(icon_password_drawable_resource)
                                                            }
                                                    }
                                                }

                                                sub_parent_input_editTextView_password_confirm.apply {
                                                    init.apply {
                                                        layoutParams = RelativeLayout.LayoutParams((devWidth.toFloat() * signup_sub_parent_input_editTextView_scaleX).toInt(), (devHeight.toFloat() * signup_sub_parent_input_editTextView_scaleY).toInt())
                                                            .apply {
                                                                background = ContextCompat.getDrawable(mContext, android.R.color.transparent)

                                                                hint = editText_password_confirm_hint_value
                                                                setHintTextColor(editText_hint_color)

                                                                setTextColor(editText_text_color)

                                                                maxLines = 1
                                                                singleLine = true
                                                                filters = arrayOf(InputFilter.LengthFilter(editText_password_max_length))

                                                                addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)

                                                                addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE)
                                                                addRule(RelativeLayout.RIGHT_OF, sub_parent_input_imageView_password_confirm.id)

                                                                setMargins((devWidth.toFloat() * signup_sub_parent_input_editTextView_marginLeft).toInt(),0,0, 0)
                                                            }
                                                    }
                                                }

                                                if(!init.use_under_bar) sub_parent_input_view_password_confirm.visibility = View.GONE

                                                sub_parent_input_view_password_confirm.apply {
                                                    init.apply {
                                                        layoutParams = RelativeLayout.LayoutParams((devWidth.toFloat() * signup_sub_parent_input_scaleX).toInt(), (devHeight.toFloat() * under_bar_height).toInt())
                                                            .apply {
                                                                backgroundColor = under_bar_color

                                                                addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)

                                                                setMargins(0,0,0,(devHeight.toFloat() * signup_sub_parent_input_view_marginBottom).toInt())
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
                                            var sub_parent_input_name: RelativeLayout = init.signup_contentView_Type_A_sub_parent_input_name
                                            var sub_parent_input_imageView_name: ImageView = init.signup_contentView_Type_A_sub_parent_input_imageView_name
                                            var sub_parent_input_editTextView_name: EditText = init.signup_contentView_Type_A_sub_parent_input_editTextView_name
                                            var sub_parent_input_view_name: View = init.signup_contentView_Type_A_sub__parent_input_view_name

                                            if(!use) sub_parent_input_name.visibility = View.GONE

                                            // Sub Parent Input Layout Settings
                                            sub_parent_input_name.apply {
                                                init.apply {
                                                    layoutParams = LinearLayout.LayoutParams((devWidth.toFloat() * signup_sub_parent_input_scaleX).toInt(), (devHeight.toFloat() * signup_sub_parent_input_scaleY).toInt())
                                                        .apply {
                                                            gravity = Gravity.CENTER_HORIZONTAL
                                                        }
                                                }

                                                sub_parent_input_imageView_name.apply {
                                                    init.apply {
                                                        layoutParams = RelativeLayout.LayoutParams((devWidth.toFloat() * signup_sub_parent_input_imageView_scaleX).toInt(), (devHeight.toFloat() * signup_sub_parent_input_imageView_scaleY).toInt())
                                                            .apply {
                                                                addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)

                                                                setImageResource(icon_name_drawable_resource)
                                                            }
                                                    }
                                                }

                                                sub_parent_input_editTextView_name.apply {
                                                    init.apply {
                                                        layoutParams = RelativeLayout.LayoutParams((devWidth.toFloat() * signup_sub_parent_input_editTextView_scaleX).toInt(), (devHeight.toFloat() * signup_sub_parent_input_editTextView_scaleY).toInt())
                                                            .apply {
                                                                background = ContextCompat.getDrawable(mContext, android.R.color.transparent)

                                                                hint = editText_name_hint_value
                                                                setHintTextColor(editText_hint_color)

                                                                setTextColor(editText_text_color)

                                                                maxLines = 1
                                                                singleLine = true
                                                                filters = arrayOf(InputFilter.LengthFilter(editText_name_max_length))

                                                                addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)

                                                                addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE)
                                                                addRule(RelativeLayout.RIGHT_OF, sub_parent_input_imageView_name.id)

                                                                setMargins((devWidth.toFloat() * signup_sub_parent_input_editTextView_marginLeft).toInt(),0,0, 0)
                                                            }
                                                    }
                                                }

                                                if(!init.use_under_bar) sub_parent_input_view_name.visibility = View.GONE

                                                sub_parent_input_view_name.apply {
                                                    init.apply {
                                                        layoutParams = RelativeLayout.LayoutParams((devWidth.toFloat() * signup_sub_parent_input_scaleX).toInt(), (devHeight.toFloat() * under_bar_height).toInt())
                                                            .apply {
                                                                backgroundColor = under_bar_color

                                                                addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)

                                                                setMargins(0,0,0,(devHeight.toFloat() * signup_sub_parent_input_view_marginBottom).toInt())
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
                                            var sub_parent_input_phone: RelativeLayout = init.signup_contentView_Type_A_sub_parent_input_phone
                                            var sub_parent_input_imageView_phone: ImageView = init.signup_contentView_Type_A_sub_parent_input_imageView_phone
                                            var sub_parent_input_editTextView_phone: EditText = init.signup_contentView_Type_A_sub_parent_input_editTextView_phone
                                            var sub_parent_input_view_phone: View = init.signup_contentView_Type_A_sub__parent_input_view_phone
                                            var sub_parent_input_button_phone: Button = init.signup_contentView_Type_A_sub__parent_input_button_phone

                                            if(!use) sub_parent_input_phone.visibility = View.GONE

                                            // Sub Parent Input Layout Settings
                                            sub_parent_input_phone.apply {
                                                init.apply {
                                                    layoutParams = LinearLayout.LayoutParams((devWidth.toFloat() * signup_sub_parent_input_scaleX).toInt(), (devHeight.toFloat() * signup_sub_parent_input_scaleY).toInt())
                                                        .apply {
                                                            gravity = Gravity.CENTER_HORIZONTAL
                                                        }
                                                }

                                                sub_parent_input_imageView_phone.apply {
                                                    init.apply {
                                                        layoutParams = RelativeLayout.LayoutParams((devWidth.toFloat() * signup_sub_parent_input_imageView_scaleX).toInt(), (devHeight.toFloat() * signup_sub_parent_input_imageView_scaleY).toInt())
                                                            .apply {
                                                                addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)

                                                                setImageResource(icon_phone_drawable_resource)
                                                            }
                                                    }
                                                }

                                                sub_parent_input_editTextView_phone.apply {
                                                    init.apply {
                                                        layoutParams = RelativeLayout.LayoutParams((devWidth.toFloat() * signup_sub_parent_input_editTextView_scaleX).toInt(), (devHeight.toFloat() * signup_sub_parent_input_editTextView_scaleY).toInt())
                                                            .apply {
                                                                background = ContextCompat.getDrawable(mContext, android.R.color.transparent)

                                                                hint = editText_phone_hint_value
                                                                setHintTextColor(editText_hint_color)

                                                                setTextColor(editText_text_color)

                                                                maxLines = 1
                                                                singleLine = true
                                                                filters = arrayOf(InputFilter.LengthFilter(editText_phone_max_length))

                                                                inputType = InputType.TYPE_CLASS_NUMBER

                                                                addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)

                                                                addRule(RelativeLayout.RIGHT_OF, sub_parent_input_imageView_phone.id)

                                                                setMargins((devWidth.toFloat() * signup_sub_parent_input_editTextView_marginLeft).toInt(),0,0, 0)
                                                            }
                                                    }
                                                }

                                                var view_scaleX = (devWidth.toFloat() * init.signup_sub_parent_input_scaleX).toInt()

                                                if(useAuth) {
                                                    view_scaleX -= (devWidth.toFloat() * init.passwordAuth_button_scaleX).toInt()

                                                    // show phone auth item
                                                    setSignUpPhoneAuthItem()
                                                } else {
                                                    sub_parent_input_button_phone.visibility = View.GONE
                                                    init.signup_contentView_Type_A_sub_parent_input_phone_auth.visibility = View.GONE
                                                }

                                                // [인증번호 요청] 버튼
                                                sub_parent_input_button_phone.apply {
                                                    init.apply {
                                                        layoutParams = RelativeLayout.LayoutParams((devWidth.toFloat() * passwordAuth_button_scaleX).toInt(), (devHeight.toFloat() * passwordAuth_button_scaleY).toInt())
                                                            .apply {
                                                                addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE)
                                                                addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)
                                                                setMargins(0,0,0,(devHeight.toFloat() * signup_sub_parent_input_view_marginBottom).toInt())

                                                                if(passwordAuth_button_background_resource != DATA_NONE) backgroundResource = passwordAuth_button_background_resource
                                                                else if(passwordAuth_button_background_color != DATA_NONE) backgroundColor = passwordAuth_button_background_color

                                                                text = passwordAuth_button_text_value
                                                                textSize = passwordAuth_button_text_size
                                                                textColor = passwordAuth_button_text_color
                                                            }

                                                        setOnClickListener {
                                                            viewModel.requestPhoneAuth(sub_parent_input_editTextView_phone.text.toString(), auth_time)
                                                        }
                                                    }
                                                }

                                                if(!init.use_under_bar) sub_parent_input_view_phone.visibility = View.GONE

                                                sub_parent_input_view_phone.apply {
                                                    init.apply {
                                                        layoutParams = RelativeLayout.LayoutParams(view_scaleX, (devHeight.toFloat() * under_bar_height).toInt())
                                                            .apply {
                                                                backgroundColor = under_bar_color

                                                                addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)

                                                                setMargins(0,0,0,(devHeight.toFloat() * signup_sub_parent_input_view_marginBottom).toInt())
                                                            }
                                                    }
                                                }
                                            }
                                        }

                                        /** "휴대폰" 인증 기능 이용시 사용될 [인증번호 입력] */
                                        private fun setSignUpPhoneAuthItem() {
                                            var sub_parent_input_phone_auth: RelativeLayout = init.signup_contentView_Type_A_sub_parent_input_phone_auth
                                            var sub_parent_input_imageView_phone_auth: ImageView = init.signup_contentView_Type_A_sub_parent_input_imageView_phone_auth
                                            var sub_parent_input_editTextView_phone_auth: EditText = init.signup_contentView_Type_A_sub_parent_input_editTextView_phone_auth
                                            var sub_parent_input_view_phone_auth: View = init.signup_contentView_Type_A_sub__parent_input_view_phone_auth
                                            var sub_parent_input_button_phone_auth: Button = init.signup_contentView_Type_A_sub__parent_input_button_phone_auth

                                            // Sub Parent Input Layout Settings
                                            sub_parent_input_phone_auth.apply {
                                                init.apply {
                                                    //                                    backgroundColor = Color.RED    // 임시

                                                    layoutParams = LinearLayout.LayoutParams((devWidth.toFloat() * signup_sub_parent_input_scaleX).toInt(), (devHeight.toFloat() * signup_sub_parent_input_scaleY).toInt())
                                                        .apply {
                                                            gravity = Gravity.CENTER_HORIZONTAL
                                                        }
                                                }

                                                sub_parent_input_imageView_phone_auth.apply {
                                                    init.apply {
                                                        layoutParams = RelativeLayout.LayoutParams((devWidth.toFloat() * signup_sub_parent_input_imageView_scaleX).toInt(), (devHeight.toFloat() * signup_sub_parent_input_imageView_scaleY).toInt())
                                                            .apply {
                                                                addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)

                                                                setImageResource(icon_phoneAuth_drawable_resource)
                                                            }
                                                    }
                                                }

                                                sub_parent_input_editTextView_phone_auth.apply {
                                                    init.apply {
                                                        layoutParams = RelativeLayout.LayoutParams((devWidth.toFloat() * signup_sub_parent_input_editTextView_scaleX).toInt(), (devHeight.toFloat() * signup_sub_parent_input_editTextView_scaleY).toInt())
                                                            .apply {
                                                                background = ContextCompat.getDrawable(mContext, android.R.color.transparent)

                                                                hint = editText_phoneAuth_hint_value
                                                                setHintTextColor(editText_hint_color)

                                                                setTextColor(editText_text_color)

                                                                maxLines = 1
                                                                singleLine = true
                                                                filters = arrayOf(InputFilter.LengthFilter(editText_phoneAuth_max_length))

                                                                inputType = InputType.TYPE_CLASS_NUMBER

                                                                addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)

                                                                addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE)
                                                                addRule(RelativeLayout.RIGHT_OF, sub_parent_input_imageView_phone_auth.id)

                                                                setMargins((devWidth.toFloat() * signup_sub_parent_input_editTextView_marginLeft).toInt(),0,0, 0)
                                                            }
                                                    }
                                                }

                                                // [인증번호 확인] 버튼
                                                sub_parent_input_button_phone_auth.apply {
                                                    init.apply {
                                                        layoutParams = RelativeLayout.LayoutParams((devWidth.toFloat() * passwordAuth_confirm_button_scaleX).toInt(), (devHeight.toFloat() * passwordAuth_confirm_button_scaleY).toInt())
                                                            .apply {
                                                                addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE)
                                                                addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)
                                                                setMargins(0,0,0,(devHeight.toFloat() * signup_sub_parent_input_view_marginBottom).toInt())

                                                                if(passwordAuth_confirm_button_background_resource != DATA_NONE) backgroundResource = passwordAuth_confirm_button_background_resource
                                                                else if(passwordAuth_confirm_button_background_color != DATA_NONE) backgroundColor = passwordAuth_confirm_button_background_color

                                                                text = passwordAuth_confirm_button_text_value
                                                                textSize = passwordAuth_confirm_button_text_size
                                                                textColor = passwordAuth_confirm_button_text_color
                                                            }

                                                        setOnClickListener {
                                                            viewModel.onSignUpPhoneAuthConfirmButtonClick(sub_parent_input_editTextView_phone_auth.text.toString())
                                                        }
                                                    }
                                                }

                                                if(!init.use_under_bar) sub_parent_input_view_phone_auth.visibility = View.GONE

                                                sub_parent_input_view_phone_auth.apply {
                                                    init.apply {
                                                        layoutParams = RelativeLayout.LayoutParams((devWidth.toFloat() * signup_sub_parent_input_scaleX).toInt() - (devWidth.toFloat() * init.passwordAuth_confirm_button_scaleX).toInt(), (devHeight.toFloat() * under_bar_height).toInt())
                                                            .apply {
                                                                backgroundColor = under_bar_color

                                                                addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE)

                                                                setMargins(0,0,0,(devHeight.toFloat() * signup_sub_parent_input_view_marginBottom).toInt())
                                                            }
                                                    }
                                                }

                                                setPhoneAuthTimer()
                                            }
                                        }

                                        /** "휴대폰" 인증 타이머 */
                                        private fun setPhoneAuthTimer() {
                                            var sub_parent_input_phone_auth_timer: RelativeLayout = init.signup_contentView_Type_A_sub_parent_input_phone_auth_timer_layout
                                            var sub_parent_input_phone_auth_timer_textView: TextView = init.signup_contentView_Type_A_sub_parent_input_phone_auth_timer_textView

                                            sub_parent_input_phone_auth_timer.apply {
                                                init.apply {
                                                    layoutParams = LinearLayout.LayoutParams(wrapContent, wrapContent)
                                                }

                                                sub_parent_input_phone_auth_timer_textView.apply {
                                                    init.apply {
                                                        text = "남은 시간 0$auth_time:00"
                                                        textSize = 12f
                                                        textColor = Color.RED
                                                    }
                                                }
                                            }
                                        }

                                        /**
                                         * "약관" 이용시 사용될 Main Layout
                                         * @param use 사용 true or false
                                         */
                                        private fun setAgreementLayout(use: Boolean) {
                                            // main layout
                                            var sub_parent_agreement: LinearLayout = init.signup_contentView_Type_A_sub_parent_agreement

                                            if(!use) sub_parent_agreement.visibility = View.GONE

                                            sub_parent_agreement.apply {
                                                init.apply {
                                                    layoutParams = LinearLayout.LayoutParams((devWidth.toFloat() * 0.9f).toInt(), wrapContent)
                                                        .apply {
                                                            gravity = Gravity.CENTER_HORIZONTAL
                                                        }
                                                    //                backgroundColor = Color.RED // 임시
                                                }

                                                setAgreementAll(devWidth, devHeight)
                                                setAgreemnetItem1(devWidth, devHeight)
                                                setAgreemnetItem2(devWidth, devHeight)
                                            }
                                        }

                                        /** "약관" 이용시 사용될 [전체 동의] */
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
                                                    } else {
                                                        init.signup_contentView_Type_A_sub_parent_agreement_item1_sub_checkBox.isChecked = true
                                                        init.signup_contentView_Type_A_sub_parent_agreement_item2_sub_checkBox.isChecked = true
                                                    }
                                                }
                                            }
                                        }

                                        /** "약관" 이용시 사용될 첫 번째 항목 */
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
                                                        viewModel.onSignUpShowAgreementItem1ButtonClick()
                                                    }
                                                }
                                            }
                                        }

                                        /** "약관" 이용시 사용될 두 번째 항목 */
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

                                                        text = agreement_checkBox_item2_show_text_value
                                                        textColor = agreement_checkBox_item2_show_text_color
                                                        textSize = agreement_checkBox_item2_show_text_size

                                                        gravity = Gravity.CENTER
                                                    }

                                                    setOnClickListener {
                                                        viewModel.onSignUpShowAgreementItem2ButtonClick()
                                                    }
                                                }
                                            }
                                        }

                                        /** "회원가입 완료" 버튼 */
                                        private fun setAgreementButton() {
                                            var sub_parent_agreement_button_layout: LinearLayout = init.signup_contentView_Type_A_sub_parent_agreement_button_layout
                                            var sub_parent_agreement_button: Button = init.signup_contentView_Type_A_sub_parent_agreement_button

                                            sub_parent_agreement_button_layout.apply {
                                                init.apply {
                                                    layoutParams = LinearLayout.LayoutParams((devWidth.toFloat() * 0.9f).toInt(), matchParent)
                                                        .apply {
                                                            gravity = Gravity.CENTER_HORIZONTAL
                                                        }
                                                    //                backgroundColor = Color.RED // 임시

                                                    gravity = Gravity.BOTTOM
                                                }

                                                // [회원가입 완료] 버튼
                                                sub_parent_agreement_button.apply {
                                                    init.apply {
                                                        if(signup_complete_button_background_resource != DATA_NONE) {
                                                            layoutParams = LinearLayout.LayoutParams((devWidth.toFloat() * signup_complete_button_scaleX).toInt(), (devHeight.toFloat() * signup_complete_button_scaleY).toInt())
                                                                .apply {
                                                                    backgroundResource = signup_complete_button_background_resource
                                                                    setMargins(0,0,0, (devHeight.toFloat() * signup_complete_button_marginBottom).toInt())
                                                                }

                                                        } else {
                                                            layoutParams = LinearLayout.LayoutParams(matchParent, wrapContent)
                                                        }

                                                        if(signup_complete_button_background_color != DATA_NONE) backgroundColor = signup_complete_button_background_color

                                                        text = signup_complete_button_text_value
                                                        textColor = signup_complete_button_text_color
                                                        textSize = signup_complete_button_text_size
                                                    }

                                                    setOnClickListener {
                                                        init.apply {
                                                            viewModel.onSignUpAgreementButtonClick(
                                                                idEmail = signup_contentView_Type_A_sub_parent_input_editTextView_id_email.text.toString(),
                                                                password = signup_contentView_Type_A_sub_parent_input_editTextView_password.text.toString(),
                                                                phoneNumber = signup_contentView_Type_A_sub_parent_input_editTextView_password_confirm.text.toString(),
                                                                name = signup_contentView_Type_A_sub_parent_input_editTextView_name.text.toString(),
                                                                authNumber = signup_contentView_Type_A_sub_parent_input_editTextView_phone.text.toString(),
                                                                agreement_all = signup_contentView_Type_A_sub_parent_agreement_all_checkBox.isChecked
                                                            )
                                                        }
                                                    }
                                                }
                                            }
                                        }

                                        override fun onRemove() {

                                        }
                                    })
                                }
                                init.CUSTOM -> {

                                }
                            }
                        })
                    }
                }

                dispose?.let { it.apply {
                    if(!isDisposed) dispose() }
                }
            }
            override fun onSubscribe(d: Disposable) {
                dispose = d
            }
            override fun onNext(t: Void) {}
            override fun onError(e: Throwable) {}
        }

        viewModel.observerFindPwSettings = object : Observer<Void> {
            var dispose: Disposable? = null

            override fun onComplete() {
                init.login_Type_A_sub_parent_2_info_group_find_pw.apply {
                    gravity = Gravity.CENTER
                    textSize = init.login_sub_parent_2_info_group_findPW_textSize
                    textColor = init.login_sub_parent_2_info_group_findPW_textColor
                    layoutParams =  LinearLayout.LayoutParams((devWidth.toFloat() * init.login_sub_parent_2_info_group_findPW_scaleX).toInt(), (devHeight.toFloat() * init.login_sub_parent_2_info_group_findPW_scaleY).toInt() )
                    translationX = (devWidth.toFloat() * init.login_sub_parent_2_info_group_findPW_positionX)
                    translationY = (devHeight.toFloat() * init.login_sub_parent_2_info_group_findPW_positionY)
                    text = init.login_sub_parent_2_info_group_findPW_text
                    setOnClickListener {
//                        onFindInfoClick_LoginScene(util , viewModel , con)
                    }
                }

                dispose?.let { it.apply {
                    if(!isDisposed) dispose() }
                }
            }
            override fun onSubscribe(d: Disposable) {
                dispose = d
            }
            override fun onNext(t: Void) {}
            override fun onError(e: Throwable) {}
        }

        // 회원가입 인증번호 요청 observer
        viewModel.observerUpdateUI = object : Observer<Boolean> {
            var status: Boolean = false
            var dispose: Disposable? = null

            override fun onSubscribe(d: Disposable) {
                dispose = d
            }

            override fun onNext(t: Boolean) {
                status = t
            }
            override fun onComplete() {
                if(status) {
                    init.apply {
                        signup_contentView_Type_A_sub_parent_input_editTextView_phone.isEnabled = false
                        signup_contentView_Type_A_sub__parent_input_button_phone.isClickable = false
                        signup_contentView_Type_A_sub__parent_input_button_phone.backgroundTintList = ColorStateList.valueOf(
                            Color.GRAY)
                    }

                    dispose?.let { it.apply {
                        if(!isDisposed) dispose() }
                    }
                }
                else {
                    Snackbar.make(contentView!!,"휴대폰 번호를 다시 확인해 주세요", Snackbar.LENGTH_SHORT).show()
                }
            }
            override fun onError(e: Throwable) {}
        }

        // 회원가입 인증 요청 타이머 observer
        viewModel.observerTimer = object : Observer<String> {
            var dispose: Disposable? = null

            override fun onSubscribe(d: Disposable) {
                dispose = d
            }

            override fun onComplete() {
                init.apply {
                    signup_contentView_Type_A_sub_parent_input_editTextView_phone.isEnabled = true
                    signup_contentView_Type_A_sub__parent_input_button_phone.isClickable = true
                    signup_contentView_Type_A_sub__parent_input_button_phone.backgroundTintList = null

                    signup_contentView_Type_A_sub_parent_input_phone_auth_timer_textView.text = "남은 시간 0$auth_time:00"
                }

                dispose?.let { it.apply {
                    if(!isDisposed) dispose() }
                }
            }

            override fun onNext(t: String) {
                init.signup_contentView_Type_A_sub_parent_input_phone_auth_timer_textView.text = t
            }

            override fun onError(e: Throwable) {

            }
        }

        // 회원가입 인증번호 확인 observer
        viewModel.observerSingUpPhoneAuthConfirm = object : Observer<Boolean> {
            var status: Boolean = false
            var disposable: Disposable? = null
            override fun onComplete() {
                if(status) {
                    Snackbar.make(contentView!!,"인증번호 확인 성공", Snackbar.LENGTH_SHORT).show()
                    init.apply {
                        signup_contentView_Type_A_sub_parent_input_editTextView_phone_auth.isEnabled = false
                        signup_contentView_Type_A_sub__parent_input_button_phone_auth.isClickable = false
                        signup_contentView_Type_A_sub__parent_input_button_phone_auth.backgroundTintList = ColorStateList.valueOf(
                            Color.GRAY)

                        signup_contentView_Type_A_sub_parent_input_phone_auth_timer_textView.text = "남은 시간 0$auth_time:00"
                    }

                    viewModel.disposableTimer?.let { it.apply {
                        if(!isDisposed) dispose() }
                    }

                    disposable?.let { it.apply {
                        if(!isDisposed) dispose() }
                    }
                }
                else {
                    Snackbar.make(contentView!!,"인증번호를 다시 확인해 주세요", Snackbar.LENGTH_SHORT).show()
                }
            }

            override fun onSubscribe(d: Disposable) {
                disposable = d
            }

            override fun onNext(t: Boolean) {
                status = t
            }

            override fun onError(e: Throwable) {

            }
        }

        // 회원가입 약관 Item1 observer
        viewModel.observerAgreementItem1 = object : Observer<Void> {
            var disposable: Disposable? = null
            override fun onComplete() {
                Snackbar.make(contentView!!,"이용약관 보여주기", Snackbar.LENGTH_SHORT).show()
            }

            override fun onSubscribe(d: Disposable) {
                disposable = d
            }

            override fun onNext(t: Void) {}
            override fun onError(e: Throwable) {}
        }

        // 회원가입 약관 Item2 observer
        viewModel.observerAgreementItem2 = object : Observer<Void> {
            var disposable: Disposable? = null
            override fun onComplete() {
                Snackbar.make(contentView!!,"개인정보취급방침 보여주기", Snackbar.LENGTH_SHORT).show()
            }

            override fun onSubscribe(d: Disposable) {
                disposable = d
            }

            override fun onNext(t: Void) {}
            override fun onError(e: Throwable) {}
        }

        //메인 VIewpager
        viewModel.observerMainScenes = object : Observer<Boolean> {
            var status: Boolean = false
            var dispose: Disposable? = null

            override fun onSubscribe(d: Disposable) {
                dispose = d
            }

            override fun onNext(t: Boolean) {
                status = t
            }
            override fun onComplete() {

                //set onCreateListener
                var onCreateListener_model_A : GeneralPerposeRecyclerViewAdapter.createViewListener = object : GeneralPerposeRecyclerViewAdapter.createViewListener  {

                    override fun onCreate(ui: AnkoContext<ViewGroup>): View = init.getViewpager_Item_1_MAIN(baseContext)
                }

                //set onBindListener
                var onBindListener_model_A : GeneralPerposeRecyclerViewAdapter.bindListener = object : GeneralPerposeRecyclerViewAdapter.bindListener {

                    override fun onBind(holder: RecyclerView.ViewHolder, position: Int, data : Any, baseContext : Context) {

                        holder.itemView.findViewById<TextView>(R.id.viewPager_item_text).text = data.toString()
                        holder.itemView.run {
                            setOnClickListener {baseContext.toast("position : $position")}
                        }
                    }

                }

                val eventList = ArrayList<Any>()
                eventList.add("PAGE : 1")
                eventList.add("PAGE : 2")
                eventList.add("PAGE : 3")
                eventList.add("PAGE : 4")
                eventList.add("PAGE : 5")
                eventList.add("PAGE : 6")
                eventList.add("PAGE : 7")
                eventList.add("PAGE : 7")

                mMainPagerAdapter = GeneralPerposeRecyclerViewAdapter(baseContext)
                mMainPagerAdapter!!.setOnCreateViewListener(onCreateListener_model_A)
                mMainPagerAdapter!!.setOnBindListener(onBindListener_model_A)
                mMainPagerAdapter.setDataList(eventList)

                init.main_viewPager.apply {
                    layoutParams =  FrameLayout.LayoutParams((devWidth.toFloat() * 1.0f).toInt(), (devHeight.toFloat() * 0.9f).toInt() )
                    translationX = (devWidth.toFloat() * 0.0f)
                    translationY = (devHeight.toFloat() * 0.1f)
                    adapter = mMainPagerAdapter
                }

                init.main_tabLayout.apply {
                    layoutParams =  FrameLayout.LayoutParams((devWidth.toFloat() * 1.0f).toInt(), (devHeight.toFloat() * 0.1f).toInt() )
                    translationX = (devWidth.toFloat() * 0.0f)
                    translationY = (devHeight.toFloat() * 0.0f)
                    tabMode = TabLayout.MODE_SCROLLABLE
                }

                TabLayoutMediator(init.main_tabLayout, init.main_viewPager) { tab, position ->
                    tab.text = position.toString()
                }.attach()

            }
            override fun onError(e: Throwable) {}
        }

    }
}
