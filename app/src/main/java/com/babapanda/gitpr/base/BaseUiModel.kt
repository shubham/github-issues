package com.babapanda.gitpr.base

import android.os.Parcel
import android.os.Parcelable
import android.view.View
import androidx.databinding.BaseObservable

open class BaseUiModel() : BaseObservable(), Parcelable, BaseHandler<BaseUiModel>{
    var layoutResId: Int = 0

    var adapterPosition = -1

    var wrappedChildItemHeight: Int = 0

    constructor(parcel: Parcel) : this() {
        layoutResId = parcel.readInt()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(layoutResId)
    }

    override fun onclick(view: View, data: BaseUiModel) {
    }

    companion object CREATOR : Parcelable.Creator<BaseUiModel> {
        override fun createFromParcel(parcel: Parcel): BaseUiModel {
            return BaseUiModel(parcel)
        }

        override fun newArray(size: Int): Array<BaseUiModel?> {
            return arrayOfNulls(size)
        }
    }

    open fun isRecyclable() = true
}
