package com.example.core

open interface BaseView<T> {
    fun getPresenter(): T
}