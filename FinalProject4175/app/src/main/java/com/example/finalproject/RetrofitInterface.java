package com.example.finalproject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitInterface {
  // get all list
  @GET("eventlist")
  Call<List<Event>> getEvents();

  @GET("grouplist")
  Call<List<Groups>> getGroups();

  @GET("userlist")
  Call<List<User>> getUsers();

  @GET("groupuserlist")
  Call<List<Group_User>> getGroup_User();

  // create list
  @POST("eventlist")
  Call<Event>createPost(@Body Event event);

  @POST("grouplist")
  Call<Groups>createPost(@Body Groups groups);

  @POST("groupuserlist")
  Call<Group_User>createPost(@Body Group_User group_user);

  @POST("userlist")
  Call<User>createUser(@Body User user);

  // get specific data
  @GET("grouplist/{GroupName}")
  Call<List<Groups>> getAgroup(@Path("GroupName") String groupname);

  @GET("grouplist/{GroupName}")
  Call<Groups> getAgroup2(@Path("GroupName") String groupname);

  @GET("eventlist/byGroupName/{GroupName}")
  Call<List<Event>> getEventByGroup(@Path("GroupName") String groupname);

  @GET("userlist/{UserEmail}")
  Call<List<User>> getAuser(@Path("UserEmail") String useremail);

  @GET("groupuserlist/{UserEmail}")
  Call<List<Group_User>> getGroup_UserByEamil(@Path("UserEmail") String useremail);

  @GET("grouplist/byEmail/{UserEmail}")
  Call<List<Groups>> getGroupByEamil(@Path("UserEmail") String useremail);


  // update
  @PUT("grouplist/update/{GroupName}")
  Call<Groups> putGroup(@Path("GroupName") String groupName, @Body Groups groups);
  //*** update ***
  @PUT("groupuserlist/update/{GroupUserId}")
  Call<Group_User> putGroupUser(@Path("GroupUserId") String groupUserId, @Body Group_User group_user);

  @PUT("/userlist/update/{UserEmail}")
  Call<User> putUser(@Path("UserEmail") String UserEmail, @Body User user);

  // delete

  @DELETE("grouplist/delete/{GroupName}")
  Call<Void> deleteAGroup(@Path("GroupName") String groupName);

  @DELETE("groupuserlist/delete/{GroupName}")
  Call<Void> deleteGroupUser(@Path("GroupName") String groupName);

  @DELETE("groupuserlist/delete/{GroupUserId}")
  Call<Void> deleteGroupUserFromGroup(@Path("GroupUserId") int groupuserid);


}
