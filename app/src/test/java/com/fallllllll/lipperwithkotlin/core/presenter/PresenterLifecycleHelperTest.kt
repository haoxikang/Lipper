package com.fallllllll.lipperwithkotlin.core.presenter

import com.fallllllll.lipperwithkotlin.utils.mock
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

/**
 * Created by fallllllll on 2017/6/20/020.
 * GitHub :  https://github.com/348476129/Lipper
 */
class PresenterLifecycleHelperTest {

    @Rule
    @JvmField
     var mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    lateinit var presenter: Contract.Presenter

    @Test
    fun testPresenterLifecycleHelperTest() {
        val presenterLifecycleHelper = PresenterLifecycleHelper()
        presenterLifecycleHelper.addPresenter(presenter)
        presenterLifecycleHelper.onPresenterCreate()
        presenterLifecycleHelper.destroyPresenter()
        verify(presenter).attach()
        verify(presenter).onPresenterCreate()
        verify(presenter).detach()
    }
}