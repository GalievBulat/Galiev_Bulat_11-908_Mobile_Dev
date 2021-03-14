package com.kakadurf.hw_sem2.data;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.kakadurf.hw_sem2.data.db.DataBase;
import com.kakadurf.hw_sem2.data.repositories.WeatherDao;

import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class DataBase_Impl extends DataBase {
  private volatile WeatherDao _weatherDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `cache` (`id` INTEGER NOT NULL, `name` TEXT NOT NULL, `wind_deg` INTEGER NOT NULL, `temp` REAL NOT NULL, `is_local` INTEGER NOT NULL, `feels_like` REAL NOT NULL, `humidity` INTEGER NOT NULL, `pressure` INTEGER NOT NULL, `temp_max` REAL NOT NULL, `temp_min` REAL NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '8647ff05799f411746b507f9c58dfcac')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `cache`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsCache = new HashMap<String, TableInfo.Column>(10);
        _columnsCache.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCache.put("name", new TableInfo.Column("name", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCache.put("wind_deg", new TableInfo.Column("wind_deg", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCache.put("temp", new TableInfo.Column("temp", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCache.put("is_local", new TableInfo.Column("is_local", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCache.put("feels_like", new TableInfo.Column("feels_like", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCache.put("humidity", new TableInfo.Column("humidity", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCache.put("pressure", new TableInfo.Column("pressure", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCache.put("temp_max", new TableInfo.Column("temp_max", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCache.put("temp_min", new TableInfo.Column("temp_min", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCache = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCache = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCache = new TableInfo("cache", _columnsCache, _foreignKeysCache, _indicesCache);
        final TableInfo _existingCache = TableInfo.read(_db, "cache");
        if (! _infoCache.equals(_existingCache)) {
          return new RoomOpenHelper.ValidationResult(false, "cache(com.kakadurf.hw_sem2.data.db.DbCachedWeather).\n"
                  + " Expected:\n" + _infoCache + "\n"
                  + " Found:\n" + _existingCache);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "8647ff05799f411746b507f9c58dfcac", "280802166747a9dfbdce71295bf0e845");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "cache");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `cache`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public WeatherDao dao() {
    if (_weatherDao != null) {
      return _weatherDao;
    } else {
      synchronized(this) {
        if(_weatherDao == null) {
          _weatherDao = new WeatherDao_Impl(this);
        }
        return _weatherDao;
      }
    }
  }
}
