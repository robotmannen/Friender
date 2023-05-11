package com.jorfald.friender

import android.print.PrintJob
import com.jorfald.friender.model.dataClasses.Employment
import com.jorfald.friender.model.dataClasses.UserLocation
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test
import java.lang.Exception
import java.util.*

class UtilsTest {

    @Test
    fun getAge_returnsAgeInNumber() {
        val ageInYears = Utils.getAge(Calendar.getInstance().get(Calendar.YEAR), "1999-05-19")
        assertEquals("23", ageInYears)

    }

    @Test
    fun getAge_handlesException() {
        val ageInYears = Utils.getAge(Calendar.getInstance().get(Calendar.YEAR), "Nittende mai nittennittini")
        assertEquals("-", ageInYears)

    }

    @Test
    fun getFullName() {
        val fullName = Utils.getFullName("Thomas", "Korsnes")
        assertEquals("Thomas Korsnes", fullName)
    }

    @Test
    fun isMale_returnsTrueWhenMale() {
        assertEquals(true, Utils.isMale("Male"))
    }

    @Test
    fun isMale_returnsFalseWhenFemale() {
        assertEquals(false, Utils.isMale("Female"))
    }

    @Test
    fun isMale_returnsFalseWhenBigender() {
        assertEquals(false, Utils.isMale("Bigender"))
    }
    @Test
    fun isMale_returnsFalseWhenGenderfluid() {
        assertEquals(true, Utils.isMale("genderfluid"))
    }
    @Test
    fun isMale_returnsFalseWhenNonbinary() {
        assertEquals(false, Utils.isMale("non-binary"))
    }
    @Test
    fun isMale_returnsTrueWhenPolygender() {
        assertEquals(true, Utils.isMale("polygender"))
    }
    @Test
    fun isMale_returnsTrueWhenAgender() {
        assertEquals(true, Utils.isMale("agender"))
    }
    @Test
    fun isMale_returnsFalseWhenElse() {
        assertEquals(false, Utils.isMale("dog"))
    }
    @Test
    fun getEmploymentText_returnsTitleAndKeySkill() {
        val employment = Employment("Dog Groomer", "Holding the Leash")

        assertEquals(
            "Role: ${employment.title}\nSkill: ${employment.key_skill}",
            Utils.getEmploymentText(employment)
        )
    }

    @Test
    fun getEmploymentText_handlesNull() {
        assertEquals("Hobo...", Utils.getEmploymentText(null))
    }

    @Test
    fun getLocationText() {
        val userLocation = UserLocation("Oslo", "Norway")

        assertEquals("Oslo, Norway", Utils.getLocationText(userLocation))
    }

    @Test
    fun getProfilePictureUrl_returnsMaleUrlWithoutRandomNumber() {
        assertEquals(
            "https://randomuser.me/api/portraits/men",
            Utils.getProfilePictureUrl("male").substring(0, 39)
        )
    }

    @Test
    fun getProfilePictureUrl_returnsFemaleUrlWithoutRandomNumber() {
        assertEquals(
            "https://randomuser.me/api/portraits/women",
            Utils.getProfilePictureUrl("female").substring(0, 41)
        )
    }
}