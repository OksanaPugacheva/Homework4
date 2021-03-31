package com.example.myapplication

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Checkable
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.core.content.res.use
import androidx.core.view.isVisible
import com.example.myapplication.databinding.AnotherListItemBinding

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class ListItem2View @JvmOverloads constructor(context: Context,
                                              attributeSet: AttributeSet? = null,
                                              defStyleAttr: Int = 0,
                                              defStyleRes: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr, defStyleRes), Checkable {

    private val binding = AnotherListItemBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
    )
    var leading: Drawable?
        get() = binding.anotherLeading.drawable
        set(value) {
            binding.anotherLeading.apply {
                isVisible = value != null
                setImageDrawable(value)
            }
        }
    var title: CharSequence? by binding.anotherTitle::text
    var subtitle: CharSequence? by binding.anotherSubtitle::text


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
        }


    }

    override fun setChecked(checked: Boolean) {
        TODO("Not yet implemented")
    }

    override fun isChecked(): Boolean {
        TODO("Not yet implemented")
    }

    override fun toggle() {
        TODO("Not yet implemented")
    }
}