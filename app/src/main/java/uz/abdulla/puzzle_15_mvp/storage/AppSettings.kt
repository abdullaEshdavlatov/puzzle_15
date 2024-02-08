package uz.abdulla.puzzle_15_mvp.storage

import android.content.Context
import android.content.SharedPreferences

object AppSettings {

    private const val NAME = "puzzle15"
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences

    fun init(context: Context){
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit){
        val editor = edit()
        operation(editor)
        editor.apply()
    }

    var sound: Boolean?
        get() =  preferences.getBoolean("sound",true)
        set(value) = preferences.edit {
            if (value != null){
                it.putBoolean("sound",value)
            }
        }
    var music: Boolean?
        get() =  preferences.getBoolean("music",true)
        set(value) = preferences.edit {
            if (value != null){
                it.putBoolean("music",value)
            }
        }

}