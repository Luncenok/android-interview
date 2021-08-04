package com.goodylabs.android.interview.ui.characterdetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.goodylabs.android.interview.MainCoroutineRule
import com.goodylabs.android.interview.data.repositories.CharacterRepository
import com.goodylabs.android.interview.runBlockingTest
import com.goodylabs.android.interview.utilities.getValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import javax.inject.Inject

@HiltAndroidTest
@ExperimentalCoroutinesApi
class CharacterDetailsViewModelTest {
    private lateinit var viewModel: CharacterDetailsViewModel
    private val hiltRule = HiltAndroidRule(this)
    private val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val coroutineRule = MainCoroutineRule()

    @get:Rule
    val rule = RuleChain
        .outerRule(hiltRule)
        .around(instantTaskExecutorRule)
        .around(coroutineRule)

    @Inject
    lateinit var characterRepository: CharacterRepository

    @Before
    fun setUp() {
        hiltRule.inject()
        viewModel = CharacterDetailsViewModel(characterRepository)
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    @Test
    @Throws(InterruptedException::class)
    fun getCharacter() = coroutineRule.runBlockingTest {
        assertNull(getValue(viewModel.character))
    }
}
