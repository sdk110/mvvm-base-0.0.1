package com.codberg.mvvm_type_A.sample.viewmodel

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

    var disposableTimer: Disposable? = null
    var observerTimer: Observer<String>? = null

    var observerUpdateUI: Observer<Boolean>? = null

    var observerSingUpPhoneAuthConfirm: Observer<Boolean>? = null

    var observerAgreementItem1: Observer<Void>? = null
    var observerAgreementItem2: Observer<Void>? = null

    var observerSignUpSettings: Observer<Void>? = null
    var observerFindPwSettings: Observer<Void>? = null

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

    fun setFindPwSettings() {
        Observable.create<Void>{it.onComplete()}
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe ({}, {},
                { observerFindPwSettings!!.onComplete() }
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
     * 휴대폰 인증번호 확인에 따른 ui update 및 call request phone auth number api
     */
    fun onSignUpPhoneAuthConfirmButtonClick(phoneAuthNumber: String) {
        // todo -> 휴대폰 인증 번호 확인 api

        var status = !phoneAuthNumber.isNullOrBlank()

        Observable.create<Boolean>{
            it.onNext(status)
            it.onComplete()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { observerSingUpPhoneAuthConfirm!!.onNext(it) },
                { observerSingUpPhoneAuthConfirm!!.onError(it) },
                { observerSingUpPhoneAuthConfirm!!.onComplete() }
            )
            .apply { compositeDisposable.add(this) }
    }

    /**
     * 휴대폰 인증번호 요청에 따른 update UI, run Timer, call request phone number api
     */
    fun requestPhoneAuth(phoneNumber: String, auth_time: Int) {
        var status = !phoneNumber.isNullOrBlank()

        // 요청 직후 UI 업데이트
        Observable.create<Boolean>{
            it.onNext(status)
            it.onComplete()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { observerUpdateUI!!.onNext(it) },
                { observerUpdateUI!!.onError(it)},
                {
                    observerUpdateUI!!.onComplete()
                    if(status) startRequestPhoneTimer(auth_time)
                }
            )
            .apply { compositeDisposable.add(this) }

        // todo -> 휴대폰 번호 인증 요청
    }

    /**
     * 휴대폰 인증번호 요청 시간 타이머
     */
    private fun startRequestPhoneTimer(auth_time: Int) {
        var minit = auth_time - 1
        var second = 60

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

                    if(second == 59) observerTimer?.onNext("남은 시간 0$minit:$second")
                    else {
                        if(second < 10) observerTimer?.onNext("남은 시간 0$minit:0$second")
                        else observerTimer?.onNext("남은 시간 0$minit:$second")
                    }
                },
                { error ->
                    observerTimer!!.onError(error)
                },
                {
                    observerTimer?.let {
                        it.onComplete()
                    }
                }
            ).apply { compositeDisposable.add(this) }
    }
}