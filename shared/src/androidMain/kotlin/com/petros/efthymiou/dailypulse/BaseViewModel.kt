package com.petros.efthymiou.dailypulse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

actual open class BaseViewModel: ViewModel() {
    // to avoid memeroy leaks and be able to remove bound viewmodelscope wihtin scopes
    actual val scope = viewModelScope
}