package com.example.fitpeotasksample.viewmodel

import android.os.Looper
import com.example.fitpeotasksample.api.FakeApiService
import com.example.fitpeotasksample.data.network.ApiRepository
import com.example.fitpeotasksample.ui.home.model.UIState
import com.example.fitpeotasksample.ui.home.viewmodel.HomeViewModel
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import junit.framework.Assert.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.robolectric.annotation.LooperMode
import javax.inject.Inject

@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [25], application = HiltTestApplication::class)
@ExperimentalCoroutinesApi
@LooperMode(LooperMode.Mode.PAUSED)
class HomeViewModelTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var apiRepository: ApiRepository

    @BindValue
    @JvmField
    val fakeApiService: FakeApiService = FakeApiService()

    @Mock
    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setup() {
        hiltRule.inject()
        homeViewModel = HomeViewModel(apiRepository)
    }

    @Test
    fun `test User data success`() = runBlockingTest {
        homeViewModel.loadUserData()
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        val value = homeViewModel.userFlow.value
        assertTrue(value is UIState.Success)
        assertNotNull(value.data)
        assertEquals(1, value.data?.get(0)?.id)
        assertEquals("accusamus beatae ad facilis cum similique qui sunt", value.data?.get(0)?.title)
    }

    @Test
    fun `test User data api failure`() = runBlockingTest {
        fakeApiService.failUserApi = true
        homeViewModel.loadUserData()
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        val value = homeViewModel.userFlow.value
        assertTrue(value is UIState.Error)
        assertNull(value.data)
    }

    @Test
    fun `test User wrong data`() = runBlockingTest {
        fakeApiService.wrongResponse = true
        homeViewModel.loadUserData()
        Shadows.shadowOf(Looper.getMainLooper()).idle()
        val value = homeViewModel.userFlow.value
        assertTrue(value is UIState.Success)
        assertNotNull(value.data)
        assertEquals("", value.data?.get(0)?.title)
    }

}