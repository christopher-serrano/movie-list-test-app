package com.serranocjm.movielisttestapp.ui.adapter.base

abstract class ItemModel {
    abstract fun type(typeFactory: BaseTypeFactory): Int
}
