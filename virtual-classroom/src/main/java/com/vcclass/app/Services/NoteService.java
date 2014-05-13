/*
 * Brief: This class will handle all actions relating to persisting the Note Object in the database
 * Author: Chris Forehand
 * Date Started: 2/2014
 * Test Cases:
 * Major Mods: None 
 * */

package com.vcclass.app.Services;

import java.sql.Date;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.PreparedStatement;
import com.vcclass.app.Data.Note;
import com.vcclass.app.Repository.NoteDAO;
import com.vcclass.app.RowMappers.NoteRowMapper;

@Service("noteService")
public class NoteService implements NoteDAO
{
	private DataSource dataSource; 
	private JdbcTemplate jdbc;
	
	@Override
	public void setDataSource(DataSource ds) 
	{
		this.dataSource = ds;
		this.jdbc = new JdbcTemplate(dataSource); 
	}
	
	@Override
	public List<Note> GetStudentNotes(int studentId) 
	{			
		String sql = "select * FROM Note WHERE User_Id = ?"; 
		List<Note> noteList = new ArrayList<Note>();
		
		Object [] parameters = new Object[]{new Integer(studentId)};  
		List<Map<String, Object>> rows = jdbc.queryForList(sql, parameters);
		
		// map the rows returned from the database to Java Objects
		for(Map row: rows)
		{
			Note note = new Note(); 
			note.Id = (Integer) row.get("Id"); 
			note.CourseId = (Integer)row.get("Course_Id");
			note.DateCreated = (Timestamp)row.get("DateCreated"); 
			note.FilePath = (String)row.get("FilePath"); 
			note.OwnerId = (Integer)row.get("User_Id"); 
			
			noteList.add(note); 
		}
				
		return noteList;
	}
	
	@Override
	public Note GetNote(int studentId, int noteId)
	{
		String sql = "select * FROM Note WHERE User_Id = ? AND Id = ?"; 
		Note note = new Note();
		
		// query for the object (rows) and then use an external Row Mapper to map the rows to object properties
		note = jdbc.queryForObject(sql, new Object[]{studentId, noteId}, new NoteRowMapper());
		
		return note; 
	}
	
	
	@Override
	public int AddNote(final int studentId, final Note note, final int courseId) 
	{
		final String sql = "insert into Note (User_Id, Course_Id, DateCreated, FilePath) values (?, ?, ?, ?)";
		
		// this is required to return the ID of the last element you just inserted
		// If we try to select the "MAX" Id, there is a race condition, since this is a multi-user application
		// Basically, one user can create a note, at the same time another user creates, then we get the "MAX" Id
		// it could be the user 1 or user 2, we cannot guarantee which one will be returned first
		KeyHolder keyHolder = new GeneratedKeyHolder();
	        jdbc.update(
	        		new PreparedStatementCreator()  
	        		{	             
						@Override
						public java.sql.PreparedStatement createPreparedStatement(
								java.sql.Connection arg0) throws SQLException {
							   PreparedStatement ps =
			                            (PreparedStatement) arg0.prepareStatement(sql, new String[] {"id"});
			                        ps.setInt(1, studentId);
			                        ps.setInt(2, courseId); 
			                        ps.setDate(3, (Date) note.DateCreated);
			                        ps.setString(4, note.FilePath);
			                        return ps;
						}
	                }, keyHolder);
	        
        return keyHolder.getKey().intValue();
	}
	
	@Override
	public boolean DeleteNote(int studentId, int noteId) 
	{
		String sql = "delete FROM Note WHERE Id = ? AND User_Id = ?"; 
		jdbc.update(sql, noteId, studentId); 
		return true;
	}
}
