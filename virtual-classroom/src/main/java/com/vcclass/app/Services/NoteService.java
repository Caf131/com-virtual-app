package com.vcclass.app.Services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

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
		String sql = "select * FROM Note WHERE Student_StudentId = ?"; 
		List<Note> noteList = new ArrayList<Note>();
		
		Object [] parameters = new Object[]{new Integer(studentId)};  
		List<Map<String, Object>> rows = jdbc.queryForList(sql, parameters);
		
		for(Map row: rows)
		{
			Note note = new Note(); 
			note.Id = (Integer) row.get("Note Id"); 
			note.CourseId = (Integer)row.get("Courses_CourseID");
			note.DateCreated = (Date)row.get("DateCreated"); 
			note.CourseCode = (String)row.get("CourseCode"); 
			note.CourseName = (String)row.get("CourseName"); 
			note.FilePath = (String)row.get("FilePath"); 
			note.OwnerId = (Integer)row.get("Student_StudentID"); 
			noteList.add(note); 
		}
				
		return noteList;
	}
	
	@Override
	public Note GetNote(int studentId)
	{
		String sql = "select * FROM Note WHERE Student_StudentID = ?"; 
		Note note = new Note();
		note = jdbc.queryForObject(sql, new Object[]{studentId}, new NoteRowMapper());
		
		return note; 
	}
	
	@Override
	public int AddNote(int studentId, Note note) 
	{
		
		
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public boolean DeleteNote(int studentId, int noteId) 
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean UpdateNote(int studentId, int noteId) 
	{
		// TODO Auto-generated method stub
		return false;
	} 
}
