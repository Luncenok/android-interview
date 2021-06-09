package com.goodylabs.android.interview.util

import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.goodylabs.android.interview.MainCoroutineRule
import com.goodylabs.android.interview.ui.characterdetails.CharacterDetailsFragmentArgs
import com.goodylabs.android.interview.utilities.testCharacter
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain

@HiltAndroidTest
@ExperimentalCoroutinesApi
class ArgsViewModelTest {
    private lateinit var viewModel: ArgsViewModel
    private val hiltRule = HiltAndroidRule(this)
    private val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val coroutineRule = MainCoroutineRule()

    @get:Rule
    val rule = RuleChain
        .outerRule(hiltRule)
        .around(instantTaskExecutorRule)
        .around(coroutineRule)

    @Before
    fun setUp() {
        hiltRule.inject()

        val savedStateHandle: SavedStateHandle = SavedStateHandle().apply {
            set("args", Bundle().apply { putInt("characterKey", testCharacter.id) })
        }
        viewModel = ArgsViewModel(savedStateHandle)
    }

    @Test
    fun gerArguments() {
        val args: CharacterDetailsFragmentArgs by viewModel.navArgs()
        assertEquals(1, args.characterKey)
    }
}