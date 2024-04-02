package com.bbasoglu.swipefun.uimodule.toolbar

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.bbasoglu.swipefun.uimodule.R
import com.bbasoglu.swipefun.uimodule.databinding.StandardToolbarBinding
import com.bbasoglu.swipefun.uimodule.extensions.disableClickTemporarily
import com.bbasoglu.swipefun.uimodule.extensions.dp2px
import com.bbasoglu.swipefun.uimodule.extensions.setTextWithAnimation

class StandardCustomToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    val binding: StandardToolbarBinding =
        StandardToolbarBinding.inflate(LayoutInflater.from(context), this)

    fun setToolbar(toolbarData: StandardToolbarData?) {
        toolbarData?.let {
            setTitle(
                it.title,
                it.titleColorId,
                it.isTitleCenter,
                it.textAnimationEnabled,
                it.titleSize
            )
            setToolbarLeftIcon(
                it.leftIconResourceId,
                it.leftIconColorId,
                it.leftIconText,
                it.toolbarLeftIconClick
            )
            setToolbarRightIcon(
                it.rightIconResourceId,
                it.rightIconColorId,
                it.toolbarRightIconClick
            )
            visibility = VISIBLE
        } ?: run { visibility = GONE }
    }

    private fun setTitle(
        title: String?,
        titleColorId: Int?,
        isTitleCenter: Boolean,
        textAnimationEnabled: Boolean,
        titleSize: Float?
    ) {
        binding.toolbarTitleTV.gravity = if (isTitleCenter) {
            Gravity.CENTER
        } else {
            Gravity.START
        }

        setTitleText(title, textAnimationEnabled)
        setTitleColor(titleColorId)

        titleSize?.let {
            binding.toolbarTitleTV.textSize = it.dp2px.toFloat()
        }
    }

    private fun setTitleText(title: String?, textAnimationEnabled: Boolean) {
        title ?: return
        binding.apply {
            if (toolbarTitleTV.text != title) {
                if (textAnimationEnabled) {
                    toolbarTitleTV.setTextWithAnimation(title)
                } else {
                    toolbarTitleTV.text = title
                }
            }
            toolbarTitleTV.visibility = VISIBLE
        }
    }

    private fun setTitleColor(titleColorId: Int?) {
        binding.apply {
            titleColorId?.let {
                toolbarTitleTV.setTextColor(ContextCompat.getColor(context, titleColorId))
            } ?: let {
                toolbarTitleTV.setTextColor(
                    ContextCompat.getColor(
                        context,
                        R.color.textColorPrimary
                    )
                )
            }
        }
    }

    private fun setToolbarLeftIcon(
        resourceId: Int?,
        colorId: Int?,
        leftIconText: String?,
        toolbarIconClick: ToolbarIconClick?
    ) {
        binding.run {
            resourceId?.let {
                toolbarLeftIconIV.visibility = VISIBLE
                if (resourceId != DEFAULT_BUTTON) {
                    toolbarLeftIconIV.setImageResource(resourceId)
                } else {
                    toolbarLeftIconIV.setImageResource(R.drawable.back_button)
                }

                toolbarLeftIconIV.setOnClickListener {
                    toolbarLeftIconIV.disableClickTemporarily()
                    toolbarIconClick?.onClick()
                }
            } ?: run { toolbarLeftIconIV.visibility = GONE }

            colorId?.let {
                if (colorId != DEFAULT_BUTTON) {
                    toolbarLeftIconIV.setColorFilter(
                        ContextCompat.getColor(context, colorId),
                        android.graphics.PorterDuff.Mode.SRC_IN
                    )
                } else {
                    toolbarLeftIconIV.setColorFilter(
                        ContextCompat.getColor(
                            context,
                            R.color.colorOnPrimary
                        ),
                        android.graphics.PorterDuff.Mode.SRC_IN
                    )
                }
            } ?: run {
                toolbarRightIconIV.colorFilter = null
            }
            leftIconText?.let {
                leftText.visibility = VISIBLE
                leftText.text = it
                leftText.setOnClickListener {
                    leftText.disableClickTemporarily()
                    toolbarIconClick?.onClick()
                }
            } ?: run { leftText.visibility = GONE }
        }
    }

    private fun setToolbarRightIcon(
        resourceId: Int?,
        colorId: Int?,
        toolbarIconClick: ToolbarIconClick?
    ) {
        binding.run {
            resourceId?.let {
                toolbarRightIconIV.visibility = VISIBLE
                if (resourceId != DEFAULT_BUTTON) {
                    toolbarRightIconIV.setImageResource(resourceId)
                } else {
                    toolbarRightIconIV.setImageResource(R.drawable.ic_settings)
                }
                toolbarRightIconIV.setOnClickListener {
                    toolbarRightIconIV.disableClickTemporarily()
                    toolbarIconClick?.onClick()
                }
            } ?: run { toolbarRightIconIV.visibility = GONE }

            colorId?.let {
                if (colorId != DEFAULT_BUTTON) {
                    toolbarRightIconIV.setColorFilter(
                        ContextCompat.getColor(context, colorId),
                        android.graphics.PorterDuff.Mode.SRC_IN
                    )
                } else {
                    toolbarRightIconIV.setColorFilter(
                        ContextCompat.getColor(
                            context,
                            R.color.colorOnPrimary
                        ),
                        android.graphics.PorterDuff.Mode.SRC_IN
                    )
                }
            } ?: run {
                toolbarRightIconIV.colorFilter = null
            }
        }
    }

    fun clearCallbacks() {
        binding.run {
            toolbarLeftIconIV.setOnClickListener(null)
            toolbarRightIconIV.setOnClickListener(null)
        }
    }

    companion object {
        const val DEFAULT_BUTTON = -1
    }

    interface ToolbarIconClick {
        fun onClick()
    }
}
