package com.narendra.travelersbridge.Retrofit;

import com.narendra.travelersbridge.POJO.CityState;
import com.narendra.travelersbridge.POJO.Register;
import com.narendra.travelersbridge.POJO.SearchLocation;
import com.narendra.travelersbridge.POJO.Transection;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @FormUrlEncoded
    @POST("userRegister")
    Call<Register> RegisterUser(@Field("email") String emailId,
                                @Field("mobile") String contactNumber,
                                @Field("password") String Password
                                    );
    @FormUrlEncoded
    @POST("userLogin")
    Call<Register> LoginUser(@Field("email") String emailId,
                          @Field("password") String password
                              );
    @GET("location")
    Call<Register> FlightSearchLocation();

    @FormUrlEncoded
    @POST("searchflight")
    Call<SearchLocation> searchResult (@Field("user_id") String userId,
                                       @Field("source") String source,
                                       @Field("destination") String destination,
                                       @Field("fromdate") String fromdate,
                                       @Field("type") String flightType,
                                       @Field("mode") String trip_type,
                                       @Field("adults") String adults,
                                       @Field("childrens") String children,
                                       @Field("infants") String infants,
                                       @Field("cabinclass") String cabinclass,
                                       @Field("todate") String toDate
                                     );
    @FormUrlEncoded
    @POST("editProfile/{user_id}")
    Call<Register> editProfile(@Path("user_id") String userId,
                               @Field("name") String Name,
                               @Field("comp_name") String CompanyName,
                               @Field("state") String state,
                               @Field("city") String city,
                               @Field("pincode") String pinCode,
                               @Field("pan") String Pan,
                               @Field("address") String address
                               );
    @GET("admin/state")
    Call<CityState> findAllState();

    @GET("admin/city")
    Call<CityState> findAllCitys(@Query("state") String StateName);
    @FormUrlEncoded
    @POST("changePassword/{user_id}")
    Call<Register> changePassword(@Path("user_id") String userId,
                                  @Field("old_password") String oldPassword,
                                  @Field("new_password") String newpassword
                                  );
    @FormUrlEncoded
    @POST("contactUs")
    Call<Register> contactUs(@Field("type") String type,
                             @Field("name") String UserType,
                             @Field("subject")  String subject,
                             @Field("message")  String message
                             );
    @FormUrlEncoded
    @POST("transactionHistory")
    Call<Transection> transactionHistory(@Field("user_id") String userId,
                                         @Field("from_date") String fromDate,
                                         @Field("to_date") String toDate);

}
