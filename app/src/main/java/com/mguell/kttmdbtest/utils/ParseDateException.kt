package com.mguell.kttmdbtest.utils

import java.text.ParseException

internal class ParseDateException(s: String, errorOffset: Int) : ParseException(s, errorOffset)