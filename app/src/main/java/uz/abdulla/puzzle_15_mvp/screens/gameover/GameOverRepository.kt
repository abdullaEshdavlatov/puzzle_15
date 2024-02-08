package uz.abdulla.puzzle_15_mvp.screens.gameover

import android.content.Context
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position

import nl.dionsegijn.konfetti.core.emitter.Emitter
import uz.abdulla.puzzle_15_mvp.database.HighScoreDatabase
import java.util.concurrent.TimeUnit

class GameOverRepository(private val context:Context): GameOverContract.Repository {
    override fun getDatabaseInstance(): HighScoreDatabase {
        return HighScoreDatabase.getDatabaseInstance(context)
    }

    override fun getAnimation(): Party {
        return Party(
            speed = 0f,
            maxSpeed = 30f,
            damping = 0.9f,
            spread = 360,
            colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
            emitter = Emitter(duration = 100, TimeUnit.MILLISECONDS).max(100),
            position = Position.Relative(0.5, 0.3)
        )
    }
}