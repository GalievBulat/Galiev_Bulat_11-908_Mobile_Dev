package com.kakadurf.hw_sem2.data;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;

import com.kakadurf.hw_sem2.data.db.DbCachedWeather;
import com.kakadurf.hw_sem2.data.repositories.WeatherDao;

import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class WeatherDao_Impl implements WeatherDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DbCachedWeather> __insertionAdapterOfDbCachedWeather;

  private final SharedSQLiteStatement __preparedStmtOfClearLocalSpots;

  public WeatherDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDbCachedWeather = new EntityInsertionAdapter<DbCachedWeather>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `cache` (`id`,`name`,`wind_deg`,`temp`,`is_local`,`feels_like`,`humidity`,`pressure`,`temp_max`,`temp_min`) VALUES (?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, DbCachedWeather value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        stmt.bindLong(3, value.getWindDeg());
        stmt.bindDouble(4, value.getTemp());
        final int _tmp;
        _tmp = value.isLocal() ? 1 : 0;
        stmt.bindLong(5, _tmp);
        stmt.bindDouble(6, value.getFeelsLike());
        stmt.bindLong(7, value.getHumidity());
        stmt.bindLong(8, value.getPressure());
        stmt.bindDouble(9, value.getTempMax());
        stmt.bindDouble(10, value.getTempMin());
      }
    };
    this.__preparedStmtOfClearLocalSpots = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM cache WHERE is_local = 1";
        return _query;
      }
    };
  }

  @Override
  public void insertWeather(final DbCachedWeather... cachedWeather) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfDbCachedWeather.insert(cachedWeather);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateLocalSpots(final List<DbCachedWeather> list) {
    __db.beginTransaction();
    try {
      WeatherDao.DefaultImpls.updateLocalSpots(WeatherDao_Impl.this, list);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void clearLocalSpots() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfClearLocalSpots.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfClearLocalSpots.release(_stmt);
    }
  }

  @Override
  public List<DbCachedWeather> getSavedLocalSpots() {
    final String _sql = "SELECT * FROM cache WHERE is_local = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfWindDeg = CursorUtil.getColumnIndexOrThrow(_cursor, "wind_deg");
      final int _cursorIndexOfTemp = CursorUtil.getColumnIndexOrThrow(_cursor, "temp");
      final int _cursorIndexOfIsLocal = CursorUtil.getColumnIndexOrThrow(_cursor, "is_local");
      final int _cursorIndexOfFeelsLike = CursorUtil.getColumnIndexOrThrow(_cursor, "feels_like");
      final int _cursorIndexOfHumidity = CursorUtil.getColumnIndexOrThrow(_cursor, "humidity");
      final int _cursorIndexOfPressure = CursorUtil.getColumnIndexOrThrow(_cursor, "pressure");
      final int _cursorIndexOfTempMax = CursorUtil.getColumnIndexOrThrow(_cursor, "temp_max");
      final int _cursorIndexOfTempMin = CursorUtil.getColumnIndexOrThrow(_cursor, "temp_min");
      final List<DbCachedWeather> _result = new ArrayList<DbCachedWeather>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final DbCachedWeather _item;
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final int _tmpWindDeg;
        _tmpWindDeg = _cursor.getInt(_cursorIndexOfWindDeg);
        final double _tmpTemp;
        _tmpTemp = _cursor.getDouble(_cursorIndexOfTemp);
        final boolean _tmpIsLocal;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsLocal);
        _tmpIsLocal = _tmp != 0;
        final double _tmpFeelsLike;
        _tmpFeelsLike = _cursor.getDouble(_cursorIndexOfFeelsLike);
        final int _tmpHumidity;
        _tmpHumidity = _cursor.getInt(_cursorIndexOfHumidity);
        final int _tmpPressure;
        _tmpPressure = _cursor.getInt(_cursorIndexOfPressure);
        final double _tmpTempMax;
        _tmpTempMax = _cursor.getDouble(_cursorIndexOfTempMax);
        final double _tmpTempMin;
        _tmpTempMin = _cursor.getDouble(_cursorIndexOfTempMin);
        _item = new DbCachedWeather(_tmpId,_tmpName,_tmpWindDeg,_tmpTemp,_tmpIsLocal,_tmpFeelsLike,_tmpHumidity,_tmpPressure,_tmpTempMax,_tmpTempMin);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public DbCachedWeather getSavedSpotByName(final String name) {
    final String _sql = "SELECT * FROM cache WHERE name = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (name == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, name);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfWindDeg = CursorUtil.getColumnIndexOrThrow(_cursor, "wind_deg");
      final int _cursorIndexOfTemp = CursorUtil.getColumnIndexOrThrow(_cursor, "temp");
      final int _cursorIndexOfIsLocal = CursorUtil.getColumnIndexOrThrow(_cursor, "is_local");
      final int _cursorIndexOfFeelsLike = CursorUtil.getColumnIndexOrThrow(_cursor, "feels_like");
      final int _cursorIndexOfHumidity = CursorUtil.getColumnIndexOrThrow(_cursor, "humidity");
      final int _cursorIndexOfPressure = CursorUtil.getColumnIndexOrThrow(_cursor, "pressure");
      final int _cursorIndexOfTempMax = CursorUtil.getColumnIndexOrThrow(_cursor, "temp_max");
      final int _cursorIndexOfTempMin = CursorUtil.getColumnIndexOrThrow(_cursor, "temp_min");
      final DbCachedWeather _result;
      if(_cursor.moveToFirst()) {
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final int _tmpWindDeg;
        _tmpWindDeg = _cursor.getInt(_cursorIndexOfWindDeg);
        final double _tmpTemp;
        _tmpTemp = _cursor.getDouble(_cursorIndexOfTemp);
        final boolean _tmpIsLocal;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsLocal);
        _tmpIsLocal = _tmp != 0;
        final double _tmpFeelsLike;
        _tmpFeelsLike = _cursor.getDouble(_cursorIndexOfFeelsLike);
        final int _tmpHumidity;
        _tmpHumidity = _cursor.getInt(_cursorIndexOfHumidity);
        final int _tmpPressure;
        _tmpPressure = _cursor.getInt(_cursorIndexOfPressure);
        final double _tmpTempMax;
        _tmpTempMax = _cursor.getDouble(_cursorIndexOfTempMax);
        final double _tmpTempMin;
        _tmpTempMin = _cursor.getDouble(_cursorIndexOfTempMin);
        _result = new DbCachedWeather(_tmpId,_tmpName,_tmpWindDeg,_tmpTemp,_tmpIsLocal,_tmpFeelsLike,_tmpHumidity,_tmpPressure,_tmpTempMax,_tmpTempMin);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public DbCachedWeather getSavedSpotById(final int id) {
    final String _sql = "SELECT * FROM cache WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfWindDeg = CursorUtil.getColumnIndexOrThrow(_cursor, "wind_deg");
      final int _cursorIndexOfTemp = CursorUtil.getColumnIndexOrThrow(_cursor, "temp");
      final int _cursorIndexOfIsLocal = CursorUtil.getColumnIndexOrThrow(_cursor, "is_local");
      final int _cursorIndexOfFeelsLike = CursorUtil.getColumnIndexOrThrow(_cursor, "feels_like");
      final int _cursorIndexOfHumidity = CursorUtil.getColumnIndexOrThrow(_cursor, "humidity");
      final int _cursorIndexOfPressure = CursorUtil.getColumnIndexOrThrow(_cursor, "pressure");
      final int _cursorIndexOfTempMax = CursorUtil.getColumnIndexOrThrow(_cursor, "temp_max");
      final int _cursorIndexOfTempMin = CursorUtil.getColumnIndexOrThrow(_cursor, "temp_min");
      final DbCachedWeather _result;
      if(_cursor.moveToFirst()) {
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final int _tmpWindDeg;
        _tmpWindDeg = _cursor.getInt(_cursorIndexOfWindDeg);
        final double _tmpTemp;
        _tmpTemp = _cursor.getDouble(_cursorIndexOfTemp);
        final boolean _tmpIsLocal;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsLocal);
        _tmpIsLocal = _tmp != 0;
        final double _tmpFeelsLike;
        _tmpFeelsLike = _cursor.getDouble(_cursorIndexOfFeelsLike);
        final int _tmpHumidity;
        _tmpHumidity = _cursor.getInt(_cursorIndexOfHumidity);
        final int _tmpPressure;
        _tmpPressure = _cursor.getInt(_cursorIndexOfPressure);
        final double _tmpTempMax;
        _tmpTempMax = _cursor.getDouble(_cursorIndexOfTempMax);
        final double _tmpTempMin;
        _tmpTempMin = _cursor.getDouble(_cursorIndexOfTempMin);
        _result = new DbCachedWeather(_tmpId,_tmpName,_tmpWindDeg,_tmpTemp,_tmpIsLocal,_tmpFeelsLike,_tmpHumidity,_tmpPressure,_tmpTempMax,_tmpTempMin);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
