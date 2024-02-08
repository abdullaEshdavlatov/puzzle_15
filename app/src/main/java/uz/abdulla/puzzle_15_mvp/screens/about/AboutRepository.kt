package uz.abdulla.puzzle_15_mvp.screens.about

import android.content.Context
import uz.abdulla.puzzle_15_mvp.R
import uz.abdulla.puzzle_15_mvp.model.About

class AboutRepository(private val context: Context) : AboutContract.Repository{
    override fun getData(): About {
        val pageInfo = context.packageManager.getPackageInfo(context.packageName,0)
        return About(pageInfo.versionName,context.getString(R.string.app_info))
    }
}