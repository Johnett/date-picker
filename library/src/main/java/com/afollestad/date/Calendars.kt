/**
 * Designed and developed by Aidan Follestad (@afollestad)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.afollestad.date

import androidx.annotation.CheckResult
import com.afollestad.date.data.DayOfWeek
import com.afollestad.date.data.asDayOfWeek
import java.util.Calendar

/** @author Aidan Follestad (@afollestad) */
var Calendar.year: Int
  get() = get(Calendar.YEAR)
  set(value) {
    set(Calendar.YEAR, value)
  }

/** @author Aidan Follestad (@afollestad) */
var Calendar.month: Int
  get() = get(Calendar.MONTH)
  set(value) {
    set(Calendar.MONTH, value)
  }

/** @author Aidan Follestad (@afollestad) */
var Calendar.dayOfMonth: Int
  get() = get(Calendar.DAY_OF_MONTH)
  set(value) {
    set(Calendar.DAY_OF_MONTH, value)
  }

/** @author Aidan Follestad (@afollestad) */
val Calendar.totalDaysInMonth: Int
  get() = getActualMaximum(Calendar.DAY_OF_MONTH)

/** @author Aidan Follestad (@afollestad) */
@CheckResult internal fun Calendar.incrementMonth(): Calendar {
  return (clone() as Calendar).apply {
    add(Calendar.MONTH, 1)
    set(Calendar.DAY_OF_MONTH, 1)
  }
}

/** @author Aidan Follestad (@afollestad) */
@CheckResult internal fun Calendar.decrementMonth(): Calendar {
  return (clone() as Calendar).apply {
    add(Calendar.MONTH, -1)
    set(Calendar.DAY_OF_MONTH, totalDaysInMonth)
  }
}

/** @author Aidan Follestad (@afollestad) */
internal var Calendar.dayOfWeek: DayOfWeek
  get() = get(Calendar.DAY_OF_WEEK).asDayOfWeek()
  set(value) = set(Calendar.DAY_OF_WEEK, value.rawValue)
