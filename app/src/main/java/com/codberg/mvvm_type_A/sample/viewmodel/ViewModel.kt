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
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.util.concurrent.TimeUnit

class ViewModel(private val model: networkServiece) : BaseKotlinViewModel(), AnkoLogger {

    private val TAG = "ViewModel"

    private val compositeDisposable = CompositeDisposable()

    var disposableTimer: Disposable? = null
    var observerTimer: DisposableObserver<String>? = null

    var observerUpdateUI: DisposableObserver<Boolean>? = null
    var disposableUpdateUI: Disposable? = null

    val updateUI = MutableLiveData<Boolean>()

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

    fun phoneAuthInitUI() {
        Observable.create<Boolean>{
            it.onNext(false)
            it.onComplete()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observerUpdateUI!!)
    }

    fun requestPhoneAuth(phoneNumber: String, auth_time: Int) {
        // 요청 직후 UI 업데이트
        Observable.create<Boolean>{
            it.onNext(true)
            it.onComplete()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observerUpdateUI!!)

        // todo -> 휴대폰 번호 인증 요청

        startRequestPhoneTimer(auth_time)
    }

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
            )
    }


}