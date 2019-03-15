package com.sulistyolabs.footballschedule.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DBOpenHelper (ctx: Context): ManagedSQLiteOpenHelper(ctx, "Favorites.db", null, 2 ) {

    companion object {

        private var instance: DBOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DBOpenHelper {
            if (instance == null) {
                instance = DBOpenHelper(ctx.applicationContext)
            }
            return instance as DBOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(FavoriteMatch.TABLE_FAVORITE, true,
                FavoriteMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteMatch.MATCH_ID to TEXT + UNIQUE,
                FavoriteMatch.JSON_DATA to TEXT)

        db?.createTable(FavoriteTeam.TABLE_FAVORITE, true,
                FavoriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteTeam.TEAM_ID to TEXT + UNIQUE,
                FavoriteTeam.JSON_DATA to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(FavoriteMatch.TABLE_FAVORITE,true)

    }
}

val  Context.database: DBOpenHelper
    get() = DBOpenHelper.getInstance(applicationContext)