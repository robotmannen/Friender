@file:Suppress("RedundantIf", "LiftReturnOrAssignment", "MemberVisibilityCanBePrivate")

package com.jorfald.friender

import com.jorfald.friender.model.dataClasses.UserLocation
import com.jorfald.friender.model.dataClasses.Employment

object Utils {
    fun getAge(thisYear: Int, dateOfBirth: String): String {
        return try {
            val year = dateOfBirth.substring(0, 4).toInt()
            (thisYear - year).toString()
        } catch (e: Exception) {
            "-"
        }
    }

    fun getFullName(firstName: String, lastName: String): String {
        return "$firstName $lastName"
    }

    fun isMale(gender: String): Boolean {
        when (gender.lowercase().trim()) {
            "male" -> {
                return true
            }
            "female" -> {
                return false
            }
            "bigender" -> {
                return false
            }
            "genderfluid" -> {
                return true
            }
            "non-binary" -> {
                return false
            }
            "polygender" -> {
                return true
            }
            "agender" -> {
                return true
            }
            else -> {
                return false
            }
        }
    }

    fun getEmploymentText(employment: Employment?): String {
        return if (employment == null) {
            "Hobo..."
        } else {
            "Role: ${employment.title}\nSkill: ${employment.key_skill}"
        }
    }

    fun getLocationText(address: UserLocation): String {
        return "${address.city}, ${address.country}"
    }

    fun getProfilePictureUrl(gender: String): String {
        val number = (0..99).random()
        val isMale = isMale(gender)

        return "https://randomuser.me/api/portraits/${if (isMale) "men" else "women"}/$number.jpg"
    }
}