package com.codberg.mvvm_type_A.sample.view.init

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.res.ColorStateList
import android.graphics.Color
import com.codberg.mvvm_type_A.sample.view.MainViewManager

import android.os.Bundle
import androidx.core.animation.addListener
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import com.libs.cutil_kotlin.BaseKotlinActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.codberg.mvvm_type_A.sample.viewmodel.ViewModel
import com.google.android.material.snackbar.Snackbar
import com.libs.cutil_kotlin.BasicUtil
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import org.jetbrains.anko.*

open class initActivity : BaseKotlinActivity<ViewModel>() {
    lateinit var initViewManager : MainViewManager
    lateinit var init : init_data
    var isDraw : Boolean = false
    override val viewModel: ViewModel by viewModel()

    override fun onCustomBackPressed(): Int = 3
    override fun onBackground(): Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 초기 회원가입에 대한 observer settings
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

        init = init_data(this)
        initViewManager = MainViewManager(getViewUtil(),viewModel,this, init)

        setView()

        when(init.layout_config)  {
            in (0..1) -> nextScene()
            2 -> initViewManager.setLogin(init)
            3 -> initViewManager.setMain(init)
        }
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
    }
}
