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
package com.afollestad.date.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.InsetDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.afollestad.date.R
import com.afollestad.date.util.getFloat

/** @author Aidan Follestad (@afollestad) */
internal class DayOfMonthTextView(
  context: Context,
  attrs: AttributeSet?
) : AppCompatTextView(context, attrs) {
  private val ratio: Float = context.getFloat(R.dimen.day_of_month_height_ratio)
  private var originalBackground: Drawable? = null
  private var lastInsetPadding: Int? = null

  var skipInset: Boolean = false
    set(value) {
      field = value
      if (!value) lastInsetPadding = null
      invalidateBackground()
    }

  override fun onMeasure(
    widthMeasureSpec: Int,
    heightMeasureSpec: Int
  ) {
    val width = MeasureSpec.getSize(widthMeasureSpec)
    val height = (width * ratio).toInt()
    setMeasuredDimension(width, height)
    invalidateBackground()
  }

  override fun setBackground(background: Drawable?) {
    originalBackground = background
    super.setBackground(originalBackground)
    invalidateBackground()
  }

  private fun invalidateBackground() {
    if (skipInset || measuredWidth == 0 || measuredWidth == measuredHeight) return
    val currentBg: Drawable = originalBackground ?: return
    lastInsetPadding = if (measuredWidth > measuredHeight) {
      val insetPadding = (measuredWidth - measuredHeight) / 2
      if (lastInsetPadding == insetPadding) return
      super.setBackground(InsetDrawable(currentBg, insetPadding, 0, insetPadding, 0))
      insetPadding
    } else {
      val insetPadding = (measuredHeight - measuredWidth) / 2
      if (lastInsetPadding == insetPadding) return
      super.setBackground(InsetDrawable(currentBg, 0, insetPadding, 0, insetPadding))
      insetPadding
    }
  }
}
