package com.codberg.mvvm_type_A.sample.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.libs.cutil_kotlin.BaseKotlinViewModel


import com.codberg.mvvm_type_A.sample.model.request.request_testApi
import com.codberg.mvvm_type_A.sample.model.response.singleTypeData_Response
import com.codberg.mvvm_type_A.sample.model.response.listTypeData_Response
import com.codberg.mvvm_type_A.sample.model.service.networkServiece
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.util.concurrent.TimeUnit

class ViewModel(private val model: networkServiece) : BaseKotlinViewModel(), AnkoLogger {

    private val TAG = javaClass.simpleName

    val compositeDisposable = CompositeDisposable()
    val compositeFindIdDisposable = CompositeDisposable()
    val compositeFindPwDisposable = CompositeDisposable()

    var disposableTimer: Disposable? = null
    var disposableFindIdTimer: Disposable? = null
    var disposableFindPwTimer: Disposable? = null

    var observerSignUpTimer: Observer<String>? = null
    var observerFindIdTimer: Observer<String>? = null
    var observerFindPwTimer: Observer<String>? = null

    var observerSignUpUpdateUI: Observer<Boolean>? = null
    var observerFindIdUpdateUI: Observer<Boolean>? = null
    var observerFindPwUpdateUI: Observer<Boolean>? = null

    var observerSingUpPhoneAuthConfirm: Observer<Boolean>? = null
    var observerFindIdPhoneAuthConfirm: Observer<Boolean>? = null
    var observerFindPwPhoneAuthConfirm: Observer<Boolean>? = null

    var observerAgreementItem1: Observer<Void>? = null
    var observerAgreementItem2: Observer<Void>? = null

    var observerSignUpSettings: Observer<Void>? = null
    var observerFindIdPwSettings: Observer<Void>? = null
    var observerMainScenes: Observer<Boolean>? = null

    companion object  { val POST = 0; val GET  = 1 }

    fun onBackPressed() {

    }

    //리스트 타입 예시
    val _listType_ResponseLiveData = MutableLiveData<List<listTypeData_Response>>()
    val listType_ResponseLiveData:LiveData<List<listTypeData_Response>> get() = _listType_ResponseLiveData

    //단일 데이터 예시
    private val _singleDataType_ResponseLiveData = MutableLiveData<singleTypeData_Response>()
    val singleDataType_ResponseLiveData:LiveData<singleTypeData_Response> get() = _singleDataType_ResponseLiveData

    fun notifyListTypeData(requestType : Int) {

        when(requestType)  {

            GET ->  {
                addDisposable(model.testApi_GET("header_GET","1","desc",1,100)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        {
                            _listType_ResponseLiveData.postValue(it)
                        },
                        {
                            it.printStackTrace()
                        }
                    )
                )
            }

            POST -> {
                var request : request_testApi = request_testApi("1","ASC",3,300)

                addDisposable(model.testApi_POST("header_POST",request)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        {
                            _listType_ResponseLiveData.postValue(it)
                        },
                        {
                            it.printStackTrace()
                        }
                    )
                )
            }

        }

    }

    fun notifySingleData(requestType : Int)  {

        when(requestType)  {

            GET -> {
                addDisposable(model.testApi2_GET("header_GET","1","desc",1,100)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        {
                            info("${it.name} - singleType")
                            _singleDataType_ResponseLiveData.postValue(it)
                        },
                        {
                            it.printStackTrace()
                        }
                    )
                )
            }

            POST -> {
                var request : request_testApi = request_testApi("1","ASC",3,300)

                addDisposable(model.testApi2_POST("header_POST",request)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        {
                            info("${it.name} - singleType")
                            _singleDataType_ResponseLiveData.postValue(it)
                        },
                        {
                            it.printStackTrace()
                        }
                    )
                )
            }

        }

    }

    fun setSignUpSettings() {
        Observable.create<Void>{it.onComplete()}
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe ({}, {},
                { observerSignUpSettings!!.onComplete() }
            )
            .apply { compositeDisposable.add(this) }
    }

    fun setFindIdPwSettings() {
        Observable.create<Void>{it.onComplete()}
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe ({}, {},
                { observerFindIdPwSettings!!.onComplete() }
            )
            .apply { compositeDisposable.add(this) }
    }

    fun onSignUpShowAgreementItem1ButtonClick() {
        Observable.create<Void>{it.onComplete()}
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe ({}, {},
                { observerAgreementItem1!!.onComplete() }
            )
            .apply { compositeDisposable.add(this) }
    }

    fun onSignUpShowAgreementItem2ButtonClick() {
        Observable.create<Void> { it.onComplete() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({}, {},
                { observerAgreementItem2!!.onComplete() }
            )
            .apply { compositeDisposable.add(this) }
    }

    fun onSignUpAgreementButtonClick(idEmail: String, password: String, name: String, phoneNumber: String, authNumber: String, agreement_all: Boolean) {
        // todo -> 1. 모든 정보가 입력 되었는지 확인
        // todo -> 2. 인증번호 확인이 안됐으면, 확인 요청 토스트 띄우기
        // todo -> 3. 약관 동의 여부 확인
        // todo -> 4. 메인 화면 or 로그인 화면 이동
//        setDisposableTimer()
//        phoneAuthInitUI()
    }

    /**
     * 휴대폰 인증번호 요청에 따른 update UI, run Timer, call request phone number api
     */
    fun requestPhoneAuth(phoneNumber: String, auth_time: Int, type: Int) {
        var status = !phoneNumber.isNullOrBlank()

        // 요청 직후 UI 업데이트
        Observable.create<Boolean>{
            it.onNext(status)
            it.onComplete()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(type) {
                        0 -> { observerSignUpUpdateUI!!.onNext(it) }
                        1 -> { observerFindIdUpdateUI!!.onNext(it) }
                        2 -> { observerFindPwUpdateUI!!.onNext(it) }
                    }
                },
                {
                    when(type) {
                        0 -> { observerSignUpUpdateUI!!.onError(it) }
                        1 -> { observerFindIdUpdateUI!!.onError(it) }
                        2 -> { observerFindPwUpdateUI!!.onError(it) }
                    }
                },
                {
                    when(type) {
                        0 -> { observerSignUpUpdateUI!!.onComplete() }
                        1 -> { observerFindIdUpdateUI!!.onComplete() }
                        2 -> { observerFindPwUpdateUI!!.onComplete() }
                    }

                    if(status) startRequestPhoneTimer(auth_time, type)
                }
            )
            .apply {
                when(type) {
                    0 -> { compositeDisposable.add(this) }
                    1 -> { compositeFindIdDisposable.add(this) }
                    2 -> { compositeFindPwDisposable.add(this) }
                }
            }

        // todo -> 휴대폰 번호 인증 요청
    }

    /**
     * 휴대폰 인증번호 요청 시간 타이머
     */
    private fun startRequestPhoneTimer(auth_time: Int, type: Int) {
        var minit = auth_time - 1
        var second = 60

        when(type) {
            0 -> {
                disposableTimer = Observable.interval(1, TimeUnit.SECONDS).take((60 * auth_time).toLong())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            if(second == 0) {
                                minit--
                                second = 59
                            }
                            else {
                                second--
                            }

                            if(second == 59) observerSignUpTimer?.onNext("남은 시간 0$minit:$second")
                            else {
                                if(second < 10) observerSignUpTimer?.onNext("남은 시간 0$minit:0$second")
                                else observerSignUpTimer?.onNext("남은 시간 0$minit:$second")
                            }
                        },
                        { error ->
                            observerSignUpTimer!!.onError(error)
                        },
                        {
                            observerSignUpTimer?.let {
                                it.onComplete()
                            }
                        }
                    ).apply { compositeDisposable.add(this) }
            }
            1 -> {
                disposableFindIdTimer = Observable.interval(1, TimeUnit.SECONDS).take((60 * auth_time).toLong())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            if(second == 0) {
                                minit--
                                second = 59
                            }
                            else {
                                second--
                            }

                            if(second == 59) observerFindIdTimer?.onNext("남은 시간 0$minit:$second")
                            else {
                                if(second < 10) observerFindIdTimer?.onNext("남은 시간 0$minit:0$second")
                                else observerFindIdTimer?.onNext("남은 시간 0$minit:$second")
                            }
                        },
                        { error ->
                            observerFindIdTimer!!.onError(error)
                        },
                        {
                            observerFindIdTimer?.let {
                                it.onComplete()
                            }
                        }
                    ).apply { compositeFindIdDisposable.add(this) }
            }
            2 -> {
                disposableFindPwTimer = Observable.interval(1, TimeUnit.SECONDS).take((60 * auth_time).toLong())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            if(second == 0) {
                                minit--
                                second = 59
                            }
                            else {
                                second--
                            }

                            if(second == 59) observerFindPwTimer?.onNext("남은 시간 0$minit:$second")
                            else {
                                if(second < 10) observerFindPwTimer?.onNext("남은 시간 0$minit:0$second")
                                else observerFindPwTimer?.onNext("남은 시간 0$minit:$second")
                            }
                        },
                        { error ->
                            observerFindPwTimer!!.onError(error)
                        },
                        {
                            observerFindPwTimer?.let {
                                it.onComplete()
                            }
                        }
                    ).apply { compositeFindPwDisposable.add(this) }
            }
        }
    }
    
    //메인화면 뷰페이저
    fun requestSetMain() {

        Observable.create<Boolean>{
            it.onNext(true)
            it.onComplete()
        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            { observerMainScenes!!.onNext(it)  },
            { observerMainScenes!!.onError(it) },
            { observerMainScenes!!.onComplete() }
        )
        .apply { compositeDisposable.add(this) }

    }

    // 아이디 찾기 휴대폰 입력 EditText after changed
    fun findIdPhoneEditTextAfterChanged(inputText: String) {
        Log.e("steve", "input text : $inputText")
    }

    // 아이디 찾기 Main Button
    fun findIdButton() {
        Log.e("steve", "onClick Find ID")
    }

    // 비밀번호 찾기 id or email 입력 EditText after chaned
    fun findPwEmailEditTextAfterChanged(inputText: String) {
        Log.e("steve", "input text : $inputText")
    }

    // 비밀번호 찾기 휴대폰 입력 EditText after changed
    fun findPwPhoneEditTextAfterChanged(inputText: String) {
        Log.e("steve", "input text : $inputText")
    }

    // 비밀번호 찾기 Main Button
    fun findPwButton() {
        Log.e("steve", "onClick Find PW")
    }

    /**
     * 휴대폰 인증번호 확인에 따른 ui update 및 call request phone auth number api
     */
    fun onSignUpPhoneAuthConfirmButtonClick(phoneAuthNumber: String, type: Int) {
        // todo -> 휴대폰 인증 번호 확인 api

        var status = !phoneAuthNumber.isNullOrBlank()

        Observable.create<Boolean>{
            it.onNext(status)
            it.onComplete()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(type) {
                        0 -> { observerSingUpPhoneAuthConfirm!!.onNext(it) }
                        1 -> { observerFindIdPhoneAuthConfirm!!.onNext(it) }
                        2 -> { observerFindPwPhoneAuthConfirm!!.onNext(it) }
                    }
                },
                {
                    when(type) {
                        0 -> { observerSingUpPhoneAuthConfirm!!.onError(it) }
                        1 -> { observerFindIdPhoneAuthConfirm!!.onError(it) }
                        2 -> { observerFindPwPhoneAuthConfirm!!.onError(it) }
                    }
                },
                {
                    when(type) {
                        0 -> { observerSingUpPhoneAuthConfirm!!.onComplete() }
                        1 -> { observerFindIdPhoneAuthConfirm!!.onComplete() }
                        2 -> { observerFindPwPhoneAuthConfirm!!.onComplete() }
                    }
                }
            )
            .apply {
                when(type) {
                    0 -> { compositeDisposable.add(this) }
                    1 -> { compositeFindIdDisposable.add(this) }
                    2 -> { compositeFindPwDisposable.add(this) }
                }
            }
    }
}