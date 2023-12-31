package net.gamal.chefaatask.core.android.helpers.validation

import net.gamal.chefaatask.R

object ChefeaValidator {

    //------------------------------------------- Validation Values & Methods ----------------------
    private fun isFieldRequired(field: String?) = field.isNullOrEmpty()


    private fun isFieldEqualZero(input: Float) = input == 0f


    fun validateWidthAndHeightInput(
        width: String, height: String, produceValidation: (InputFieldError) -> Unit,
    ): Boolean = when {

        isFieldRequired(width) -> {
            produceValidation(InputFieldError(InputFiledType.WIDTH, R.string.required))
            false
        }

        isFieldEqualZero(width.toFloat()) -> {
            produceValidation(InputFieldError(InputFiledType.WIDTH, R.string.required))
            false
        }

        isFieldRequired(height) -> {
            produceValidation(InputFieldError(InputFiledType.HEIGHT, R.string.required))
            false
        }

        isFieldEqualZero(height.toFloat()) -> {
            produceValidation(InputFieldError(InputFiledType.HEIGHT, R.string.required))
            false
        }

        else -> {
            true
        }
    }

}