package com.company.tictactoebootcamp.utils

import com.company.tictactoebootcamp.BuildConfig

class ApplicationBuildConfigFieldsProvider : BuildConfigFieldsProvider {

    override fun get(): BuildConfigFields = BuildConfigFields(
        buildType = BuildConfig.BUILD_TYPE,
        versionCode = BuildConfig.VERSION_CODE,
        versionName = BuildConfig.VERSION_NAME,
    )
}