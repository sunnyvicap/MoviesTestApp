package com.example.billeasytest.base


interface BaseMvpPresenter<V : BaseView>{


    fun attach(view: V)


    fun detach()


    fun isAttached(): Boolean

}