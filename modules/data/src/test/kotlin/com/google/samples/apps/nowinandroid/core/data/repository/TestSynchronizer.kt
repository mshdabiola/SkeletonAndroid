/*
 *abiola 2024
 */

package com.google.samples.apps.nowinandroid.core.data.repository

import com.google.samples.apps.nowinandroid.core.datastore.ChangeListVersions
import com.google.samples.apps.nowinandroid.core.datastore.NiaPreferencesDataSource
import com.mshdabiola.data.Synchronizer

/**
 * Test synchronizer that delegates to [NiaPreferencesDataSource]
 */
class TestSynchronizer(
    private val niaPreferences: NiaPreferencesDataSource,
) : Synchronizer {
    override suspend fun getChangeListVersions(): ChangeListVersions =
        niaPreferences.getChangeListVersions()

    override suspend fun updateChangeListVersions(
        update: ChangeListVersions.() -> ChangeListVersions,
    ) = niaPreferences.updateChangeListVersion(update)
}
