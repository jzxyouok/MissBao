package cn.missbao.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cn.missbao.bean.Word;
import cn.missbao.utils.LogUtil;

/**
 * Role
 * Created by 冀泽阳 on 2016/4/13.
 */
public class WordDao {

    private WordOpenHelper helper;


    public WordDao(Context context) {
        helper = new WordOpenHelper(context);
    }

    /** 添加一个单词 */
    public void addWord(String table,Word word){
        SQLiteDatabase sql = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("_id",word._id);
        values.put("word",word.word);
        values.put("soundmark",word.soundmark);
        values.put("translate",word.translate);
        sql.insert(table,null,values );
        sql.close();
    }

    /** 删除一个单词 */
    public void deleteWord(String table,Word word){
        SQLiteDatabase sql = helper.getWritableDatabase();
        sql.delete(table, "word=?", new String[]{word.word});
        sql.close();
    }

    /** 遍历单词 */
    public List<Word> queryAll(String table){
        SQLiteDatabase sql = helper.getReadableDatabase();
        Cursor cursor = sql.query(table, new String[]{"_id","word", "soundmark","translate"},
                null, null, null, null, null);
        List<Word> words = new ArrayList<Word>();
        while (cursor.moveToNext()){
            Word word = new Word();
            word._id = cursor.getString(0);
            word.word = cursor.getString(1);
            word.soundmark = cursor.getString(2);
            word.translate = cursor.getString(3);
            words.add(word);
        }
        cursor.close();
        sql.close();
        return words;
    }

    /** 查询一个单词 */
    public Word queryWord(String _id){
        SQLiteDatabase sql = helper.getReadableDatabase();
        Cursor cursor = sql.query("word", new String[]{"word", "soundmark", "translate"},
                "_id=?", new String[]{_id}, null, null, null);
        Word word = new Word();
        LogUtil.e("!!!!!!!!!!!!!");
        if (cursor != null && cursor.getCount() != 0){
            while (cursor.moveToNext()){
                LogUtil.e("!!!!!!!!!!!!!");
            word.word = cursor.getString(0);
            word.soundmark = cursor.getString(1);
            word.translate = cursor.getString(2);
            }
        }
        cursor.close();
        sql.close();
        return word;
    }

    /** 删除单词表 */
    public void deleteAll(Context context,String table){
        SQLiteDatabase sql = helper.getWritableDatabase();

        List<Word> words = new ArrayList<Word>();
        WordDao dao = new WordDao(context);
        words = dao.queryAll(table);
        for (int i=0;i<words.size();i++){
            Word word = new Word();
            word.word = words.get(i).word;
            sql.delete(table, "word=?", new String[]{word.word});
        }
        sql.close();
    }

    /** 添加一个单词集合 */
    public void addWordList(String table,List<Word> words){
        SQLiteDatabase sql = helper.getWritableDatabase();
        if (words.size() > 0){
            for (int i=0;i<words.size();i++){
                Word word = words.get(i);
                ContentValues values = new ContentValues();
                values.put("_id",word._id);
                values.put("word",word.word);
                values.put("soundmark",word.soundmark);
                values.put("translate", word.translate);
                sql.insert(table, null, values);
            }
        }
        sql.close();
    }
}
