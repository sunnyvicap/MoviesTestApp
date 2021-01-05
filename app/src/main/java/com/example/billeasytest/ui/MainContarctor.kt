package com.example.billeasytest.ui

import com.example.billeasytest.base.BaseMvpPresenter
import com.example.billeasytest.base.BaseView

interface MainContarctor {

    interface Presenter : BaseMvpPresenter<MainContarctor.View>{

    }

    interface View  : BaseView{

    }
}