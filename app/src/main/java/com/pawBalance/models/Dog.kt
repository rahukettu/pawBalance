package com.pawBalance.models

import java.time.LocalDate
import java.time.temporal.ChronoUnit

data class Dog(
    val name: String,
    val breed: String,
    val age: Int, // Age in years
    val weight: Float, // Weight in kg
    val isNeutered: Boolean,
    val isVaccinated: Boolean,
    val isVaccinatedUntil: LocalDate? = null,
    val microchipId: String? = null, // Optional
    val dateOfBirth: LocalDate? = null // Optional
) {
    // if dateOfBirth is provided
    val ageInMonths: Long?
        get() = dateOfBirth?.let { ChronoUnit.MONTHS.between(it, LocalDate.now()) }

    val fullDescription: String
        get() = "$name, a $age-year-old $breed, weighing $weight kg."
}