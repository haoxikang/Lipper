package com.fallllllll.lipperwithkotlin.data.network.service

import com.fall.retrofitannotation.RetrofitService
import com.fallllllll.lipperwithkotlin.core.constants.BASE_URL
import com.fallllllll.lipperwithkotlin.core.constants.LOGIN_URL
import com.fallllllll.lipperwithkotlin.data.local.user.UserToken
import io.reactivex.Flowable
import okhttp3.RequestBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

/**
 * Created by fall on 2017/6/2/002.
 * GitHub :  https://github.com/348476129/LipperWithKotlin
 */
@RetrofitService(LOGIN_URL)
interface OauthService {
    @Multipart
    @POST("token")
     fun getToken(@Part("client_id") client_id: RequestBody, @Part("client_secret") client_secret: RequestBody, @Part("redirect_uri") redirect_uri: RequestBody, @Part("code") code: RequestBody): Flowable<UserToken>

}