package com.example.myapplication;

import android.app.LauncherActivity
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.SoundEffectConstants
import android.widget.Checkable
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.use
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import com.example.myapplication.databinding.ListItemBinding

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
public class ListItemView @JvmOverloads constructor(context: Context,
                                                    attributeSet: AttributeSet? = null,
                                                    defStyleAttr: Int = 0,
                                                    defStyleRes: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr, defStyleRes), Checkable {

    private val heightOneLine = context.dpToPx(56)
    private val heightTwoLine = context.dpToPx(72)
    private val titleBaseLineOffset = context.dpToPx(32)

    private val binding = ListItemBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
    )
    var leading: Drawable?
        get() = binding.leading.drawable
        set(value) {
            binding.leading.apply {
                isVisible = value != null
                setImageDrawable(value)
            }
        }
    var title: CharSequence? by binding.title::text
    var subtitle: CharSequence?
        get() = binding.subtitle.text
        set(value) {
            binding.root.updateLayoutParams {
                height = if (value != null) heightTwoLine
                else heightOneLine
            }
            binding.title.apply {
                firstBaselineToTopHeight = if (value != null) {
                    titleBaseLineOffset
                } else 0
                updateLayoutParams<ConstraintLayout.LayoutParams> {
                    verticalBias = if (value != null) 0f else 0.5f
                }
            }
            binding.subtitle.apply {
                isVisible = value != null
                text = value
            }
        }
    var hasCheckBox by binding.checkBox::isVisible

    init {
        context.theme.obtainStyledAttributes(
                attributeSet,
                R.styleable.ListItemView,
                defStyleAttr,
                defStyleRes
        ).use {
            title = it.getString(R.styleable.ListItemView_title)
            subtitle = it.getString(R.styleable.ListItemView_subtitle)
            leading = it.getDrawable(R.styleable.ListItemView_leading)
            hasCheckBox = it.getBoolean(R.styleable.ListItemView_hasCheckbox, false)
        }

    }

    override fun setChecked(checked: Boolean) {
        binding.checkBox.isChecked = checked
    }

    override fun isChecked(): Boolean {
        return binding.checkBox.isChecked
    }

    override fun toggle() {
        binding.checkBox.toggle()
    }

    override fun performClick(): Boolean {
        return if (hasCheckBox) {
            toggle()
            val handled = super.performClick()
            if (!handled) {
                // View only makes a sound effect if the onClickListener was
                // called, so we'll need to make one here instead.
                playSoundEffect(SoundEffectConstants.CLICK)
            }
            handled
        } else {
            super.performClick()
        }
    }
}

fun Context.dpToPx(dp: Number) = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(),
        resources.displayMetrics
).toInt()