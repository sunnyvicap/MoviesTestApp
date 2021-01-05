package com.example.billeasytest.base


open class BasePresenter<V : BaseView> : BaseMvpPresenter<V> {

    private var mView: V? = null

    override fun attach(view: V) {
        mView = view;
    }

    override fun detach() {
        mView = null;
    }

    override fun isAttached(): Boolean {
        return mView != null;
    }


    fun getView(): V {
        return mView!!
    }
}