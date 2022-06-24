package com.ardakaplan.aob_demo

import com.ardakaplan.aob_demo.aobobjects.Company
import com.ardakaplan.aob_demo.aobobjects.EnvironmentType
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import java.io.Serializable


/**
 * Created by Arda Kaplan at 24.06.2022 - 15:05
 *
 * ardakaplan101@gmail.com
 */
class AobGeniiMessagingObject(
    val id: Int,
    val userId: String,
    val uuid: String,
    val company: Company,
    val environmentType: EnvironmentType,
    var type: Type,
    val trigger: Trigger
) : Serializable {


    enum class Type(id: Int) {
        HANDSHAKE(0),
        STAND_UP(1),  //ayağa kaldırma
        OPEN_WITHOUT_ORDER(2),  //siparişsiz açma
        GENII_TO_AOB_TEST(3),
    }

    enum class Trigger(
        name: String
    ) {
        AOB("AOB"),
        GENII("GENII")
    }

    override fun toString(): String {

        val gson = GsonBuilder().setPrettyPrinting().create()

        return gson.toJson(this)
    }
}