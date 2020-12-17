package com.abhi.electiondata.model
import java.io.Serializable

data class VoterId(
    val SerialNo: String,
    val Name: String,
    val Guardian: String,
    val HouseNo: String,
    val HouseName: String,
    val GenderAge: String,
    val IDCardNo: String,
    val BOOTH: String,
): Serializable