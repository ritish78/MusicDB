package com.company.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Datasource {
    public static final String DB_NAME = "music.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\adria\\IdeaProjects\\MusicDB\\" + DB_NAME;
    //For albums table
    public static final String TABLE_ALBUMS = "albums";
    public static final String COLUMN_ALBUM_ID = "_id";
    public static final String COLUMN_ALBUM_NAME = "name";
    public static final String COLUMN_ALBUM_ARTIST = "artist";
    //For artist table
    public static final String TABLE_ARTISTS = "artists";
    public static final String COLUMN_ARTIST_ID = "_id";
    public static final String COLUMN_ARTIST_NAME = "name";
    //For songs table
    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_SONGS_ID = "_id";
    public static final String COLUMN_SONGS_TRACK = "track";
    public static final String COLUMN_SONGS_TITLE = "title";
    public static final String COLUMN_SONGS_ALBUMID = "album";

    //Connection
    private Connection conn;

    public boolean open(){
        try{
            conn = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        }catch(SQLException e){
            System.out.println("Couldn't connect to the database: "+e.getMessage());
            return false;
        }
    }
    public void close(){
        try{
            if (conn !=null){
                conn.close();
            }
        }catch(SQLException e){
            System.out.println("Couldn't close connection: "+e.getMessage());
        }
    }



    //Code to return list of Artists from the database
    public List<Artists> queryArtist(){
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            //First, creating connection
            statement = conn.createStatement();
            //Getting the set of results from the artists table
            resultSet = statement.executeQuery("SELECT * FROM "+TABLE_ARTISTS);

            List<Artists> artistsList = new ArrayList<>();

            while(resultSet.next()){
                Artists artists = new Artists();
                artists.setId(resultSet.getInt(COLUMN_ARTIST_ID));
                artists.setName(resultSet.getString(COLUMN_ARTIST_NAME));
                artistsList.add(artists);
            }
            //Returning the List which contains artists if we don't face any exception
            return artistsList;
        }catch(SQLException e){
            System.out.println("Query Failed: "+e.getMessage());
            //Returning null since getting the SQLException means we don't have any data.
            return null;
        }finally{
            //Now closing the resultSet
            try{
                if(resultSet != null){
                    resultSet.close();
                }
            }catch (SQLException e){
                System.out.println("No data in the database!");
            }
            //Now closing the statement
            try{
                if(statement != null){
                    statement.close();
                }
            }catch(SQLException e){
                System.out.println("Couldn't execute any command!");
            }
        }
    }


    //Code to return List of Albums
    public List<Albums> queryAlbums(){
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            //First, creating connection
            statement = conn.createStatement();
            //Getting the set of results from the albums table
            resultSet = statement.executeQuery("SELECT * FROM "+TABLE_ALBUMS);

            ArrayList<Albums> albumsList = new ArrayList<>();

            while (resultSet.next()){
                Albums albums = new Albums();
                albums.setId(resultSet.getInt(COLUMN_ALBUM_ID));
                albums.setName(resultSet.getString(COLUMN_ALBUM_NAME));
                albums.setArtist(resultSet.getInt(COLUMN_ALBUM_ARTIST));
                albumsList.add(albums);
            }
            //Returning the List which contains artists if we don't face any exception
            return albumsList;
        }catch(SQLException e){
            System.out.println("Query Failed: "+e.getMessage());
            //Returning null since getting the SQLException means we don't have any data.
            return null;
        }finally {
            //Now closing the resultSet
            try{
                if (resultSet != null){
                    resultSet.close();
                }
            }catch(SQLException e){
                System.out.println("No results!");
            }
            //Now closing the statement
            try{
                if (statement != null){
                    statement.close();
                }
            }catch (SQLException e){
                System.out.println("No data source!");
            }
        }
    }


    //Code to return Songs
    public List<Songs> querySongs(){
        Statement statement = null;
        ResultSet resultSet = null;

        ArrayList<Songs> songsList = new ArrayList<>();

        try{
            //First, creating connection
            statement = conn.createStatement();
            //Getting the set of results from the songs table
            resultSet = statement.executeQuery("SELECT * FROM "+ TABLE_SONGS);

            while (resultSet.next()){
                Songs songs = new Songs();
                songs.setId(resultSet.getInt(COLUMN_SONGS_ID));
                songs.setTrack(resultSet.getInt(COLUMN_SONGS_TRACK));
                songs.setTitle(resultSet.getString(COLUMN_SONGS_TITLE));
                songs.setAlbumId(resultSet.getInt(COLUMN_SONGS_ALBUMID));
                songsList.add(songs);
            }
            //Returning the List which contains artists if we don't face any exception
            return songsList;

        }catch (SQLException e){
            System.out.println("Connection lost!");
            return null;
        }finally {
            //Now closing the resultSet
            try{
                if (resultSet != null){
                    resultSet.close();
                }
            }catch (SQLException e){
                System.out.println("No data in the database!");
            }
            //Now closing the Statement
            try{
                if (statement != null){
                    statement.close();
                }
            }catch (SQLException e){
                System.out.println("Query couln't be performed!");
            }
        }
    }
}
