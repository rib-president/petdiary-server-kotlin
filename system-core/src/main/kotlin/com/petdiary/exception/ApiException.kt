package com.petdiary.exception

import com.petdiary.exception.enums.ApiExceptionEnum

class ApiException(val apiExceptionEnum: ApiExceptionEnum): RuntimeException(apiExceptionEnum.message)