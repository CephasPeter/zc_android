package com.zurichat.app.models


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

data class Room(
    @SerializedName("_id")
    @Expose
    val id: String = "", // 614b774544a9bd81cedc0cbb

    @SerializedName("bookmarks")
    @Expose
    val bookmarks: List<Any> = emptyList(),

    @SerializedName("created_at")
    @Expose
    val createdAt: String = LocalDateTime.now(ZoneOffset.UTC).toString(), // 2021-09-22T18:34:45.041328Z

    @SerializedName("org_id")
    @Expose
    val orgId: String = "", // 6145eee9285e4a18402074cd

    @SerializedName("pinned")
    @Expose
    val pinned: List<Any> = emptyList(),

    @SerializedName("private")
    @Expose
    val isPrivateRoom: Boolean = false,

    @SerializedName("room_name")
    @Expose
    val roomName: String = "",

    @SerializedName("room_user_ids")
    @Expose
    val roomUserIds: List<String> = emptyList()
){
    override fun equals(other: Any?) = ((other is Room ) && other.orgId == orgId && other.id == id)
    override fun hashCode() = Objects.hash(orgId, id)
}