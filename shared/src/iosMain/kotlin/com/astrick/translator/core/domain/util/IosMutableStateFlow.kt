package com.astrick.translator.core.domain.util

import kotlinx.coroutines.flow.MutableStateFlow

class IosMutableStateFlow<T>(
    private val initialValue: T
): CommonMutableStateFlow<T>(MutableStateFlow(initialValue))

